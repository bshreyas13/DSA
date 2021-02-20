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
     * node removed status
     */
    private boolean nodeRemoved;

    /**
     * node removed status
     */
    private boolean searchFound;
    /**
     * check for node exists while inserting
     */
    private boolean nodeAlreadyExists;

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
    public void updateDepth(Node<V> node, int depth) {
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
            nodeAlreadyExists = false;
            searchByKeyValue(node.getKey(), node.getValue());
            if (root == null || !nodeAlreadyExists) {
                root = insert(root, node);
                updateDepth(root, 0);
                return true;
            }
        }
        return false;
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
            node.setSize(1);
            return node;
        }

        int c = node.getKey().compareTo(parent.getKey());

        if (c < 0) {
            parent.setLeft(insert(parent.getLeft(), node));
        }

        else {
            parent.setRight(insert(parent.getRight(), node));
        }

        parent.setSize(1 + size(parent.getLeft()) + size(parent.getRight()));

        return parent;
    }


    /**
     * Delete node
     * 
     * @param key
     *            remove by key
     */
    public void remove(String key) {
        nodeRemoved = false;
        root = delete(root, key);
        updateDepth(root, 0);
        if (!nodeRemoved) {
            System.out.println(String.format("Rectangle rejected %s", key));
        }
    }


    /**
     * Delete node
     * 
     * @param parent
     *            start node
     * @param toBeDeleted
     *            key
     * @return
     *         node recursive
     */
    private Node<V> delete(Node<V> parent, Node<V> toBeDeleted) {
        if (parent == null) {
            nodeRemoved = false;
            return null;
        }

        int c = toBeDeleted.getKey().compareTo(parent.getKey());

        if (c < 0) {
            parent.setLeft(delete(parent.getLeft(), toBeDeleted));
        }

        else if (c > 0) {
            parent.setRight(delete(parent.getRight(), toBeDeleted));
        }

        else if (parent.equals(toBeDeleted)) {

            if (parent.getLeft() == null) {
                nodeRemoved = true;
                return parent.getRight();
            }

            else if (parent.getRight() == null) {
                nodeRemoved = true;
                return parent.getLeft();
            }

            else {
                Node<V> temp = getMax(parent.getLeft());
                parent.setValue(temp.getValue());
                parent.setLeft(deleteMax(parent.getLeft()));
                nodeRemoved = true;
            }
        }

        parent.setSize(1 + size(parent.getLeft()) + size(parent.getRight()));

        return parent;
    }


    /**
     * Delete node
     * 
     * @param parent
     *            start node
     * @param key
     *            key
     * @return
     *         node recursive
     */
    private Node<V> delete(Node<V> parent, String key) {
        if (parent == null) {
            nodeRemoved = false;
            return null;
        }

        int c = key.compareTo(parent.getKey());

        if (c < 0) {
            parent.setLeft(delete(parent.getLeft(), key));
        }

        else if (c > 0) {
            parent.setRight(delete(parent.getRight(), key));
        }

        else {
            if (parent.getLeft() == null) {
                nodeRemoved = true;
                return parent.getRight();
            }

            else if (parent.getRight() == null) {
                nodeRemoved = true;
                return parent.getLeft();
            }

            else {
                Node<V> temp = getMax(parent.getLeft());
                parent.setValue(temp.getValue());
                parent.setLeft(deleteMax(parent.getLeft()));
                nodeRemoved = true;
            }
        }

        parent.setSize(1 + size(parent.getLeft()) + size(parent.getRight()));

        return parent;
    }


    /**
     * Get max node
     * 
     * @param right
     *            right node
     * @return
     *         max node
     */
    private Node<V> getMax(Node<V> right) {
        if (right.getRight() == null) {
            return right;
        }
        return getMax(right.getRight());
    }


    /**
     * Delete max node in right subtree
     * 
     * @param right
     *            right node
     * @return
     *         node
     */
    private Node<V> deleteMax(Node<V> right) {
        if (right.getRight() == null) {
            return right.getLeft();
        }
        right.setRight(deleteMax(right.getRight()));
        return right;
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
        Node<V> node,
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
     * @param value
     *            value to match
     * @return
     * 
     *         The value found
     */
    public Node<V> searchByKeyValue(String key, V value) {
        return searchWithValue(root, key, value);
    }


    /**
     * Associated method for {@link #searchByKey(Comparable)}
     * 
     * @param node
     *            The current node at each iteration
     * @param key
     *            The key value
     * @param value
     *            value of node
     * @return
     *         The value associated with key
     */
    private Node<V> searchWithValue(Node<V> node, String key, V value) {
        if (node == null) {
            return null;
        }
        int c = key.compareTo(node.getKey());
        if (c == 0 && node.getValue().isShapeEquals(value)) {
            nodeAlreadyExists = true;
            return node;
        }
        if (c < 0) {
            return searchWithValue(node.getLeft(), key,value);
        }
        return searchWithValue(node.getRight(), key, value);
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
    public Node<V> searchByKey(String key) {
        searchFound = false;
        Node<V> node = search(root, key);
        //if (!searchFound) {
         //   System.out.println(String.format("Rectangle not found: %s", key));
        //}
        return node;
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
    private Node<V> search(Node<V> node, String key) {
        if (node == null) {
            return null;
        }
        int c = key.compareTo(node.getKey());
        if (c == 0) {
            //System.out.println(String.format("Rectangle found: (%s, %s)", key,
              //  node.getValue()));
            searchFound = true;
        }
        if (c < 0) {
            return search(node.getLeft(), key);
        }
        return search(node.getRight(), key);
    }


    /**
     * Remove the values by matched Shape
     * 
     * @param shape
     *            The shape value
     * @return
     *         return status
     */
    public boolean removeByRect(Shape shape) {
        nodeRemoved = false;
        removeByShape(root, shape);
        updateDepth(root, 0);
        return nodeRemoved;
    }


    /**
     * Associated method to {@link #removeByRect(Shape)}
     * 
     * @param node
     *            The node at current iteration
     * @param shape
     *            If this value matches, the node will be removed.
     */
    private void removeByShape(Node<V> node, Shape shape) {
        if (node == null) {
            return;
        }
        if (!nodeRemoved) {
            removeByShape(node.getLeft(), shape);
        }
        if (node.getValue().isShapeEquals(shape)) {
            root = delete(root, node);
            if (nodeRemoved) {
                return;
            }
        }
        if (!nodeRemoved) {
            removeByShape(node.getRight(), shape);
        }
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
    private void inOrder(Node<V> node) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeft());
        System.out.println(String.format("Node has depth %d, Value %s", node
            .getDepth(), node));
        inOrder(node.getRight());
    }
}