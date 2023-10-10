package MusicPlayer;

public class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void insertAtPosition(Node<T> data, int position) {
        if (position < 0 || position > size) {
            throw new IllegalArgumentException("Invalid position");
        }

        Node<T> newNode = data;

        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else if (position == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else if (position == size) {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
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

    public void deleteByPosition(int position) {
        if (position < 0 || position >= size) {
            throw new IllegalArgumentException("Invalid position");
        }

        if (size == 1) {
            head = null;
            tail = null;
        } else if (position == 0) {
            head = head.next;
            head.prev = null;
        } else if (position == size - 1) {
            tail = tail.prev;
            tail.next = null;
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

    public int getSize() {
        return size;
    }

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
