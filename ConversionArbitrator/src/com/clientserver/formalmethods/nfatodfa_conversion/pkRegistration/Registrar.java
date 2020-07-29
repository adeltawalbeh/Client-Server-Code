package com.clientserver.formalmethods.nfatodfa_conversion.pkRegistration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import pkServer.Server;

/**
 *
 * @author Ahmad Fahmawi
 */
public class Registrar implements Runnable, Serializable {

    public static void decreaseLoad(String serverID) {
        Server server = servers.get(serverID);
        server.increasLoad();
        servers.put(serverID, server);
    }

    private final int PORT = Configration.getPort("conversionRegistrar");
    private final String HOST = Configration.getHost("conversionRegistrar");
    private static Object lock = new Object();
    static Map<String, Server> servers = new HashMap<String, Server>();
    private ServerSocket serverSocket;
    Socket loaderSocket;
    ObjectOutputStream loaderOOS;
    ObjectInputStream loaderOIS;
    private static Registrar instance;

    private static String getUselessServerKey() {
        int i = 1000;
        String serverKey = null;
        for (Server server : servers.values()) {
            if (server.getLoad() < i) {
                i = server.getLoad();
                serverKey = server.getServerID();
            }
        }
        return serverKey;
    }

    public static Server getServer() {
        String serverKey = getUselessServerKey();
        Server server = servers.get(serverKey);
        server.increasLoad();
        servers.put(serverKey, server);
        return server;
    }

    private Registrar() {
        try {

            serverSocket = new ServerSocket(PORT);

            System.out.println(".run()...Registrar");
        } catch (IOException ex) {
            System.err.println("com.clientserver.formalmethods.nfatodfa_conversion.pkRegistration");
            System.err.println("Class Registrar");
            System.err.println("CAN\'T CONNECT ON PORT " + PORT);
        }

        while (true) {
            this.run();
        }

    }

    public static Registrar getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new Registrar();
                }
            }
        }
        return instance;
    }

    public Server getServer(String serverKey) {
        return servers.get(serverKey);
    }

    @Override
    public void run() {

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Socket clientSocket = null;

        try {
// accept connection     

            System.out.println("Registrar.run()... wait for server in line 97");

            clientSocket = serverSocket.accept();
            System.out.println(".run()...Registrar accept server in line 100");
// prepare client connection 
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ois = new ObjectInputStream(clientSocket.getInputStream());
// get server information           
            System.out.println(" Registrar.run() ...  read server information in line 105");

            Server server = (Server) ois.readObject();
            //String pp = (String) ois.readObject();
            String serverKey = ((server.getServerName() + server.getPORT()).hashCode()) + "";
            server.setServerID(serverKey);
            System.out.println("Registrar.run() ...server information readed.. server id is  " + server.getServerName());
// set server key

            synchronized (servers) {
// add the server to the list 
                servers.put(serverKey, server);
                System.out.println("com.clientserver.formalmethods.nfatodfa_conversion.pkRegistration");
                System.out.println("Class Registrar");
                System.out.println("SERVER ADDED ");
                System.out.println(server);
            }
// send the server information to the loader 
//            loaderSocket = new Socket(Configration.getHost("conversionLoader"), Configration.getPort("conversionLoader"));
//            loaderOOS = new ObjectOutputStream(loaderSocket.getOutputStream());
//            loaderOIS = new ObjectInputStream(loaderSocket.getInputStream());
//            loaderOOS.writeObject(server);
//            oos.close();
//            ois.close();
//            clientSocket.close();
//            loaderOIS.close();
//            loaderOOS.close();
//            loaderSocket.close();
        } catch (IOException ex) {
            System.err.println("com.clientserver.formalmethods.nfatodfa_conversion.pkRegistration");
            System.err.println("Class Registrar");
            System.err.println("CAN\'T ACCEPT MORE SERVERS ");
        } catch (ClassNotFoundException ex) {
            System.err.println("com.clientserver.formalmethods.nfatodfa_conversion.pkRegistration");

            System.err.println("Class Registrar");
            System.err.println("112");
            System.err.println("CAN\'T FIND SERVER CLASS ");
//        } finally {
//            try {
//                oos.close();
//                ois.close();
//                clientSocket.close();
//            } catch (IOException ex) {
//                System.err.println("com.clientserver.formalmethods.nfatodfa_conversion.pkRegistration");
//                System.err.println("Class Registrar");
//                System.err.println("CAN\'T CLOSE SERVER SOCKET ");
//            }
//       
        }

    }
}
