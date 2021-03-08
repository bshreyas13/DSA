import java.io.File;
import java.util.Scanner;

/**
 * Represents the DNA Tree
 * 
 * @author {bshreyas and veerad}
 * @version 2021-03-07
 */
public class DNAtree {

    private static Tree tree = new Tree();
    private static boolean success;

    /**
     * get operation status
     * 
     * @return
     *         operation status
     */
    public static boolean isSuccess() {
        return success;
    }


    /**
     * @param args
     *            args command line arguments expecting file name with commands
     */
    public static void main(String[] args) {
        try {
            tree = new Tree();
            process(args[0]);
            success = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @param tree
     * @param fileName
     * @throws Exception
     */
    private static void process(String fileName) throws Exception {
        File f = new File(fileName);
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim().replaceAll(" +", " ");
            if (!line.isEmpty()) {
                processCommand(line);
            }
        }
        sc.close();
    }


    /**
     * Processes the command line
     * 
     * @param line
     *            the line with command and params
     */
    private static void processCommand(String line) {
        String[] params = line.split(" ");
        if (params != null && params.length > 0) {
            switch (params[0]) {
                case "insert":
                    tree.insert(new Sequence(params[1]));
                    System.out.flush();
                    break;
                case "remove":
                    tree.remove(new Sequence(params[1]));
                    System.out.flush();
                    break;
                case "print":
                    Print.setPrint(PrintMode.NONE);
                    if (params.length > 1) {
                        if (params[1].compareTo("lengths") == 0) {
                            Print.setPrint(PrintMode.LENGTH);
                        }
                        else if (params[1].compareTo("stats") == 0) {
                            Print.setPrint(PrintMode.STATS);
                        }
                    }
                    tree.dump();
                    System.out.flush();
                    break;
                case "search":
                    tree.search(new SearchSequence(params[1]));
                    System.out.flush();
                    break;
                default:
                    break;
            }
        }
    }

}
