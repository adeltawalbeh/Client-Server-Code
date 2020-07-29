package com.clientserver.formalmethods.nfatodfa_conversion.pkArbitrator;

import com.clientserver.formalmethods.nfatodfa_conversion.pkModel.Response;
import com.clientserver.formalmethods.nfatodfa_conversion.pkRegistration.Configration;
import com.clientserver.formalmethods.nfatodfa_conversion.pkRegistration.Registrar;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import pkServer.Server;

/**
 *
 * @author Ahmad Fahmawi
 */
public class Arbitrator implements Runnable {

    private final int PORT = Configration.getPort("clientArbitrator");
    private final String HOST = Configration.getHost("clientArbitrator");
    private static Object lock = new Object();

    private ServerSocket serverSocket;

    private static Arbitrator instance;

    private Arbitrator() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Client Arbitrator connected");

            while (true) {
                this.run();
            }
        } catch (IOException ex) {
            System.err.println("Client Arbitrator");
            System.err.println("IOException catched in class Client Arbitrator line 38");
            System.err.println("CAN\'T CONNECT ON PORT " + PORT);
            System.err.println(ex);

        }
    }

    public static Arbitrator getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new Arbitrator();
                }
            }
        }
        return instance;
    }

    @Override
    public void run() {
        try {
            Socket clientSocket;
// wait connection from client
            clientSocket = serverSocket.accept();
            System.err.println("connection accepted from checker server on port " + clientSocket.getPort());
// prepare client connection 
            ObjectOutputStream clientOOS = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream clientOIS = new ObjectInputStream(clientSocket.getInputStream());
// get input string from client             
            String input = (String) clientOIS.readObject();
// get less use server
            Server server = Registrar.getServer();
// prepare connection with checker server 
            Socket checkerSocket = new Socket(server.getHOST(), server.getPORT());
            ObjectOutputStream checkerOOS = new ObjectOutputStream(checkerSocket.getOutputStream());
            ObjectInputStream checkerOIS = new ObjectInputStream(checkerSocket.getInputStream());
// send the given input string to the checker server            
            checkerOOS.writeObject(input);
// get checking responses from checker back
            ArrayList<Response> responses = (ArrayList<Response>) checkerOIS.readObject();
// send responses back to the client
            clientOOS.writeObject(responses);
// decrease checker server load in Registrar
            Registrar.decreaseLoad(server.getServerID());

            clientSocket.close();
            checkerSocket.close();

        } catch (IOException ex) {
            System.out.println(ex);
            System.err.println("Client Arbitrator");
            System.err.println("IOException catched in line 89");
            System.err.println(ex);
        } catch (ClassNotFoundException ex) {
            System.err.println("Client Arbitrator");
            System.err.println("ClassNotFoundException catched in line 89");
            System.err.println(ex);
        }

    }

}
