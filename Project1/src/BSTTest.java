
import org.junit.Test;

// -------------------------------------------------------------------------
/**
 * Test the BST function (you should throw this away for your project)
 *
 * @author {shreyasb and veerad}
 * @version
 */
public class BSTTest extends student.TestCase {

	@Test()
	public void shouldAcceptValidRectangle() {
		BST<String, Rect> bst = new BST<String, Rect>();
		String key = "RECT-1";
		Rect value = new Rect(10, 10, 5, 5);
		bst.insert(key, value);
		assertEquals(bst.searchByKey(key), value);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRejectInValidRectangle() {
		BST<String, Rect> bst = new BST<String, Rect>();
		String key = "RECT-89";
		Rect value = new Rect(10, 10, -5, -5);
		bst.insert(key, value);
		assertNull(bst.searchByKey(key));
	}

}
