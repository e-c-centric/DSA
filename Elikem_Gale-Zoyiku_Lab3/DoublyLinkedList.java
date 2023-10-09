public class DoublyLinkedList<T extends Node<?>> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(Node<T> node, int pos) {
        if (pos < 1 || pos > size + 1) {
            throw new IllegalArgumentException("Invalid position");
        }

        if (pos == 1) {
            if (isEmpty()) {
                head = node;
                tail = node;
            } else {
                node.setNext(head);
                head.setPrev(node);
                head = node;
            }
        } else if (pos == size + 1) {
            node.setPrev(tail);
            tail.setNext(node);
            tail = node;
        } else {
            Node<T> current = getNode(pos - 1);
            node.setNext(current.getNext());
            current.getNext().setPrev(node);
            current.setNext(node);
            node.setPrev(current);
        }
        size++;
    }

    public void delete(int pos) {
        if (isEmpty() || pos < 1 || pos > size) {
            throw new IllegalArgumentException("Invalid position");
        }

        if (pos == 1) {
            if (size == 1) {
                head = null;
                tail = null;
            } else {
                head = head.getNext();
                head.setPrev(null);
            }
        } else if (pos == size) {
            tail = tail.getPrev();
            tail.setNext(null);
        } else {
            Node<T> current = getNode(pos);
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
        }
        size--;
    }

    public void delete(Node<T> nodeToDelete) {
        if (isEmpty() || nodeToDelete == null) {
            return; // Nothing to delete or invalid input.
        }

        // Find the node to delete in the list.
        Node<T> current = head;
        while (current != null) {
            if (current == nodeToDelete) {
                // Adjust pointers to remove the node.
                if (current.getPrev() != null) {
                    current.getPrev().setNext(current.getNext());
                } else {
                    head = current.getNext();
                }

                if (current.getNext() != null) {
                    current.getNext().setPrev(current.getPrev());
                } else {
                    tail = current.getPrev();
                }

                size--;
                return;
            }
            current = current.getNext();
        }
    }

    public Node<T> getNode(int pos) {
        if (isEmpty() || pos < 1 || pos > size) {
            throw new IllegalArgumentException("Invalid position");
        }

        Node<T> current = head;
        for (int i = 1; i < pos; i++) {
            current = current.getNext();
        }
        return current;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void swap(int pos1, int pos2) {
        if (pos1 < 1 || pos2 < 1 || pos1 > size || pos2 > size) {
            throw new IllegalArgumentException("Invalid positions");
        }

        if (pos1 == pos2) {
            return;
        }

        Node<T> node1 = getNode(pos1);
        Node<T> node2 = getNode(pos2);

        Node<T> tempPrev1 = node1.getPrev();
        Node<T> tempNext1 = node1.getNext();

        node1.setPrev(node2.getPrev());
        node1.setNext(node2.getNext());

        node2.setPrev(tempPrev1);
        node2.setNext(tempNext1);

        if (node1.getPrev() != null) {
            node1.getPrev().setNext(node1);
        } else {
            head = node1;
        }

        if (node1.getNext() != null) {
            node1.getNext().setPrev(node1);
        } else {
            tail = node1;
        }

        if (node2.getPrev() != null) {
            node2.getPrev().setNext(node2);
        } else {
            head = node2;
        }

        if (node2.getNext() != null) {
            node2.getNext().setPrev(node2);
        } else {
            tail = node2;
        }
    }
}
