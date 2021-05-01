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
     * This method will initialize MemManager and HashTable
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
     * This method will instantiate Command Processor
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
     * Processes the command line
     *
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
                System.out.println("Error: no code found");
                return false;
        }
    }


    /**
     * 
     * This method will add name to hash table and Memory Manager
     * 
     * @param name
     *            is the name of the string
     */
    public static void add(String name) {
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
     * This method will delete the name from hash and memory manager
     * 
     * @param name
     *            is the id to be deleted
     */
    public static void delete(String name) {
        int indexHash = hashTable.search(name);
        if (indexHash == -1) {
            System.out.println("|" + name
                + "| not deleted because it does not exist"
                + " in the Name database.");
        }
        else {
            Handle h = hashTable.getHandle(indexHash);
            hashTable.delete(name);
            memoryManager.remove(h);
            System.out.println("|" + name
                + "| has been deleted from the Name database.");
        }
    }


    /**
     * This method will add/update the field_name to the field_value
     * 
     * @param name
     *            the name of the object to be updated
     * @param fieldName
     *            field name to be updated/added
     * @param fieldValue
     *            value to be updated/inserted
     */
    public static void updateAdd(
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
            DBRecord r = new DBRecord(recordByte);
            r.updateDelete(fieldName);
            r.updateAdd(fieldName, fieldValue);
            recordByte = r.getData();
            memoryManager.remove(h);
            Handle hNew = memoryManager.add(recordByte);
            hashTable.setHandle(hNew, indexHash);
            System.out.println("Updated DBRecord: |" + r.getDataString() + "|");
        }

    }


    /**
     * This method will update and delete record
     * 
     * @param name
     *            name of the object
     * @param fieldName
     *            field to be deleted
     */
    public static void updateDelete(String name, String fieldName) {

        int indexHash = hashTable.search(name);
        if (indexHash == -1) {
            System.out.println("|" + name + "| not updated "
                + "because it does not exist in the Name database.");
        }
        else {
            Handle h = hashTable.getHandle(indexHash);
            byte[] recordByte = memoryManager.getData(h);
            DBRecord r = new DBRecord(recordByte);
            boolean deleted = r.updateDelete(fieldName);
            if (!deleted) {
                System.out.println("|" + name + "| not updated "
                    + "because the field |" + fieldName + "| does not exist");
                return;
            }
            else {
                recordByte = r.getData();
                memoryManager.remove(h);
                Handle newHandle = memoryManager.add(recordByte);
                hashTable.setHandle(newHandle, indexHash);
                System.out.println("Updated DBRecord: |" + r.getDataString()
                    + "|");
            }
        }

    }


    /**
     * This method will print hash table
     */
    public static void printHashTable() {
        hashTable.print();
    }


    /**
     * This method will print free blocks
     */
    public static void printBlocks() {
        memoryManager.dump();
    }

}
