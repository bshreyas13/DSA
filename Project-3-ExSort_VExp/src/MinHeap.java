// import java.util.*;
// Min-heap implementation
/**
 * Min Heap implemetation
 * 
 * @author bshreyas and veerad
 * @version 4/10/2021
 *
 * @param <T>
 *            Generic parameter so we can use it in multiple ways.
 */
public class MinHeap<T extends Comparable<T>> {
    private T[] heapArray; // Pointer to the heap array
    private int max; // Maximum size of the heap
    private int size; // Number of things now in heap

    /**
     * Contructor that generates a Min Heap with given maximum size.
     * 
     * @param maximum
     *            Maximim size of the heap to construct.
     */
    @SuppressWarnings("unchecked")
    MinHeap(int maximum) {
        heapArray = (T[])new Comparable[maximum];
        max = maximum;
        size = 0;
    }


    /**
     * Gets the current filled
     * 
     * @return
     *         int: num of items in heap
     */
    // Return current size of the heap
    public int heapSize() {
        return size;
    }


    /**
     * Restores the max value and size value
     */
    public void restoreMaxSize() {
        max = heapArray.length;
        size = max;
    }


    /**
     * Tells if heap is full
     * 
     * @return
     *         boolean: true if heap is full.
     */
    public boolean isHeapFull() {
        return size == max;
    }


    /**
     * Tells if the heap has just been initialized.
     * 
     * @return
     *         boolean: true if the tree is null.
     */
    public boolean isNull() {
        return heapArray[0] == null;
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
        return (pos >= size / 2) && (pos < size);
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
        if (pos >= size / 2) {
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
     * Insert a single record.
     * 
     * @param record
     *            Record to be inserted
     */
    // Insert val into heap
    void insert(T record) {
        if (size >= max) {
            System.out.println("Heap is full");
            return;
        }
        int curr = size++;
        heapArray[curr] = record; // Start at end of heap
        // Now sift up until curr's parent's key > curr's ke y
        while ((curr != 0) && (heapArray[curr].compareTo(heapArray[parent(
            curr)]) <= 0)) {
            swap(heapArray, curr, parent(curr));
            curr = parent(curr);
        }
    }


    /**
     * Inserts a block of data into the heap and heapify it.
     * 
     * @param records
     *            Records to be inserted in the heap.
     */
    void insertBlock(T[] records) {
        for (int i = 0; i < records.length; i++) {
            heapArray[size + i] = records[i];
        }
        size += records.length;
        buildHeap();
    }


    /**
     * Helper funtion to build the heap properly.
     */
    // Heapify contents of Heap
    public void buildHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
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
        if ((pos < 0) || (pos >= size)) {
            return; // Illegal position
        }
        while (!isLeaf(pos)) {
            int j = leftChild(pos);
            if ((j < (size - 1)) && (heapArray[j].compareTo(heapArray[j
                + 1]) >= 0)) {
                j++; // j is now index of child with greater value
            }
            if (size > 1 && heapArray[pos].compareTo(heapArray[j]) < 0) {
                return;
            }

            swap(heapArray, pos, j);
            pos = j; // Move down
        }
    }


    /**
     * Returns the minimum value from the heap array and doesn't destroy it.
     * 
     * @return
     *         Minimum Record
     */
    public T getMin() {
        return heapArray[0];
    }


    /**
     * Returns the minimum value and destroys lives by putting them in prison.
     * 
     * @return
     *         Minim record.
     */
    // Remove and return maximum value
    public T removeMin() {
        if (size == 0) {
            return null; // Removing from empty heap
        }
        swap(heapArray, 0, --size); // Swap maximum with last value
        siftdown(0); // Put new heap root val in correct place
        max--;
        return heapArray[size];
    }


    /**
     * Returns the string form of the heap.
     * 
     * @return
     *         String: heap as string
     */
    public String toString() {
        String ret = "";
        for (int i = 0; i < size; i++) {
            ret = ret + heapArray[i] + " ";
        }
        return ret;
    }


    /**
     * Inserts without reducing heap numbers.
     * 
     * @param replacement
     *            Data to replace
     */
    public void goodInsert(T replacement) {
        heapArray[0] = replacement;
        siftdown(0);

    }


    /**
     * Inserts by reducing heap numbers
     * 
     * @param replacement
     *            Data to replace
     */
    public void badInsert(T replacement) {
        heapArray[0] = replacement;
        swap(heapArray, 0, --size); // Swap maximum with last value
        max -= 1;
        siftdown(0); // Put new heap root val in correct place

    }


    /**
     * Returns the maximum size value
     * 
     * @return
     *         int: maximum size of the heap.
     */
    public int heapMax() {
        return max;
    }
}
