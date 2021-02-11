
import java.io.File;
import java.util.Scanner;
import java.util.*;
/**
 * 
 * @author shreyas
 *
 */
public class Parser {
	List<String> cmdLine;
	List<List<String>> cmds;
	/**
	 * 
	 * @param path to the command-file (input) 
	 */

	public Parser(String path){
	try {
        File f = new File(path);
        //System.out.println("::::"+f.exists());
        Scanner sc = new Scanner(f);      
        cmds = new ArrayList<>();      
        while(sc.hasNextLine()){//While we have text to read
           String line = sc.nextLine();//Get our next line
           String[] chars = line.split(" ");
           //System.out.println(chars.length);
           cmdLine = new ArrayList<>();
           for (int i=0 ; i<chars.length; i++) {             
        	   //System.out.println (line.isEmpty());
        	   if (chars[i].trim().isEmpty()==false) {
        	   cmdLine.add(chars[i]);
        	   //System.out.println(chars[i]);
        	   }   
           }
           
           if (cmdLine.isEmpty()==false) {
        	   cmds.add(cmdLine);
           }   
        }

     } 
    catch (Exception e) {

        e.printStackTrace();
     }
	}
}
