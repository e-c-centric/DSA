import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        System.out.println("\u001B[32m" + "Welcome to the Task Management System" + "\u001B[0m");
        System.out.println("Would you like to use the default size of 10 for the stack and queue? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String choiceSize = scanner.nextLine();
        Stack stack;
        Queue queue;
        Queue completedTasks;
       
        if (choiceSize.equalsIgnoreCase("y")) {
            stack = new Stack(10);
            queue = new Queue(10);
            completedTasks = new Queue(20);
        } else {
            System.out.println("Enter the size of the stack: ");
            int stackSize = scanner.nextInt();
            System.out.println("Enter the size of the queue: ");
            int queueSize = scanner.nextInt();
            stack = new Stack(stackSize);
            queue = new Queue(queueSize);
            completedTasks = new Queue(stackSize + queueSize);
        }

        int task_id = 0;

        while (true) {
            System.out.println("\n" + "\u001B[34m");
            System.out.println("Task Management System");
            System.out.println("1. Add Task");
            System.out.println("2. Process Task");
            System.out.println("3. View High Priority Tasks");
            System.out.println("4. View Normal Priority Tasks");
            System.out.println("5. View Completed Tasks");
            System.out.println("6. Quit");
            System.out.println("\u001B[0m \n");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            System.out.println("\n");

            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    scanner.nextLine();
                    String description = scanner.nextLine();
                    System.out.print("Enter task priority (0-10): ");
                    int priority = scanner.nextInt();
                    Task task = new Task(++task_id, description, Task.Status.PENDING, priority);
                    if (priority >= 5) {
                        stack.push(task);
                    } else {
                        queue.enqueue(task);
                    }
                    System.out.println("Task added successfully");
                    break;
                case 2:
                    System.out.println("Processing...\nFetching tasks...\n");
                    if (!stack.isEmpty()) {
                        Task highPriorityTask = stack.pop();
                        highPriorityTask.setStatus(Task.Status.COMPLETED);
                        completedTasks.enqueue(highPriorityTask);
                        System.out.println("High priority task processed and moved to completed tasks");
                    }else if (!queue.isEmpty()) {
                        Task normalPriorityTask = queue.dequeue();
                        normalPriorityTask.setStatus(Task.Status.COMPLETED);
                        completedTasks.enqueue(normalPriorityTask);
                        System.out.println("Normal priority task processed and moved to completed tasks");
                    } else {
                        System.out.println("No tasks to process");
                    }
                    break;
                case 3:
                    if (stack.isEmpty()) {
                        System.out.println("\u001B[92m" + "No uncompleted high priority tasks. Go you!" + "\u001B[0m");
                    } else {
                        System.out.println("Stack:");
                        for (Task i : stack.getTasks()) {
                            if (i != null) {
                                System.out.println(i);
                            }
                            ;
                        }
                    }
                    break;
                case 4:
                    if (queue.isEmpty()) {
                        System.out
                                .println("\u001B[92m" + "No uncompleted normal priority tasks. Go you!" + "\u001B[0m");
                    } else {
                        System.out.println("Queue:");
                        for (Task i : queue.getTasks()) {
                            if (i != null) {
                                System.out.println(i);
                            }
                            ;
                        }
                    }
                    break;
                case 5:
                    if (completedTasks.isEmpty()) {
                        System.out.println(
                                "\u001B[31m" + "No tasks have been completed. Be more productive!" + "\u001B[0m");
                    } else {
                        System.out.println("Completed tasks:");
                        for (Task i : completedTasks.getTasks()) {
                            if (i != null) {
                                System.out.println(i);
                            }
                            ;
                        }
                    }
                    break;
                case 6:
                    System.out.println("Exiting Task Management System");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\u001B[31m" + "Invalid choice" + "\u001B[0m");
            }
        }
    }
}