/**
 * An interface for buffer representing a block from a file.
 * 
 * 
 * @author bshreyas and veerad
 * @version 02-26-2021
 */
public interface Buffer {
    /**
     * Read the contents of the block represented by this buffer. This will
     * return a new byte array of size() size.
     * 
     * @return A byte array with the contents of this Buffer.
     */
    public abstract byte[] read();


    /**
     * This marks the Buffer as dirty to tell it that a write needs to happen
     * when it gets flushed from memory.
     * 
     * @param data
     *            The data to write to the block represented by the buffer.
     */
    public abstract void write(byte[] data);


    /**
     * Size of the block in buffer
     * 
     * @return The size of the block.
     */
    public abstract int size();


    /**
     * Write the data to storage and free the memory that this buffer
     * represents. Any reads or writes after require disk access.
     */
    public abstract void flush();
}
