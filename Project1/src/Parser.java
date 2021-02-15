
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.*;

/**
 * 
 * @author {shreyasb and veerad}
 *
 */
public class Parser {

	private static BST<String, Rect> bst = new BST<String, Rect>();
	private static File outFile;

	/**
	 * 
	 * @param path to the command-file (input)
	 */

	public static void parse(String path) throws Exception {
		System.out.println("Creating output file");
		outFile = new File("result.txt");
		if (outFile.exists())
			outFile.delete();
		outFile.createNewFile();
		System.out.println(String.format("Output file path is  %s", outFile.getAbsolutePath()));

		System.out.println(String.format("Processing input file %s", path));
		File f = new File(path);
		Scanner sc = new Scanner(f);
		while (sc.hasNextLine()) {
			String line = sc.nextLine().trim().replaceAll(" +", " ");
			if (!line.isEmpty()) {
				System.out.println(String.format("Given Command %s", line));
				processCommand(line);
			}
		}

	}

	private static void processCommand(String line) {
		String[] cps = line.split(" ");
		if (cps != null && cps.length > 0) {
			switch (cps[0]) {
			case "insert":
				processInsert(cps);
				break;
			case "regionsearch":
				processRegionSearch(cps);
				break;
			default:
				System.out.println("Invalid command");
			}
		}
	}

	private static void processRegionSearch(String[] cps) {
		String output = new String();
		try {
			System.out.println("Processing regionsearch command ...");
			Rect rect = getRect(Integer.parseInt(cps[2]), Integer.parseInt(cps[3]), Integer.parseInt(cps[4]),
					Integer.parseInt(cps[5]));
			bst.searchForRegion(rect);
		} catch (Exception ex) {
			output = ex.getMessage();
		}
		writeToOutput(output);

	}

	private static void processInsert(String[] cps) {
		String output = new String();
		try {
			System.out.println("Processing insert command ...");
			String name = cps[1];
			Rect rect = getRect(Integer.parseInt(cps[2]), Integer.parseInt(cps[3]), Integer.parseInt(cps[4]),
					Integer.parseInt(cps[5]));
			bst.insert(name, rect);
			output = String.format("Rectangle accepted: (%s, %s, %s, %s, %s)", name, rect.x, rect.y, rect.width,
					rect.height);
		} catch (Exception ex) {
			output = ex.getMessage();
		}
		writeToOutput(output);
	}

	private static Rect getRect(int x, int y, int w, int h) {
		return new Rect(x, y, w, h);
	}

	private static void writeToOutput(final String msg) {
		try {
			FileWriter fw = new FileWriter(outFile, true);
			fw.write(String.format("%s%s", msg, System.lineSeparator()));
			fw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
