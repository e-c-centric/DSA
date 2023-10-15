package MusicPlayer;

/**
 * This class represents a doubly linked list data structure.
 * It allows for insertion and deletion of elements at specific positions,
 * as well as deletion of elements by their value.
 * @param <T> the type of data stored in the linked list
 * @see Node
 * @Author Elikem Asudo Gale-Zoyiku
 */
public class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Constructs an empty doubly linked list.
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Inserts a node containing the given data at the specified position in the linked list.
     * @param data the data to be inserted
     * @param position the position at which to insert the data
     * @throws IllegalArgumentException if the position is invalid (less than 0 or greater than the size of the list)
     */
    public void insertAtPosition(Node<T> data, int position) {
        if (position < 0 || position > size) {
            throw new IllegalArgumentException("Invalid position");
        }

        Node<T> newNode = data;

        if (size == 0) {
            head = newNode;
            tail = newNode;
            head.prev = tail;
            tail.next = head;
        } else if (position == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            head.prev = tail;
            tail.next = head;
        } else if (position == size) {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
            head.prev = tail;
        } else {
            Node<T> current = head;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }
            newNode.prev = current;
            newNode.next = current.next;
            current.next.prev = newNode;
            current.next = newNode;
        }

        size++;
    }

    /**
     * Deletes the node at the specified position in the linked list.
     * @param position the position of the node to be deleted
     * @throws IllegalArgumentException if the position is invalid (less than 0 or greater than or equal to the size of the list)
     */
    public void deleteByPosition(int position) {
        if (position < 0 || position >= size) {
            throw new IllegalArgumentException("Invalid position");
        }

        if (size == 1) {
            head = null;
            tail = null;
        } else if (position == 0) {
            head = head.next;
            head.prev = tail;
            tail.next = head;
        } else if (position == size - 1) {
            tail = tail.prev;
            tail.next = head;
            head.prev = tail;
        } else {
            Node<T> current = head;
            for (int i = 0; i < position; i++) {
                current = current.next;
            }
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        size--;
    }

    /**
     * Deletes the first node containing the given data from the linked list.
     * @param data the data to be deleted
     * @see #deleteByPosition(int)
     * @see Song#equals(Song)
     */
    public void deleteByElement(T data) {
        Node<T> current = head;
        int position = 0;

        while (current != null) {
            if (current.data.equals(data)) {
                deleteByPosition(position);
                return;
            }
            current = current.next;
            position++;
        }
    }

    /**
     * Returns the size of the linked list.
     * @return the size of the linked list
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the node at the specified position in the linked list.
     * @param position the position of the node to be returned
     * @return the node at the specified position
     * @throws IllegalArgumentException if the position is invalid (less than 0 or greater than or equal to the size of the list)
     */
    public Node<T> get(int position) {
        if (position < 0 || position >= size) {
            throw new IllegalArgumentException("Invalid position");
        }

        Node<T> current = head;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }

        return current;
    }
}