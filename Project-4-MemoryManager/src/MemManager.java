// On my honor:
// - I have not used source code obtained from another student,
//   or any other unauthorized source, either modified or
//   unmodified.
//
// - All source code and documentation used in my program is
//   either my original work, or was derived by me from the
//   source code published in the textbook for this course.
//
// - I have not discussed coding details about this project
//   with anyone other than my partner (in the case of a joint
//   submission), instructor, ACM/UPE tutors or the TAs assigned
//   to this course. I understand that I may discuss the concepts
//   of this program with other students, and that another student
//   may help me debug my program so long as neither of us writes
//   anything during the discussion or modifies any computer file
//   during the discussion. I have violated neither the spirit nor
//   letter of this restriction.

import java.util.Arrays;

/**
 * Code for the Memory manager class
 * this will handle the creation, addition,
 * deletion and updates for a given memory block
 * 
 * @author {bshreyas and veerad}
 * @version 2021-04-20
 */
public class MemManager {
    private byte[] dataBlock;
    private HandleList freeBlocks;

    /**
     * initializer for MemManager
     * 
     * @param initialMemorySize
     *            initial size for memorymanager in bytes
     *            (this should be a power of two)
     */
    public MemManager(int initialMemorySize) {
        dataBlock = new byte[initialMemorySize];
        int lengthBlocks = log2(initialMemorySize);
        freeBlocks = new HandleList(lengthBlocks + 1);
        Handle h = new Handle(initialMemorySize, 0, false);
        freeBlocks.add(h, lengthBlocks);
    }


    /**
     * the log base two of your input
     * 
     * @param n
     *            the integer
     * @return the log value
     */
    public static int log2(int n) {
        return (int)Math.ceil((Math.log(n) / Math.log(2)));
    }


    /**
     * add method adds bytes to memory location
     * 
     * @param toBeInserted
     *            - bytes to be added to memory
     * @return the location of the memory to be used
     */
    public Handle add(byte[] toBeInserted) {
        int logSizeInput = log2(toBeInserted.length);
        while (logSizeInput >= freeBlocks.getLength()) {
            doubleSize();
        }
        Handle toUse = getFreeHandle(logSizeInput);
        if (toUse == null) {
            this.doubleSize();
            toUse = getFreeHandle(logSizeInput);
        }
        // copy string to the allocated memory
        for (int i = 0; i < toBeInserted.length; i++) {
            dataBlock[toUse.getLocationStart() + i] = toBeInserted[i];
        }
        toUse.setLength(toBeInserted.length);
        return toUse;
    }


    /**
     * This method will find free blocks of the right size
     * 
     * @param logSizeInput
     *            the size needed for the item to be
     *            added to memory
     * @return freeblocks of the needed size
     */
    private Handle getFreeHandle(int logSizeInput) {
        if (freeBlocks.gethead(logSizeInput) != null) {
            return freeBlocks.pop(logSizeInput);
        }
        else {
            // make space by splitting recursively
            int i = logSizeInput;
            while (i < freeBlocks.getLength() - 1) {
                // Find the smallest free block greater than logSize
                i++;
                if (freeBlocks.gethead(i) != null) {
                    while (i > logSizeInput) {
                        // recursively split until back to logSize
                        Handle toSplit = freeBlocks.pop(i);
                        Handle[] splitted = split(toSplit);
                        freeBlocks.add(splitted[1], i - 1);
                        freeBlocks.add(splitted[0], i - 1);
                        i--;
                    }
                    return freeBlocks.pop(i);
                }
            }
            return null;
        }
    }


    /**
     * This method will merge two adjacent and equal blocks of memory into one
     * Note: the two handles must be mergeable (see Handle.isMergable)
     * 
     * @param handle1
     *            one handle to be merged
     * @param handle2
     *            the other handle to be merged
     * @return the handle for the new object, null in the case
     *         where they cant be merged
     */
    public Handle mergeBlocks(Handle handle1, Handle handle2) {
        int length = handle1.getLength() + handle2.getLength();
        int locStart = Math.min(handle1.getLocationStart(), handle2
            .getLocationStart());
        Handle merged = new Handle(length, locStart, false);
        return merged;
    }


    /**
     * This method will split a given block of free memory into two adjacent
     * equal blocks
     * 
     * @param h
     *            the memory block to be split
     * @return the smaller memory block
     */
    public Handle[] split(Handle h) {
        Handle[] result = new Handle[2];
        result[0] = new Handle(h.getLength() / 2, h.getLocationStart(), false);
        result[1] = new Handle(h.getLength() / 2, h.getLocationStart() + h
            .getLength() / 2, false);
        return result;
    }


    /**
     * This method will find the handle with a given string
     * 
     * @param name
     *            is the name of the string being searched
     * @return the handle object for the string searched, null if the handle
     *         does not exist
     */

    /**
     * This method will double the size of our memory
     */
    private void doubleSize() {
        byte[] copy = dataBlock;
        dataBlock = new byte[copy.length * 2];
        for (int i = 0; i < copy.length; i++) {
            dataBlock[i] = copy[i];
        }
        freeBlocks.incrementSize();
        Handle newBlock = new Handle(dataBlock.length / 2, dataBlock.length / 2,
            false);
        if (freeBlocks.gethead(log2(dataBlock.length / 2)) != null) {
            Handle h1 = freeBlocks.pop(log2(dataBlock.length / 2));
            h1 = mergeBlocks(h1, newBlock);
            freeBlocks.add(h1, log2(h1.getLength()));
        }
        else {
            freeBlocks.add(newBlock, log2(dataBlock.length / 2));
        }
        System.out.println("Memory pool expanded to be " + dataBlock.length
            + " bytes.");
    }


    /**
     * deletes the string from memory and releases it for further use
     * 
     * @param h
     *            the handle for the memory to be deleted
     */
    public void remove(Handle h) {
        int logLength = log2(h.getLength());
        h.setLength(1 << logLength);
        if (freeBlocks.gethead(logLength) == null) {
            freeBlocks.add(h, logLength);
            return;
        }
        else {
            Handle currMergeCandidate = freeBlocks.gethead(logLength);
            while (currMergeCandidate != null) {
                if (h.isMergable(currMergeCandidate)) {
                    freeBlocks.remove(logLength, currMergeCandidate);
                    h = mergeBlocks(currMergeCandidate, h);
                    logLength = log2(h.getLength());
                    currMergeCandidate = freeBlocks.gethead(logLength);
                    continue;
                }
                currMergeCandidate = currMergeCandidate.getNext();
            }
            freeBlocks.add(h, logLength);
        }
    }


    /**
     * Dump a printout of the free block list
     */
    public void dump() {
        Handle curr;
        boolean isempty = true;
        for (int i = 0; i < freeBlocks.getLength(); i++) {
            curr = freeBlocks.gethead(i);
            if (curr == null) {
                continue;
            }
            System.out.print((1 << i) + ": ");
            while (curr != null) {
                isempty = false;
                System.out.print(curr.getLocationStart() + " ");
                curr = curr.getNext();
            }
            System.out.print("\n");
        }

        if (isempty) {
            System.out.println("No free blocks are available.");
        }
    }


    /**
     * gets data
     * 
     * @param h
     *            handle location of data
     * @return the datablock
     */
    public byte[] getData(Handle h) {
        return Arrays.copyOfRange(dataBlock, h.getLocationStart(), h
            .getLocationStart() + h.getLength());

    }

}
