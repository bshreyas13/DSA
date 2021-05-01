
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Parse the input file for commands
 * 
 * @author {bshreyas and veerad}
 * @version 2021-04-20
 */
public class Parser {
    private static Parser p = null;
    private static Scanner scan;

    /**
     * This method will initialize the parser
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

    /**
     * This method will clear parser
     */
    public static void delete() {
        scan = null;
        p = null;
    }


    /**
     * This will read the next line of the parser
     * @return string array
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
