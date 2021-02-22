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
        String[] fileNames = {
            // comment files not needed
            "TestCases.txt", "RemoveTest.txt"};
        for (String f : fileNames) {
            System.out.flush();
            systemOut().clearHistory();
            RectangleDB.main(new String[] { f });
            RectangleDB manager = new RectangleDB();
            Parser parserTest = new Parser();
            assertNotNull(parserTest);
            assertNotNull(manager);
        }

    }
}
