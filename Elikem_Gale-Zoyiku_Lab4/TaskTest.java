import java.util.Scanner;

class Task {
    private int task_id;
    private String description;
    private String status;
    private int priority;

    public Task(int task_id, String description, String status, int priority) {
        this.task_id = task_id;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }

    public int getTask_id() {
        return task_id;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }
}

class Stack {
    Task[] stack;
    int top;

    public Stack(int size) {
        stack = new Task[size];
        top = -1;
    }

    public void push(Task task) {
        if (top == stack.length - 1) {
            System.out.println("Stack Overflow");
        } else {
            top++;
            stack[top] = task;
        }
    }

    public Task pop() {
        if (top == -1) {
            System.out.println("Stack Underflow");
            return null;
        } else {
            Task task = stack[top];
            top--;
            return task;
        }
    }

    public Task top() {
        if (top == -1) {
            System.out.println("Stack is empty");
            return null;
        } else {
            return stack[top];
        }
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == stack.length - 1;
    }
}

class Queue {
    Task[] queue;
    int front;
    int rear;

    public Queue(int size) {
        queue = new Task[size];
        front = -1;
        rear = -1;
    }

    public void enqueue(Task task) {
        if (rear == queue.length - 1) {
            System.out.println("Queue Overflow");
        } else {
            rear++;
            queue[rear] = task;
            if (front == -1) {
                front = 0;
            }
        }
    }

    public Task dequeue() {
        if (front == -1 || front > rear) {
            System.out.println("Queue Underflow");
            return null;
        } else {
            Task task = queue[front];
            front++;
            return task;
        }
    }

    public boolean isEmpty() {
        return front == -1 || front > rear;
    }

    public boolean isFull() {
        return rear == queue.length - 1;
    }
}

public class TaskTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack stack = new Stack(10);
        Queue queue = new Queue(10);
        Queue completedTasks = new Queue(10);
        int task_id = 0;

        while (true) {
            System.out.println("Task Management System");
            System.out.println("1. Add Task");
            System.out.println("2. Process Task");
            System.out.println("3. View Stack");
            System.out.println("4. View Queue");
            System.out.println("5. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    scanner.nextLine();
                    String description = scanner.nextLine();
                    System.out.print("Enter task priority (0-10): ");
                    int priority = scanner.nextInt();
                    Task task = new Task(++task_id, description, "pending", priority);
                    if (priority >= 5) {
                        stack.push(task);
                    } else {
                        queue.enqueue(task);
                    }
                    System.out.println("Task added successfully");
                    break;
                case 2:
                    if (!stack.isEmpty()) {
                        Task highPriorityTask = stack.pop();
                        highPriorityTask.setStatus("completed");
                        completedTasks.enqueue(highPriorityTask);
                        System.out.println("High priority task processed and moved to completed tasks");
                    } else if (!queue.isEmpty()) {
                        Task normalPriorityTask = queue.dequeue();
                        normalPriorityTask.setStatus("completed");
                        completedTasks.enqueue(normalPriorityTask);
                        System.out.println("Normal priority task processed and moved to completed tasks");
                    } else {
                        System.out.println("No tasks to process");
                    }
                    break;
                case 3:
                    if (stack.isEmpty()) {
                        System.out.println("Stack is empty");
                    } else {
                        System.out.println("Stack:");
                        for (int i = stack.top; i >= 0; i--) {
                            Task taskInStack = stack.stack[i];
                            System.out.println(taskInStack.getTask_id() + " " + taskInStack.getDescription() + " " + taskInStack.getStatus() + " " + taskInStack.getPriority());
                        }
                    }
                    break;
                case 4:
                    if (queue.isEmpty()) {
                        System.out.println("Queue is empty");
                    } else {
                        System.out.println("Queue:");
                        for (int i = queue.front; i <= queue.rear; i++) {
                            Task taskInQueue = queue.queue[i];
                            System.out.println(taskInQueue.getTask_id() + " " + taskInQueue.getDescription() + " " + taskInQueue.getStatus() + " " + taskInQueue.getPriority());
                        }
                    }
                    break;
                case 5:
                    System.out.println("Exiting Task Management System");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
