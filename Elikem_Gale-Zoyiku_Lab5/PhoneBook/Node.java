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

    /**
     * Returns true if the specified object is equal to the current node, false
     * otherwise. Two nodes are equal if they have the same contact. Invokes the
     * equals method of the Contact class.
     *
     * @param obj the object to compare to the current node
     * @return true if the specified object is equal to the current node, false
     *         otherwise
     * @see Contact#equals(Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            Node other = (Node) obj;
            return contact.equals(other.getContact());
        } else {
            return false;
        }
    }

    /**
     * Returns true if the current node is less than the specified node, false
     * otherwise. A node is less than another node if its contact is
     * lexicographically less than the other node's contact. Invokes the lessThan
     * method of the Contact class.
     *
     * @param other the node to compare to the current node
     * @return true if the current node is less than the specified node, false
     *         otherwise
     * @see Contact#lessThan(Contact)
     */
    public boolean lessThan(Node other) {
        return contact.lessThan(other.getContact().getName());
    }
}