package com.how2java.bitcoin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ServerManager {
    private static Collection<BitCoinServer> servers = Collections.synchronizedCollection(new ArrayList<BitCoinServer>());

    public static void broadCast(String msg) {
        for (BitCoinServer bitCoinServer : servers) {
            try {
                bitCoinServer.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static int getTotal(){
        return servers.size();
    }
    public static void add(BitCoinServer bitCoinServer){
        servers.add(bitCoinServer);
        System.out.println("New connection to join! The current total number of connections is "+servers.size());
    }
    public static void remove(BitCoinServer  server){
        servers.remove(server);
        System.out.println("Connection to exit! The current total number of connections is : "+servers.size());
    }
}
