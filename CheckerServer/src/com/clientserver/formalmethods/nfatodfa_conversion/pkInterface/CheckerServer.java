package com.clientserver.formalmethods.nfatodfa_conversion.pkInterface;

import com.clientserver.formalmethods.nfatodfa_conversion.pkModel.DFA;
import com.clientserver.formalmethods.nfatodfa_conversion.pkModel.NFA;
import com.clientserver.formalmethods.nfatodfa_conversion.pkModel.Response;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import pkServer.Server;

/**
 *
 * @author Ahmad Fahmawi
 */
public class CheckerServer implements Runnable, Serializable {

    private int maxCapacity;
    private int PORT;
    private String HOST;
    private String ServerName;
    private String serverID;
    private int load;
    ArrayList<NFA> NFAL = new ArrayList<>();
    ArrayList<DFA> DFAL = new ArrayList<>();
    ServerSocket serverSocket;

    public int getLoad() {
        return load;
    }

    public void increasLoad() {
        this.load++;
    }

    public void decreasLoad() {
        this.load--;
    }

    public String getServerID() {
        return serverID;
    }

    public void setServerID(String serverID) {
        this.serverID = serverID;
    }

    public CheckerServer(int maxCapacity, int PORT, String HOST, String ServerName) {
        this.maxCapacity = maxCapacity;
        this.PORT = PORT;
        this.HOST = HOST;
        this.ServerName = ServerName;
        this.load = 0;

        try {
            //Socket registrarSocket = new Socket("localhost", 5437);
            Socket registrarSocket = new Socket(Configration.getHost("clientRegistrar"), Configration.getPort("clientRegistrar"));
            ObjectOutputStream registrarOOS = new ObjectOutputStream(registrarSocket.getOutputStream());
            ObjectInputStream registrarOIS = new ObjectInputStream(registrarSocket.getInputStream());
            Server server = new Server(this.maxCapacity, this.PORT, this.HOST, this.ServerName);
            registrarOOS.writeObject(server);
            serverSocket = new ServerSocket(PORT);
            System.out.println("Checker Server connected on port " + PORT);
            NFAL = getAutomata();
            NFA_Conversion();
//start listen on my port to serve the comming request
            while (true) {

                this.run();
            }
        } catch (IOException ex) {
            System.err.println("Checker Server");
            System.err.println("IOException");
            System.err.println("Exception catched in line 81");
        }

    }

    private ArrayList<NFA> getAutomata() {
        return DB.getAutomata();
    }

    private void NFA_Conversion() throws IOException {
        NFAL = DB.getAutomata();

        System.out.println("######################################## NFAL size is " + NFAL.size());

        for (NFA nfa : NFAL) {
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Socket arbitratorSocket = new Socket(Configration.getHost("conversionArbitrator"), Configration.getPort("conversionArbitrator"));
                        System.out.println("checker server connected with conversion arbitrator on port " + Configration.getPort("conversionArbitrator"));
                        ObjectOutputStream arbitratorOOS = new ObjectOutputStream(arbitratorSocket.getOutputStream());
                        ObjectInputStream arbitratorOIS = new ObjectInputStream(arbitratorSocket.getInputStream());
// send NFA to convert 
                        arbitratorOOS.writeObject(nfa);
// get bacj converted NFA as DFA 
                        DFA dfa = (DFA) arbitratorOIS.readObject();
// store DFA locally to use it in check process later
                        synchronized (DFAL) {
                            DFAL.add(dfa);
                        }
                    } catch (ClassNotFoundException ex) {
                        System.err.println("Checker Server");
                        System.err.println("ClassNotFoundException");
                        System.err.println("Exception catched in line 113");
                        System.err.println(ex);
                    } catch (IOException ex) {
                        System.err.println("Checker Server");
                        System.err.println("IOException");
                        System.err.println("Exception catched in line 118");
                        System.err.println(ex);
                    }
                }
            });
            th.start();
        }

    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getPORT() {
        return PORT;
    }

    public String getHOST() {
        return HOST;
    }

    public String getServerName() {
        return ServerName;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setServerName(String ServerName) {
        this.ServerName = ServerName;
    }

    @Override
    public String toString() {
        return "Server{  HOST=" + HOST + ", PORT=" + PORT + ", ServerName=" + ServerName + "maxCapacity=" + maxCapacity + '}';
    }

    @Override
    public void run() {
        Socket arbitratorSocket;
        try {
            //   Socket client = serverSocket.accept();
            ArrayList< Response> responses = new ArrayList< Response>();
// wait a connection from client arbitratir
            arbitratorSocket = serverSocket.accept();//new Socket(Configration.getHost("clientArbitrator"), Configration.getPort("clientArbitrator"));
            ObjectOutputStream arbitratorOOS = new ObjectOutputStream(arbitratorSocket.getOutputStream());
            ObjectInputStream arbitratorOIS = new ObjectInputStream(arbitratorSocket.getInputStream());
// get string input to check it on the DFA automatas
            String input = (String) arbitratorOIS.readObject();

            System.err.println("@@@@@@@@@@@ input " + input);
            for (DFA dfa : DFAL) {
                responses.add(dfa.start(input));
            }

// filter responses 
            ArrayList<Response> acceptedResponses = filterResponses(responses);
// send back to the client all accepted responses
            arbitratorOOS.writeObject(acceptedResponses);

        } catch (IOException ex) {
            System.err.println("Checker Server");
            System.err.println("IOException");
            System.err.println("Exception catched in line 181");
        } catch (ClassNotFoundException ex) {
            System.err.println("Checker Server");
            System.err.println("ClassNotFoundException");
            System.err.println("Exception catched in line 185");
        }

    }

    /*
    Filter a given responses and return back the responses who has accepted state only
    @param ArrayList<Response> responses
    @return ArrayList<Response> that contains only accepted request
     */
    private ArrayList<Response> filterResponses(ArrayList<Response> responses) {
        ArrayList<Response> acceptedResponses = new ArrayList<>();
        for (Response response : responses) {
            if (response.isAccepted()) {
                acceptedResponses.add(response);
            }
        }
        return acceptedResponses;

    }
}
