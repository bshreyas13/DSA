
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
        BST<String, Rect> bst = new BST<String, Rect>();
        String key = "RECT-1";
        Rect value = new Rect(10, 10, 5, 5);
        bst.insert(key, value);
        bst.insert("RECT-2", new Rect(11, 11, 1, 1));
        bst.insert("RECT-2", new Rect(11, 11, 2, 1));
        bst.insert("null", new Rect(5, 5, 5, 5));
        //bst.insert(null, new Rect(11, 11, 1, 1));
        assertEquals(bst.searchByKey(key), value);
    }


    /**
     * Verifies the rejection of invalid rectangle
     */
    public void shouldRejectInValidRectangle() {
        BST<String, Rect> bst = new BST<String, Rect>();
        bst.insert("RECT-1", new Rect(10, 10, 5, 5));
        String key = "RECT-89";
        Rect value = new Rect(10, 10, -5, -5);
        bst.insert(key, value);
        assertNull(bst.searchByKey(key));
    }


    /**
     * tests BST Dump
     */
    @Test
    public void shouldDisplayBSTDump() {
        BST<String, Rect> bst = new BST<String, Rect>();
        String k1 = "RECT1";
        Rect v1 = new Rect(10, 10, 5, 5);
        String k2 = "RECT2";
        Rect v2 = new Rect(10, 10, 5, 5);
        bst.insert(k1, v1);
        bst.insert(k2, v2);
        String path = bst.dump();
        assertNotNull(path);
    }


    /**
     * tests BST delete feature
     */
    @Test
    public void shouldDeleteTheKey() {
        BST<String, Rect> bst = new BST<String, Rect>();
        String key = "RECT-89";
        Rect value = new Rect(10, 10, 5, 5);
        bst.insert(key, value);
        bst.insert("RECT-12", new Rect(12, 12, 1, 1));
        bst.insert("RECT-130", new Rect(130, 13, 1, 1));
        bst.insert("RECT-6", new Rect(12, 12, 6, 1));
        bst.remove(key);
        Rect rect = bst.searchByKey(key);
        assertNull(rect);
    }


    /**
     * tests BST delete feature by region
     */
    @Test
    public void shouldDeleteByRegion() {
        BST<String, Rect> bst = new BST<String, Rect>();
        String key = "RECT-89";
        Rect value = new Rect(10, 10, 5, 5);
        bst.insert(key, value);
        bst.insert("RECT-12", new Rect(12, 12, 1, 1));
        bst.insert("RECT-130", new Rect(130, 13, 1, 1));
        bst.insert("RECT-6", new Rect(12, 12, 6, 1));
        bst.removeAllByShape(new Rect(12, 12, 6, 1));
        Rect rect = bst.searchByKey("RECT-6");
        assertNull(rect);
    }


    /**
     * tests search feature by region
     */
    @Test
    public void shouldSearchByRegion() {
        BST<String, Rect> bst = new BST<String, Rect>();
        bst.insert("RECT-1", new Rect(10, 10, 5, 5));
        String result = bst.searchByRegion(new Rect(11, 11, 1, 1));
        assertEquals(result, "RECT-1,");
    }


    /**
     * tests BST intersection feature
     */
    @Test
    public void shouldSearchForIntersections() {
        BST<String, Rect> bst = new BST<String, Rect>();
        bst.insert("RECT-1", new Rect(10, 10, 5, 5));
        bst.insert("RECT-2", new Rect(11, 11, 1, 1));
        String result = bst.searchForIntersections();
        assertEquals(result, "RECT-1,RECT-2,");
    }

}
