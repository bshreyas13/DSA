
public class Print {

	public static Node insertedNode;
	public static PrintMode printMode;

	public static void printInserted(Sequence s) {
		if (insertedNode != null) {
			System.out.println(String.format("sequence %s inserted at level %d", s, insertedNode.getLevel()));
		}
	}

	public static void node(Node node) {
		String spaces = "";
		for (int i = 1; i <= node.getLevel(); i++) {
			spaces += "  ";
		}
		String text = "";
		String metadata = "";
		if (node instanceof InternalNode) {
			text = "I";
		} else if (node instanceof EmptyNode) {
			text = "E";
		} else if (node instanceof LeafNode) {
			text = ((LeafNode) node).getSequence().toString();
			if (printMode == PrintMode.LENGTH) {
				metadata = String.format("%d", text.length());
			} else if (printMode == PrintMode.STATS) {
				for (char x : text.toCharArray()) {
					metadata += String.format("%c:%.2f ", x, charStat(text, x));
				}
				metadata = metadata.trim();
			}
		}
		System.out.println(String.format("%s%s %s", spaces, text, metadata));
	}

	public static void info(String info) {
		System.out.println(info);
	}

	public static void noSeqFound(Sequence sequence) {
		info(String.format("sequence %s does not exist", sequence));
	}

	public static void searchSummary(SearchSequence searchSequence) {
		info(String.format("# of nodes visited: %d", searchSequence.getNodes()));
		if (searchSequence.searchFound()) {
			for (Sequence sequence : searchSequence.getMatches()) {
				info(String.format("sequence: %s", sequence.getSequence()));
			}
		} else {
			info("no sequence found");
		}
	}

	private static float charStat(String text, char c) {
		if (text != null && text.length() > 0) {
			int count = 0;
			for (char x : text.toCharArray()) {
				if (c == x) {
					count++;
				}
			}
			return ((float) count / (float) text.length()) * 100;
		}
		return 0;
	}
}
