
/**
 * Represents the BST
 * 
 * @author {shreyasb and veerad}
 * @version 2021-2-15
 */
public class BST {

    /**
     * Root node of the BST.
     */
    private Node root;

    /**
     * Searchstatus
     */
    private boolean searchStatus;

    /**
     * Default constructor
     */
    public BST() {
        root = null;
    }


    /**
     * 
     * @param node
     *            New node to be inserted
     * @return
     *         true if inserted false otherwise
     */
    public boolean insert(Node node) {
        if (searchByNode(root, node) == null) {
            root = insert(root, node);
            updateDepth(root, 0);
            return true;
        }

        return false;
    }


    /**
     * Calculate node depth
     * 
     * @param node
     *            node to calculate
     * @param depth
     *            depth of node
     */
    void updateDepth(Node node, int depth) {
        if (node != null) {
            node.setDepth(depth);
            updateDepth(node.getLeft(), depth + 1);
            updateDepth(node.getRight(), depth + 1);
        }
    }


    /**
     * Inserts a node in the BST
     * 
     * @param parent
     *            The tree node
     * @param node
     *            New node to be inserted
     * @return
     *         the root node at each iteration
     */
    private Node insert(Node parent, Node node) {
        if (parent == null) {
            node.setSize(1);
            return node;
        }
        int c = node.compareTo(parent);
        if (c < 0) {
            parent.setLeft(insert(parent.getLeft(), node));
        }
        else if (c > 0) {
            parent.setRight(insert(parent.getRight(), node));
        }
        parent.setSize((1 + (parent.getLeft() == null
            ? 0
            : parent.getLeft().getSize()) + (parent.getRight() == null
                ? 0
                : parent.getRight().getSize())));
        return parent;
    }


    public boolean remove(String key) {

        searchKey(key);
        //System.out.println(isSearchStatus());
        if (isSearchStatus() == true) {
            root = remove(root, key);
            updateDepth(root,0);
            return true;
        }

        return false;
    }


    /**
     * 
     * @param root
     * @param key
     * @return
     */

    /**
     * Remove the node from tree associated with key
     * 
     * @param node
     *            The node to be removed
     * @param key
     *            The key associated with node
     * @return
     *         The node used to traverse recursively
     */
    private Node remove(Node node, String key) {
        if (node == null) {
            return null;
        }

        int difference = key.compareTo(node.getKey());

        if (difference < 0) {
            node.setLeft(remove(node.getLeft(), key));
        }

        else if (difference > 0) {
            node.setRight(remove(node.getRight(), key));
        }

        else {
            if (node.getRight() == null) {
                return node.getLeft();
            }
            if (node.getLeft() == null) {
                return node.getRight();
            }
            Node t = node;
            node = findMinimumInRightSubTree(t.getRight());
            node.setRight(deleteMinimum(t.getRight()));
            node.setLeft(t.getLeft());
        }

        node.setSize(size(node.getLeft()) + size(node.getRight()) + 1);
        return node;
    }
   

    /**
     * While deleting the node from tree, this method will be used to identify
     * the minimum value in the right sub tree
     * 
     * @param x
     *            The node value at current iteration
     * @return
     *         Returns the left minimum node
     */
    private Node findMinimumInRightSubTree(Node x) {
        if (x.getLeft() == null) {
            return x;
        }
        else {
            return findMinimumInRightSubTree(x.getLeft());
        }
    }


    /**
     * Delete the most minimum node from current node
     * 
     * @param x
     *            The current node in the iteration
     * @return
     *         The node with update left node
     */
    private Node deleteMinimum(Node x) {
        if (x.getLeft() == null) {
            return x.getRight();
        }
        x.setLeft(deleteMinimum(x.getLeft()));
        x.setSize(size(x.getLeft()) + size(x.getRight()) + 1);
        return x;
    }

    public void searchKey(String name) {
        setSearchStatus(false);
        searchKey(root, name);
    }
    /**
     * Returns the size of the node
     * 
     * @param node
     *            The node
     * @return
     *         Size of the node
     */
    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        else {
            return node.getSize();
        }
    }
    /**
     * Associated method for
     * 
     * @param node
     *            The current node at each iteration
     * @param name
     *            name of the key
     * @return
     *         
     */
    private int searchKey(Node node, String name) {
        int count = 0 ;
        if (node == null) {
            return count;
        }
        int c = node.compareKey(name);
        if (c == 0) {
            setSearchStatus(true);
            count++;
            return count;
        }
        if (c < 0) {
            return searchKey(node.getLeft(), name);
        }
        return searchKey(node.getRight(), name);
    }


    /**
     * Associated method for {@link #searchByKey(Comparable)}
     * 
     * @param parent
     *            The current node at each iteration
     * @param child
     *            The node value to be searched
     * @return
     *         The node if found already
     */
    private Node searchByNode(Node parent, Node child) {
        if (parent == null) {
            return null;
        }
        int c = child.compareTo(parent);
        if (c == 0) {
            return parent;
        }
        if (c < 0) {
            return searchByNode(parent.getLeft(), child);
        }
        return searchByNode(parent.getRight(), child);
    }


    /**
     * search by name
     * 
     * @param name
     *            The node key
     */
    public void searchByKey(String name) {
        setSearchStatus(false);
        searchByKey(root, name);
    }


    /**
     * Associated method for
     * 
     * @param node
     *            The current node at each iteration
     * @param name
     *            name of the key
     * @return
     *         The node if found already
     */
    private Node searchByKey(Node node, String name) {
        if (node == null) {
            return null;
        }
        int c = node.compareKey(name);
        if (c == 0) {
            System.out.println(String.format("Rectangle found: %s", node));
            setSearchStatus(true);
        }
        if (c < 0) {
            return searchByKey(node.getLeft(), name);
        }
        return searchByKey(node.getRight(), name);
    }


    /**
     * Display tree in-order
     * 
     * @return
     *         status contains the path of nodes with in-order tree traversal
     */
    public boolean dump() {
        System.out.println("BST dump:");
        if (root != null) {
            inOrder(root);
        }
        else {
            System.out.println("Node has depth 0, Value (null)");
        }
        System.out.println(String.format("BST size is: %d", (root != null
            ? root.getSize()
            : 0)));
        return true;
    }


    /**
     * Utility method for in order traversal
     * 
     * @param node
     *            The start node
     * 
     */
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeft());
        System.out.println(String.format("Node has depth %d, Value %s", node
            .getDepth(), node));
        inOrder(node.getRight());
    }


    /**
     * @return the searchStatus
     */
    public boolean isSearchStatus() {
        return searchStatus;
    }


    /**
     * @param searchStatus
     *            the searchStatus to set
     */
    public void setSearchStatus(boolean searchStatus) {
        this.searchStatus = searchStatus;
    }

}
