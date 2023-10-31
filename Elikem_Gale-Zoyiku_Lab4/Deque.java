public class Deque {
    private Task[] tasks;
    private int front;
    private int rear;
    private int capacity;

    public Deque(int size) {
        tasks = new Task[size];
        front = -1;
        rear = -1;
        capacity = size;
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return (front == 0 && rear == capacity - 1) || (front == rear + 1);
    }

    public void push(Task task) {
        if (task.getPriority() > 5) {
            if (isEmpty() || task.getPriority() >= tasks[front].getPriority()) {
                // Push the high-priority task to the front of the Deque
                pushFront(task);
            } else {
                // Pop the current front of the Deque
                Task currentFront = popFront();

                // Place the new high-priority task at the front of the Deque
                pushFront(task);

                // Put the popped front back onto the front of the Deque
                pushFront(currentFront);
            }
        } else {
            // Push the task to the rear of the Deque
            pushRear(task);
        }
    }

    public Task pop() {
        if (isEmpty()) {
            System.out.println("Deque is empty");
            return null;
        }

        Task poppedTask = tasks[front];
        front = (front + 1) % capacity;

        // Calculate the threshold for high-priority tasks
        int highPriorityThreshold = (int) (0.25 * capacity);

        if (front <= rear) {
            int i = front;
            while (i <= rear && i < front + highPriorityThreshold) {
                if (tasks[i] != null && tasks[i].getPriority() > 5) {
                    i++;
                } else {
                    break;
                }
            }

            if (i <= rear) {
                front = i;
            }
        } else {
            int i = front;
            int count = 0;
            while (count < highPriorityThreshold) {
                if (tasks[i] != null && tasks[i].getPriority() > 5) {
                    i++;
                    count++;
                } else {
                    i++;
                }
            }

            front = i % capacity;
        }

        return poppedTask;
    }

    public void pushFront(Task task) {
        if (isFull()) {
            System.out.println("Deque is full. Cannot push.");
            return;
        }

        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else if (front == 0) {
            front = capacity - 1;
        } else {
            front--;
        }

        tasks[front] = task;
    }

    public void pushRear(Task task) {
        if (isFull()) {
            System.out.println("Deque is full. Cannot push.");
            return;
        }

        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else if (rear == capacity - 1) {
            rear = 0;
        } else {
            rear = rear + 1;
        }

        tasks[rear] = task;
    }

    public Task popFront() {
        if (isEmpty()) {
            System.out.println("Deque is empty. Cannot pop.");
            return null;
        }

        Task poppedTask = tasks[front];

        if (front == rear) {
            front = -1;
            rear = -1;
        } else if (front == capacity - 1) {
            front = 0;
        } else {
            front = front + 1;
        }

        return poppedTask;
    }

    public Task popRear() {
        if (isEmpty()) {
            System.out.println("Deque is empty. Cannot pop.");
            return null;
        }

        Task poppedTask = tasks[rear];

        if (front == rear) {
            front = -1;
            rear = -1;
        } else if (rear == 0) {
            rear = capacity - 1;
        } else {
            rear = rear - 1;
        }

        return poppedTask;
    }

    public Task[] getTasks() {
        return tasks;
    }

   
}
