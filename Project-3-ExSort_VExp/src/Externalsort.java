import java.io.IOException;
import java.util.LinkedList;

/**
 * External Sort large files when stored on disk
 */

/**
 * The class containing the main method.
 *
 * @author bshreyas and veerad
 * @version 4/10/2021
 */

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

public class Externalsort {
 // Constants as per specification
    private final static int BLOCK_SIZE = 8192; // bytes
    private final static int RECORD_SIZE = 16; // bytes
    private final static int HEAP_SIZE = 8; // bytes
    private final static int RECORDS_PER_BLOCK = BLOCK_SIZE / RECORD_SIZE;

    /**
     * Main function that sorts the given file.
     * 
     * @param args
     *            Command line parameters
     * @throws IOException
     */
    
    public static void main(String[] args) throws IOException {
        FileIO runFile = new FileIO(args[0], "runFile.bin");
        Buffer inBuffer = new Buffer(1); // 1 block input buffer
        Buffer outBuffer = new Buffer(1); // 1 block output buffer

        LinkedList<Integer> runStatList = new LinkedList<Integer>();
        LinkedList<Long> pointersList = new LinkedList<Long>();

        MinHeap<Record> sortingHeap = new MinHeap<Record>(HEAP_SIZE
            * RECORDS_PER_BLOCK);

        boolean isHeapified = false;
        boolean isFinalRun = false;
        boolean finalRunDone = false;

        if (inBuffer.isEmpty() && !runFile.isEndOfFile()) {
            inBuffer.insertBlock(runFile.getCurrBlock());
        }
        while (!sortingHeap.isHeapFull()) {
            sortingHeap.insertBlock(inBuffer.removeBlock());
            if (inBuffer.isEmpty() && !runFile.isEndOfFile()) {
                inBuffer.insertBlock(runFile.getCurrBlock());
            }
        }

        while (!finalRunDone) {
            boolean allRunsComplete = false;
            int runLength = 0;
            long runStartPointer = runFile.getWritePointer();
            if (!sortingHeap.isHeapNull() && isHeapified) {
                sortingHeap.resetMaxSize();
                sortingHeap.buildHeap();
                isHeapified = false;
            }

            if (isFinalRun) {
                while (!allRunsComplete) {
                    Record min = sortingHeap.removeMin();
<<<<<<< HEAD
                    outBuffer.insertRecordEnd(min);
                    if (outBuffer.isFull()) {
                        runLength += 512;
                        runFile.outBlock((Record[])outBuffer.removeBlock());
                    }
                    if (sortingHeap.heapMaxSize() == 0) {
                        allRunsComplete = true;
                        finalRunDone = true;
                    }
                }
            }
            else {
                while (!allRunsComplete) {

                    {
                        if (inBuffer.isEmpty() && !runFile.isEndOfFile()) {
                            inBuffer.insertBlock(runFile.getCurrBlock());
                        }
                        if (!inBuffer.isEmpty()) {
                            Record min = sortingHeap.getMinRecord();
                            outBuffer.insertRecordEnd(min);
=======
                    outBuffer.insertRecEnd(min);
                    if (outBuffer.isFull()) {
                        runLength += 512;
                        runFile.outBlock((Record[])outBuffer.removeBlock());
                    }
                    if (sortingHeap.heapMaxSize() == 0) {
                        allRunsComplete = true;
                        finalRunDone = true;
                    }
                }
            }
            else {
                while (!allRunsComplete) {

                    {
                        if (inBuffer.isEmpty() && !runFile.isEndOfFile()) {
                            inBuffer.insertBlock(runFile.getCurrBlock());
                        }
                        if (!inBuffer.isEmpty()) {
                            Record min = sortingHeap.getMinRecord();
                            outBuffer.insertRecEnd(min);
>>>>>>> branch 'master' of https://web-cat.cs.vt.edu/Web-CAT/WebObjects/Web-CAT.woa/git/StudentProject/adc6100a-0c25-4d2a-8b13-8d97011838b3

                            if (outBuffer.isFull()) {
                                runLength += 512;
                                runFile.outBlock((Record[])outBuffer
                                    .removeBlock());
                            }
                            Record replacement = inBuffer.removeRecordEnd();
                            if (replacement.compareTo(min) < 0) {
                                sortingHeap.dirtyInsert(replacement);
                            }
                            else {
                                sortingHeap.cleanInsert(replacement);
                            }
                            if (sortingHeap.heapMaxSize() == 0) {
                                allRunsComplete = true;
                                isHeapified = true;
                            }
                        }
                        else {
                            allRunsComplete = true;
                            isHeapified = true;
                            isFinalRun = true;
                        }
                    }

                }
            }
            
            while (!outBuffer.isEmpty()) {
                Record[] writeOut = outBuffer.removeBlock();
                runFile.outBlock(writeOut);
                runLength += writeOut.length;
            }
            
            if (runLength > 0) {
                runStatList.add(runLength);
                pointersList.add(runStartPointer);
            }

        }
        pointersList.add(runFile.getWritePointer());
        long[] endPointers = new long[pointersList.size()];
        for (int i = 0; i < endPointers.length; i++) {
            endPointers[i] = pointersList.removeFirst();
        }
        MergeFile.merge("runFile.bin", endPointers, args[0]);
    }

}
