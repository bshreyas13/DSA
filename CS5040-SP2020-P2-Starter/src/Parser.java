import java.io.File;
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
        bst.clear();

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
        String[] params = line.split(" ");
        if (params != null && params.length > 0) {
            switch (params[0]) {
                case "insert":
                    processInsert(params);
                    System.out.flush();
                    break;
                case "remove":
                    processRemove(params);
                    System.out.flush();
                    break;
                case "regionsearch":
                    processRegionSearch(params);
                    System.out.flush();
                    break;
                case "intersections":
                    processIntersections();
                    System.out.flush();
                    break;
                case "search":
                    processSearch(params);
                    System.out.flush();
                    break;
                case "dump":
                    processDump();
                    System.out.flush();
                    break;
                default:
                    break;
            }
        }
    }


    /**
     * search
     */
    private static void processSearch(String[] params) {
        bst.searchByKey(params[1]);
    }


    /**
     * Search intersections
     */
    private static void processIntersections() {
        bst.searchForIntersections();
    }


    /**
     * to remove
     * 
     * @param cps
     *            cps values
     */
    private static void processRemove(String[] cps) {

        if (cps.length == 2) {
            bst.removeByKey(cps[1]);
        }
        else if (cps.length == 5) {
            Rect rect = getRect(Integer.parseInt(cps[1]), Integer.parseInt(
                cps[2]), Integer.parseInt(cps[3]), Integer.parseInt(cps[4]));
            boolean status = bst.removeByValue(rect);
            if (!status) {
                String output = String.format(
                    "Rectangle rejected: (%d, %d, %d, %d)", rect.x, rect.y,
                    rect.width, rect.height);
                System.out.println(output);
            }
        }

    }


    /**
     * Process dump command
     * 
     */
    private static void processDump() {
        bst.dump();
    }


    /**
     * Process region search command
     * 
     * @param cps
     *            Region search command with params
     */
    private static void processRegionSearch(String[] cps) {
        try {
            Rect rect = getRect(Integer.parseInt(cps[1]), Integer.parseInt(
                cps[2]), Integer.parseInt(cps[3]), Integer.parseInt(cps[4]));

            bst.searchByRegion(rect);
        }
        catch (Exception ex) {
            ex.getMessage();
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
            String name = cps[1];
            Rect rect = getRect(Integer.parseInt(cps[2]), Integer.parseInt(
                cps[3]), Integer.parseInt(cps[4]), Integer.parseInt(cps[5]));
            boolean status = bst.insert(new Node<Rect>(name, rect));
            if (status) {
                String output = String.format(
                    "Rectangle accepted: (%s, %d, %d, %d, %d)", name, rect.x,
                    rect.y, rect.width, rect.height);
                System.out.println(output);
            }
            else {
                String output = String.format(
                    "Rectangle rejected: (%s, %d, %d, %d, %d)", name, rect.x,
                    rect.y, rect.width, rect.height);
                System.out.println(output);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
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

}
