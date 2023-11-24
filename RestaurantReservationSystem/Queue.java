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

public class Queue {
    private Node front;
    private Node rear;

    public Queue() {
        front = null;
        rear = null;
    }

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

    public void dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty. Cannot dequeue.");
        }
        front = front.getNext();
        if (front == null) {
            rear = null;
        }
    }

    public Customer peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty. Cannot peek.");
        }
        return front.getCustomer();
    }

    public boolean isEmpty() {
        return front == null;
    }

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