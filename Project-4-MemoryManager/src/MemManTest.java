
import student.TestCase;

/**
 * Tests all the methods in MemMan
 * 
 * @author {bshreyas and veerad}
 * @version 2021-04-20
 */
public class MemManTest extends TestCase {

    /**
     * input file with commands
     */
    public void testSampleInput() {
        String[] args = new String[3];
        args[0] = "32";
        args[1] = "10";
        args[2] = "sampleInput.txt";
        systemOut().clearHistory();
        MemMan.main(args);
        assertFalse(systemOut().getHistory().contains("Error"));
        systemOut().clearHistory();
        Parser.delete();
        CommandProcessor.delete();
    }
}
