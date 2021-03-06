package com.clientserver.formalmethods.nfatodfa_conversion.pkInterface;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ahmad Fahmawi
 */
public class Configration {

    static Map<String, Integer> ports = new HashMap<String, Integer>();
    static Map<String, String> hosts = new HashMap<String, String>();

    public Configration() {

    }

    public static String getHost(String serverName) {
        return hosts.get(serverName);
    }

    public static boolean addHost(String serverName, String host) {
        return hosts.put(serverName, host) != null;
    }

    public static int getPort(String serverName) {
        System.out.println("com.clientserver.formalmethods.nfatodfa_conversion.pkInterface.Configration.getPort(" + serverName + ")  ");

        return ports.get(serverName);
    }

    public static boolean addPort(String serverName, int port) {
        return ports.put(serverName, port) != null;
    }
}
