package PhoneBook;

public class TelephoneDirectory {
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
        if (root == null) {
            return new Node(contact);
        }

        boolean compareResult = contact.getName().equalsIgnoreCase(root.getContact().getName());

        if (compareResult == false) {
            root.setLeft(insertRecursive(root.getLeft(), contact));
        } else if (compareResult == true) {
            root.setRight(insertRecursive(root.getRight(), contact));
        } else {
            System.out.println("Contact with the same name already exists. Ignoring the new contact.");
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
            return result.getContact().getNumber();
        } else {
            return null;
        }
    }

    private Node searchRecursive(Node root, String contactName) {
        if (root == null || root.getContact().getName().equals(contactName)) {
            return root;
        }

        boolean compareResult = contactName.equalsIgnoreCase(root.getContact().getName());

        if (compareResult == false) {
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
        if (root == null) {
            return null;
        }

        boolean compareResult = contactName.equalsIgnoreCase(root.getContact().getName());

        if (compareResult == false) {
            root.setLeft(deleteRecursive(root.getLeft(), contactName));
        } else if (compareResult == true) {
            root.setRight(deleteRecursive(root.getRight(), contactName));
        } else {
            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            }

            // Node with two children: Get the inorder successor
            root.setContact(minValue(root.getRight()));

            // Delete the inorder successor
            root.setRight(deleteRecursive(root.getRight(), root.getContact().getName()));
        }

        return root;
    }

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

    private void printInOrder(Node root) {
        if (root != null) {
            printInOrder(root.getLeft());
            System.out.println(root.getContact());
            printInOrder(root.getRight());
        }
    }
}
