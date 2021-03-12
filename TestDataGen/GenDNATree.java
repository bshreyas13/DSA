import java.util.Random;
import java.util.ArrayList;
import java.nio.file.Files;
import java.io.File;

public class GenDNATree {
    public static void main(String... args) throws Exception {
        for(int i=1;i<=4;i++){
        char[] chars = new char[] { 'A', 'C', 'G', 'T' };
        ArrayList<String> lines = new ArrayList<>();
        Random cRandom = new Random();
        Random lRandom = new Random();
        for (int line = 1; line <= 7000; line++) {
            int length = lRandom.nextInt(17) + 1;
            String text = "";
            for (int l = 1; l <= length; l++) {
                text += chars[cRandom.nextInt(chars.length)];
            }       
            lines.add("insert " + text);
        }
        lines.add("print");
        Files.write(new File(
            "input_"+i+".txt")
                .toPath(), lines);
    }
    }
}
