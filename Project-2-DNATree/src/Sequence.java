
public class Sequence {
	public static final char[] ALPHABET = { 'A', 'C', 'G', 'T' };
	private int position = 0;

	private final char[] characters;

	public Sequence(String sequenceId) {
		characters = sequenceId.toCharArray();
	}

	public char current() {
		return characters[position];
	}

	public char next() {
		if (!hasNext()) {
			return current();
		}
		return characters[position++];
	}

	public char prev() {
		if (!hasPrev()) {
			return current();
		}
		return characters[--position];
	}

	public boolean hasNext() {
		return (position < characters.length);
	}

	public boolean hasPrev() {
		return position > 0;
	}

	public int length() {
		return characters.length;
	}

	public String toString() {
		return new String(characters);
	}

	public boolean equals(Object obj) {
		if (obj instanceof Sequence) {
			return (this.toString().equals(((Sequence) obj).toString()));
		}
		return super.equals(obj);
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getSequence() {
		return new String(characters);
	}

	public char[] getCharacters() {
		return characters;
	}

	public boolean isPrefixOf(Sequence other) {
		return other.toString().startsWith(new String(characters));
	}
}
