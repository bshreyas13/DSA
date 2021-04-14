import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

import student.TestCase;

/**
 * Test Class to test Externalsort
 * 
 * @author bshreyas and veerad
 * @version 4/10/2021
 */
public class ExternalsortTest extends TestCase {

    // Constants as per specification
    private final static int BLOCK_SIZE = 8192; // bytes
    private final static int RECORD_SIZE = 16; // bytes
    private final static int RECORDS_PER_BLOCK = BLOCK_SIZE / RECORD_SIZE;
    private final static int HEAP_SIZE = 8; // blocks

    /**
     * Get code coverage of the class declaration.
     * 
     * @throws IOException
     */
    public void testExternalsortInit() throws IOException {
        Externalsort sorter = new Externalsort();
        assertNotNull(sorter);
    }


    /**
     * Tests the External sort on 16 blocks of records.
     * 
     * @throws IOException
     */
    @SuppressWarnings("unused")
    public void test16() throws IOException {
        String[] args = { "Test16", "16" };
        GenFileProject3.main(args);
        Externalsort.main(args);

        try {
            RandomAccessFile outFile = new RandomAccessFile(args[0] + "Out.bin",
                "r");
            byte[] b1 = new byte[RECORD_SIZE];
            byte[] b2 = new byte[RECORD_SIZE];
            int rec1 = outFile.read(b1);
            int rec2 = outFile.read(b2);
            outFile.seek(RECORD_SIZE);
            ByteBuffer b11 = ByteBuffer.wrap(b1);
            double val1 = b11.getDouble(HEAP_SIZE);
            ByteBuffer b12 = ByteBuffer.wrap(b2);
            double val2 = b12.getDouble(HEAP_SIZE);
            int copies = 0;
            assertTrue(val1 < val2);
            for (int i = 0; i < (Integer.parseInt(args[1])) * RECORDS_PER_BLOCK
                - 1; i++) {
                outFile.seek(i * RECORD_SIZE);
                rec1 = outFile.read(b1);
                rec2 = outFile.read(b2);
                b11 = ByteBuffer.wrap(b1);
                b12 = ByteBuffer.wrap(b2);
                val1 = b11.getDouble(HEAP_SIZE);
                val2 = b12.getDouble(HEAP_SIZE);
                assertTrue(val1 < val2);
            }
            outFile.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.flush();
    }


    /**
     * Tests the External sort on 24 blocks of records.
     * 
     * @throws IOException
     */
    @SuppressWarnings("unused")
    public void test24() throws IOException {
        String[] args = { "Test24", "24" };
        GenFileProject3.main(args);
        Externalsort.main(args);
        try {
            RandomAccessFile outFile = new RandomAccessFile(args[0] + "Out.bin",
                "r");
            byte[] b1 = new byte[RECORD_SIZE];
            byte[] b2 = new byte[RECORD_SIZE];
            int rec1 = outFile.read(b1);
            int rec2 = outFile.read(b2);
            outFile.seek(RECORD_SIZE);
            ByteBuffer b1Buffer = ByteBuffer.wrap(b1);
            double val1 = b1Buffer.getDouble(HEAP_SIZE);
            ByteBuffer b2Buffer = ByteBuffer.wrap(b2);
            double val2 = b2Buffer.getDouble(HEAP_SIZE);
            int copies = 0;
            assertTrue(val1 < val2);
            for (int i = 0; i < (Integer.parseInt(args[1])) * RECORDS_PER_BLOCK
                - 1; i++) {
                outFile.seek(i * RECORD_SIZE);
                rec1 = outFile.read(b1);
                rec2 = outFile.read(b2);
                b1Buffer = ByteBuffer.wrap(b1);
                b2Buffer = ByteBuffer.wrap(b2);
                val1 = b1Buffer.getDouble(HEAP_SIZE);
                val2 = b2Buffer.getDouble(HEAP_SIZE);
                assertTrue(val1 < val2);
            }
            outFile.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.flush();
    }


    /**
     * Tests the External sort on 32 blocks of records.
     * 
     * @throws IOException
     */
    @SuppressWarnings("unused")
    public void test32() throws IOException {
        String[] args = { "Test32", "32" };
        GenFileProject3.main(args);
        Externalsort.main(args);
        try {
            RandomAccessFile outFile = new RandomAccessFile(args[0] + "Out.bin",
                "r");
            byte[] b1 = new byte[RECORD_SIZE];
            byte[] b2 = new byte[RECORD_SIZE];
            int rec1 = outFile.read(b1);
            int rec2 = outFile.read(b2);
            outFile.seek(RECORD_SIZE);
            ByteBuffer b1Buffer = ByteBuffer.wrap(b1);
            double val1 = b1Buffer.getDouble(HEAP_SIZE);
            ByteBuffer b2Buffer = ByteBuffer.wrap(b2);
            double val2 = b2Buffer.getDouble(HEAP_SIZE);
            int copies = 0;
            assertTrue(val1 < val2);
            for (int i = 0; i < (Integer.parseInt(args[1])) * RECORDS_PER_BLOCK
                - 1; i++) {
                outFile.seek(i * RECORD_SIZE);
                rec1 = outFile.read(b1);
                rec2 = outFile.read(b2);
                b1Buffer = ByteBuffer.wrap(b1);
                b2Buffer = ByteBuffer.wrap(b2);
                val1 = b1Buffer.getDouble(HEAP_SIZE);
                val2 = b2Buffer.getDouble(HEAP_SIZE);
                assertTrue(val1 < val2);
            }
            outFile.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.flush();
    }


    /**
     * Tests the External sort on 48 blocks of records.
     * 
     * @throws IOException
     */
    @SuppressWarnings("unused")
    public void test48() throws IOException {
        String[] args = { "Test48", "48" };
        GenFileProject3.main(args);
        Externalsort.main(args);
        try {
            RandomAccessFile outFile = new RandomAccessFile(args[0] + "Out.bin",
                "r");
            byte[] b1 = new byte[RECORD_SIZE];
            byte[] b2 = new byte[RECORD_SIZE];
            int rec1 = outFile.read(b1);
            int rec2 = outFile.read(b2);
            outFile.seek(RECORD_SIZE);
            ByteBuffer b1Buffer = ByteBuffer.wrap(b1);
            double val1 = b1Buffer.getDouble(HEAP_SIZE);
            ByteBuffer b2Buffer = ByteBuffer.wrap(b2);
            double val2 = b2Buffer.getDouble(HEAP_SIZE);
            assertTrue(val1 < val2);
            int copies = 0;
            for (int i = 0; i < (Integer.parseInt(args[1])) * RECORDS_PER_BLOCK
                - 1; i++) {
                outFile.seek(i * RECORD_SIZE);
                rec1 = outFile.read(b1);
                rec2 = outFile.read(b2);
                b1Buffer = ByteBuffer.wrap(b1);
                b2Buffer = ByteBuffer.wrap(b2);
                val1 = b1Buffer.getDouble(HEAP_SIZE);
                val2 = b2Buffer.getDouble(HEAP_SIZE);
                assertTrue(val1 < val2);
            }
            outFile.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.flush();
    }


    /**
     * Tests the External sort on 500 blocks of records.
     * Test for worst case by reversing order sorted set.
     * 
     * @throws IOException
     */
    @SuppressWarnings("unused")
    public void testWorstCase500() throws IOException {
        String[] args = { "Test500", "500" };
        GenFileProject3.main(args);
        Externalsort.main(args);
        try {
            RandomAccessFile outFile = new RandomAccessFile(args[0] + "Out.bin",
                "r");
            byte[] b1 = new byte[RECORD_SIZE];
            byte[] b2 = new byte[RECORD_SIZE];
            int rec1 = outFile.read(b1);
            int rec2 = outFile.read(b2);
            outFile.seek(RECORD_SIZE);
            ByteBuffer b1Buffer = ByteBuffer.wrap(b1);
            double val1 = b1Buffer.getDouble(HEAP_SIZE);
            ByteBuffer b2Buffer = ByteBuffer.wrap(b2);
            double val2 = b2Buffer.getDouble(HEAP_SIZE);
            assertTrue(val1 < val2);
            int copies = 0;
            for (int i = 0; i < (Integer.parseInt(args[1])) * RECORDS_PER_BLOCK
                - 1; i++) {
                outFile.seek(i * RECORD_SIZE);
                rec1 = outFile.read(b1);
                rec2 = outFile.read(b2);
                b1Buffer = ByteBuffer.wrap(b1);
                b2Buffer = ByteBuffer.wrap(b2);
                val1 = b1Buffer.getDouble(HEAP_SIZE);
                val2 = b2Buffer.getDouble(HEAP_SIZE);
            }
            FileIO reverse = new FileIO(args[0] + "Out.bin", args[0]
                + "ReverseSort.bin");
            for (long i = reverse.getReadLength() - RECORD_SIZE; i >= 0; i -=
                RECORD_SIZE) {
                reverse.outRecord(reverse.getPartialBlock(i, i
                    + RECORD_SIZE)[0]);
            }
            outFile = new RandomAccessFile(args[0] + "ReverseSort.bin", "r");
            rec1 = outFile.read(b1);
            rec2 = outFile.read(b2);
            outFile.seek(RECORD_SIZE);
            b1Buffer = ByteBuffer.wrap(b1);
            val1 = b1Buffer.getDouble(HEAP_SIZE);
            b2Buffer = ByteBuffer.wrap(b2);
            val2 = b2Buffer.getDouble(HEAP_SIZE);
            assertTrue(val1 > val2);
            copies = 0;
            for (int i = 0; i < (Integer.parseInt(args[1])) * RECORDS_PER_BLOCK
                - 1; i++) {
                outFile.seek(i * RECORD_SIZE);
                rec1 = outFile.read(b1);
                rec2 = outFile.read(b2);
                b1Buffer = ByteBuffer.wrap(b1);
                b2Buffer = ByteBuffer.wrap(b2);
                val1 = b1Buffer.getDouble(HEAP_SIZE);
                val2 = b2Buffer.getDouble(HEAP_SIZE);
            }
            String[] newArg = new String[1];
            newArg[0] = args[0] + "ReverseSort.bin";
            Externalsort.main(newArg);
            outFile = new RandomAccessFile(args[0] + "ReverseSort.bin"
                + "Out.bin", "r");
            rec1 = outFile.read(b1);
            rec2 = outFile.read(b2);
            outFile.seek(RECORD_SIZE);
            b1Buffer = ByteBuffer.wrap(b1);
            val1 = b1Buffer.getDouble(HEAP_SIZE);
            b2Buffer = ByteBuffer.wrap(b2);
            val2 = b2Buffer.getDouble(HEAP_SIZE);
            assertTrue(val1 < val2);
            copies = 0;
            for (int i = 0; i < (Integer.parseInt(args[1])) * RECORDS_PER_BLOCK
                - 1; i++) {
                outFile.seek(i * RECORD_SIZE);
                rec1 = outFile.read(b1);
                rec2 = outFile.read(b2);
                b1Buffer = ByteBuffer.wrap(b1);
                b2Buffer = ByteBuffer.wrap(b2);
                val1 = b1Buffer.getDouble(HEAP_SIZE);
                val2 = b2Buffer.getDouble(HEAP_SIZE);
                assertTrue(val1 < val2);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.flush();
    }

}
