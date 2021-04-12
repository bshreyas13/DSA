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
     * Tests the External sort on 16 blocks of random records.
     * 
     * @throws IOException
     */
    @SuppressWarnings("unused")
    public void testRandom16() throws IOException {
        String[] args = { "Random16", "16" };
        Genfile_proj3.main(args);
        Externalsort.main(args);
        RandomAccessFile f2 = new RandomAccessFile(args[0] + "Out.bin", "r");
        byte[] b1 = new byte[16];
        byte[] b2 = new byte[16];
        int m1 = f2.read(b1);
        int m2 = f2.read(b2);
        f2.seek(16);
        ByteBuffer b11 = ByteBuffer.wrap(b1);
        double l1 = b11.getDouble(8);
        ByteBuffer b12 = ByteBuffer.wrap(b2);
        double l2 = b12.getDouble(8);
        int copies = 0;
        assertTrue(l1 < l2);
        for (int i = 0; i < (Integer.parseInt(args[1])) * 512 - 1; i++) {
            f2.seek(i * 16);
            m1 = f2.read(b1);
            m2 = f2.read(b2);
            b11 = ByteBuffer.wrap(b1);
            b12 = ByteBuffer.wrap(b2);
            l1 = b11.getDouble(8);
            l2 = b12.getDouble(8);
            assertTrue(l1 < l2);
        }
        System.out.flush();
    }


    /**
     * Tests the External sort on 24 blocks of random records.
     * 
     * @throws IOException
     */
    @SuppressWarnings("unused")
    public void testRandom24() throws IOException {
        String[] args = { "Random24", "24" };
        Genfile_proj3.main(args);
        Externalsort.main(args);
        RandomAccessFile f2 = new RandomAccessFile(args[0] + "Out.bin", "r");
        byte[] b1 = new byte[16];
        byte[] b2 = new byte[16];
        int m1 = f2.read(b1);
        int m2 = f2.read(b2);
        f2.seek(16);
        ByteBuffer b11 = ByteBuffer.wrap(b1);
        double l1 = b11.getDouble(8);
        ByteBuffer b12 = ByteBuffer.wrap(b2);
        double l2 = b12.getDouble(8);
        int copies = 0;
        assertTrue(l1 < l2);
        for (int i = 0; i < (Integer.parseInt(args[1])) * 512 - 1; i++) {
            f2.seek(i * 16);
            m1 = f2.read(b1);
            m2 = f2.read(b2);
            b11 = ByteBuffer.wrap(b1);
            b12 = ByteBuffer.wrap(b2);
            l1 = b11.getDouble(8);
            l2 = b12.getDouble(8);
            assertTrue(l1 < l2);
        }
        System.out.flush();
    }


    /**
     * Tests the External sort on 32 blocks of random records.
     * 
     * @throws IOException
     */
    @SuppressWarnings("unused")
    public void testRandom32() throws IOException {
        String[] args = { "Random32", "32" };
        Genfile_proj3.main(args);
        Externalsort.main(args);
        RandomAccessFile f2 = new RandomAccessFile(args[0] + "Out.bin", "r");
        byte[] b1 = new byte[16];
        byte[] b2 = new byte[16];
        int m1 = f2.read(b1);
        int m2 = f2.read(b2);
        f2.seek(16);
        ByteBuffer b11 = ByteBuffer.wrap(b1);
        double l1 = b11.getDouble(8);
        ByteBuffer b12 = ByteBuffer.wrap(b2);
        double l2 = b12.getDouble(8);
        assertTrue(l1 < l2);
        int copies = 0;
        for (int i = 0; i < (Integer.parseInt(args[1])) * 512 - 1; i++) {
            f2.seek(i * 16);
            m1 = f2.read(b1);
            m2 = f2.read(b2);
            b11 = ByteBuffer.wrap(b1);
            b12 = ByteBuffer.wrap(b2);
            l1 = b11.getDouble(8);
            l2 = b12.getDouble(8);
            assertTrue(l1 < l2);
        }
        System.out.flush();
    }


    /**
     * Tests the External sort on 48 blocks of random records.
     * 
     * @throws IOException
     */
    @SuppressWarnings("unused")
    public void testRandom48() throws IOException {
        String[] args = { "Random48", "48" };
        Genfile_proj3.main(args);
        Externalsort.main(args);
        RandomAccessFile f2 = new RandomAccessFile(args[0] + "Out.bin", "r");
        byte[] b1 = new byte[16];
        byte[] b2 = new byte[16];
        int m1 = f2.read(b1);
        int m2 = f2.read(b2);
        f2.seek(16);
        ByteBuffer b11 = ByteBuffer.wrap(b1);
        double l1 = b11.getDouble(8);
        ByteBuffer b12 = ByteBuffer.wrap(b2);
        double l2 = b12.getDouble(8);
        assertTrue(l1 < l2);
        int copies = 0;
        for (int i = 0; i < (Integer.parseInt(args[1])) * 512 - 1; i++) {
            f2.seek(i * 16);
            m1 = f2.read(b1);
            m2 = f2.read(b2);
            b11 = ByteBuffer.wrap(b1);
            b12 = ByteBuffer.wrap(b2);
            l1 = b11.getDouble(8);
            l2 = b12.getDouble(8);
            assertTrue(l1 < l2);
        }
        System.out.flush();
    }


    /**
     * Tests the External sort on 500 blocks of random records.
     * Reverses the sort to test the worst case as well.
     * 
     * @throws IOException
     */
    @SuppressWarnings("unused")
    public void testRandomAndWorstCase500() throws IOException {
        String[] args = { "Random500", "500" };
        Genfile_proj3.main(args);
        Externalsort.main(args);
        RandomAccessFile f2 = new RandomAccessFile(args[0] + "Out.bin", "r");
        byte[] b1 = new byte[16];
        byte[] b2 = new byte[16];
        int m1 = f2.read(b1);
        int m2 = f2.read(b2);
        f2.seek(16);
        ByteBuffer b11 = ByteBuffer.wrap(b1);
        double l1 = b11.getDouble(8);
        ByteBuffer b12 = ByteBuffer.wrap(b2);
        double l2 = b12.getDouble(8);
        assertTrue(l1 < l2);
        int copies = 0;
        for (int i = 0; i < (Integer.parseInt(args[1])) * 512 - 1; i++) {
            f2.seek(i * 16);
            m1 = f2.read(b1);
            m2 = f2.read(b2);
            b11 = ByteBuffer.wrap(b1);
            b12 = ByteBuffer.wrap(b2);
            l1 = b11.getDouble(8);
            l2 = b12.getDouble(8);
        }
        FileAccess reverse = new FileAccess(args[0] + "Out.bin", args[0]
            + "ReverseSort.bin");
        for (long i = reverse.getReadLength() - 16; i >= 0; i -= 16) {
            reverse.writeRecord(reverse.getPartialBlock(i, i + 16)[0]);
        }
        f2 = new RandomAccessFile(args[0] + "ReverseSort.bin", "r");
        m1 = f2.read(b1);
        m2 = f2.read(b2);
        f2.seek(16);
        b11 = ByteBuffer.wrap(b1);
        l1 = b11.getDouble(8);
        b12 = ByteBuffer.wrap(b2);
        l2 = b12.getDouble(8);
        assertTrue(l1 > l2);
        copies = 0;
        for (int i = 0; i < (Integer.parseInt(args[1])) * 512 - 1; i++) {
            f2.seek(i * 16);
            m1 = f2.read(b1);
            m2 = f2.read(b2);
            b11 = ByteBuffer.wrap(b1);
            b12 = ByteBuffer.wrap(b2);
            l1 = b11.getDouble(8);
            l2 = b12.getDouble(8);
        }
        String[] newArg = new String[1];
        newArg[0] = args[0] + "ReverseSort.bin";
        Externalsort.main(newArg);
        f2 = new RandomAccessFile(args[0] + "ReverseSort.bin" + "Out.bin", "r");
        m1 = f2.read(b1);
        m2 = f2.read(b2);
        f2.seek(16);
        b11 = ByteBuffer.wrap(b1);
        l1 = b11.getDouble(8);
        b12 = ByteBuffer.wrap(b2);
        l2 = b12.getDouble(8);
        assertTrue(l1 < l2);
        copies = 0;
        for (int i = 0; i < (Integer.parseInt(args[1])) * 512 - 1; i++) {
            f2.seek(i * 16);
            m1 = f2.read(b1);
            m2 = f2.read(b2);
            b11 = ByteBuffer.wrap(b1);
            b12 = ByteBuffer.wrap(b2);
            l1 = b11.getDouble(8);
            l2 = b12.getDouble(8);
            assertTrue(l1 < l2);
        }
        System.out.flush();
    }

}
