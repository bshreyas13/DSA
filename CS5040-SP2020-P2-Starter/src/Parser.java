import java.io.File;
import java.util.Scanner;
import java.util.*;

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
        
        List<String> rectangle = new ArrayList<>();
        
        while(sc.hasNextLine()){//While we have text to read
           String line = sc.nextLine();//Get our next line
           String[] chars = line.split(" ");
           scancmd = new Scanner(line);//Create a scanner from this line
           //System.out.println(chars.length);
           for (int i=0 ; i<chars.length; i++) {             
        	   //System.out.println (line.isEmpty());
        	   if (chars[i].isEmpty()==false) {
        	   rectangle.add(chars[i]);
        	   //System.out.println(chars[i]);
           }
           }
        }System.out.println(rectangle);

     } 
    catch (Exception e) {

        e.printStackTrace();
     }
	}
}
