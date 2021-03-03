import java.util.Arrays;

/**
 * Implementation of Generic Array List using Arrays
 * 
 * @author {shreyasb and veerad}
 * @version 2021-2-15
 * @param <E>
 *            datatype to saved in the list
 */

public class MyList<E> {
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;

    /**
     * Method to call ArrayList
     */
    public MyList() {
        elements = new Object[DEFAULT_CAPACITY];
    }


    /**
     * Add to list
     * 
     * @param e
     *            parameter to add to list
     */
    public void add(E e) {
        if (size == elements.length) {
            ensureCapa();
        }
        elements[size++] = e;
    }


    /**
     * Method to get length
     * 
     * @return
     *         length of list
     */
    public int length() {
        return size;
    }


    /**
     * Copy elements over and increase size
     */
    private void ensureCapa() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }


    /**
     * Method to access element given index
     * 
     * @param i
     *            index
     * @return
     *         element
     */
    @SuppressWarnings("unchecked")
    public E get(int i) {
        if (i >= size || i < 0) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size " + i);
        }
        return (E)elements[i];
    }
}
