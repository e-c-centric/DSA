package PhoneBook;

/**
 * Represents a node in a binary search tree. A node has a contact, a left child
 * node, and a right child node.
 */
public class Node {
    private Contact contact;
    private Node left;
    private Node right;

    /**
     * Constructs a new Node object with the specified contact. The left and right
     * child nodes are set to null.
     *
     * @param contact the contact to be stored in the node
     */
    public Node(Contact contact) {
        this.contact = contact;
        this.left = null;
        this.right = null;
    }

    /**
     * Returns the contact stored in the node.
     *
     * @return the contact stored in the node
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Sets the contact to be stored in the node.
     *
     * @param contact the contact to be stored in the node
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * Returns the left child node of the current node.
     *
     * @return the left child node of the current node
     */
    public Node getLeft() {
        return left;
    }

    /**
     * Sets the left child node of the current node.
     *
     * @param left the left child node to be set
     */
    public void setLeft(Node left) {
        this.left = left;
    }

    /**
     * Returns the right child node of the current node.
     *
     * @return the right child node of the current node
     */
    public Node getRight() {
        return right;
    }

    /**
     * Sets the right child node of the current node.
     *
     * @param right the right child node to be set
     */
    public void setRight(Node right) {
        this.right = right;
    }

    /**
     * Returns a string representation of the node.
     *
     * @return a string representation of the node
     */
    @Override
    public String toString() {
        return contact.toString();
    }
}