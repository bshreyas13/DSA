// Min-heap implementation
/**
 * Min Heap implementation based on OpenDSA 10.17
 * 
 * @author bshreyas and veerad
 * @version 4/10/2021
 *
 * @param <T>
 *            Generic parameter so we can use it in multiple ways.
 */
public class MinHeap<T extends Comparable<T>> {
    private T[] heap; // Pointer to the heap array
    private int maxSize; // Maximum size of the heap
    private int n; // Number of things now in heap

    /**
     * Constructor that generates a Min Heap with given maximum size.
     * 
     * @param maximum
     *            Maximim size of the heap to construct.
     */
    @SuppressWarnings("unchecked")
    MinHeap(int maximum) {
        heap = (T[])new Comparable[maximum];
        maxSize = maximum;
        n = 0;
        buildHeap();
    }


    /**
     * Gets the current filled
     * 
     * @return
     *         int: num of items in heap
     */
    // Return current size of the heap
    public int heapSize() {
        return n;
    }


    /**
     * Resets the maxSize and n
     */
    public void resetMaxSize() {
        maxSize = heap.length;
        n = maxSize;
    }


    /**
     * Tells if heap is full
     * 
     * @return
     *         boolean: true if heap is full.
     */
    public boolean isHeapFull() {
        return n == maxSize;
    }


    /**
     * Tells if the heap is null.
     * 
     * @return
     *         boolean: true if the tree is null.
     */
    public boolean isHeapNull() {
        return heap[0] == null;
    }


    /**
     * Tells if the position is a leaf.
     * 
     * @param pos
     *            int: position of the record in the heap array.
     * @return
     *         boolean: true if the position is a leaf.
     */
    // Return true if pos a leaf position, false otherwise
    public boolean isLeaf(int pos) {
        return (pos >= n / 2) && (pos < n);
    }


    /**
     * Gets the leftchild of the current position.
     * 
     * @param pos
     *            position of the node of which we want the leftchild.
     * @return
     *         int, leftchild of a number
     */
    // Return position for left child of pos
    public int leftChild(int pos) {
        if (pos >= n / 2) {
            return -1;
        }
        return 2 * pos + 1;
    }


    /**
     * Parent of the current position
     * 
     * @param pos
     *            int: position of the child.
     * @return
     *         int: position of its parent.
     */
    // Return position for parent
    private int parent(int pos) {
        if (pos <= 0) {
            return -1;
        }
        return (pos - 1) / 2;
    }


    /**
     * Inserts a block of data and heapifys it.
     * 
     * @param records
     *            Records to be inserted in the heap.
     */
    void insertBlock(T[] records) {
        for (int i = 0; i < records.length; i++) {
            heap[n + i] = records[i];
        }
        n += records.length;
        buildHeap();
    }


    /**
     * Helper function to build the heap properly.
     */
    // Heapify contents of Heap
    public void buildHeap() {
        for (int i = n / 2 - 1; i >= 0; i--) {
            siftdown(i);
        }
    }


    /**
     * Swaps p1 and p2 in array
     * 
     * @param array
     *            array for the swap to happen.
     * @param p1
     *            index of first item
     * @param p2
     *            index of second item
     */
    public void swap(T[] array, int p1, int p2) {
        T temp = array[p1];
        array[p1] = array[p2];
        array[p2] = temp;
    }


    /**
     * Checks where an element should be placed
     * 
     * @param pos
     *            elements position to be shifted down.
     */
    // Put element in its correct place
    private void siftdown(int pos) {
        if ((pos < 0) || (pos >= n)) {
            return; // Illegal position
        }
        while (!isLeaf(pos)) {
            int j = leftChild(pos);
            if ((j < (n - 1)) && (heap[j].compareTo(heap[j + 1]) >= 0)) {
                j++; // j is now index of child with greater value
            }
            if (n > 1 && heap[pos].compareTo(heap[j]) < 0) {
                return;
            }

            swap(heap, pos, j);
            pos = j; // Move down
        }
    }


    /**
     * Returns the minimum value from the heap array and doesn't destroy it.
     * 
     * @return
     *         Minimum Record
     */
    public T getMinRecord() {
        return heap[0];
    }


    /**
     * Returns the minimum value and destroys lives by putting them in prison.
     * 
     * @return
     *         Minim record.
     */
    // Remove and return minimum value
    public T removeMin() {
        if (n == 0) {
            return null; // Removing from empty heap
        }
        swap(heap, 0, --n); // Swap minimum with first value
        siftdown(0); // Put new heap root val in correct place
        maxSize--;
        return heap[n];
    }


    /**
     * Returns the string form of the heap.
     * 
     * @return
     *         String: heap as string
     */
    public String toString() {
        String heapString = "";
        for (int i = 0; i < n; i++) {
            heapString = heapString + heap[i] + " ";
        }
        return heapString;
    }


    /**
     * Inserts without reducing heap size.
     * 
     * @param replacement
     *            Data to replace
     */
    public void cleanInsert(T replacement) {
        heap[0] = replacement;
        siftdown(0);
    }


    /**
     * Inserts by reducing heap size
     * 
     * @param replacement
     *            Data to replace
     */
    public void dirtyInsert(T replacement) {
        heap[0] = replacement;
        swap(heap, 0, --n); // Swap maximum with last value
        maxSize -= 1;
        siftdown(0); // Put new heap root val in correct place
    }


    /**
     * Gives the maximum size value
     * 
     * @return
     *         int: maximum size of the heap.
     */
    public int heapMaxSize() {
        return maxSize;
    }
}
