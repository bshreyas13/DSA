
/**
 * On my honor:
 * - I have not used source code obtained from another student,
 * or any other unauthorized source, either modified or
 * unmodified.
 * 
 * - All source code and documentation used in my program is
 * either my original work, or was derived by me from the
 * source code published in the textbook for this course.
 *
 * - I have not discussed coding details about this project with
 * anyone other than my partner (in the case of a joint
 * submission), instructor, ACM/UPE tutors or the TAs assigned
 * to this course. I understand that I may discuss the concepts
 * of this program with other students, and that another student
 * may help me debug my program so long as neither of us writes
 * anything during the discussion or modifies any computer file
 * during the discussion. I have violated neither the spirit nor
 * letter of this restriction.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Parser will parse the file into commands for the operation controler
 * 
 * @author {shreyasb and veerad}
 * @version 2021-04-20
 */
public class Parser {
    private static Parser p = null;
    private static Scanner scan;

    /**
     * Initializing the parser
     * 
     * @param str
     *            filename for the file to read
     * @throws FileNotFoundException
     */
    public Parser(String str) throws FileNotFoundException {
        Parser.scan = new Scanner(new File(str));
        Parser.p = this;
    }


    /**
     * This method will create parser instance
     * 
     * @param str
     *            name of the file
     * @return parser instance
     * @throws FileNotFoundException
     */
    public static Parser getInstance(String str) throws FileNotFoundException {
        if (p == null) {
            p = new Parser(str);
        }
        return p;
    }

    public static void delete() {
        scan = null;
        p = null;
    }


    /**
     * This will read the next line of the parser
     * 
     * @return a string array with
     *         0th index is the command
     *         1st index is the name
     *         2nd index is the field
     *         3rd index is the field value
     *         (if input is correct)
     */
    public String[] readNextLine() {
        
        if (!Parser.scan.hasNextLine()) {
            return null;
        }
        String currentLine = Parser.scan.nextLine().trim();
        
        while (currentLine.contains("\t")) {
            currentLine = currentLine.replace("\t", " ");
        }
        while (currentLine.contains("  ")) {
            currentLine = currentLine.replace("  ", " ");
        }
        while (currentLine.contains(" <SEP>")) {
            currentLine = currentLine.replace(" <SEP>", "<SEP>");
        }
        while (currentLine.contains("<SEP> ")) {
            currentLine = currentLine.replace("<SEP> ", "<SEP>");
        }

        // Split using <sep tag>
        String[] split1 = currentLine.split("<SEP>");
        String[] split2;

        if (split1[0].startsWith("update")) {
            split2 = new String[2];
            split2[0] = split1[0].substring(0, split1[0].indexOf(" ", 7));
            split2[1] = split1[0].substring(split1[0].indexOf(" ", 7) + 1);
        }
        else {
            split2 = split1[0].split(" ", 2);
        }

        // combining everything to return
        String[] commands = new String[split1.length + split2.length - 1];
        for (int i = 0; i < split2.length; i++) {
            commands[i] = split2[i];
        }

        for (int i = split2.length; i < split1.length + split2.length
            - 1; i++) {
            commands[i] = split1[i - split2.length + 1];
        }
        return commands;
    }

}
