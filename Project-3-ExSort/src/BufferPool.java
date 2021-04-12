/**
 * A buffer pool that has some number of buffers to keep in memory.
 * This ensures that only a certain number of buffers are backed in memory.
 * 
 * @author bshreyas and veerad
 * @version 02-26-2021
 */
public interface BufferPool {
    /**
     * Get a handle to a Buffer that represents the block of the file
     * that is backing this BufferPool.
     * 
     * @param block
     *            index of the block we want to acquire.
     * @return A buffer handle to that index.
     */
    public abstract Buffer acquireBuffer(int block);


    /**
     * Return the number of blocks that this Buffer Pool represents.
     * 
     * @return The number of blocks that this Buffer Pool has.
     */
    public abstract int size();


    /**
     * Alert the buffer pool that its services are no longer needed and that
     * it should write out all data to the disk.
     */
    public abstract void flush();
}
