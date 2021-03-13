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

        String[] fileNames = { "input_.txt" };

        for (String f : fileNames) {
            System.out.flush();
            systemOut().clearHistory();
            DNAtree.main(new String[] { f });
            assertTrue(DNAtree.isSuccess());
        }

    }
}
