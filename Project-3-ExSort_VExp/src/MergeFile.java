import java.io.IOException;
import java.util.Arrays;

/**
 * MergeFile Object that merges the run file and stores it in another file.
 * 
 * @author bshreyas and veerad
 * @version 4/10/2021
 */
public class MergeFile {
    /**
     * The merge function
     * 
     * @param in
     *            String: name of input file
     * @param rps
     *            Array of longs: specifying the end pointers of runs.
     * @param out
     *            String: name of output file to store in.
     * @throws IOException
     */
    public static void merge(String in, long[] rps, String out)
        throws IOException {
        FileAccess mergeFiles = new FileAccess(in, out + "Out.bin");
        long[] currentPointers = Arrays.copyOf(rps, rps.length - 1);
        BlocksBuffer[] runBufs = new BlocksBuffer[currentPointers.length];
        boolean[] runsOver = new boolean[runBufs.length];
        for (int i = 0; i < currentPointers.length; i++) {
            runBufs[i] = new BlocksBuffer(1);
            runsOver[i] = false;
        }
        BlocksBuffer outBuff = new BlocksBuffer(1);
        boolean mergeOver = false;
        while (!mergeOver) {
            boolean recOnlyOut = true;
            for (int i = 0; i < runBufs.length; i++) {
                if (!runsOver[i] && runBufs[i].isEmpty()) {

                    if (rps[i + 1] - currentPointers[i] >= 512 * 16) {
                        runBufs[i].insertBlock(mergeFiles.getBlock(
                            currentPointers[i]));
                        currentPointers[i] = mergeFiles.getReadPointer();
                    }
                    else {
                        runBufs[i].insertBlock(mergeFiles.getPartialBlock(
                            currentPointers[i], rps[i + 1]));
                        currentPointers[i] = mergeFiles.getReadPointer();
                    }
                    if (currentPointers[i] == rps[i + 1]) {
                        runsOver[i] = true;
                    }

                }
            }

            int buffmin = -1;
            Record min = null;
            for (int i = 0; i < runBufs.length; i++) {
                if (!runBufs[i].isEmpty()) {
                    buffmin = i;
                    min = runBufs[i].getFirst();
                    break;
                }
            }
            if (buffmin == -1) {
                if (outBuff.isFull()) {
                    mergeFiles.writeBlock(outBuff.removeBlock());
                }
                break;
            }
            for (int i = 0; i < runBufs.length; i++) {
                if (!runBufs[i].isEmpty() && min.compareTo(runBufs[i]
                    .getFirst()) > 0) {

                    min = runBufs[i].getFirst();
                    buffmin = i;

                }
                recOnlyOut = recOnlyOut && runBufs[i].isEmpty();
            }
            outBuff.insertLastRecord(runBufs[buffmin].removeFirstRecord());
            if (outBuff.isFull()) {
                mergeFiles.writeBlock(outBuff.removeBlock());

            }

        }

        FileAccess printFile = new FileAccess(out + "Out.bin", "Empty.bin");
        int numRec = 0;
        while (printFile.getReadLength() != printFile.getReadPointer()) {
            outBuff.insertBlock(printFile.getBlock());
            System.out.print(outBuff.removeFirstRecord().toString());
            numRec += 1;
            if (numRec % 5 == 0) {
                System.out.print("\n");
            }
            else {
                System.out.print(" ");
            }
        }
    }

}
