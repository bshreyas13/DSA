
/**
 * A linked list that allows adding at the
 * head or promoting a member of the list to the head.
 * 
 * This queue will always have a specified amount of objects in it.
 * Add an object to the head, the tail will be popped off and returned.
 * If there aren't enough objects in the list to satisfy this, nulls will
 * pad the list.
 * 
 * If arbitrary elements from the list are removed a null to be appended to the
 * tail of the list.
 * 
 * @author bshreyas and veerad
 * @version 02-26-2021
 *
 * @param <T>
 *            The type of the objects stored in here.
 */
public class MyLinkedList<T> {
    // The sentinel nodes
    LLNode head;
    LLNode tail;

    /**
     * Allocate a new Finite Linked Priority Queue of a given size.
     * 
     * @param size
     *            The number of nodes in the FLPQ
     */
    public MyLinkedList(int size) {
        // The sentinel nodes.
        head = new LLNode(null);
        tail = new LLNode(null);

        // Link them
        head.setNext(tail);
        tail.setPrev(head);

        // Populate the list with null entries.
        for (int i = 0; i < size; i++) {
            LLNode null_node = new LLNode(null);
            head.getNext().setPrev(null_node);
            null_node.setNext(head.getNext());
            null_node.setPrev(head);
            head.setNext(null_node);
        }
    }


    /**
     * Remove an object from the list.
     * Remove the object and append a null at the end. If no such object found,
     * simply return quietly.
     * 
     * @param data
     *            The data to remove from the list.
     */
    public void remove(T data) {
        LLNode node = head;
        while ((node = node.getNext()) != tail) {
            if (data.equals(node.getData())) {
                // Remove it
                remove_node(node);

                // Insert a null at the end.
                LLNode null_node = new LLNode(null);
                null_node.setNext(tail);
                null_node.setPrev(tail.getPrev());
                tail.getPrev().setNext(null_node);
                tail.setPrev(null_node);

                return;
            }
        }
    }


    /**
     * Insert or promote an object. The passed object will be promoted to the
     * head of the FLPQ if it is in the list (and null returned), or it will be
     * inserted at the head of the list and the tail returned.
     * 
     * @param data
     *            The data to become the head of the queue.
     * @return The tail or null.
     */
    public T insertOrPromote(T data) {
        // Always insert on null insertions.
        if (data == null)
            return insert(data);

        LLNode node = head;
        while ((node = node.getNext()) != tail) {
            if (data.equals(node.getData())) {
                promote(node);
                return null;
            }
        }

        return insert(data);
    }


    /**
     * Insert a new node at the head of the list and pop off the tail of the
     * list.
     * 
     * @param data
     *            The data to insert at the head of the queue
     * @return The data that used to be at the tail.
     */
    private T insert(T data) {
        // Allocate and insert a new node.
        LLNode node = new LLNode(data);
        node.setPrev(head);
        node.setNext(head.getNext());
        head.getNext().setPrev(node);
        head.setNext(node);

        // Remove the last element from the list.
        LLNode last = tail.getPrev();
        last.getPrev().setNext(tail);
        tail.setPrev(last.getPrev());

        // Return the data
        return last.getData();
    }


    /**
     * Take a node, and remove it from its current position in the list, and
     * move it to the head of the list.
     * 
     * @param node
     *            The node to promote
     */
    private void promote(LLNode node) {
        // Remove from the current position
        remove_node(node);

        // Insert at head.
        node.setPrev(head);
        node.setNext(head.getNext());
        head.getNext().setPrev(node);
        head.setNext(node);
    }


    /**
     * Remove a node from the list
     * 
     * @param node
     *            The node to remove
     */
    private void remove_node(LLNode node) {
        // Remove from the current position
        node.getNext().setPrev(node.getPrev());
        node.getPrev().setNext(node.getNext());
    }

    /**
     * The internal nodes in the FLPQ.
     */
    private class LLNode {
        private final T data;
        private LLNode next;
        private LLNode prev;

        /**
         * Allocate a new node.
         * 
         * @param data
         *            The data to store in the node.
         */
        public LLNode(T data) {
            this.data = data;
        }


        /**
         * Get the next node in the list.
         * 
         * @return next
         */
        public LLNode getNext() {
            return next;
        }


        /**
         * Set the next node in the list.
         * 
         * @param next
         *            The node to link to.
         */
        public void setNext(LLNode next) {
            this.next = next;
        }


        /**
         * Get the previous node in the list.
         * 
         * @return prev
         */
        public LLNode getPrev() {
            return prev;
        }


        /**
         * Set the previous node in the list
         * 
         * @param prev
         *            The node to link to.
         */
        public void setPrev(LLNode prev) {
            this.prev = prev;
        }


        /**
         * Get the data stored in this node
         * 
         * @return data
         */
        public T getData() {
            return data;
        }
    }
}
