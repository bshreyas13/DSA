/**
 * On my honor:
 * - I have not used source code obtained from another student,
 * or any other unauthorized source, either modified or
 * unmodified.
 * 
 * - All source code and documentation used in my program is
 * either my original work, or was derived by me from the
 * source code published in the textbook for this course.
 *
 * - I have not discussed coding details about this project with
 * anyone other than my partner (in the case of a joint
 * submission), instructor, ACM/UPE tutors or the TAs assigned
 * to this course. I understand that I may discuss the concepts
 * of this program with other students, and that another student
 * may help me debug my program so long as neither of us writes
 * anything during the discussion or modifies any computer file
 * during the discussion. I have violated neither the spirit nor
 * letter of this restriction.
 */

/**
 * The class defining the handles for the Memory Manager
 *
 * @author {shreyasb and veerad}
 * @version 2021-04-20
 *
 */

public class Handle {
    private int length; // total length for empty block; used length for used
    private int locationStart;
    private Handle next = null;

    /**
     * Constructor for memory blockhandle
     * 
     * @param length
     *            is the length of the memory block
     * @param locStart
     *            is the start of the memory block
     * @param filled
     *            true if filled / false if not filled
     */
    public Handle(int length, int locStart, boolean filled) {
        this.length = length;
        this.setLocationStart(locStart);
        this.next = null;
    }


    /**
     * 
     * @return length of the memory block (the size on disk). this will have the
     *         space occupied if filled; otherwise the maximum size this block
     *         can hold
     */
    public int getLength() {
        return length;
    }


    /**
     * Sets the length of a block of memory
     * 
     * @param length
     *            is the new length of this handle
     */
    protected void setLength(int length) {
        this.length = length;
    }


    /**
     * get the next handle in the list.
     * 
     * @return the next Handle in the list
     */
    public Handle getNext() {
        return next;
    }


    /**
     * set the next handle in the list
     * 
     * @param next
     *            the next handle in the list
     */
    public void setNext(Handle next) {
        this.next = next;
    }


    /**
     * get the start of the memory block
     * 
     * @return start of the memory block
     */
    public int getLocationStart() {
        return locationStart;
    }


    /**
     * set the start of the location
     * 
     * @param locationStart
     *            start of memory block
     */
    public void setLocationStart(int locationStart) {
        this.locationStart = locationStart;
    }


    /**
     * finds if two handles are next to each other
     * in the disk and can be merged
     * 
     * @param h
     *            the candidate handle
     * @return true if handles are adjacent, false otherwise
     */
    public boolean isMergable(Handle h) {
        boolean isBuddy = Math.min(h.getLocationStart(), locationStart)
            % (length * 2) == 0;

        return ((h.getLocationStart() == locationStart + length || h
            .getLocationStart() + length == locationStart) && MemManager.log2(
                length) == MemManager.log2(h.getLength()) && isBuddy);
    }

}
