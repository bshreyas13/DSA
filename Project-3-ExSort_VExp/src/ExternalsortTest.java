import student.TestCase;

/**
 * @author {Your Name Here}
 * @version {Put Something Here}
 */
public class ExternalsortTest extends TestCase {

    /**
     * set up for tests
     */
    public void setUp() {
        // nothing to set up.
    }


    /**
     * Get code coverage of the class declaration.
     */
    public void testExternalsortInit() {
        String[] file = {"input1.bin"};
        System.out.flush();
        systemOut().clearHistory();
        Externalsort.main(file);
        Externalsort sorter = new Externalsort();
        assertNotNull(sorter);
    }

}
