// On my honor:
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project
// with anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

/**
 * this class defines the list of objects for the handles
 * 
 * @author {bshreyas and veerad}
 * @version 2021-04-20
 */
public class HandleList {
    private int length;
    private Handle[] list;

    /**
     * This method will initialize HandleList
     * 
     * @param length
     *            the length of the list
     */
    public HandleList(int length) {
        this.length = length;
        this.list = new Handle[length];
    }


    /**
     * This method will increase the size of the list by 1
     */
    public void incrementSize() {
        Handle[] listCopy = list;
        length++;
        list = new Handle[length];
        for (int i = 0; i < listCopy.length; i++) {
            list[i] = listCopy[i];
        }
    }


    /**
     * This method will add a new handle at the position
     * 
     * @param h
     *            the handle to be added to the list
     * @param pos
     *            the position where it should be added
     */
    public void add(Handle h, int pos) {
        Handle head = list[pos];
        if (head == null || head.getLocationStart() > h.getLocationStart()) {
            h.setNext(list[pos]);
            list[pos] = h;
        }
        else {
            Handle curr = head;
            while (curr.getNext() != null) {
                if (h.getLocationStart() < curr.getNext().getLocationStart()) {
                    h.setNext(curr.getNext());
                    curr.setNext(h);
                    return;
                }
                curr = curr.getNext();
            }
            curr.setNext(h);
            h.setNext(null);
        }

    }


    /**
     * This method will get the head of the list at position pos
     * 
     * @param pos
     *            the position for which we want the head
     * @return the head at the position(null if the list is empty)
     */
    public Handle gethead(int pos) {
        return list[pos];
    }


    /**
     * This method will get the length of the list
     * 
     * @return the length
     */
    public int getLength() {
        return length;
    }


    /**
     * Removes the handle at position
     * 
     * @param h
     *            the handle to be removed from the list
     * @param pos
     *            the position where it should be removed
     * 
     */
    public void remove(int pos, Handle h) {
        Handle currHandle = list[pos];
        if (currHandle == h) {
            list[pos] = h.getNext();
        }
        else {
            Handle prev = null;
            while (currHandle != null) {
                prev = currHandle;
                currHandle = currHandle.getNext();
                if (currHandle == h) {
                    prev.setNext(currHandle.getNext());
                    return;
                }
            }
        }
    }


    /**
     * Pops the item at pos
     * 
     * @param pos
     *            - position of item to be removed
     * @return returns the list item at pos
     */
    public Handle pop(int pos) {
        Handle head = list[pos];
        if (head == null) {
            return null;
        }
        list[pos] = head.getNext();
        head.setNext(null);
        return head;
    }

}
