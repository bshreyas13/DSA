import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * 
 * @author {shreyasb and veerad}
 * @version 2021-2-15
 *
 */
public class Parser {

	  private static BST<Rect> bst = new BST<Rect>();
	    private static File outFile;

    /**
     * Parse the input file for commands
     * 
     * @param path
     *            to the command-file (input)
     * @throws Exception
     *             If file not found and error while processing command
     * @return
     *         true once parsing is successful
     */

    public static boolean parse(String path) throws Exception {
        outFile = new File("result.txt");
        if (outFile.exists()) {
            outFile.delete();
        }
        outFile.createNewFile();

        File f = new File(path);
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim().replaceAll(" +", " ");
            if (!line.isEmpty()) {
                processCommand(line);
            }
        }
        sc.close();
        return true;
    }


    /**
     * Processes the command line
     * 
     * @param line
     *            the line with command and params
     */
    /**
     * @param line
     */
    private static void processCommand(String line) {
        String[] cps = line.split(" ");
        if (cps != null && cps.length > 0) {
            switch (cps[0]) {
                case "insert":
                    processInsert(cps);
                    break;
                case "dump":
                    processDump();
                    break;
                case "regionsearch":
                    processRegionSearch(cps);
                    break;
                case "remove":
                	processRemove(cps);
                	break;
                case "search":
                	processSearch(cps);
                	break;
                case "intersections":
                	processIntersections(cps);
                	break;
                default:
                    break;
            }
        }
    }
    /**
     * Process intersections command
     * @param cps
     */
    private static void processIntersections(String[] cps) {
    	String output = "";
        try {
            output += bst.searchForIntersections();
        }
        catch (Exception ex) {
            output += ex.getMessage();
        }
        if (output.length()>0) {
        	writeToOutput(output);
        }

    }
    
    /**
     * Process remove command
     * @param cps
     */
    private static void processSearch(String[] cps) {
        String name = cps[1];
        boolean isValue = name.chars().allMatch( Character::isDigit );
        if (isValue== true) {
        	Rect rect = getRect(Integer.parseInt(cps[1]), Integer.parseInt(
                    cps[2]), Integer.parseInt(cps[3]), Integer.parseInt(cps[4]));
        	bst.removeAllByShape(rect);
        	
        	String output = String.format(
                    "Rectangle removed: %s", rect);
            //System.out.println(output);
            writeToOutput(output);

        }
        
        else {
        MyList<Rect> rect = bst.searchByKey(name);
        String output = String.format(
                "Rectangle found: %s,%s", name,rect);
        //System.out.println(output);
        writeToOutput(output);
        }
    }

    /**
     * Process remove command
     * @param cps
     */
    private static void processRemove(String[] cps) {
        String name = cps[1];
        boolean isValue = name.chars().allMatch( Character::isDigit );
        if (isValue== true) {
        	Rect rect = getRect(Integer.parseInt(cps[1]), Integer.parseInt(
                    cps[2]), Integer.parseInt(cps[3]), Integer.parseInt(cps[4]));
        	bst.removeAllByShape(rect);
        	
        	String output = String.format(
                    "Rectangle removed: %s", rect);
            //System.out.println(output);
            writeToOutput(output);

        }
        
        else {
        bst.remove(name);
        String output = String.format(
                "Rectangle removed: (%s)", name);
        //System.out.println(output);
        writeToOutput(output);
        }
    }
    /**
     * Process dump command
     * 
     */
    private static void processDump() {
        String output = bst.dump();
        //System.out.println(output);
        writeToOutput(output);
    }


    /**
     * Process region search command
     * 
     * @param cps
     *            Region search command with params
     */
    private static void processRegionSearch(String[] cps) {
    	String output = "";
        try {
            Rect rect = getRect(Integer.parseInt(cps[1]), Integer.parseInt(
                cps[2]), Integer.parseInt(cps[3]), Integer.parseInt(cps[4]));
            output += bst.searchByRegion(rect);
        }
        catch (Exception ex) {
            output += ex.getMessage();
        }
        if (output.length()>0) {
        	writeToOutput(output);
        }

    }


    /**
     * Process insert command
     * 
     * @param cps
     *            Insert command with params
     */
    private static void processInsert(String[] cps) {
        String output = "";
        try {
            String name = cps[1];
            Rect rect = getRect(Integer.parseInt(cps[2]), Integer.parseInt(
                cps[3]), Integer.parseInt(cps[4]), Integer.parseInt(cps[5]));
            boolean status = bst.insert(new Node<Rect>(name, rect));
            if (status) {
                output = String.format(
                    "Rectangle accepted: (%s, %s, %s, %s, %s)", name, rect.x,
                    rect.y, rect.width, rect.height);
            }
            else {
                output = String.format(
                    "Rectangle rejected: (%s, %s, %s, %s, %s)", name, rect.x,
                    rect.y, rect.width, rect.height);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        writeToOutput(output);
    }


    /**
     * Create a rectangle for commands
     * 
     * @param x
     *            Top left x
     * @param y
     *            Top left y
     * @param w
     *            width of rectangle
     * @param h
     *            height of rectangle
     * @return
     *         Rect object
     */
    private static Rect getRect(int x, int y, int w, int h) {
        return new Rect(x, y, w, h);
    }


    /**
     * Save output to file
     * 
     * @param msg
     *            line to append to file
     */
    private static void writeToOutput(final String msg) {
        try {
            System.out.println(msg);
            FileWriter fw = new FileWriter(outFile, true);
            fw.write(String.format("%s", msg));
            fw.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
