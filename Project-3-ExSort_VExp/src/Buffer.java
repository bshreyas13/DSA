import java.util.Arrays;

/**
 * Buffer Class that stores blocks of Records.
 * This class has functions to add and remove blocks from buffer
 * And to monitor buffer status
 * 
 * @author bshreyas and veerad
 * @version 4/10/2021
 */

public class Buffer {
    private Record[] block;
    private int bufferOccupied;

    // Constants as per specification
    private final static int BLOCK_SIZE = 8192; // bytes
    private final static int RECORD_SIZE = 16; // bytes
    private final static int RECORDS_PER_BLOCK = BLOCK_SIZE / RECORD_SIZE;

    /**
     * Constructor to initialize blocks in buffer pool
     * 
     * @param numBlocks
     *            number of blocks to store in buffer.
     */
    public Buffer(int numBlocks) {
        bufferOccupied = 0;
        block = new Record[RECORDS_PER_BLOCK * numBlocks];
    }


    /**
     * Inserts block of data to this buffer.
     * 
     * @param oneBlock
     *            Block of records to be inserted.
     */
    public void insertBlock(Record[] oneBlock) {
        block = oneBlock;
        bufferOccupied = oneBlock.length;
    }


    /**
     * Removes a record from the end of the buffer.
     * 
     * @return
     *         Record at the end of the buffer.
     */
    public Record removeRecEnd() {
        bufferOccupied -= 1;
        return block[bufferOccupied];
    }


    /**
     * Removes from the front of the buffer
     * 
     * @return
     *         Record at the front of the buffer.
     */

    public Record removeRecFront() {
        Record firstRecord;
        firstRecord = block[0];
        for (int i = 0; i < bufferOccupied - 1; i++) {
            block[i] = block[i + 1];
        }
        bufferOccupied--;
        return firstRecord;
    }


    /**
     * Inserts a record at the end of the buffer
     * 
     * @param r
     *            Record to be inserted.
     */
    public void insertRecEnd(Record r) {
        block[bufferOccupied] = r;
        bufferOccupied += 1;
    }


    /**
     * Removes the block of buffer data present inside.
     * 
     * @return
     *         Array of records in the buffer.
     */
    public Record[] removeBlock() {
        Record[] ret = Arrays.copyOfRange(block, 0, bufferOccupied);
        block = new Record[block.length];
        bufferOccupied = 0;
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
        return bufferOccupied == 0;
    }


    /**
     * Tells if the buffer is full or not.
     * 
     * @return
     *         boolean: true if buffer is full.
     */
    public boolean isFull() {
        return bufferOccupied == block.length;
    }
}
