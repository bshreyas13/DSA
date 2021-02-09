import java.io.File;
import java.util.Scanner;

public class Parser {
	/**
	 * 
	 * @param path to the command-file (input) 
	 */
	public void Read(String path){
	try {
		

        File f = new File(path);

        System.out.println("::::"+f.exists());

        Scanner sc = new Scanner(f);

        Scanner scancmd;//Declare two scanners one to read the file and one to read the text pulled from the file
        
        String cmd = null;
        
        while(sc.hasNextLine()){//While we have text to read
           String line = sc.nextLine();//Get our next line
           scancmd = new Scanner(line);//Create a scanner from this line
           System.out.println(line);
           
           if (line.isBlank() == false) {  
        	   //System.out.println (line.isEmpty());
        	   cmd = scancmd.next();//Get the first word (the command) on each line
        	   System.out.println(cmd);
           }
        }

     } 
    catch (Exception e) {

        e.printStackTrace();
     }
	}
}
