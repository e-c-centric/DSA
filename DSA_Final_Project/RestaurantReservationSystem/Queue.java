package RestaurantReservationSystem;

class Node {
    private Customer customer;
    private Node next;

    public Node(Customer customer) {
        this.customer = customer;
        next = null;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Node getNext() {
        return next;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public boolean equals(Object o) {
        if (o instanceof Node) {
            Node other = (Node) o;
            return customer.equals(other.getCustomer());
        } else {
            return false;
        }
    }
}

/**
 * The Queue class implements a queue data structure using a linked list. The
 * queue is a first-in, first-out (FIFO) data structure, meaning that the first
 * element added to the queue will be the first element removed from the queue.
 * 
 * The time complexity of enqueue, dequeue, peek, and isEmpty methods in the
 * Queue class is O(1). This is
 * because these methods perform a constant number of operations regardless of
 * the size of the queue. The toString method has a time complexity of O(n),
 * because it iterates over each node in the queue and appends its customer to a
 * StringBuilder, resulting in a linear runtime with respect to the number of
 * elements in the queue.
 */
public class Queue {
    private Node front;
    private Node rear;

    public Queue() {
        front = null;
        rear = null;
    }

    /**
     * Time complexity of O(1)
     * 
     * @param customer
     */
    public void enqueue(Customer customer) {
        Node newNode = new Node(customer);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
    }

    /**
     * Removes the customer at the front of the queue. Throws an exception if the
     * queue is empty.
     * Time complexity of O(1)
     */
    public void dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty. Cannot dequeue.");
        }
        front = front.getNext();
        if (front == null) {
            rear = null;
        }
    }

    /**
     * The peek method returns the customer at the front of the queue without
     * removing it. Throws an exception if the queue is empty.
     * Time complexity of O(1)
     * 
     * @return the customer at the front of the queue
     */
    public Customer peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty. Cannot peek.");
        }
        return front.getCustomer();
    }

    /**
     * The isEmpty method returns true if the queue is empty and false otherwise.
     * Time complexity of O(1)
     * 
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * Time complexity of O(n)
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = front;
        while (current != null) {
            sb.append(current.getCustomer()).append("\n");
            current = current.getNext();
        }
        return sb.toString();
    }
}