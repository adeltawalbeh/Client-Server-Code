
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileService {
         static List<String> list; 
 
  
public static ArrayList<String> Read_File_() throws IOException{
         ArrayList<String> list1=new ArrayList<String>(); 
         BufferedReader bufferedReader = null;
       try {
           
           File file_Read = new File("testCases.txt");
           bufferedReader = new BufferedReader(new FileReader(file_Read)); 
           String line = null;
           int count = 0;
           
       
            while((line = bufferedReader.readLine()) != null) 
                list1.add(line);
            }   

        catch (Exception ex) {
           bufferedReader.close();
           Logger.getLogger(FileService.class.getName()).log(Level.SEVERE, null, ex);
       } 
        finally {          }
    return list1 ;
}


public static void print_List_(List<String> list)
{
    for(String str:list) {
	System.out.println(str);
	}
System.out.println("Size : "+list.size());


}//

public static void Write_TO_File_()
{
    
    
    
}



}










