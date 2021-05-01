
/**
 * 
 * {Project Description Here}
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
import java.io.FileNotFoundException;

/**
 * The class containing the main method. Extend with your code, and update this
 * docblock
 *
 * @author {bshreyas and veerad}
 * @version 2021-04-20
 */

public class MemMan {
    /**
     * Calling parser
     * 
     * @param args
     *            Command Line Arguments
     * @return an instant of the parser
     */
    public static Parser getParser(String[] args) {

        String inputFormat =
            "The program expects the input in this format:/n "
            + "java MemMan <initial-memory-size> "
            + "<initial-hash-size> <command-file>\r";
        if (args == null || args.length != 3) {
            System.out.println("Error: Input not as expected");
            System.out.println(
                "This program expects three parameters: "
                + "the initial memory size, the initial hash size, "
                + "and the command file."
                + " Please make sure you supply the necessary arguments.");
            System.out.println(inputFormat);
            System.out.println("Program Out!! *mike drop*");
            return null;
        }
        Parser parser = null;
        try {
            parser = Parser.getInstance(args[2]);
        }
        catch (FileNotFoundException e) {
            System.out.println(
                "Error: Command file could not be found, please "
                + "make sure the file is in the same directory "
                + "and the spelling is correct");
            System.out.println(inputFormat);
            return null;
        }
        return parser;
    }


    /**
     * @param args
     *            Command line parameters
     */
    public static void main(String[] args) {

        Parser parser = MemMan.getParser(args);
        if (parser == null) {
            return;
        }
        CommandProcessor cProcessor = null;
        try {
            cProcessor = CommandProcessor.getInstance(Integer.parseInt(args[1]),
                Integer.parseInt(args[0]));
        }
        catch (NumberFormatException e) {
            System.out.println(
                "Error Invalid input: Memory size and "
                + "hash size should be an integer");
            System.out.println(
                "The program expects the input in this format:/n "
                + "java MemMan <initial-memory-size> <initial-hash-size> "
                + "<command-file>\r\n");
            return;
        }
        String[] nextline = parser.readNextLine();
        while (nextline != null) {
            if (((String)nextline[0]).equals("")) {
                nextline = parser.readNextLine();
                continue;
            }
            if (!cProcessor.executeCommand(nextline)) {
                System.out.println("Error: bad syntax");
            }
            nextline = parser.readNextLine();
        }
    }
}
