
import org.junit.Test;

// -------------------------------------------------------------------------
/**
 * Test the BST function (you should throw this away for your project)
 *
 * @author {shreyasb and veerad}
 * @version 2021-2-15
 */
public class BSTTest extends student.TestCase {

    /**
     * Verifies the insertion of valid rectangle in tree
     */
    @Test()
    public void shouldAcceptValidRectangle() {
        BST<Rect> bst = new BST<Rect>();
        String key = "RECT_1";
        Rect value = new Rect(10, 10, 5, 5);
        bst.insert(new Node<Rect>(key, value));
        bst.insert(new Node<Rect>("RECT_2", new Rect(11, 11, 1, 1)));
        bst.insert(new Node<Rect>("RECT_2", new Rect(11, 11, 1, 1)));
        bst.insert(new Node<Rect>("RECT_2", new Rect(11, 11, 2, 1)));
        bst.insert(new Node<Rect>(null, new Rect(5, 5, 5, 5)));
        bst.insert(new Node<Rect>("rec_zeroinput", new Rect(0, 0, 30, 6)));
        assertNotNull(bst.searchByKey(key));
    }


    /**
     * Verifies the rejection of invalid rectangle
     */
    public void shouldRejectInValidRectangle() {
        BST<Rect> bst = new BST<Rect>();
        bst.insert(new Node<Rect>("RECT_1", new Rect(10, 10, 5, 5)));
        String key = "RECT_89";
        Rect value = new Rect(10, 10, -5, -5);
        bst.insert(new Node<Rect>(key, value));
        bst.insert(new Node<Rect>("rec_zeroinput", new Rect(0, 0, 7, 6)));
        assertNull(bst.searchByKey(key));
    }


    /**
     * tests BST Dump
     */
    @Test
    public void shouldDisplayBSTDump() {
        BST<Rect> bst = new BST<Rect>();
        String k1 = "RECT1";
        Rect v1 = new Rect(10, 10, 5, 5);
        String k2 = "RECT2";
        Rect v2 = new Rect(10, 10, 5, 5);
        bst.insert(new Node<Rect>(k1, v1));
        bst.insert(new Node<Rect>(k2, v2));
        assertTrue(bst.dump());
    }


    /**
     * tests BST delete feature
     */
    @Test
    public void shouldDeleteTheKey() {
        BST<Rect> bst = new BST<Rect>();

        bst.insert(new Node<Rect>("R37", new Rect(12, 12, 1, 1)));
        bst.insert(new Node<Rect>("R24", new Rect(12, 12, 1, 1)));
        bst.insert(new Node<Rect>("R42", new Rect(12, 12, 1, 1)));
        bst.insert(new Node<Rect>("R42", new Rect(12, 12, 1, 1)));
        bst.insert(new Node<Rect>("R120", new Rect(12, 12, 1, 1)));
        bst.insert(new Node<Rect>("R40", new Rect(12, 12, 1, 1)));
        bst.insert(new Node<Rect>("R24", new Rect(12, 12, 1, 1)));
        bst.insert(new Node<Rect>("R119", new Rect(12, 12, 1, 1)));
        bst.insert(new Node<Rect>("R32", new Rect(12, 12, 1, 1)));
        bst.insert(new Node<Rect>("R7", new Rect(12, 12, 1, 1)));
        bst.insert(new Node<Rect>("R2", new Rect(12, 12, 1, 1)));
        bst.remove("R120");
        bst.remove("R42");
        
        String key = "RECT_89";
        Rect value = new Rect(10, 10, 5, 5);
        bst.insert(new Node<Rect>(key, value));
        bst.insert(new Node<Rect>("RECT_12", new Rect(12, 12, 1, 1)));
        bst.insert(new Node<Rect>("RECT_52", new Rect(12, 12, 1, 1)));
        bst.insert(new Node<Rect>("RECT_62", new Rect(12, 12, 1, 1)));
        bst.insert(new Node<Rect>("RECT_125", new Rect(12, 12, 1, 1)));
        bst.insert(new Node<Rect>("RECT_112", new Rect(12, 12, 1, 1)));
        bst.insert(new Node<Rect>("RECT_172", new Rect(12, 12, 1, 1)));
        bst.insert(new Node<Rect>("RECT_130", new Rect(130, 13, 1, 1)));
        bst.insert(new Node<Rect>("RECT_6", new Rect(12, 12, 6, 1)));
        bst.insert(new Node<Rect>("R1", new Rect(12, 12, 6, 1)));
        bst.insert(new Node<Rect>("R3", new Rect(12, 12, 6, 1)));
        bst.insert(new Node<Rect>("R7", new Rect(12, 12, 6, 1)));
        bst.insert(new Node<Rect>("R6", new Rect(12, 12, 6, 1)));
        bst.insert(new Node<Rect>("R9", new Rect(12, 12, 6, 1)));
        bst.insert(new Node<Rect>("R2", new Rect(12, 12, 6, 1)));
        bst.insert(new Node<Rect>("R10", new Rect(12, 12, 6, 1)));
        bst.insert(new Node<Rect>("R119", new Rect(12, 12, 6, 1)));
        bst.insert(new Node<Rect>("R15", new Rect(12, 12, 6, 1)));
        bst.remove(key);
        bst.remove("6");
        assertNull(bst.searchByKey(key));
    }


    /**
     * tests BST delete feature by region
     */
    @Test
    public void shouldDeleteByRegion() {
        BST<Rect> bst = new BST<Rect>();
        String key = "RECT_89";
        Rect value = new Rect(10, 10, 5, 5);
        bst.insert(new Node<Rect>(key, value));
        bst.insert(new Node<Rect>("RECT_12", new Rect(12, 12, 1, 1)));
        bst.insert(new Node<Rect>("RECT_130", new Rect(130, 13, 1, 1)));
        bst.insert(new Node<Rect>("RECT_6", new Rect(12, 12, 6, 1)));
        bst.removeByRect(new Rect(12, 12, 6, 1));
        assertNull(bst.searchByKey("RECT_6"));
    }


    /**
     * tests search feature by region
     */
    @Test
    public void shouldSearchByRegion() {
        BST<Rect> bst = new BST<Rect>();
        bst.insert(new Node<Rect>("RECT_1", new Rect(10, 10, 5, 5)));
        String result = bst.searchByRegion(new Rect(11, 11, 1, 1));
        assertNotNull(result);
    }


    /**
     * tests BST intersection feature
     */
    @Test
    public void shouldSearchForIntersections() {
        BST<Rect> bst = new BST<Rect>();
        bst.insert(new Node<Rect>("RECT_1", new Rect(10, 10, 5, 5)));
        bst.insert(new Node<Rect>("RECT_2", new Rect(11, 11, 1, 1)));
        String result = bst.searchForIntersections();
        assertNotNull(result);
    }

}
