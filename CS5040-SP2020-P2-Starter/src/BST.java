
/**
 * Represents the BST
 * 
 * @author {shreyasb and veerad}
 * @version 2021-2-15
 * @param <K>
 *            Key type
 * @param <V>
 *            Value type
 */
public class BST<K extends Comparable<K>, V extends Shape> {

    /**
     * Root node of the BST.
     */
    private Node<K, V> root;

    /**
     * Default constructor
     */
    public BST() {
        root = null;
    }


    /**
     * @param key
     *            The name of the key, which used to insert in BST
     * @param val
     *            The value associated with the key
     */
    public void insert(K key, V val) {
        if (val.isShapeValid()) {
            root = insert(root, key, val);
        }
    }


    /**
     * Inserts a node in the BST
     * 
     * @param node
     *            The tree node
     * @param key
     *            Key used to insert the node
     * @param value
     *            value associated with the node
     * @return
     *         The newly inserted node
     */
    private Node<K, V> insert(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node<K, V>(key, value, 1);
        }

        int difference = key.compareTo(node.getKey());
        if (difference < 0) {
            node.setLeft(insert(node.getLeft(), key, value));
        }
        else if (difference > 0) {
            node.setRight(insert(node.getRight(), key, value));
        }
        else {
            node.setValue(value);
        }
        node.setSize(1 + size(node.getLeft()) + size(node.getRight()));

        return node;
    }


    /**
     * Remove the node from the BST
     * 
     * @param key
     *            Key used to remove node from tree
     */
    public void remove(K key) {
        root = remove(root, key);
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
    private Node<K, V> remove(Node<K, V> node, K key) {
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
            Node<K, V> t = node;
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
        return sb.toString();
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
        Node<K, V> node,
        V value,
        StringBuilder sb) {
        if (node == null) {
            return;
        }
        inOrderForIntersection(node.getLeft(), value, sb);
        if (node.getValue().shapeIntersects(value) && !sb.toString().contains(
            node.toString())) {
            sb.append(String.format("%s,", node));
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
    private void traverseEachNode(Node<K, V> node, StringBuilder sb) {
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
    public V searchByKey(K key) {
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
    private V inOrderForSearchByKey(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        inOrderForSearchByKey(node.getLeft(), key);
        if (node.getKey().equals(key)) {
            return node.getValue();
        }
        inOrderForSearchByKey(node.getRight(), key);
        return null;
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
    private void inOrderForShape(Node<K, V> node, Shape shape) {
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
    private Node<K, V> findMinimumInRightSubTree(Node<K, V> x) {
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
    private Node<K, V> deleteMinimum(Node<K, V> x) {
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
    private int size(Node<K, V> node) {
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
    private void inOrder(Node<K, V> node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeft(), sb);
        sb.append(String.format("Node has depth %d, Value %s%s", node.getSize(),
            node.getValue().toString(), System.lineSeparator()));
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
        inOrder(root, sb);
        sb.append(String.format("BST size is: %d", root.getSize()));
        return sb.toString();
    }
}
