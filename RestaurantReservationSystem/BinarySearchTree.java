package RestaurantReservationSystem;

/**
 * This class represents a node in a binary search tree. A node has a table and
 * left and right children. The table is the data stored in the node. The left
 * and right children are the nodes that are less than and greater than the node
 * respectively.
 */
class Node {
    private Table table;
    private Node left;
    private Node right;

    public Node(Table table) {
        this.table = table;
        left = null;
        right = null;
    }

    public Table getTable() {
        return table;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public boolean equals(Object o) {
        if (o instanceof Node) {
            Node other = (Node) o;
            return table.equals(other.getTable());
        } else {
            return false;
        }
    }
}

public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(Table table) {
        root = insert(root, table);
    }

    private Node insert(Node node, Table table) {
        if (node == null) {
            node = new Node(table);
        } else {
            if (table.getCapacity() <= node.getTable().getCapacity()) {
                node.setLeft(insert(node.getLeft(), table));
            } else {
                node.setRight(insert(node.getRight(), table));
            }
        }
        return node;
    }

    public void delete(int tableNumber) {
        if (root == null) {
            System.out.println("Tree is empty");
        } else {
            if (!search(tableNumber)) {
                System.out.println("Table not found");
            } else {
                root = delete(root, tableNumber);
                System.out.println("Table deleted");
            }
        }
    }

    private Node delete(Node node, int tableNumber) {
        if (node == null) {
            return null;
        }

        if (tableNumber < node.getTable().getCapacity()) {
            node.setLeft(delete(node.getLeft(), tableNumber));
        } else if (tableNumber > node.getTable().getCapacity()) {
            node.setRight(delete(node.getRight(), tableNumber));
        } else {
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }

            node.setTable(minValue(node.getRight()).getTable());
            node.setRight(delete(node.getRight(), node.getTable().getCapacity()));
        }
        return node;
    }

    private Node minValue(Node node) {
        Node current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    public boolean search(int tableNumber) {
        return search(root, tableNumber);
    }

    private boolean search(Node node, int tableNumber) {
        if (node == null) {
            return false;
        }

        int nodeTableNumber = node.getTable().getCapacity();
        if (tableNumber < nodeTableNumber) {
            return search(node.getLeft(), tableNumber);
        } else if (tableNumber > nodeTableNumber) {
            return search(node.getRight(), tableNumber);
        } else {
            return true;
        }
    }

    public void inorder() {
        inorder(root);
    }

    private void inorder(Node node) {
        if (node != null) {
            inorder(node.getLeft());
            System.out.println(node.getTable().getCapacity() + " ");
            inorder(node.getRight());
        }
    }
}
