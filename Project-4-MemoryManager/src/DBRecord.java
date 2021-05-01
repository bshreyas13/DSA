
/**
 * DBRecord class collects the information
 * related to each string
 * 
 * @author {shreyasb and veerad}
 * @version 2021-04-20
 *
 */
public class DBRecord {
    private String name;
    private String fieldandValues;
    private static final String DELIMITER = "<SEP>";

    /**
     * Constructor
     * 
     * @param name
     *            of record
     * @param f
     *            field and value part of string
     */
    public DBRecord(String name, String f) {
        this.name = name;
        this.fieldandValues = f;
    }


    /**
     * Constructor
     * 
     * @param name
     *            of record
     * 
     */
    public DBRecord(String name) {
        this.name = name;

    }


    /**
     * Constructor
     * 
     * @param data
     *            - String
     */
    public DBRecord(byte[] data) {
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
     * 
     * This method will update String after deleting the field
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
     * This method will update string with field and value
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
     * 
     * This method will get memory for the added data
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
     * This method will concatenate name with field and values
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
