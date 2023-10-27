public class Deque<E> {
    private E[] data;
    private int front;
    private int rear;
    private int size;

    public Deque(int capacity) {
        data = (E[]) new Object[capacity];
        front = -1;
        rear = -1;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public int size() {
        return size;
    }

    public void addFront(E element) {
        if (isFull()) {
            throw new RuntimeException("Deque is full");
        }

        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else if (front == 0) {
            front = data.length - 1;
        } else {
            front--;
        }

        data[front] = element;
        size++;
    }

    public void addRear(E element) {
        if (isFull()) {
            throw new RuntimeException("Deque is full");
        }

        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else if (rear == data.length - 1) {
            rear = 0;
        } else {
            rear++;
        }

        data[rear] = element;
        size++;
    }

    public E removeFront() {
        if (isEmpty()) {
            throw new RuntimeException("Deque is empty");
        }

        E element = data[front];
        data[front] = null;

        if (front == rear) {
            front = -1;
            rear = -1;
        } else if (front == data.length - 1) {
            front = 0;
        } else {
            front++;
        }

        size--;
        return element;
    }

    public E removeRear() {
        if (isEmpty()) {
            throw new RuntimeException("Deque is empty");
        }

        E element = data[rear];
        data[rear] = null;

        if (front == rear) {
            front = -1;
            rear = -1;
        } else if (rear == 0) {
            rear = data.length - 1;
        } else {
            rear--;
        }

        size--;
        return element;
    }

    public E peekFront() {
        if (isEmpty()) {
            throw new RuntimeException("Deque is empty");
        }

        return data[front];
    }

    public E peekRear() {
        if (isEmpty()) {
            throw new RuntimeException("Deque is empty");
        }

        return data[rear];
    }

    public static void main(String[] args) {
        Deque<Object> deque = new Deque<>(7);
        deque.addFront(1);
        deque.addFront(2);
        deque.addRear(3);
        deque.addRear(4);
        deque.addFront(5);
        deque.addFront("Hello, world!");
        System.out.println(deque.removeFront());
        System.out.println(deque.removeFront());
        System.out.println(deque.removeRear());
        System.out.println(deque.removeRear());
        System.out.println(deque.removeFront());
        System.out.println(deque.removeFront());
    }
}