/**
 * An array-based deque that stores and processes tasks based on priority.
 */
public class Deque {
    private Task[] tasks; // The array to store tasks.
    private int front; // The index of the front element.
    private int rear; // The index of the rear element.
    private int size;

    /**
     * Constructs a new ArrayDeque with the given size.
     * 
     * @param size The maximum capacity of the deque.
     */
    public Deque(int size) {
        tasks = new Task[size];
        front = -1;
        rear = -1;
        size = 0;
    }

    /**
     * Pushes a task into the deque based on its priority.
     * If the task has a priority greater than or equal to 5, it is inserted at
     * the front in descending order of priority. If the task's priority is less
     * than 5, it is added to the rear.
     * 
     * @param task The task to be pushed into the deque.
     * @see #pushNormal(Task)
     */
    public void push(Task task) {
        if (task.getPriority() >= 5) {
            if (isEmpty() || tasks[front].getPriority() <= task.getPriority()) {
                pushNormal(task);
            } else {
                int highPriorityEnd = front;
                while (highPriorityEnd <= rear && tasks[highPriorityEnd].getPriority() >= 5) {
                    highPriorityEnd++;
                }

                if (highPriorityEnd > front) {
                    int i = highPriorityEnd;
                    Task prev = task;
                    while (i <= rear) {
                        Task temp = tasks[i];
                        tasks[i] = prev;
                        prev = temp;
                        i++;
                    }
                }

                tasks[front] = task;
            }
        } else {
            pushNormal(task);
        }
        size++;
    }

    /**
     * Pops a task from the front of the deque.
     * If the deque is not empty and a number of tasks equal to a quarter of the
     * array's length have been popped from the front, it checks if there are
     * still high-priority tasks. If high-priority tasks exist, it moves to the
     * first normal-priority item and pops a quarter of the normal-priority tasks.
     * 
     * @return The task that was popped from the front of the deque.
     */
    public Task pop() {
        if (isEmpty()) {
            System.out.println("Deque is empty");
            return null;
        }

        Task task = tasks[front];
        tasks[front] = null;
        front++;

        if (front > tasks.length / 4) {
            int highPriorityEnd = front;
            while (highPriorityEnd <= rear && tasks[highPriorityEnd].getPriority() >= 5) {
                highPriorityEnd++;
            }

            if (highPriorityEnd < rear) {
                int normalEnd = highPriorityEnd;
                while (normalEnd <= rear && tasks[normalEnd].getPriority() < 5) {
                    normalEnd++;
                }

                if (normalEnd <= rear) {
                    int i = highPriorityEnd;
                    int j = normalEnd;
                    while (j <= rear) {
                        tasks[i++] = tasks[j++];
                    }

                    for (int k = rear - (normalEnd - highPriorityEnd) + 1; k <= rear; k++) {
                        tasks[k] = null;
                    }
                    rear = rear - (normalEnd - highPriorityEnd);
                } else {
                    for (int k = front; k <= rear; k++) {
                        tasks[k] = null;
                    }
                    front = -1;
                    rear = -1;
                }
            }
        }
        size--;
        return task;
    }

    /**
     * Pushes a task to the rear of the deque.
     * 
     * @param task The task to be pushed to the rear of the deque.
     */
    public void pushNormal(Task task) {
        if (rear == tasks.length - 1) {
            System.out.println("Deque is full");
        } else {
            if (isEmpty()) {
                front = 0;
            }
            rear++;
            tasks[rear] = task;
        }
    }

    /**
     * Checks if the deque is empty.
     * 
     * @return {@code true} if the deque is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Gets the tasks in the deque.
     * 
     * @return The tasks in the deque.
     */
    public Task[] getTasks() {
        Task[] content = new Task[size];
        if (isEmpty()) {
            return content;
        }

        int index = 0;
        int i = front;
        while (true) {
            content[index] = tasks[i];
            index++;
            if (index == size) {
                break;
            }
            i = (i + 1) % tasks.length;
        }
        return content;
    }

    /**
     * Prints the content of the deque.
     * 
     * @param deque The deque whose content is to be printed.
     */
    public static void printDequeContent(Deque deque) {
        Task[] tasks = deque.getTasks();
        for (Task task : tasks) {
            System.out.println(task);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Deque deque = new Deque(10);
        Random rand = new Random();

        // Pushing tasks with various priorities into the deque.
        for (int i = 0; i < 10; i++) {
            int priority = rand.nextInt(10) + 1;
            Task task = new Task(i + 1, "Task " + (i + 1), Task.Status.PENDING, priority);
            deque.push(task);
        }

        // Printing the deque's content.
        System.out.println("Initial Deque Contents:");
        printDequeContent(deque);

        // Poping tasks from the deque.
        for (int i = 0; i < 5; i++) {
            Task task = deque.pop();
            System.out.println("Popped Task: " + task.getDescription());
        }

        // Printing the deque's content after popping.
        System.out.println("Deque Contents After Popping:");
        printDequeContent(deque);
    }

}