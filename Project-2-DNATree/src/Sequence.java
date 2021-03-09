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
        return (position < characters.length);
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


    /**
     * Set position
     * 
     * @param position
     *            position
     */
    public void setPosition(int position) {
        this.position = position;
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
     * get characters
     * 
     * @return char array
     */
    public char[] getCharacters() {
        return characters;
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
}
