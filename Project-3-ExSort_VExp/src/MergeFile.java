import java.io.IOException;
import java.util.Arrays;

/**
 * MergeFile Object that merges the run file and stores it in another file.
 * 
 * @author bshreyas and veerad
 * @version 4/10/2021
 */
public class MergeFile {
        // Constants as per specification
           private final static int BLOCK_SIZE = 8192; // bytes
           private final static int RECORD_SIZE = 16; // bytes

           /**
            * The merge function
            * 
            * @param in
            *            String: name of input file
            * @param endPointers
            *            Array of longs: specifying the end pointers of runs.
            * @param out
            *            String: name of output file to store in.
            * @throws IOException
            */
           public static void merge(String in, long[] endPointers, String out)
               throws IOException {
               
               FileIO mergeFiles = new FileIO(in, out + "Out.bin");
               long[] currPointers = Arrays.copyOf(endPointers, endPointers.length
                   - 1);
               Buffer[] runBuffers = new Buffer[currPointers.length];
               boolean[] runStatus = new boolean[runBuffers.length];
               for (int i = 0; i < currPointers.length; i++) {
                   runBuffers[i] = new Buffer(1);
                   runStatus[i] = false;
               }
               
               Buffer outBuffer = new Buffer(1);
               boolean mergeComplete = false;
               while (!mergeComplete) {
              
                   for (int i = 0; i < runBuffers.length; i++) {
                       if (!runStatus[i] && runBuffers[i].isEmpty()) {

                           if (endPointers[i + 1] - currPointers[i] >= BLOCK_SIZE) {
                               runBuffers[i].insertBlock(mergeFiles.getBlock(
                                   currPointers[i]));
                               currPointers[i] = mergeFiles.getReadPointer();
                           }
                           else {
                               runBuffers[i].insertBlock(mergeFiles.getPartialBlock(
                                   currPointers[i], endPointers[i + 1]));
                               currPointers[i] = mergeFiles.getReadPointer();
                           }
                           if (currPointers[i] == endPointers[i + 1]) {
                               runStatus[i] = true;
                           }

                       }
                   }

                   int bufferMinIdx = -1;
                   Record min = null;
                   for (int i = 0; i < runBuffers.length; i++) {
                       if (!runBuffers[i].isEmpty()) {
                           bufferMinIdx = i;
                           min = runBuffers[i].getFirst();
                           break;
                       }
                   }
                   if (bufferMinIdx == -1) {
                       if (outBuffer.isFull()) {
                           mergeFiles.outBlock(outBuffer.removeBlock());
                       }
                       break;
                   }
                   for (int i = 0; i < runBuffers.length; i++) {
                       if (!runBuffers[i].isEmpty() && min.compareTo(runBuffers[i]
                           .getFirst()) > 0) {

                           min = runBuffers[i].getFirst();
                           bufferMinIdx = i;

                       }
                       
                   }
                   outBuffer.insertRecEnd(runBuffers[bufferMinIdx].removeRecFront());
                   if (outBuffer.isFull()) {
                       mergeFiles.outBlock(outBuffer.removeBlock());

                   }

               }

               FileIO printFile = new FileIO(out + "Out.bin", "Empty.bin");
               int numRec = 0;
               while (printFile.getReadLength() != printFile.getReadPointer()) {
                   outBuffer.insertBlock(printFile.getCurrBlock());
                   System.out.print(outBuffer.removeRecFront().toString());
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
