class Node<T> {
    T data;
    Node<T> next;
    Node<T> prev;

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

class DoublyLinkedList<T extends Node<?>> {
    int size = 0;
    private Node<T> head;
    private Node<T> tail;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void insert(T data, int pos) {
        if (pos == 1) {
            Node<T> newNode = new Node<T>(data);
            newNode.next = head;
            head.prev = newNode;
            newNode.prev = null;
            head = newNode;
            size++;
        } else if (pos == size + 1) {
            Node<T> newNode = new Node<T>(data);
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current;
            newNode.next = null;
            tail = newNode;
            size++;
        } else {
            Node<T> newNode = new Node<T>(data);
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
            tail = current.prev;
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
}