import java.io.File;
import java.util.Scanner;

/**
 * 
 * @author {shreyasb and veerad}
 * @version 2021-2-15
 *
 */
public class Parser {

    private static BST bst = new BST();
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
                case "search":
                    processSearch(cps);
                    break;
                case "remove":
                    processRemove(cps);
                default:
                    break;
            }
        }
    }


    /**
     * Process dump command
     * 
     */
    private static void processSearch(String[] cps) {
        bst.searchByKey(cps[1]);
        if (!bst.isSearchStatus()) {
            System.out.println(String.format("Rectangle not found: %s",
                cps[1]));
        }

    }


    /**
     * Process dump command
     * 
     */
    private static void processDump() {
        bst.dump();
    }

    private static void processRemove(String[] cps) {
        try {
            String key = cps[1];
            
            if ( bst.remove(key)==false) {
                System.out.println(String.format("Rectangle rejected: %s",
                    key));
            }
   
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    /**
     * Process insert command
     * 
     * @param cps
     *            Insert command with params
     */
    private static void processInsert(String[] cps) {
        try {
            Node node = Node.build(cps[1], Integer.parseInt(cps[2]), Integer
                .parseInt(cps[3]), Integer.parseInt(cps[4]), Integer.parseInt(
                    cps[5]));
            if (node.isValid() && bst.insert(node)) {
                System.out.println(String.format("Rectangle accepted: %s",
                    node));
            }
            else {
                System.out.println(String.format("Rectangle rejected: %s",
                    node));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
