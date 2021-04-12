import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * File Access Object that handles two files, one for read and the other for
 * write
 * 
 * @author bshreyas and veerad
 * @version 4/10/2021
 */

public class FileAccess {
    private RandomAccessFile file;
    private byte[] blockBytes;
    @SuppressWarnings("unused")
    private int readPointer = 0;
    private RandomAccessFile runFile;

    /**
     * Constructor to initialize FileAccess object.
     * 
     * @param readName
     *            String: name of the read file.
     * @param writeName
     *            String: name of the write file.
     * @throws FileNotFoundException
     */
    public FileAccess(String readName, String writeName)
        throws FileNotFoundException {
        file = new RandomAccessFile(readName, "r");
        blockBytes = new byte[512 * 16];
        runFile = new RandomAccessFile(writeName, "rw");
    }


    /**
     * Gets a block of data from the file at current pointer.
     * 
     * @return
     *         Array of Records
     * @throws IOException
     */
    public Record[] getBlock() throws IOException {
        readPointer = file.read(blockBytes);
        Record[] block = new Record[512];
        for (int i = 0; i < 512; i++) {
            byte[] sixteenByte = Arrays.copyOfRange(blockBytes, i * 16, (i * 16)
                + 16);
            block[i] = new Record(sixteenByte);
        }
        return block;

    }


    /**
     * Gets a block of data from the file at given pointer.
     * 
     * @param pos
     *            pointer from where to read the file.
     * @return
     *         Array of Records.
     * @throws IOException
     */
    public Record[] getBlock(long pos) throws IOException {
        file.seek(pos);
        readPointer = file.read(blockBytes);
        Record[] block = new Record[512];
        for (int i = 0; i < 512; i++) {
            byte[] sixteenByte = Arrays.copyOfRange(blockBytes, i * 16, (i * 16)
                + 16);
            block[i] = new Record(sixteenByte);
        }
        return block;
    }


    /**
     * Returns part of a block from given start to given end position.
     * 
     * @param pos
     *            Pointer from where to start reading.
     * @param end
     *            Pointer at which to end reading.
     * @return
     *         Array of Records.
     * @throws IOException
     */
    public Record[] getPartialBlock(long pos, long end) throws IOException {
        file.seek(pos);
        byte[] readBytes = new byte[(int)(end - pos)];
        Record[] fin = new Record[(int)(end - pos) / 16];
        readPointer = file.read(readBytes);
        for (int i = 0; i < fin.length; i++) {
            fin[i] = new Record(Arrays.copyOfRange(readBytes, i * 16, (i + 1)
                * 16));
        }
        return fin;
    }


    /**
     * Tells if we have reached the end of read file.
     * 
     * @return
     *         boolean: true if end of read file.
     * @throws IOException
     */
    public boolean isEndOfReadFile() throws IOException {
        return file.getFilePointer() == file.length();
    }


    /**
     * Gets the pointer at which the read file is currently pointing.
     * 
     * @return
     *         long: current pointer of the read file.
     * @throws IOException
     */
    public long getReadPointer() throws IOException {
        return file.getFilePointer();
    }


    /**
     * Writes a block or partial block to the write file
     * 
     * @param b
     *            Array of blocks to write.
     * @throws IOException
     */
    public void writeBlock(Record[] b) throws IOException {
        byte[] writeByte = new byte[b.length * 16];
        for (int i = 0; i < b.length; i++) {
            System.arraycopy(b[i].getCompleteRecord(), 0, writeByte, i * 16,
                16);
        }
        runFile.write(writeByte);
    }


    /**
     * Writes a record to the file
     * 
     * @param b
     *            Record to be written.
     * @throws IOException
     */
    public void writeRecord(Record b) throws IOException {
        runFile.write(b.getCompleteRecord());
    }


    /**
     * Gets the position of the write file.
     * 
     * @return
     *         long: pointer of the current wrie file location.
     * @throws IOException
     */
    public long getWriteFilePointer() throws IOException {
        return runFile.getFilePointer();
    }


    /**
     * Gets the length of the read file.
     * 
     * @return
     *         long: Length of the read file.
     * @throws IOException
     */
    public long getReadLength() throws IOException {
        return file.length();
    }


    /**
     * Gets the length of the write file.
     * 
     * @return Length of the write file.
     * @throws IOException
     */
    public long getWriteLength() throws IOException {
        return runFile.length();
    }
}
