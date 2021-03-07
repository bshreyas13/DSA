import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class DNAtree {

	public static void main(String[] args) {
		Tree dnaTree = new Tree();
		try {
			process(dnaTree, args[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void process(Tree dnaTree, String fileName) throws Exception {
		String line = null;
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new DataInputStream(new FileInputStream(fileName))));
		while ((line = br.readLine()) != null) {
			String[] data = line.split(" ");
			switch (data[0]) {
			case "insert":
				dnaTree.insert(new Sequence(data[1]));
				System.out.flush();
				break;
			case "remove":
				dnaTree.remove(new Sequence(data[1]));
				System.out.flush();
				break;
			case "print":
				Print.printMode = PrintMode.NONE;
				if (data.length > 1) {
					if (data[1].compareTo("lengths") == 0) {
						Print.printMode = PrintMode.LENGTH;
					} else if (data[1].compareTo("stats") == 0) {
						Print.printMode = PrintMode.STATS;
					}
				}
				dnaTree.dump();
				System.out.flush();
				break;
			case "search":
				dnaTree.search(new SearchSequence(data[1]));
				System.out.flush();
			default:
				break;
			}
		}
		br.close();

	}

	public static void sequenceAlreadyExists(Sequence s) {
		System.out.println(String.format("sequence %s already exists", s));
	}
}
