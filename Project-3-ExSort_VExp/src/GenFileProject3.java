// WARNING: This program uses the Assertion class. When it is run,
// assertions must be turned on. For example, under Linux, use:
// java -ea Genfile

/**
 * Generate a data file. The size is a multiple of 8192 bytes.
 * Each record is one long and one double.
 */

import java.io.*;
import java.util.*;

/**
 * Class to generate test input files
 * 
 * @author bshreyas and veerad
 * @version 4/10/2021
 */

public class GenFileProject3 {

    private static final int NUM_RECS = 512; // Because they are short ints

    // Initialize the random variable
    private static Random value = new Random(); // Hold the Random class object

    /**
     * Generates random long
     * 
     * @return random long value
     */
    private static long randLong() {
        return value.nextLong();
    }


    /**
     * Generates random long
     * 
     * @return random double value
     */
    private static double randDouble() {
        return value.nextDouble();
    }


    /**
     * @param args
     *            command line arguments expecting commands
     */
    public static void main(String[] args) throws IOException {
        long val;
        double val2;
        assert (args.length == 2) : "\nUsage: Genfile <filename> <size>"
            + "\nOptions \nSize is measured in blocks of 8192 bytes";

        int filesize = Integer.parseInt(args[1]); // Size of file in blocks
        DataOutputStream file = new DataOutputStream(new BufferedOutputStream(
            new FileOutputStream(args[0])));

        for (int i = 0; i < filesize; i++) {
            for (int j = 0; j < NUM_RECS; j++) {
                val = (long)(randLong());
                file.writeLong(val);
                val2 = (double)(randDouble());
                file.writeDouble(val2);
            }
        }
        file.flush();
        file.close();
    }

}
