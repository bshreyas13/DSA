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
 * Code for Hash (String based with quadratic probing)
 * 
 * @author {bshreyas and veerad}
 * @version 2021-04-20
 */

public class Hash {

    private int hashSize;
    private int filledSize;
    private String[] data = null;
    private String seperator = "<SEP>";
    private Handle[] values;

    /**
     * Create a new Hash object.
     * 
     * @param hashSize
     *            slots in the hashtable
     */
    public Hash(int hashSize) {
        this.hashSize = hashSize;
        filledSize = 0;
        data = new String[hashSize];
        values = new Handle[hashSize];
    }


    /**
     * Compute the hash function. Uses the "sfold" method from the OpenDSA
     * module on hash functions
     *
     * @param s
     *            The string that we are hashing
     * @param m
     *            The size of the hash table
     * @return The home slot for that string
     */
    // You can make this private in your project.
    // This is public for distributing the hash function in a way
    // that will pass milestone 1 without change.
    public int h(String s, int m) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }
        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }
        return (int)(Math.abs(sum) % m);
    }


    /**
     * this function will find the index using quadratic probing
     * index = homeshot + i^2
     * 
     * @param homeSlot
     *            THe ideal index for the key
     * @param iteration
     *            The n'th iteration of the function (starts with 0)
     * @return
     */
    private int findIndex(int homeSlot, int iteration) {
        return ((homeSlot % hashSize) + (iteration % hashSize) * (iteration)
            % hashSize) % this.hashSize;
    }


    /**
     * This function will add the string to the hashtable. Ensure
     * that the string does not already exist in the table before%2
     * calling this function.
     * 
     * @param name
     *            the string to be added
     * @param h
     *            the memory handle associated with the name
     * @return the index added
     */
    public int add(String name, Handle h) {
        if ((filledSize) >= hashSize / 2) {
            this.doubleTableSize();
        }
        int homeSlot = this.h(name, this.hashSize);
        int index = homeSlot;
        int i = 0;
        while (data[index % hashSize] != null && !data[index % hashSize].equals(
            seperator)) {
            i++;
            index = this.findIndex(homeSlot, i);
        }
        data[index % hashSize] = name;
        values[index % hashSize] = h;
        filledSize += 1;

        return index;
    }


    /**
     * This function will double the size of the tables
     * Note: indexes will change
     */
    private void doubleTableSize() {
        String[] dataCopy = data;
        Handle[] valuesCopy = values;
        data = new String[hashSize * 2];
        values = new Handle[hashSize * 2];
        hashSize = data.length;
        filledSize = 0;
        for (int i = 0; i < dataCopy.length; i++) {
            if (dataCopy[i] == null) {
                continue;
            }
            if (dataCopy[i].equals(seperator)) {
                continue;
            }

            this.add(dataCopy[i], valuesCopy[i]);
        }

        System.out.println("Name hash table size doubled to " + hashSize
            + " slots.");
    }


    /**
     * This will search the hash table and return the index
     * 
     * @param name
     *            the name to be searched
     * @return -1 if the name is not found, otherwise index
     */
    public int search(String name) {
        int homeSlot = this.h(name, this.hashSize);
        int index = homeSlot;
        int i = 0;
        while (data[index] != null) {
            if (data[index].equals(name)) {
                return index;
            }
            i++;
            index = this.findIndex(homeSlot, i);
        }
        return -1;
    }


    /**
     * Delete the name if it exists in the table
     * 
     * @param name
     *            String to be deleted
     * @return true if successfully deleted, false otherwise
     * 
     */
    public boolean delete(String name) {
        int index = this.search(name);
        if (index == -1) {
            // if we can't find this in the hashtable
            return false;
        }
        data[index] = this.seperator;
        values[index] = null;
        filledSize -= 1;
        return true;
    }


    /**
     * This methodwill print the hash table
     */
    public void print() {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                continue;
            }
            if (data[i].equals(seperator)) {
                continue;
            }
            System.out.println("|" + data[i] + "| " + i);
        }
        System.out.println("Total records: " + this.filledSize);
    }


    /**
     * This method will return handle for the passed index
     * 
     * @param index
     *            index of the hashtable - will usually come from teh search
     *            Function
     * @return the Handle at the index
     */
    public Handle getHandle(int index) {
        return values[index];
    }


    /**
     * This method will assign handle for the passed index
     * 
     * @param h
     *            the handle
     * @param index
     *            the location of the handle in values
     */
    public void setHandle(Handle h, int index) {
        values[index] = h;
    }

}
