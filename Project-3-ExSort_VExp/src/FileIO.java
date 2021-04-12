import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * File Access Object that handles two files, 
 * one for read and the other for write
 * 
 * 
 * @author bshreyas and veerad
 * @version 4/10/2021
 */

public class FileIO {
    private RandomAccessFile file;
    private byte[] oneBlock;
    @SuppressWarnings("unused")
    private int endOfFile = 0;
    private RandomAccessFile runFile;

    // Constants as per specification
    private final static int BLOCK_SIZE = 8192; // bytes
    private final static int RECORD_SIZE = 16; // bytes
    private final static int RECORDS_PER_BLOCK = BLOCK_SIZE / RECORD_SIZE;

    /**
     * Constructor to initialize FileIO object.
     * 
     * @param inFileName
     *            String: name of the file that is read.
     * @param outFileName
     *            String: name of the file written out.
     * @throws FileNotFoundException
     */
    public FileIO(String inFileName, String outFileName)
        throws FileNotFoundException {
        file = new RandomAccessFile(inFileName, "r");
        oneBlock = new byte[BLOCK_SIZE];
        runFile = new RandomAccessFile(outFileName, "rw");
    }


    /**
     * Gets a block of data from the file at current pointer.
     * 
     * @return
     *         Array of Records
     * @throws IOException
     */
    public Record[] getCurrBlock() throws IOException {
        endOfFile = file.read(oneBlock);
        Record[] block = new Record[RECORDS_PER_BLOCK];
        for (int i = 0; i < RECORDS_PER_BLOCK; i++) {
            byte[] oneBlockBytes = Arrays.copyOfRange(oneBlock, i * RECORD_SIZE,
                (i * RECORD_SIZE) + RECORDS_PER_BLOCK);
            block[i] = new Record(oneBlockBytes);
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
        endOfFile = file.read(oneBlock);
        Record[] block = new Record[RECORDS_PER_BLOCK];
        for (int i = 0; i < RECORDS_PER_BLOCK; i++) {
            byte[] oneBlockBytes = Arrays.copyOfRange(oneBlock, i * RECORD_SIZE,
                (i * RECORD_SIZE) + RECORDS_PER_BLOCK);
            block[i] = new Record(oneBlockBytes);
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
        Record[] partBlock = new Record[(int)(end - pos) / RECORD_SIZE];
        endOfFile = file.read(readBytes);
        for (int i = 0; i < partBlock.length; i++) {
            partBlock[i] = new Record(Arrays.copyOfRange(readBytes, i * RECORD_SIZE, (i + 1)
                * RECORD_SIZE));
        }
        return partBlock;
    }


    /**
     * Tells if we have reached the end of file being read.
     * 
     * @return
     *         boolean: true if end of read file.
     * @throws IOException
     */
    public boolean isEndOfFile() throws IOException {
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
    public void outBlock(Record[] b) throws IOException {
        byte[] outByte = new byte[b.length * RECORD_SIZE];
        for (int i = 0; i < b.length; i++) {
            System.arraycopy(b[i].getCompleteRecord(), 0, outByte, i * RECORD_SIZE,
                RECORD_SIZE);
        }
        runFile.write(outByte);
    }


    /**
     * Writes a record to the file
     * 
     * @param b
     *            Record to be written.
     * @throws IOException
     */
    public void outRecord(Record b) throws IOException {
        runFile.write(b.getCompleteRecord());
    }


    /**
     * Gets the position of the file being written.
     * 
     * @return
     *         long: pointer of the current file location.
     * @throws IOException
     */
    public long getWritePointer() throws IOException {
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
