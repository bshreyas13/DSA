
public class Print {

	public static Node insertedNode;

	public static void printInserted(Sequence s) {
		if (insertedNode != null) {
			System.out.println(String.format("sequence %s inserted at level %d", s, insertedNode.getLevel()));
		}
	}

	public static void node(String s, int level) {
		String spaces = "";
		for (int i = 1; i <= level; i++) {
			spaces += "  ";
		}
		System.out.println(String.format("%s%s", spaces, s));
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
}
