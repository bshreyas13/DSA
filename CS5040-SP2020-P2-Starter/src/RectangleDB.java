/**
 * {Project Description Here}
 */

/**
 * The class containing the main method.
 *
 * @author {shreyas and Veera}
 * @version {Put Something Here}
 */

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

import java.util.*;
public class RectangleDB {
    /**
     * @param args
     *     Command line parameters
     */
    public static void main(String[] args) {
        // This is the main file for the program
    	String filePath = args[0];
    	Parser read = new Parser(filePath);
    	List<List<String>> cmdsList = read.cmds;
    	
    	/* Code for testing compare 
    	List<String> line1 = cmdsList.get(0);
    	List<String> line2 = cmdsList.get(1);		
    	Rectangle r1 = new Rectangle(line1.get(1),Integer.parseInt(line1.get(2)),Integer.parseInt(line1.get(3)),Integer.parseInt(line1.get(4)),Integer.parseInt(line1.get(5)));
    	Rectangle r2 = new Rectangle(line2.get(1),Integer.parseInt(line2.get(2)),Integer.parseInt(line2.get(3)),Integer.parseInt(line2.get(4)),Integer.parseInt(line2.get(5)));
    	int test = Rectangle.compare(r1,r2);
    	System.out.println(test);
		*/
    	
    	for (int i =0 ; i<2 ; i++) {
    	System.out.println(cmdsList.get(i));
    	List<String> line = cmdsList.get(i);
    	Rectangle r = new Rectangle(line.get(1),Integer.parseInt(line.get(2)),Integer.parseInt(line.get(3)),Integer.parseInt(line.get(4)),Integer.parseInt(line.get(5)));
    	System.out.println(r);
    	//break;

    	}
    }
}

