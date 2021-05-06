
import student.TestCase;

/**
 * Tests the methods in HandleList Class
 * 
 * @author {bshreyas and veerad}
 * @version 2021-04-20
 */
public class HandleListTest extends TestCase {

    /**
     * test case for all methods
     * 
     */
    public void testAll() {
        HandleList list = new HandleList(10);

        assertNull(list.pop(0));

        Handle h1 = new Handle(8, 0, false);
        Handle h2 = new Handle(8, 8, false);
        Handle h3 = new Handle(8, 16, false);

        list.add(h1, 2);
        list.add(h2, 2);
        list.add(h3, 2);

        assertEquals(h1, list.pop(2));
        assertEquals(h2, list.pop(2));
        assertEquals(h3, list.pop(2));

    }


    /**
     * 
     * test case for add
     */
    public void testAdd() {
        HandleList list = new HandleList(4);
        Handle h1 = new Handle(8, 0, false);
        Handle h2 = new Handle(8, 64, false);
        Handle h3 = new Handle(8, 32, false);

        list.add(h1, 1);
        list.add(h2, 1);
        list.add(h3, 1);

        assertEquals(h1, list.pop(1));
        assertEquals(h3, list.pop(1));
        assertEquals(h2, list.pop(1));
    }


    /**
     * test case for remove
     */
    public void testRemove() {
        HandleList list = new HandleList(4);
        Handle h1 = new Handle(8, 0, true);
        Handle h2 = new Handle(8, 64, true);
        Handle h3 = new Handle(8, 32, true);

        list.add(h1, 1);
        list.add(h2, 1);
        list.add(h3, 1);

        assertNotNull(list.getHead(1));
        list.remove(1, h2);
        list.remove(1, h3);
        list.remove(1, h1);

        assertNull(list.getHead(1));

    }

}
