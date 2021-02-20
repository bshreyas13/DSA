
/**
 * Represents the BST
 * 
 * @author {shreyasb and veerad}
 * @version 2021-2-15
 * @param <V>
 *            Value type
 */
public class BST<V extends Shape> {

    /**
     * Root node of the BST.
     */
    private Node<V> root;

    /**
     * Searchstatus
     */
    private boolean searchStatus;

    /**
     * Key for given value
     */
    private String searchKey;

    /**
     * Value for given key
     */
    private MyList<V> searchValues;

    /**
     * Default constructor
     */
    public BST() {
        root = null;
    }


    /**
     * Calculate node depth
     * 
     * @param node
     *            node to calculate
     * @param depth
     *            depth of node
     */
    void updateDepth(Node<V> node, int depth) {
        if (node != null) {
            node.setDepth(depth);
            updateDepth(node.getLeft(), depth + 1);
            updateDepth(node.getRight(), depth + 1);
        }
    }


    /**
     * 
     * @param node
     *            New node to be inserted
     * @return
     *         true if inserted false otherwise
     */
    public boolean insert(Node<V> node) {
        if (node != null && node.isValid()) {
            MyList<V> val = searchValue(node.getKey());
            if (isNodeFound(root, node)) {
                root = insert(root, node);
                updateDepth(root, 0);
                return true;
            }
            System.out.println(val.length(val));
            for (int i = 0; i < val.length(val); i++) {
                V value = val.get(i);
                if (value == null || !value.isShapeEquals(node.getValue())) {
                    root = insert(root, node);
                    return true;
                }

            }

        }
        return false;
    }


    /**
     * Checks if Value exists
     * 
     * @param value
     */
    private MyList<V> searchValue(String key) {
        setSearchStatus(false);
        inOrderTraverse(root, key);
        MyList<V> values = isSearchValue();
        // System.out.println(isSearchKey());
        return values;
    }


    /**
     * Utility method for in order traversal
     * 
     * @param node
     *            The start node
     * 
     */
    private V inOrderTraverse(Node<V> node, String key) {
        if (node == null) {
            return null;
        }
        inOrderTraverse(node.getLeft(), key);
        int c = node.getKey().compareTo(key);
        if (c == 0) {
            // compare values here
            // saveSearchValue(node.getValue());
            // System.out.println(node.getValue());
            return node.getValue();
        }
        inOrderTraverse(node.getRight(), key);
        return null;
    }


    /**
     * @return the searchKey
     */
    public String isSearchKey() {
        return searchKey;
    }


    /**
     * ignore this
     * 
     * @return the searchValue
     */
    public MyList<V> isSearchValue() {
        return searchValues;
    }


    /**
     * ignore this
     * 
     * @param searchKey
     *            Key corresponding to value
     * 
     */
    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }


    /**
     * 
     * @param searchValue
     *            Value corresponding to key
     * 
     */
    public void saveSearchValue(V searchValue) {
        if (searchValues != null) {
            searchValues.add(searchValue);
        }
    }


    /**
     * 
     * verify node is found
     * 
     * @param node
     *            New node to be inserted
     * @param rootNode
     *            root node
     * 
     * @return
     *         true if inserted false otherwise
     */
    public boolean isNodeFound(Node<V> rootNode, Node<V> node) {
        if (node == null || !node.isValid()) {
            return false;
        }
        if (rootNode == null) {
            return true;
        }
        if (rootNode.getKey().equals(node.getKey()) && rootNode.getValue()
            .isShapeEquals(node.getValue())) {
            return false;
        }
        return isNodeFound(node.getLeft(), node) && isNodeFound(node.getRight(),
            node);
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
    private Node<V> insert(Node<V> parent, Node<V> node) {
        if (parent == null) {
            if (node.isValid()) {
                node.setSize(1);
                return node;
            }
            else {
                return null;
            }
        }

        int difference = node.getKey().compareTo(parent.getKey());

        if (difference <= 0) {
            parent.setLeft(insert(parent.getLeft(), node));
        }
        else if (difference > 0) {
            parent.setRight(insert(parent.getRight(), node));
        }
        parent.setSize(1 + size(parent.getLeft()) + size(parent.getRight()));

        return parent;
    }


    /**
     * Remove the node from the BST
     * 
     * @param key
     *            Key used to remove node from tree
     */
    public void remove(String key) {
        // V value = searchByKey(key);
        // while (value != null) {
        root = remove(root, key);
        updateDepth(root, 0);
        // value = searchByKey(key);
        // }
    }


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
    private Node<V> remove(Node<V> node, String key) {
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
            // System.out.println("Removed:" + node);
            if (node.getRight() == null) {
                return node.getLeft();
            }
            if (node.getLeft() == null) {
                return node.getRight();
            }
            Node<V> t = node;

            node = findMinimumInRightSubTree(t.getRight());
            node.setRight(deleteMinimum(t.getRight()));
            node.setLeft(t.getLeft());
        }

        node.setSize(size(node.getLeft()) + size(node.getRight()) + 1);
        return node;
    }


    /**
     * Search BST for specific rectangle
     * 
     * @param value
     *            The rectangle used to search the for region match
     * @return
     *         String contains matched information
     */
    public String searchByRegion(V value) {
        StringBuilder sb = new StringBuilder();
        inOrderForIntersection(root, value, sb);
        return (sb.toString());
    }


    /**
     * Iterate tree to search for intersections
     * 
     * @param node
     *            The node to begin search
     * @param value
     *            The value used to search intersection
     * @param sb
     *            contains the information of the intersections
     */
    private void inOrderForIntersection(
        Node<V> node,
        V value,
        StringBuilder sb) {
        if (node == null) {
            return;
        }
        inOrderForIntersection(node.getLeft(), value, sb);
        if (node.getValue().shapeIntersects(value) && !sb.toString().contains(
            node.toString())) {
            sb.append(String.format("%s"+"\n", node));
        }
        inOrderForIntersection(node.getRight(), value, sb);
    }


    /**
     * Search for intersections in BST
     * 
     * @return
     *         String containing the information of intersections
     */
    public String searchForIntersections() {
        StringBuilder sb = new StringBuilder();
        traverseEachNode(root, sb);
        return sb.toString();
    }


    /**
     * This methods used for search intersections
     * 
     * @param node
     *            Current node in traversal
     * @param sb
     *            Contains the information after each iteration
     */
    private void traverseEachNode(Node<V> node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        traverseEachNode(node.getLeft(), sb);
        inOrderForIntersection(node, node.getValue(), sb);
        traverseEachNode(node.getRight(), sb);
    }


    /**
     * Traverse tree with and search with given key
     * 
     * @param key
     *            The key name
     * @return
     * 
     *         The value found
     */
    public V search(String key) {
        return inOrderForSearchByKey(root, key);
    }


    /**
     * Associated method for {@link #searchByKey(Comparable)}
     * 
     * @param node
     *            The current node at each iteration
     * @param key
     *            The key value
     * @return
     *         The value associated with key
     */
    private V inOrderForSearchByKey(Node<V> node, String key) {
        if (node == null) {
            return null;
        }
        inOrderForSearchByKey(node.getLeft(), key);
        if (node.getKey().equals(key)) {
            System.out.println(String.format("Rectangle found: %s", node));
            setSearchStatus(true);
            if (node.getRight() != null && node.getRight().getKey().equals(key))
                return node.getValue();
        }
        int dif = key.compareTo(node.getKey());
        if (dif < 0) {
            return inOrderForSearchByKey(node.getLeft(), key);
        }
        else {
            return inOrderForSearchByKey(node.getRight(), key);
        }

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


    /**
     * Remove the values by matched Shape
     * 
     * @param shape
     *            The shape value
     */
    public void removeAllByShape(Shape shape) {
        inOrderForShape(root, shape);
    }


    /**
     * Associated method to {@link #removeAllByShape(Shape)}
     * 
     * @param node
     *            The node at current iteration
     * @param shape
     *            If this value matches, the node will be removed.
     */
    private void inOrderForShape(Node<V> node, Shape shape) {
        if (node == null) {
            return;
        }
        inOrderForShape(node.getLeft(), shape);
        if (node.getValue().isShapeEquals(shape)) {
            remove(node.getKey());
        }
        inOrderForShape(node.getRight(), shape);
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
    private Node<V> findMinimumInRightSubTree(Node<V> x) {
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
    private Node<V> deleteMinimum(Node<V> x) {
        if (x.getLeft() == null) {
            return x.getRight();
        }
        x.setLeft(deleteMinimum(x.getLeft()));
        x.setSize(size(x.getLeft()) + size(x.getRight()) + 1);
        return x;
    }


    /**
     * Returns the size of the node
     * 
     * @param node
     *            The node
     * @return
     *         Size of the node
     */
    private int size(Node<V> node) {
        if (node == null) {
            return 0;
        }
        else {
            return node.getSize();
        }
    }


    /**
     * Traverse tree in order
     * 
     * @param node
     *            The node to start
     * @param sb
     *            StringBuilder which will hold the path while tree traversal
     */
    private void inOrder(Node<V> node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeft(), sb);
        sb.append(String.format("Node has depth %d, Value %s%s", node
            .getDepth(), node, System.lineSeparator()));
        inOrder(node.getRight(), sb);
    }


    /**
     * Display tree in-order
     * 
     * @return
     *         String contains the path of nodes with in-order tree traversal
     */
    public String dump() {
        StringBuilder sb = new StringBuilder();
        sb.append("BST dump:" + System.lineSeparator());
        if (root != null) {
            inOrder(root, sb);
        }
        else {
            sb.append("Node has depth 0, Value (null)" + System
                .lineSeparator());
        }
        sb.append(String.format("BST size is: %d", (root != null
            ? root.getSize()
            : 0)));
        return sb.toString();
    }
}
