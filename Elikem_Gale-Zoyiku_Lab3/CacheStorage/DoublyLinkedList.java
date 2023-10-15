package CacheStorage;
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
 * @param <T> The type of data stored in the nodes of the list. T is a bound to
 *            be of type Node.
 * @see Node
 * @author Elikem Asudo Gale-Zoyiku
 */
public class DoublyLinkedList<T extends Node<?>> {
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

    /**
     * The <code>getHead</code> method returns the head node of the list.
     * Alternative implementation of the <code>getHead</code> method would invoke
     * the <code>getNode</code> method with a position of 1.
     * 
     * @see DoublyLinkedList#getNode(int)
     * @param None
     * @return The head node of the list.
     */
    public Node<T> getHead() {
        return head;
    }

/** 
 * The <code>getTail</code> method returns the tail node of the list.
 * Alternative implementation of the <code>getTail</code> method would invoke
 * the <code>getNode</code> method with a position of <code>size</code>.
 * 
 * @see DoublyLinkedList#getNode(int)
 * @param None
 * @return The tail node of the list.
 */
    public Node<T> getTail() {
        return tail;
    }

    /**
     * The <code>setHead</code> method sets the head node of the list.
     * Intended to be used when the head node is not set during instantiation or
     * when the head node is to be changed.
     * 
     * @param head The node to be set as the head node of the list.
     * @return None
     * @see DoublyLinkedList#insert(T, int)
     */
    public void setHead(Node<T> head) {
        this.insert(head.data, 1);
    }

    /**
     * The <code>setTail</code> method sets the tail node of the list.
     * Intended to be used when the tail node is not set during instantiation,
     * or when the tail node is to be changed.
     * 
     * @param tail The node to be set as the tail node of the list.
     * @return None
     * @see DoublyLinkedList#insert(T, int)
     * @see DoublyLinkedList#size
     */
    public void setTail(Node<T> tail) {
        this.insert(tail.data, size + 1);
    }
}