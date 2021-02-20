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
        RectangleDB manager = new RectangleDB();
        assertNotNull(manager);
        RectangleDB.main(null);
    }
}
