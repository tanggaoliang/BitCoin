package com.how2java.bitcoin;


import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ws/bitcoinServer")
public class BitCoinServer {
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        ServerManager.add(this);
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    @OnClose
    public void onClose() {
        ServerManager.remove(this);
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("from client message :" + message);
    }

    @OnError
    public void onError(Session session , Throwable error) {
        System.out.println("error occurred!");
        error.printStackTrace();
    }

}
