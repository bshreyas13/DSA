import java.io.IOException;
import java.util.LinkedList;

/**
 * Sorts Files stored in Secondary Storage
 */

/**
 * The class containing the main method.
 *
 * @author Jaineel Nandu
 * @version 20201204
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

    /**
     * Main function that sorts the given file.
     * 
     * @param args
     *            Command line parameters
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        FileIO runFiles = new FileIO(args[0], "The run file.bin");
        BlocksBuffer ipBuffer = new BlocksBuffer(1); // 1 block(s)
        BlocksBuffer opBuffer = new BlocksBuffer(1);
        LinkedList<Integer> runLengths = new LinkedList<Integer>();
        LinkedList<Long> runPointers = new LinkedList<Long>();
        MinHeap<Record> memoryHeap = new MinHeap<Record>(8 * 512);
        boolean memHeapRun = false;
        boolean lastRun = false;
        boolean lastRunDone = false;
        if (ipBuffer.isEmpty() && !runFiles.isEndOfFile()) {
            ipBuffer.insertBlock(runFiles.getCurrBlock());
        }
        while (!memoryHeap.isHeapFull()) {
            memoryHeap.insertBlock(ipBuffer.removeBlock());
            if (ipBuffer.isEmpty() && !runFiles.isEndOfFile()) {
                ipBuffer.insertBlock(runFiles.getCurrBlock());
            }
        }

        while (!lastRunDone) {
            boolean runComplete = false;
            int runLength = 0;
            long runStartPointer = runFiles.getWritePointer();
            if (!memoryHeap.isNull() && memHeapRun) {
                memoryHeap.restoreMaxSize();
                memoryHeap.buildHeap();
                memHeapRun = false;
            }

            if (lastRun) {
                while (!runComplete) {
                    Record min = memoryHeap.removeMin();
                    opBuffer.insertLastRecord(min);
                    if (opBuffer.isFull()) {
                        runLength += 512;
                        runFiles.outBlock((Record[])opBuffer.removeBlock());
                    }
                    if (memoryHeap.heapMax() == 0) {
                        runComplete = true;
                        lastRunDone = true;
                    }
                }
            }
            else {
                while (!runComplete) {

                    {
                        if (ipBuffer.isEmpty() && !runFiles.isEndOfFile()) {
                            ipBuffer.insertBlock(runFiles.getCurrBlock());
                        }
                        if (!ipBuffer.isEmpty()) {
                            Record min = memoryHeap.getMin();
                            opBuffer.insertLastRecord(min);

                            if (opBuffer.isFull()) {
                                runLength += 512;
                                runFiles.outBlock((Record[])opBuffer
                                    .removeBlock());
                            }
                            Record replacement = ipBuffer.removeLastRecord();
                            if (replacement.compareTo(min) < 0) {
                                memoryHeap.badInsert(replacement);
                            }
                            else {
                                memoryHeap.goodInsert(replacement);
                            }
                            if (memoryHeap.heapMax() == 0) {
                                runComplete = true;
                                memHeapRun = true;
                            }
                        }
                        else {
                            runComplete = true;
                            memHeapRun = true;
                            lastRun = true;
                        }
                    }

                }
            }
            while (!opBuffer.isEmpty()) {
                Record[] writeRem = opBuffer.removeBlock();
                runFiles.outBlock(writeRem);
                runLength += writeRem.length;
            }
            if (runLength > 0) {
                runLengths.add(runLength);
                runPointers.add(runStartPointer);
            }

        }
        runPointers.add(runFiles.getWritePointer());
        long[] rps = new long[runPointers.size()];
        for (int i = 0; i < rps.length; i++) {
            rps[i] = runPointers.removeFirst();
        }
        MergeFile.merge("The run file.bin", rps, args[0]);
    }

}
