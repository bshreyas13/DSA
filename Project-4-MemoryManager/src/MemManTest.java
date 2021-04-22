import student.TestCase;

/**
 * Test the main function (you should throw this away for your project and write
 * your own tests)
 * 
 * @author {Your Name Here}
 * @version {Put Something Here}
 */
public class MemManTest extends TestCase {
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
        MemMan manager = new MemMan();
        assertNotNull(manager);
        MemMan.main(null);
    }
}
