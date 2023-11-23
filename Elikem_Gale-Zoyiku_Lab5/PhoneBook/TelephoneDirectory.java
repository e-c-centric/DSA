package PhoneBook;

/**
 * This is a TelephoneDirectory class that represents a telephone directory.
 * It is implemented using a binary search tree. The tree is sorted by the
 * names of the contacts in ascending order. The tree does not allow duplicate
 * names. Each node in the tree contains a contact with information such as
 * name, phone number,
 * email, and address.
 * 
 * The TelephoneDirectory class provides methods for inserting a new contact,
 * searching for a contact, deleting a contact, and printing the telephone
 * directory.
 * 
 * @see Node
 * @see Contact
 */
public class TelephoneDirectory {
    String reset = "\u001B[0m";
    String bold = "\u001B[1m";
    String cyan = "\u001B[36m";
    String yellow = "\u001B[33m";
    private Node root;

    /**
     * Constructs a new TelephoneDirectory object with an empty tree.
     */
    public TelephoneDirectory() {
        this.root = null;
    }

    /**
     * Inserts a new contact into the telephone directory.
     *
     * @param contact the contact to be inserted
     */
    public void insert(Contact contact) {
        root = insertRecursive(root, contact);
    }

    private Node insertRecursive(Node root, Contact contact) {
        // If the tree is empty, create a new node and return it
        if (root == null) {
            return new Node(contact);
        }

        // Compare the names, ignoring case sensitivity. If the contact name is less
        // than
        // the root's contact name, insert the new contact in the left subtree.
        // Otherwise,
        // insert the new contact in the right subtree. If the contact name is the same
        // as
        // the root's contact name, ignore the new contact. This ensures that searching
        // and deleting
        // a contact is done in O(log n) time as it will utilize a form of binary search
        // to find the contact.
        int compareResult = contact.getName().compareToIgnoreCase(root.getContact().getName());

        if (compareResult < 0) {
            root.setLeft(insertRecursive(root.getLeft(), contact));
        } else if (compareResult > 0) {
            root.setRight(insertRecursive(root.getRight(), contact));
        } else {
            System.err.println(yellow+"Contact with the same name already exists. Ignoring this new contact."+reset);
            System.err.println("Contact: " + contact);
        }

        return root;
    }

    /**
     * Searches for a contact in the telephone directory and returns their phone
     * number if found.
     *
     * @param contactName the name of the contact to search for
     * @return the phone number of the contact if found, otherwise null
     */
    public String search(String contactName) {
        Node result = searchRecursive(root, contactName);
        if (result != null) {
            return result.getContact().toString();
        } else {
            return "Contact not found";
        }
    }

    private Node searchRecursive(Node root, String contactName) {
        // If the tree is empty or the contact name is the same as the root's contact
        // name, return the root
        if (root == null || root.getContact().getName().equalsIgnoreCase(contactName)) {
            return root;
        }

        // Compare the names, ignoring case sensitivity. If the contact name is less
        // than the current root's contact name, search for the contact in the left
        // subtree recursively.
        // Otherwise, search for the contact in the right subtree recursively.
        int compareResult = contactName.compareToIgnoreCase(root.getContact().getName());

        if (compareResult == 0) {
            return root;
        } else if (compareResult < 0) {
            return searchRecursive(root.getLeft(), contactName);
        } else {
            return searchRecursive(root.getRight(), contactName);
        }

    }

    /**
     * Deletes a contact from the telephone directory.
     *
     * @param contactName the name of the contact to be deleted
     */
    public void delete(String contactName) {
        root = deleteRecursive(root, contactName);
    }

    private Node deleteRecursive(Node root, String contactName) {
        // If the tree is empty, return null
        if (root == null) {
            return null;
        }

        int compareResult = contactName.compareToIgnoreCase(root.getContact().getName());

        // Recursively search for the contact to be deleted.
        // If the contact name is less than the root's contact name, delete the contact
        // in the left subtree. Otherwise, delete the contact in the right subtree.

        if (compareResult < 0) {
            root.setLeft(deleteRecursive(root.getLeft(), contactName));
        } else if (compareResult > 0) {
            root.setRight(deleteRecursive(root.getRight(), contactName));
        } else {
            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            }

            // Node with two children: Get the inorder successor (smallest in the right
            // subtree) and set it as the root contact
            root.setContact(minValue(root.getRight()));

            // Delete the inorder successor
            root.setRight(deleteRecursive(root.getRight(), root.getContact().getName()));
        }

        return root;
    }

    /**
     * Returns the contact with the smallest name in the tree. This is used to find
     * the inorder successor of a node. The inorder successor is needed when a node
     * has two children. The inorder successor is the smallest node in the right
     * subtree.
     * 
     * @param root the root of the tree
     * @return the contact with the smallest name in the tree
     */
    private Contact minValue(Node root) {
        Contact minValue = root.getContact();
        while (root.getLeft() != null) {
            minValue = root.getLeft().getContact();
            root = root.getLeft();
        }
        return minValue;
    }

    /**
     * Prints the telephone directory in ascending order.
     */
    public void print() {
        printInOrder(root);
    }

    /**
     * Prints the telephone directory in ascending order. This is done by traversing
     * the tree in order. The left subtree is traversed first, then the root, and
     * then
     * the right subtree.
     * 
     */
    private void printInOrder(Node root) {
        if (root != null) {
            printInOrder(root.getLeft());
            System.out.println(root.getContact());
            printInOrder(root.getRight());
        }
    }
}
