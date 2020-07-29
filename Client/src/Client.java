import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Maram Tawalbeh 
 */
public class Client {

    
    private static ArrayList<String> testCases;
    private static Strater client;
    public static void main(String[] args) throws IOException {
        //loading test cases
        testCases= (ArrayList<String>) FileService.Read_File_();
                       Random rand = new Random();

                       for (int i = 0; i < 20; i++) {
            int element =rand.nextInt(((testCases.size()-1)));
                       String testCase=testCases.get(element);
                      client  = new Strater(new String(testCase));
                     client.run();
        }
                       
              
     }


}


	


    

