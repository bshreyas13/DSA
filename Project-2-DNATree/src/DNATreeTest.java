import student.TestCase;

/**
 * Represents the DNA Tree test
 * 
 * @author {bshreyas and veerad}
 * @version 2021-03-07
 */
public class DNATreeTest extends TestCase {
    /**
     * Get code coverage of the DNA Tree.
     */
    public void testRInit() {
        System.out.flush();
        systemOut().clearHistory();
        String[] fileNames = { "input_.txt"};


        for (String f : fileNames) {
            DNAtree.main(new String[] { f });
            assertTrue(DNAtree.isSuccess());
        }

    }
}
