
import com.clientserver.formalmethods.nfatodfa_conversion.pkModel.Response;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */

   
public class Strater implements Runnable{
    
    private static final int PORT_NUM=3578;
    private static final String HOST="192.168.8.103";
    String testCase;
    
    @Override
    public void run() {
        Socket clientThread;
        try {
            clientThread = new Socket(HOST,PORT_NUM);
              ObjectInputStream in_obj=new ObjectInputStream(clientThread.getInputStream());
              ObjectOutputStream Out_Obj = new ObjectOutputStream(clientThread.getOutputStream());;

                       Out_Obj.writeObject(new String(this.testCase));                     
                       ArrayList<Response> responses = (ArrayList<Response>) in_obj.readObject();
                       
                       for (Response response : responses) {
                           
                System.out.println(response);
            }
                       
                      // System.out.println(responses);
        } catch (IOException ex) {
            Logger.getLogger(Strater.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Strater.class.getName()).log(Level.SEVERE, null, ex);
        }
                     
    }
    
    public Strater(String testcase)
    {
        this.testCase = testcase;
    }
}
