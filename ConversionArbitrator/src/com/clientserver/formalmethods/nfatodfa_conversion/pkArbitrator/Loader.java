//package com.clientserver.formalmethods.nfatodfa_conversion.pkArbitrator;
//
//import com.clientserver.formalmethods.nfatodfa_conversion.pkRegistration.Configration;
//import com.clientserver.formalmethods.nfatodfa_conversion.pkRegistration.Server;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.io.Serializable;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author Ahmad Fahmawi
// */
//public class Loader implements Runnable, Serializable {
//
//    private final int PORT = Configration.getPort("conversionLoader");
//    private final String HOST = Configration.getHost("conversionLoader");
//    private static Object lock = new Object();
//    Map<String, Server> servers = new HashMap<String, Server>();
//    private ServerSocket serverSocket;
//    Socket registrarSocket;
//    ObjectOutputStream registrarOOS;
//    ObjectInputStream registrarOIS;
//    private static Loader instance;
//
//    private Loader() {
//        try {
//
//            serverSocket = new ServerSocket(PORT);
////            System.out.println(Configration.getHost("conversionRegistrar"));
////            System.out.println(Configration.getPort("conversionRegistrar"));
//
//            System.out.println(".run()...loader");
////            Thread lisen = new Thread(new Runnable() {
////                @Override
////                public void run() {
////                    while (true) {
//            this.run();
//            System.err.println("com.clientserver.formalmethods.nfatodfa_conversion.pkRegistration.Loader");
////                    }
////                }
////            }
////            );
////            lisen.start();
//
//        } catch (IOException ex) {
//            System.err.println("com.clientserver.formalmethods.nfatodfa_conversion.pkRegistration");
//            System.err.println("Class Registrar");
//            System.err.println("CAN\'T CONNECT ON PORT " + PORT);
//        }
//    }
//
//    public static Loader getInstance() {
//
//        if (instance == null) {
//            synchronized (lock) {
//                if (instance == null) {
//                    instance = new Loader();
//                }
//            }
//        }
//
//        return instance;
//    }
//
//    private String getUselessServerKey() {
//        int i = 1000;
//        String serverKey = null;
//        for (Server server : servers.values()) {
//            if (server.getLoad() < i) {
//                i = server.getLoad();
//                serverKey = server.getServerID();
//            }
//        }
//        return serverKey;
//    }
//
//    public Server getServer() {
//        String serverKey = getUselessServerKey();
//        Server server = servers.get(serverKey);
//        server.increasLoad();
//        servers.put(serverKey, server);
//        return server;
//    }
//
//    public void decreaseLoad(String serverKey) {
//        Server server = servers.get(serverKey);
//        server.decreasLoad();
//        servers.put(serverKey, server);
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            try {
//                registrarSocket = serverSocket.accept();
//
//                registrarOOS = new ObjectOutputStream(registrarSocket.getOutputStream());
//                registrarOIS = new ObjectInputStream(registrarSocket.getInputStream());
//                System.out.println("com.clientserver.formalmethods.nfatodfa_conversion.pkArbitrator.Loader.run()...wait for server");
//                Server server = (Server) registrarOIS.readObject();
//                System.out.println("com.clientserver.formalmethods.nfatodfa_conversion.pkArbitrator.Loader.run()...got  server " + server.getServerName());
//                synchronized (servers) {
//                    System.out.println("com.clientserver.formalmethods.nfatodfa_conversion.pkArbitrator.Loader.run()...got  server " + server.getServerID());
//
//                    servers.put(server.getServerID(), server);
//                }
//
//            } catch (IOException ex) {
//                Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//}
