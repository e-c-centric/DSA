/**
 * The <code>Node</code> class represents a node in a doubly linked list.
 * It has a <code>data</code> field that stores the data of the node.
 * It also has a <code>next</code> field that stores the reference to the next
 * node.
 * It also has a <code>prev</code> field that stores the reference to the
 * previous node.
 * It has a <code>toString()</code> method that returns the data of the node.
 * 
 * @param <T> The type of data stored in the node.
 * @author Elikem Asudo Gale-Zoyiku
 */
class Node<T> {
    T data;
    Node<T> next;
    Node<T> prev;

    /**
     * The <code>Node</code> constructor takes in data and initializes the
     * <code>data</code> field with the given data.
     * 
     * @param data The data to be stored in the node.
     * @return A new <code>Node</code> object.
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    /**
     * The <code>toString</code> method returns the data of the node.
     * This method invokes the <code>toString</code> method of the type of data,
     * that is <code>T.toString()</code>.
     * 
     * @param None
     * @return The data of the node.
     */
    public String toString() {
        return data.toString();
    }
}

/**
 * The <code>DoublyLinkedList</code> class represents a doubly linked list.
 * It has a <code>size</code> field that stores the size of the list.
 * It also has a <code>head</code> field that stores the reference to the head
 * node.
 * It also has a <code>tail</code> field that stores the reference to the tail
 * node.
 * It has a <code>insert()</code> method that inserts a node at a given
 * position.
 * It has a <code>delete()</code> method that deletes a node at a given
 * position.
 * 
 * @param <T> The type of data stored in the nodes of the list..
 * @see <code>Node</code> class
 * @author Elikem Asudo Gale-Zoyiku
 */
class DoublyLinkedList<T extends Node<?>> {
    int size = 0;
    private Node<T> head;
    private Node<T> tail;

    /**
     * The <code>DoublyLinkedList</code> constructor initializes the
     * <code>head</code> and <code>tail</code> fields to null.
     * 
     * @param None
     * @return A new <code>DoublyLinkedList</code> object.
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * The <code>insert</code> method inserts a node at a given position.
     * 
     * @param data The song to be stored in the node.
     * @param pos  The position at which the node is to be inserted.
     * @return None
     */
    public void insert(T data, int pos) {
        Node<T> newNode = new Node<T>(data);
        if (pos == 1) {
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                head.prev = newNode;
                newNode.next = head;
                newNode.prev = null;
                head = newNode;
            }
            size++;
        } else if (pos == size + 1) {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current;
            newNode.next = null;
            this.tail = newNode;
            size++;
        } else {
            Node<T> current = head;
            int count = 1;
            while (count < (pos - 1)) {
                current = current.next;
                count++;
            }
            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;
            size++;
        }
    }

    /**
     * The <code>delete</code> method deletes a song at a given position.
     * 
     * @param pos The position of the node to be deleted.
     * @return None
     */
    public void delete(int pos) {
        if (head == null) {
            return;
        } else if (pos == 1) {
            head = head.next;
            head.prev = null;
            size--;
        } else if (pos == size + 1) {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.prev.next = null;
            current.prev = current.prev.prev;
            size--;
        } else {
            Node<T> current = head;
            int count = 1;
            while (count < (pos - 1)) {
                current = current.next;
                count++;
            }
            current.next = current.next.next;
            current.next.prev = current;
            size--;
        }
    }

    /**
     * The <code>getNode</code> method returns the node at a given position.
     * 
     * @param pos The position of the node to be returned.
     * @return The node at the given position.
     */
    public Node<T> getNode(int pos) {
        Node<T> current = head;
        int count = 1;
        while (count < pos) {
            current = current.next;
            count++;
        }
        return current;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }
}
