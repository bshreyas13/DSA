
/**
 * Represents the Sequence
 *
 * @author {bshreyas and veerad}
 * @version 2021-03-07
 */
public class Sequence {
    /**
     * Current position of sequence
     */
    private int position = 0;
    private int prefixLength = -1;

    /**
     * Characters to hold for this sequence
     */
    private final char[] characters;

    /**
     * constructor
     *
     * @param sequence
     *            sequence to save
     */
    public Sequence(String sequence) {
        characters = sequence.toCharArray();
    }


    /**
     * Return current character
     *
     * @return current character
     */
    public char current() {
        return characters[position];
    }


    /**
     * return next character
     *
     * @return next character
     */
    public char next() {
        if (!hasNext()) {
            return current();
        }
        return characters[position++];
    }


    /**
     * return previous character
     *
     * @return previous character
     */
    public char prev() {
        if (!hasPrev()) {
            return current();
        }
        return characters[--position];
    }


    /**
     * check sequence has any characters
     *
     * @return true if has false otherwise
     */
    public boolean hasNext() {
        if (prefixLength != -1)
            return (position < prefixLength + 2);
        return (position < characters.length);
    }


    public boolean hasNextPrefix() {
        return (position < Math.min(prefixLength-1, characters.length));
    }


    public char nextPrefix() {
        if (!hasNextPrefix()) {
            return current();
        }
        return characters[position++];
    }


    /**
     * Check has previous character
     *
     * @return true if has false otherwise
     */
    public boolean hasPrev() {
        return position > 0;
    }


    /**
     * return length
     *
     * @return length of sequence
     */
    public int length() {
        return characters.length;
    }


    /**
     * String representation
     *
     * @return String of sequence
     */
    public String toString() {
        return new String(characters);
    }


    /**
     * Check two sequence
     *
     * @param obj
     *            sequence
     * @return true if equal false otherwise
     */
    public boolean equals(Object obj) {
        if (obj instanceof Sequence) {
            return (this.toString().equals(((Sequence)obj).toString()));
        }
        return super.equals(obj);
    }


    /**
     * Return positon
     *
     * @return position
     */

    public int getPosition() {
        return position;
    }


    public void setPosition(int p) {
        this.position = p;
    }


    /**
     * get sequence
     *
     * @return String
     */
    public String getSequence() {
        return new String(characters);
    }


    /**
     * check other sequence is prefix of this sequence
     *
     * @param other
     *            other sequence
     * @return true if prefix false otherwise
     */
    public boolean isPrefixOf(Sequence other) {
        return other.toString().startsWith(new String(characters));
    }


    /**
     * get prefix
     * 
     * @return prefix lengths
     */
    public int getPrefixLength() {
        return prefixLength;
    }


    /**
     * set length
     * 
     * @param length
     *            length
     */
    public void setPrefixLength(int length) {
        this.prefixLength = length;
    }

}
