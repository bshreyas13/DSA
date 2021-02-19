import student.TestCase;

/**
 * @author bshreyas and veerad
 * @version 02-18-2021
 */
public class RectangleDBTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing Here
    }


    /**
     * Get code coverage of the class declaration.
     */
    public void testRInit() {
        String[] f = { "RemoveTest.txt" };
        RectangleDB.main(f);
        RectangleDB manager = new RectangleDB();
        Parser parserTest = new Parser();
        assertNotNull(parserTest);
        assertNotNull(manager);
    }
}
