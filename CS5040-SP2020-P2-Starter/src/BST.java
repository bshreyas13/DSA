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
    private Node<V> matchedNodeByShape;
    private String excludeText;

    /**
     * node removed status
     */
    private boolean nodeRemoved;

    /**
     * check for node exists while inserting
     */
    private boolean nodeAlreadyExists;

    /**
     * List to hold nodes when searched inorder
     */
    private MyList<Node<V>> allNodes;

    /**
     * Default constructor
     */
    public BST() {
        root = null;
    }


    /**
     * Clear Tree
     */
    public void clear() {
        root = null;
    }


    /**
     * To clear List elements
     */
    public void purgeList() {
        allNodes = new MyList<Node<V>>();
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
     * Calculate node size
     * 
     * @param node
     *            node to calculate
     */
    public void updateSize(Node<V> node) {
        if (node == null) {
            return;
        }
        if (node.getLeft() == null && node.getRight() == null) {
            node.setSize(1);
        }

        if (node.getLeft() != null) {
            updateSize(node.getLeft());
        }

        if (node.getRight() != null) {
            updateSize(node.getRight());
        }
        node.setSize(1 + size(node.getLeft()) + size(node.getRight()));
    }


    /**
     * 
     * @param node
     *            New node to be inserted
     * @return true if inserted false otherwise
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
     * @return the root node at each iteration
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
    public void removeByKey(String key) {
        nodeRemoved = false;
        root = iterateRemoveByKey(root, key, null);
        if (nodeRemoved) {
            updateDepth(root, 0);
            updateSize(root);
        }
        else {
            System.out.println(String.format("Rectangle rejected %s", key));
        }
    }


    /**
     * Delete node
     * 
     * @param parent
     *            start node
     * @param key
     *            key
     * @return node recursive
     */
    private Node<V> iterateRemoveByKey(
        Node<V> parent,
        String key,
        Shape value) {
        if (parent == null) {
            return null;
        }

        int c = key.compareTo(parent.getKey());

        if (c < 0) {
            parent.setLeft(iterateRemoveByKey(parent.getLeft(), key, value));
        }

        else if (c > 0) {
            parent.setRight(iterateRemoveByKey(parent.getRight(), key, value));
        }

        // node match
        else if (value == null || (parent.getValue().isShapeEquals(value))) {

            // case leaf node
            if (parent.getLeft() == null && parent.getRight() == null) {
                parent = null;
                nodeRemoved = true;
            }

            // check for valid successor in right subtree
            else if (parent.getRight() != null) {
                Node<V> tempLeft = parent.getLeft();
                parent = successor(parent);
                parent.setLeft(tempLeft);
                parent.setRight(iterateRemoveByKey(parent.getRight(), parent
                    .getKey(), value));
                nodeRemoved = true;
            }

            // can't find successor, find predecessor
            else {
                Node<V> tempRight = parent.getRight();
                parent = predecessor(parent);
                parent.setRight(tempRight);
                parent.setLeft(iterateRemoveByKey(parent.getLeft(), parent
                    .getKey(), value));
                nodeRemoved = true;
            }

        }

        return parent;
    }


    /**
     * Find right most node in left subtree
     * 
     * @param parent
     *            root node where to find
     * @return key of most high node in left subtree
     */
    private Node<V> predecessor(Node<V> parent) {
        parent = parent.getLeft();
        while (parent.getRight() != null) {
            parent = parent.getRight();
        }
        return parent;
    }


    /**
     * Find out smallest left node in right subtree
     * 
     * @param parent
     *            parent node
     * @return key of least node in left sub tree of right child
     */
    private Node<V> successor(Node<V> parent) {
        parent = parent.getRight();
        while (parent.getLeft() != null) {
            parent = parent.getLeft();
        }
        return parent;
    }


    /**
     * Search BST for specific rectangle
     * 
     * @param value
     *            The rectangle used to search the for region match
     */
    public void searchByRegion(V value) {
        excludeText = "";
        System.out.println(String.format("Rectangles intersecting region (%s):",
            value));
        traverseEachNodeForRegions(root, value);
    }


    /**
     * This methods used for search regions
     * 
     * @param node
     *            Current node in traversal
     */
    private void traverseEachNodeForRegions(Node<V> node, Shape shape) {
        if (node == null) {
            return;
        }
        traverseEachNodeForRegions(node.getLeft(), shape);
        inOrderForRegions(root, shape);
        traverseEachNodeForRegions(node.getRight(), shape);
    }


    /**
     * In-order traversal for region search
     * 
     * @param parent
     *            parent node
     * @param shape
     */
    private void inOrderForRegions(Node<V> parent, Shape shape) {
        if (parent == null) {
            return;
        }
        inOrderForRegions(parent.getLeft(), shape);
        if (parent.getValue().shapeIntersects(shape)) {
            String nodeInfo = String.format("%s", parent);
            boolean display = true;
            String[] excludes = excludeText.split(System.lineSeparator());
            for (String s : excludes) {
                if (nodeInfo.compareTo(s) == 0) {
                    display = false;
                }
            }
            if (display) {
                excludeText += String.format("%s%s", nodeInfo, System
                    .lineSeparator());
                System.out.println(nodeInfo);
            }
        }
        inOrderForRegions(parent.getRight(), shape);
    }


    /**
     * Iterate tree to search for intersections
     * 
     * @param node
     *            The node to begin search
     */
    private void inOrderForIntersection(Node<V> parent, Node<V> node) {
        if (parent == null || node == null) {
            return;
        }
        inOrderForIntersection(parent, node.getLeft());
        if (!parent.getValue().isShapeEquals(node.getValue()) && parent
            .getValue().shapeIntersects(node.getValue())) {
            String[] excludes = excludeText.split(System.lineSeparator());
            String forward = String.format("%s : %s", parent, node);
            String reverse = String.format("%s : %s", node, parent);
            boolean display = true;
            for (String s : excludes) {
                if (forward.compareTo(s) == 0) {
                    display = false;
                }
            }
            if (display) {
                excludeText += String.format("%s%s", reverse, System
                    .lineSeparator());
                System.out.println(forward);
            }
        }
        inOrderForIntersection(parent, node.getRight());
    }


    /**
     * Search for intersections in BST
     * 
     */
    public void searchForIntersections() {
        System.out.println("Intersection pairs:");
        excludeText = "";
        traverseEachNode(root);
    }


    /**
     * This methods used for search intersections
     * 
     * @param node
     *            Current node in traversal
     */
    private void traverseEachNode(Node<V> node) {
        if (node == null) {
            return;
        }
        traverseEachNode(node.getLeft());
        inOrderForIntersection(root, node);
        traverseEachNode(node.getRight());
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
     * @return The value associated with key
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
            return searchWithValue(node.getLeft(), key, value);
        }
        return searchWithValue(node.getRight(), key, value);
    }


    /**
     * Traverse tree with and search with given key
     * 
     * @param key
     *            The key name
     * 
     */
    public void searchByKey(String key) {
        // setSearchStatus(false);
        purgeList();
        inOrderSearch(root, key);
        boolean isKeyPresent = false;
        if (allNodes.length() != 0) {
            for (int i = 0; i < allNodes.length(); i++) {
                Node<V> node = allNodes.get(i);
                // System.out.println(i);
                if (node.getKey().compareTo(key) == 0) {
                    System.out.println(String.format("Rectangle found: %s",
                        node));
                    isKeyPresent = true;
                }
            }
        }
        if (!isKeyPresent) {
            System.out.println(String.format("Rectangle not found: %s", key));
        }

    }


    /**
     * Iterate tree to get all nodes
     * 
     * @param node
     *            The node to begin search
     * @param key
     *            The key used to search
     * 
     */
    private void inOrderSearch(Node<V> node, String key) {
        if (node == null) {
            return;
        }
        // System.out.println(node);
        allNodes.add(node);
        inOrderSearch(node.getLeft(), key);
        inOrderSearch(node.getRight(), key);

    }


    /**
     * Remove the values by matched Shape
     * 
     * @param value
     *            The shape value
     * @return return status
     */
    public boolean removeByValue(Shape value) {
        nodeRemoved = false;
        matchedNodeByShape = null;
        findNodeByValue(root, value);
        if (matchedNodeByShape != null) {
            root = iterateRemoveByKey(root, matchedNodeByShape.getKey(), value);
            updateDepth(root, 0);
            updateSize(root);
            matchedNodeByShape = null;
        }
        return nodeRemoved;
    }


    /**
     * Associated method to {@link #removeByValue(Shape)}
     * 
     * @param node
     *            The node at current iteration
     * @param value
     *            If this value matches, the node will be removed.
     */
    private void findNodeByValue(Node<V> node, Shape value) {
        if (node != null) {

            findNodeByValue(node.getLeft(), value);

            if (node.getValue().isShapeEquals(value)
                && matchedNodeByShape == null) {
                matchedNodeByShape = node;
                return;
            }

            findNodeByValue(node.getRight(), value);
        }
    }


    /**
     * Returns the size of the node
     * 
     * @param node
     *            The node
     * @return Size of the node
     */
    private int size(Node<V> node) {
        return node == null ? 0 : node.getSize();
    }


    /**
     * Display tree in-order
     * 
     * @return status contains the path of nodes with in-order tree traversal
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
