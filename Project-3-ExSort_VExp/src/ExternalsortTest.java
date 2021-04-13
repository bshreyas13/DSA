import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
// import java.nio.file.Files;
// import java.nio.file.Paths;
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
        Genfile_proj3.main(args);
        Externalsort.main(args);

        try {
            RandomAccessFile outFile = new RandomAccessFile(args[0] + "Out.bin",
                "r");
            byte[] b1 = new byte[RECORD_SIZE];
            byte[] b2 = new byte[RECORD_SIZE];
            int m1 = outFile.read(b1);
            int m2 = outFile.read(b2);
            outFile.seek(RECORD_SIZE);
            ByteBuffer b11 = ByteBuffer.wrap(b1);
            double l1 = b11.getDouble(HEAP_SIZE);
            ByteBuffer b12 = ByteBuffer.wrap(b2);
            double l2 = b12.getDouble(HEAP_SIZE);
            int copies = 0;
            assertTrue(l1 < l2);
            for (int i = 0; i < (Integer.parseInt(args[1])) * RECORDS_PER_BLOCK
                - 1; i++) {
                outFile.seek(i * RECORD_SIZE);
                m1 = outFile.read(b1);
                m2 = outFile.read(b2);
                b11 = ByteBuffer.wrap(b1);
                b12 = ByteBuffer.wrap(b2);
                l1 = b11.getDouble(HEAP_SIZE);
                l2 = b12.getDouble(HEAP_SIZE);
                assertTrue(l1 < l2);
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
        Genfile_proj3.main(args);
        Externalsort.main(args);
        try {
            RandomAccessFile outFile = new RandomAccessFile(args[0] + "Out.bin",
                "r");
            byte[] b1 = new byte[RECORD_SIZE];
            byte[] b2 = new byte[RECORD_SIZE];
            int m1 = outFile.read(b1);
            int m2 = outFile.read(b2);
            outFile.seek(RECORD_SIZE);
            ByteBuffer b1Buffer = ByteBuffer.wrap(b1);
            double l1 = b1Buffer.getDouble(HEAP_SIZE);
            ByteBuffer b2Buffer = ByteBuffer.wrap(b2);
            double l2 = b2Buffer.getDouble(HEAP_SIZE);
            int copies = 0;
            assertTrue(l1 < l2);
            for (int i = 0; i < (Integer.parseInt(args[1])) * RECORDS_PER_BLOCK
                - 1; i++) {
                outFile.seek(i * RECORD_SIZE);
                m1 = outFile.read(b1);
                m2 = outFile.read(b2);
                b1Buffer = ByteBuffer.wrap(b1);
                b2Buffer = ByteBuffer.wrap(b2);
                l1 = b1Buffer.getDouble(HEAP_SIZE);
                l2 = b2Buffer.getDouble(HEAP_SIZE);
                assertTrue(l1 < l2);
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
        Genfile_proj3.main(args);
        Externalsort.main(args);
        try {
            RandomAccessFile outFile = new RandomAccessFile(args[0] + "Out.bin",
                "r");
            byte[] b1 = new byte[RECORD_SIZE];
            byte[] b2 = new byte[RECORD_SIZE];
            int m1 = outFile.read(b1);
            int m2 = outFile.read(b2);
            outFile.seek(RECORD_SIZE);
            ByteBuffer b1Buffer = ByteBuffer.wrap(b1);
            double l1 = b1Buffer.getDouble(HEAP_SIZE);
            ByteBuffer b2Buffer = ByteBuffer.wrap(b2);
            double l2 = b2Buffer.getDouble(HEAP_SIZE);
            int copies = 0;
            assertTrue(l1 < l2);
            for (int i = 0; i < (Integer.parseInt(args[1])) * RECORDS_PER_BLOCK
                - 1; i++) {
                outFile.seek(i * RECORD_SIZE);
                m1 = outFile.read(b1);
                m2 = outFile.read(b2);
                b1Buffer = ByteBuffer.wrap(b1);
                b2Buffer = ByteBuffer.wrap(b2);
                l1 = b1Buffer.getDouble(HEAP_SIZE);
                l2 = b2Buffer.getDouble(HEAP_SIZE);
                assertTrue(l1 < l2);
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
        Genfile_proj3.main(args);
        Externalsort.main(args);
        try {
            RandomAccessFile outFile = new RandomAccessFile(args[0] + "Out.bin",
                "r");
            byte[] b1 = new byte[RECORD_SIZE];
            byte[] b2 = new byte[RECORD_SIZE];
            int m1 = outFile.read(b1);
            int m2 = outFile.read(b2);
            outFile.seek(RECORD_SIZE);
            ByteBuffer b1Buffer = ByteBuffer.wrap(b1);
            double l1 = b1Buffer.getDouble(HEAP_SIZE);
            ByteBuffer b2Buffer = ByteBuffer.wrap(b2);
            double l2 = b2Buffer.getDouble(HEAP_SIZE);
            assertTrue(l1 < l2);
            int copies = 0;
            for (int i = 0; i < (Integer.parseInt(args[1])) * RECORDS_PER_BLOCK
                - 1; i++) {
                outFile.seek(i * RECORD_SIZE);
                m1 = outFile.read(b1);
                m2 = outFile.read(b2);
                b1Buffer = ByteBuffer.wrap(b1);
                b2Buffer = ByteBuffer.wrap(b2);
                l1 = b1Buffer.getDouble(HEAP_SIZE);
                l2 = b2Buffer.getDouble(HEAP_SIZE);
                assertTrue(l1 < l2);
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
        Genfile_proj3.main(args);
        Externalsort.main(args);
        try {
            RandomAccessFile outFile = new RandomAccessFile(args[0] + "Out.bin",
                "r");
            byte[] b1 = new byte[RECORD_SIZE];
            byte[] b2 = new byte[RECORD_SIZE];
            int m1 = outFile.read(b1);
            int m2 = outFile.read(b2);
            outFile.seek(RECORD_SIZE);
            ByteBuffer b1Buffer = ByteBuffer.wrap(b1);
            double l1 = b1Buffer.getDouble(HEAP_SIZE);
            ByteBuffer b2Buffer = ByteBuffer.wrap(b2);
            double l2 = b2Buffer.getDouble(HEAP_SIZE);
            assertTrue(l1 < l2);
            int copies = 0;
            for (int i = 0; i < (Integer.parseInt(args[1])) * RECORDS_PER_BLOCK
                - 1; i++) {
                outFile.seek(i * RECORD_SIZE);
                m1 = outFile.read(b1);
                m2 = outFile.read(b2);
                b1Buffer = ByteBuffer.wrap(b1);
                b2Buffer = ByteBuffer.wrap(b2);
                l1 = b1Buffer.getDouble(HEAP_SIZE);
                l2 = b2Buffer.getDouble(HEAP_SIZE);
            }
            FileIO reverse = new FileIO(args[0] + "Out.bin", args[0]
                + "ReverseSort.bin");
            for (long i = reverse.getReadLength() - RECORD_SIZE; i >= 0; i -=
                RECORD_SIZE) {
                reverse.outRecord(reverse.getPartialBlock(i, i
                    + RECORD_SIZE)[0]);
            }
            outFile = new RandomAccessFile(args[0] + "ReverseSort.bin", "r");
            m1 = outFile.read(b1);
            m2 = outFile.read(b2);
            outFile.seek(RECORD_SIZE);
            b1Buffer = ByteBuffer.wrap(b1);
            l1 = b1Buffer.getDouble(HEAP_SIZE);
            b2Buffer = ByteBuffer.wrap(b2);
            l2 = b2Buffer.getDouble(HEAP_SIZE);
            assertTrue(l1 > l2);
            copies = 0;
            for (int i = 0; i < (Integer.parseInt(args[1])) * RECORDS_PER_BLOCK
                - 1; i++) {
                outFile.seek(i * RECORD_SIZE);
                m1 = outFile.read(b1);
                m2 = outFile.read(b2);
                b1Buffer = ByteBuffer.wrap(b1);
                b2Buffer = ByteBuffer.wrap(b2);
                l1 = b1Buffer.getDouble(HEAP_SIZE);
                l2 = b2Buffer.getDouble(HEAP_SIZE);
            }
            String[] newArg = new String[1];
            newArg[0] = args[0] + "ReverseSort.bin";
            Externalsort.main(newArg);
            outFile = new RandomAccessFile(args[0] + "ReverseSort.bin"
                + "Out.bin", "r");
            m1 = outFile.read(b1);
            m2 = outFile.read(b2);
            outFile.seek(RECORD_SIZE);
            b1Buffer = ByteBuffer.wrap(b1);
            l1 = b1Buffer.getDouble(HEAP_SIZE);
            b2Buffer = ByteBuffer.wrap(b2);
            l2 = b2Buffer.getDouble(HEAP_SIZE);
            assertTrue(l1 < l2);
            copies = 0;
            for (int i = 0; i < (Integer.parseInt(args[1])) * RECORDS_PER_BLOCK
                - 1; i++) {
                outFile.seek(i * RECORD_SIZE);
                m1 = outFile.read(b1);
                m2 = outFile.read(b2);
                b1Buffer = ByteBuffer.wrap(b1);
                b2Buffer = ByteBuffer.wrap(b2);
                l1 = b1Buffer.getDouble(HEAP_SIZE);
                l2 = b2Buffer.getDouble(HEAP_SIZE);
                assertTrue(l1 < l2);
            }
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.flush();
    }

}
