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
 * CommandProcessor class process
 * commands from input file, read each line and
 * execute commands
 * 
 * @author {shreyasb and veerad}
 * @version 2021-04-20
 *
 */

public class CommandProcessor {
    private static CommandProcessor cProcessor = null;
    private static Hash hashTable = null;
    private static MemManager memoryManager = null;

    /**
     * initializer for memory control will create instances of hashtable and
     * memory manager
     * 
     * @param initialHashSize
     *            the number of slot in the initial hash
     * @param initialMemorySize
     *            the size in bytes of memory manager
     */
    public CommandProcessor(int initialHashSize, int initialMemorySize) {
        memoryManager = new MemManager(initialMemorySize);
        hashTable = new Hash(initialHashSize);
    }


    /**
     * This will delete the current instance of CommandProcessor
     */
    public static void delete() {
        cProcessor = null;
        hashTable = null;
    }


    /**
     * this will get the current instance of CommandProcessor
     * or create a new instance of CommandProcessor
     * 
     * @param initialHashSize
     *            the number of slot in the initial hash
     * @param initialMemorySize
     *            the size in bytes of memory manager
     * @return the instance of CommandProcessor
     */
    public static CommandProcessor getInstance(
        int initialHashSize,
        int initialMemorySize) {
        if (cProcessor == null) {
            cProcessor = new CommandProcessor(initialHashSize,
                initialMemorySize);
        }
        return cProcessor;
    }


    /**
     * This Function will do the job of calling the relevant within this class
     * 
     * @param commands
     *            0th index is expected to be the function:
     *            "add","delete","update add" or "print"
     *            1st index is expected to be the name (hashtable or blocks for
     *            print)
     *            2nd index (only for update add and delete) is expected to be
     *            field
     *            3rd index (only for update add) is expected to be the value of
     *            the field
     * @return true if the command is correct, false otherwise
     */
    public boolean executeCommand(String[] commands) {
        String function = commands[0];
        switch (function) {
            case "add":
                CommandProcessor.add(commands[1]);
                return true;
            case "delete":
                CommandProcessor.delete(commands[1]);
                return true;
            case "update add":
                CommandProcessor.updateAdd(commands[1], commands[2],
                    commands[3]);
                return true;
            case "update delete":
                CommandProcessor.updateDelete(commands[1], commands[2]);
                return true;
            case "print":
                if (commands[1].equals("hashtable")) {
                    CommandProcessor.printHashTable();
                }
                else if (commands[1].equals("blocks")) {
                    CommandProcessor.printBlocks();
                }
                else {
                    System.out.println("Error: no code found");
                    return false;
                }
                return true;
            default:
                // todo: raise an exception here for invalid input
                System.out.println("Error: no code found");
                return false;
        }
    }


    /**
     * the add function will call the relevant hashtable and Memmanager
     * functions to add the name
     * 
     * @param name
     *            is the name of the string
     */
    public static void add(String name) {
        // need to add code to check for resizing
        // need to check if record already exists
        // need to add to memorymanager
        // add name to hashTable
        if (hashTable.search(name) == -1) {
            Handle h = memoryManager.add(name.getBytes());
            hashTable.add(name, h);
            System.out.println("|" + name
                + "| has been added to the Name database.");
        }
        else {
            // if record already exists
            System.out.println("|" + name
                + "| duplicates a record already in the Name database.");
        }
    }


    /**
     * deletes the name from both the hash and memory manager
     * 
     * @param name
     *            is the id to be deleted
     */
    public static void delete(String name) {
        // note: deletion needs to be implemented on memmanager
        // delete name from hashTable
        int indexHash = hashTable.search(name);
        if (indexHash == -1) {
            System.out.println("|" + name
                + "| not deleted because it does not exist"
                + " in the Name database.");
        }
        else {
            Handle h = hashTable.getHandle(indexHash);
            hashTable.delete(name);
            memoryManager.delete(h);
            // delete name from hashTable
            System.out.println("|" + name
                + "| has been deleted from the Name database.");
        }
    }


    /**
     * This will add/update the field field_name to the field_value
     * 
     * @param name
     *            the name of the object to be updated
     * @param fieldName
     *            field name to be updated/added
     * @param fieldValue
     *            value to be updated/inserted
     */
    public static void updateAdd(
        // this will only affect memManager not Hash
        String name,
        String fieldName,
        String fieldValue) {
        int indexHash = hashTable.search(name);
        if (indexHash == -1) {
            System.out.println("|" + name + "| not updated "
                + "because it does not exist in the Name database.");
        }
        else {
            Handle h = hashTable.getHandle(indexHash);
            byte[] recordByte = memoryManager.getData(h);
            Record r = new Record(recordByte);
            r.updateDelete(fieldName);
            r.updateAdd(fieldName, fieldValue);
            recordByte = r.getData();
            memoryManager.delete(h);
            Handle hNew = memoryManager.add(recordByte);
            hashTable.sethandle(hNew, indexHash);
            System.out.println("Updated Record: |" + r.getDataString() + "|");
        }

    }


    /**
     * deleting the field from the record
     * 
     * @param name
     *            name of the object
     * @param fieldName
     *            field to be deleted
     */
    public static void updateDelete(String name, String fieldName) {
        // this will only affect memManager not Hash
        // find name in hashTable and remove its field_name

        int indexHash = hashTable.search(name);
        if (indexHash == -1) {
            System.out.println("|" + name + "| not updated "
                + "because it does not exist in the Name database.");
        }
        else {
            Handle h = hashTable.getHandle(indexHash);
            byte[] recordByte = memoryManager.getData(h);
            Record r = new Record(recordByte);
            boolean deleted = r.updateDelete(fieldName);
            if (!deleted) {
                System.out.println("|" + name + "| not updated "
                    + "because the field |" + fieldName + "| does not exist");
                return;
            }
            else {
                recordByte = r.getData();
                memoryManager.delete(h);
                Handle hNew = memoryManager.add(recordByte);
                hashTable.sethandle(hNew, indexHash);
                System.out.println("Updated Record: |" + r.getDataString()
                    + "|");
            }
        }

    }


    /**
     * this will print the hashtable
     */
    public static void printHashTable() {
        // prints hashTable
        hashTable.print();
    }


    /**
     * this will print the empty memory blocks
     */
    public static void printBlocks() {
        // prints list of free blocks in memory pool
        memoryManager.dump();
    }

}
