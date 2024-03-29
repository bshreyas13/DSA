/**
 * {Project Description Here}
 */

/**
 * The class containing the main method.
 *
 * @author {Your Name Here}
 * @version {Put Something Here}
 */

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

public class Externalsort {

    /**
     * @param argss
     *            Command line parameters
     */
    public static void main(String[] args) {
        // Make sure we have the correct number of argsuments, if not, print an
        // error message and exit.

        // Set up the argsument names to be more meaningful.
        String datafile = args[0];
        int num_buffers = 20;

        // Inform Stats of the datafile name
        // Stats.dataFileName = datafile;

        // Load up the Record Array
        RecordTemp array = null;
        try {
            array = new RecordTemp(datafile, num_buffers);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        // Perform the sort.
        sort(array);
        array.flush();



        // Print the output of the sort. The first record of every block.
        final int RECS_PER_ARRAY = 512;
        for (int i = 0; i < (array.size() / RECS_PER_ARRAY); i++) {
            // Print the first record of the i-th block.
            System.out.printf("%5d %5d ", array.getKey(i * RECS_PER_ARRAY),
                array.getValue(i * RECS_PER_ARRAY));

            if (((i + 1) % 8 == 0)) {
                System.out.print("\n");
            } // new line
        }
        System.out.print("\n"); // Final new line.
    }


    /**
     * Perform the heap sort on the record array passed in.
     * 
     * @param array
     *            The array to sort.
     */
    public static void sort(RecordTemp array) {
        int size = array.size();
        MinHeap heap = new MinHeap(array);
        for (int i = 0; i < size; i++) {
            heap.removeMax();
        }
    }
}
