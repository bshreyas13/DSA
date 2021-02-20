import java.io.File;
import java.io.FileWriter;
import org.junit.Test;

/**
 * 
 * @author {shreyasb and veerad}
 * @version 2021-2-15
 *
 */
public class ParserTest extends student.TestCase {

    private File inFile;

    /**
     * setup the test fixture
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        String commands = "\n" + "insert r_r          -1 -20 3 4\n"
            + "  insert rec        7 -8 1 3\n" + "insert virtual_rec0 1 1 0 0\n"
            + "insert virtual_REC0 0 0 11 0\n"
            + "insert inExistRec_0 1 1 -1 -2\n" + "insert RECT-1 10 10 5 6\n"
            + "\n" + " regionsearch   11 11                 0 0\n" + "\n"
            + "intersections\n" + "\n" + "dump\n" + "\n" + "search r_r\n" + "\n"
            + "remove r_r\n" + "remove 1 1 0 0\n" + "    \n"
            + "regionsearch   -5 -5 20 20 \n";
        inFile = new File("inFile.txt");
        try {
            if (inFile.exists()) {
                inFile.delete();
            }
            inFile.createNewFile();
            FileWriter fw = new FileWriter(inFile, true);
            fw.write(commands);
            fw.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * tests the input file parsing
     * 
     * @throws Exception
     *             if parsing if failed
     * 
     */
    @Test
    public void shouldTestInputParse() throws Exception {
        boolean status = Parser.parse(inFile.getAbsolutePath());
        assertTrue(status);
    }

}
