/**
 * On my honor:
 * - I have not used source code obtained from another student,
 * or any other unauthorized source, either modified or
 * unmodified.
 * 
 * - All source code and documentation used in my program is
 * either my original work, or was derived by me from the
 * source code published in the textbook for this course.
 *
 * - I have not discussed coding details about this project with
 * anyone other than my partner (in the case of a joint
 * submission), instructor, ACM/UPE tutors or the TAs assigned
 * to this course. I understand that I may discuss the concepts
 * of this program with other students, and that another student
 * may help me debug my program so long as neither of us writes
 * anything during the discussion or modifies any computer file
 * during the discussion. I have violated neither the spirit nor
 * letter of this restriction.
 */

/**
 * Record class collects the information
 * related to each string
 * 
 * @author {shreyasb and veerad}
 * @version 2021-04-20
 *
 */
public class Record {
    private String name;
    private String fieldandValues;
    private static final String DELIMITER = "<SEP>";

    /**
     * Constructor - takes two params
     * 
     * @param name
     *            of record
     * @param f
     *            field and value part of string
     */
    public Record(String name, String f) {
        this.name = name;
        this.fieldandValues = f;
    }


    /**
     * Constructor - takes one param
     * 
     * @param name
     *            of record
     * 
     */
    public Record(String name) {
        this.name = name;

    }


    /**
     * Constructor - takes one param
     * 
     * @param data
     *            - String
     */
    public Record(byte[] data) {
        String d = new String(data);
        if (d.contains(DELIMITER)) {
            this.name = d.substring(0, d.indexOf(DELIMITER));
            this.fieldandValues = d.substring(d.indexOf(DELIMITER));
        }
        else {
            this.name = d;
            this.fieldandValues = null;
        }
    }


    /**
     * Updates the string by deleting the field and value passed in
     * 
     * @param field
     *            to be deleted
     * @return true if field is deleted, false if not
     */
    public boolean updateDelete(String field) {
        if (fieldandValues == null) {
            return false;
        }
        else if (!fieldandValues.contains(field)) {
            return false;
        }
        int i = 1;
        boolean found = false;
        String[] splitted = fieldandValues.split(DELIMITER);
        StringBuilder b = new StringBuilder();
        while (i < splitted.length - 1) {
            if (splitted[i].equals(field)) {
                // do something
                i = i + 2;
                found = true;
                continue;
            }
            b.append(DELIMITER);
            b.append(splitted[i]);
            b.append(DELIMITER);
            b.append(splitted[i + 1]);
            i = i + 2;
        }
        fieldandValues = b.toString();
        return found;
    }


    /**
     * updates string to add field and value
     * 
     * @param field
     *            identifier
     * @param value
     *            of the field
     */
    public void updateAdd(String field, String value) {
        if (fieldandValues == null) {
            this.fieldandValues = DELIMITER + field + DELIMITER + value;
            return;
        }

        int i = 1;
        boolean found = false;
        String[] splitted = fieldandValues.split(DELIMITER);
        while (i < splitted.length && !found) {
            if (splitted[i].equals(field)) {

                splitted[i + 1] = value;
                found = true;
            }
            i = i + 2;
        }

        if (!found) {
            fieldandValues = fieldandValues + DELIMITER + field + DELIMITER
                + value;
        }
        else {
            StringBuilder b = new StringBuilder();
            for (int i1 = 1; i1 < splitted.length; i1++) {
                b.append(DELIMITER);
                b.append(splitted[i1]);
            }
            fieldandValues = b.toString();
        }
    }


    /**
     * find memory for added data
     * 
     * @return name+field and value.getBytes
     */

    public byte[] getData() {
        if (fieldandValues == null) {
            return name.getBytes();
        }

        String d = name.concat(fieldandValues);
        return d.getBytes();
    }


    /**
     * Concatenates name with field and values
     * 
     * @return the concatenated string
     */
    public String getDataString() {
        if (fieldandValues == null) {
            return name;
        }

        String d = name.concat(fieldandValues);
        return d;
    }
}
