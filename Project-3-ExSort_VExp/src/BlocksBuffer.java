import java.util.Arrays;

/**
 * Buffer Class that stores blocks of Records.
 * 
 * 
 * @author bshreyas and veerad
 * @version 4/10/2021
 */
 
public class BlocksBuffer {
    private Record[] block;
    private int bufferFill;

    /**
     * Constructor to initialize buffer blocks
     * 
     * @param numBlocks
     *            number of blocks to store in buffer.
     */
    public BlocksBuffer(int numBlocks) {
        bufferFill = 0;
        block = new Record[512 * numBlocks];
    }


    /**
     * Inserts block of data to this buffer.
     * 
     * @param b
     *            Block of records to be inserted.
     */
    public void insertBlock(Record[] b) {
        block = b;
        bufferFill = b.length;
    }


    /**
     * Removes a record from the end of the buffer.
     * 
     * @return
     *         Record at the end of the buffer.
     */
    public Record removeLastRecord() {
        bufferFill -= 1;
        return block[bufferFill];
    }


    /**
     * Removes from the front of the buffer
     * 
     * @return
     *         Record at the front of the buffer.
     */
    public Record removeFirstRecord() {
        Record ret;
        ret = block[0];
        for (int i = 0; i < bufferFill - 1; i++) {
            block[i] = block[i + 1];
        }
        bufferFill--;
        return ret;
    }


    /**
     * Inserts a record at the end of the buffer
     * 
     * @param r
     *            Record to be inserted.
     */
    public void insertLastRecord(Record r) {
        block[bufferFill] = r;
        bufferFill += 1;
    }


    /**
     * Removes the block of buffer data present inside.
     * 
     * @return
     *         Array of records in the buffer.
     */
    public Record[] removeBlock() {
        Record[] ret = Arrays.copyOfRange(block, 0, bufferFill);
        block = new Record[block.length];
        bufferFill = 0;
        return ret;
    }


    /**
     * Gets the first Record without removing it.
     * 
     * @return
     *         Record in the front.
     */
    public Record getFirst() {
        return block[0];
    }


    /**
     * Tells if the buffer is empty or not.
     * 
     * @return
     *         boolean: true if the buffer is empty.
     */
    public boolean isEmpty() {
        return bufferFill == 0;
    }


    /**
     * Tells if the buffer is full or not.
     * 
     * @return
     *         boolean: true if buffer is full.
     */
    public boolean isFull() {
        return bufferFill == block.length;
    }
}
