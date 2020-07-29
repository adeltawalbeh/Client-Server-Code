package com.clientserver.formalmethods.nfatodfa_conversion.pkApplicationInterface;

import com.clientserver.formalmethods.nfatodfa_conversion.pkConverter.NFAtoDFA_Converter;
import com.clientserver.formalmethods.nfatodfa_conversion.pkModel.DFA;
import com.clientserver.formalmethods.nfatodfa_conversion.pkModel.NFA;
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
public class ConversionServer implements Runnable, Serializable {

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

    public ConversionServer(int maxCapacity, int PORT, String HOST, String ServerName) {
        this.maxCapacity = maxCapacity;
        this.PORT = PORT;
        this.HOST = HOST;
        this.ServerName = ServerName;
        this.load = 0;

        try {
//establish a connection with register to know i'm alive
            Socket registrarSocket = new Socket(Configration.getHost("conversionRegistrar"), Configration.getPort("conversionRegistrar"));

            ObjectOutputStream registrarOOS = new ObjectOutputStream(registrarSocket.getOutputStream());

            ObjectInputStream registrarOIS = new ObjectInputStream(registrarSocket.getInputStream());

            Server s = new Server(this.maxCapacity, this.PORT, this.HOST, this.ServerName);
//send conversion information to the register to know how others can talk with me         
            registrarOOS.writeObject(s);

            serverSocket = new ServerSocket(PORT);
            System.out.println("com.clientserver.formalmethods.nfatodfa_conversion.pkApplicationInterface.ConversionServer connected on port " + PORT);
//start listen on my port to serve the comming request
            while (true) {
                this.run();
            }

        } catch (IOException ex) {
            System.err.println("Conversion Server");
            System.err.println("IOException");
            System.err.println("Exception catched in line 81");
            System.err.println(ex);
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
        return "Server{  HOST=" + HOST + ", PORT=" + PORT + ", ServerName=" + ServerName + ", maxCapacity=" + maxCapacity + '}';
    }

    @Override
    public void run() {

        Socket arbitratorSocket;
        try {
// wait connection from conversion arbitrator         
            arbitratorSocket = serverSocket.accept();
            ObjectOutputStream arbitratorOOS = new ObjectOutputStream(arbitratorSocket.getOutputStream());
            ObjectInputStream arbitratorOIS = new ObjectInputStream(arbitratorSocket.getInputStream());
//get NFA to convert 
            NFA nfa = (NFA) arbitratorOIS.readObject();
//get converted NFA as DFA
            DFA dfa = NFAtoDFA_Converter.convert(nfa);
//send DFA back
            arbitratorOOS.writeObject(dfa);
        } catch (IOException ex) {
            System.err.println("Conversion Server");
            System.err.println("IOException");
            System.err.println("Exception catched in line 134");
            System.err.println(ex);
        } catch (ClassNotFoundException ex) {
            System.err.println("Conversion Server");
            System.err.println("Exception catched in line 138");
            System.err.println("ClassNotFoundException");
            System.err.println(ex);
        }

    }

}
