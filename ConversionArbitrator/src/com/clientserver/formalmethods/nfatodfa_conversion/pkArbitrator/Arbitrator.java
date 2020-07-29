package com.clientserver.formalmethods.nfatodfa_conversion.pkArbitrator;

import com.clientserver.formalmethods.nfatodfa_conversion.pkModel.DFA;
import com.clientserver.formalmethods.nfatodfa_conversion.pkModel.NFA;
import com.clientserver.formalmethods.nfatodfa_conversion.pkRegistration.Configration;
import com.clientserver.formalmethods.nfatodfa_conversion.pkRegistration.Registrar;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import pkServer.Server;

/**
 *
 * @author Ahmad Fahmawi
 */
public class Arbitrator implements Runnable {

    private final int PORT = Configration.getPort("conversionArbitrator");
    private final String HOST = Configration.getHost("conversionArbitrator");
    private static Object lock = new Object();

    private ServerSocket serverSocket;

    private static Arbitrator instance;

    private Arbitrator() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Conversion Arbitrator connected");

            while (true) {
                this.run();
            }
        } catch (IOException ex) {
            System.err.println("Conversion Arbitrator");
            System.err.println("IOException");
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
// wait connection from checker server
            System.err.println("wait connection from conversion server");
            Socket clientSocket = serverSocket.accept();
            System.err.println("connection accepted from conversion server on port " + clientSocket.getPort());
// prepare checker server connection 
            ObjectOutputStream clientOOS = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream clientOIS = new ObjectInputStream(clientSocket.getInputStream());
// get NFA to convert
            NFA nfa = (NFA) clientOIS.readObject();
// get useless conversion server
            Server server = Registrar.getServer();
// prepare converter connection
            Socket converterSocket = new Socket(server.getHOST(), server.getPORT());
            ObjectOutputStream converterOOS = new ObjectOutputStream(converterSocket.getOutputStream());
            ObjectInputStream converterOIS = new ObjectInputStream(converterSocket.getInputStream());
// send NFA to convering            
            converterOOS.writeObject(nfa);
// get converted DFA
            DFA dfa = (DFA) converterOIS.readObject();
// send DFA back
            clientOOS.writeObject(dfa);
// decrease conversion server load
            Registrar.decreaseLoad(server.getServerID());

            clientSocket.close();
            converterSocket.close();
        } catch (IOException ex) {

            System.err.println("Conversion Arbitrator");
            System.err.println("IOException");
            System.err.println(ex);
        } catch (ClassNotFoundException ex) {
            System.err.println("Conversion Arbitrator");
            System.err.println("CAN\'T FIND SERVER CLASS ");
            System.err.println(ex);
        }
    }

}
