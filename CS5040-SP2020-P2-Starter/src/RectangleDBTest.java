import student.TestCase;

/**
 * @author {shreyasb and veerad}
 * @version 2021-2-15
 */
public class RectangleDBTest extends TestCase {

    /**
     * Get code coverage of the class declaration.
     */
    public void testRInit() {
        String[] f = { "TestCases.txt" };
        RectangleDB.main(f);
        systemOut().clearHistory();
        RectangleDB manager = new RectangleDB();
        Parser parserTest = new Parser();
        assertNotNull(parserTest);
        assertNotNull(manager);
    }
}
