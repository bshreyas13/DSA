
import student.TestCase;

/**
 * Tests all the methods in MemMan
 * 
 * @author {shreyasb and veerad}
 * @version 2021-04-20
 */
public class MemManTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing Here
    }


//    /**
//     * Get code coverage of the class declaration.
//     */
//    public void testRInit() {
//        MemMan manager = new MemMan();
//        assertNotNull(manager);
//        MemMan.main(null);
//    }

//    /**
//     * test case : incorrect number of inputs
//     */
//    public void testNoparameters() {
//        // Testing for no other arguments
//        String[] args = new String[2];
//        MemMan.main(args);
//        assertEquals(false, systemOut().getHistory().contains("Error"));
//        systemOut().clearHistory();
//        Parser.delete();
//        CommandProcessor.delete();
//    }


//    /**
//     * test case: file does not exist
//     */
//    public void testFileNotExist() {
//        // Testing for file not found
//        String[] args = new String[2];
//        args = new String[3];
//        args[0] = "20";
//        args[1] = "10";
//        args[2] = "nofile.txt";
//        MemMan.main(args);
//        assertEquals(true, systemOut().getHistory().contains("Error"));
//        systemOut().clearHistory();
//        Parser.delete();
//        CommandProcessor.delete();
//    }


//    /**
//     * testcase bad input file
//     */
//    public void testBadInputFile() {
//        String[] args = new String[3];
//        args[0] = "4";
//        args[1] = "16";
//        args[2] = "badinput.txt";
//        MemMan.main(args);
//        assertEquals(true, systemOut().getHistory().contains("Error"));
//        systemOut().clearHistory();
//        Parser.delete();
//        CommandProcessor.delete();
//    }


//    /**
//     * hashsize or mem size not being an integer
//     */
//    public void testNaNSize() {
//        String[] args = new String[3];
//        args[1] = "100";
//        args[2] = "Project1_sampleInput.txt";
//        args[0] = "not a number";
//        MemMan.main(args);
//        assertEquals(true, systemOut().getHistory().contains("Error"));
//        systemOut().clearHistory();
//        Parser.delete();
//        CommandProcessor.delete();
//        args[2] = "Project1_sampleInput.txt";
//        args[0] = "100";
//        args[1] = "not a number";
//        MemMan.main(args);
//        assertEquals(true, systemOut().getHistory().contains("Error"));
//        systemOut().clearHistory();
//        Parser.delete();
//        CommandProcessor.delete();
//    }


    /**
     * input file with commands
     */
    public void testSampleInput() {
        String[] args = new String[3];
        args[0] = "32";
        args[1] = "3";
        args[2] = "sampleInput.txt";
        systemOut().clearHistory();
        MemMan.main(args);
        assertFalse(systemOut().getHistory().contains("Error"));
        systemOut().clearHistory();
        Parser.delete();
        CommandProcessor.delete();
    }
}
