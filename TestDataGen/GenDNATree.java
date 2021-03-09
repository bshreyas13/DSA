import java.util.Random;
import java.util.HashSet;
import java.nio.file.Files;
import java.io.File;

public class GenDNATree {
    public static void main(String... args) throws Exception {
        char[] chars = new char[] { 'A', 'C', 'G', 'T' };
        HashSet<String> lines = new HashSet<>();
        Random cRandom = new Random();
        Random lRandom = new Random();
        for (int line = 1; line <= 12948; line++) {
            int length = lRandom.nextInt(17) + 1;
            String text = "";
            for (int l = 1; l <= length; l++) {
                text += chars[cRandom.nextInt(chars.length)];
            }
            
            lines.add("insert " + text);
        }
        Files.write(new File(
            "input.txt")
                .toPath(), lines);
    }
}
