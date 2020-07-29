
import com.clientserver.formalmethods.nfatodfa_conversion.pkRegistration.Configration;
import com.clientserver.formalmethods.nfatodfa_conversion.pkRegistration.Registrar;
import pkServer.Server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ahmad Fahmawi
 */
public class TestClass {

    public static void main(String[] args) throws InterruptedException {
        try {
            Configration.addHost("Registrar", "localhost");
            Configration.addPort("Registrar", 2223);

            Registrar r = Registrar.getInstance();

            Socket clientSocket = new Socket(Configration.getHost("Registrar"), Configration.getPort("Registrar"));
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            oos.writeObject(new Server(10, 2222, "localhost", "testServer"));

            Socket clientSocket2 = new Socket(Configration.getHost("Registrar"), Configration.getPort("Registrar"));
            ObjectOutputStream oos2 = new ObjectOutputStream(clientSocket2.getOutputStream());
            ObjectInputStream ois2 = new ObjectInputStream(clientSocket2.getInputStream());
            oos2.writeObject(new Server(10, 2003, "localhost", "testServer2"));

        } catch (IOException ex) {
            Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
