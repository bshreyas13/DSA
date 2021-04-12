import student.TestCase;
/**
 * Test Class to test MinHeap
 * @author bshreyas and veerad
 * @version 4/10/2021
 *
 */
public class MinHeapTest extends TestCase {
    /**
     * Tests interger inserts
     */
    public void testIntegerInsert() {
        MinHeap<Integer> heap;
        heap = new MinHeap<>(9);
        heap.insert(50);
        heap.insert(12);
        heap.insert(7);
        heap.insert(25);
        heap.insert(15);
        heap.insert(20);
        heap.insert(30);
        assertEquals(7, heap.heapSize());
        String fin = "7 15 12 50 25 20 30 ";
        assertEquals(fin, heap.toString());
    }

    /**
     * Tests float inserts
     */
    public void testFloatInsert() {
        MinHeap<Float> heapf;
        heapf = new MinHeap<>(9);
        heapf.insert(50.1f);
        heapf.insert(12.0f);
        heapf.insert(7.0f);
        heapf.insert(25.0f);
        heapf.insert(15.0f);
        heapf.insert(20.0f);
        heapf.insert(30.0f);
        String fin = "7.0 15.0 12.0 50.1 25.0 20.0 30.0 ";
        assertEquals(fin, heapf.toString());
    }

    /**
     * Tests inserting multiple integers
     */
    public void testIntegerBigInsert() {
        MinHeap<Integer> heap;
        heap = new MinHeap<>(15);
        assertTrue(heap.isNull());
        heap.insert(50);
        heap.insert(12);
        assertFalse(heap.isNull());
        heap.insert(7);
        heap.insert(25);
        heap.insert(15);
        heap.insert(20);
        heap.insert(30);
        heap.insert(10);
        heap.insert(11);
        assertFalse(heap.isHeapFull());
        heap.insert(55);
        heap.insert(95);
        heap.insert(70);
        heap.insert(65);
        heap.insert(33);
        heap.insert(16);
        assertEquals(15, heap.heapSize());
        heap.restoreMaxSize();
        assertEquals(7, (int)heap.getMin());
        assertEquals(15, heap.heapMax());
        assertTrue(heap.isHeapFull());
        assertEquals(7, (int)heap.getMin());
        assertEquals(7, (int)heap.removeMin());
        assertEquals(14, heap.heapSize());
        assertEquals(14, heap.heapMax());
        heap.goodInsert(13);
        heap.badInsert(5);
        heap.badInsert(4);
        assertTrue(heap.isLeaf(10));
        assertFalse(heap.isLeaf(26));
    }
}
