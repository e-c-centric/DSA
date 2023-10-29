/**
 * This class (Stack) implements a stack of tasks. It uses an array to store the
 * tasks. The stack is bounded, i.e., it has a maximum capacity.In the
 * TaskManager system, this stack is used to store the tasks that are
 * of high priority. The tasks are stored in the order of their priority, i.e.,
 * the task with the highest priority is at the top of the stack, and the task
 * are processed in this order (highest priority task first).
 * 
 * @see Task
 * @author Elikem Asudo Gale-Zoyiku
 */
public class Stack {
    private Task[] tasks;
    private int top;

    /**
     * Constructs a new Stack object with the given size. top is initialized to
     * -1 to indicate that the stack is empty. top is a pointer to the index of
     * the top element of the stack.
     * 
     * @param size the maximum capacity of the stack
     */
    public Stack(int size) {
        tasks = new Task[size];
        top = -1;
    }

    /**
     * Pushes a task onto the stack, i.e. adds a task to the top of the stack.
     * If the stack is full, it prints "Stack is full". The stack is sorted in
     * descending order of priority using the quick sort algorithm. *
     * 
     * @apiNote top is pre-incremented to point to the next available position in
     *          the
     *          array, i.e., top is incremented before it is used in the
     *          expression.
     * @param task the task to be pushed onto the stack
     * @see #sortStack(int, int)
     */
    public void push(Task task) {
        if (top == tasks.length - 1) {
            System.out.println("Stack is full");
        } else {
            tasks[++top] = task;
            sortStack(0, top);
        }
    }

    /**
     * Pops a task from the stack, i.e. removes and returns a task from the top of
     * the stack.
     * If the stack is empty, it prints "Stack is empty".
     * 
     * @apiNote The task at the top of the stack is returned. top is then
     *          decremented to point to the next element in the
     *          stack. If top is -1, it means that the stack is empty.
     * 
     * @return the task that was popped from the stack
     */
    public Task pop() {
        if (top == -1) {
            System.out.println("Stack is empty");
            return null;
        } else {
            Task task = tasks[top];
            tasks[top--] = null;
            return task;
        }
    }

    /**
     * Returns the task at the top of the stack, i.e. the task that was most
     * recently pushed onto the stack. If the stack is empty, it prints "Stack
     * is empty".
     * 
     * @return the task at the top of the stack
     */
    public Task top() {
        if (top == -1) {
            System.out.println("Stack is empty");
            return null;
        } else {
            return tasks[top];
        }
    }

    /**
     * Returns <code>true</code> if the stack is empty, i.e., if top is -1.
     * 
     * @return <code>true</code> if the stack is empty, <code>false</code>
     *         otherwise
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Returns the array of tasks.
     * 
     * @return the array of tasks
     */
    public Task[] getTasks() {
        return tasks;
    }

    /**
     * Sorts the stack in descending order of priority using the quick sort
     * algorithm. The pivot is the last element in the array. The stack is
     * partitioned such that all elements with a priority greater than the pivot
     * are to the left of the pivot, and all elements with a priority less than
     * the pivot are to the right of the pivot. The pivot is then placed in its
     * correct position. The stack is then partitioned again, and the process
     * continues recursively until the stack is sorted.
     * 
     * @param low  the lowest index of the array
     * @param high the highest index of the array
     * @see #partition(int, int)
     * @apiNote This method is called by ONLY the push method.
     * @apiNote Helper method for the push method. Helps maintain the task with
     *          higher priorities are popped before those with lower priorities.
     */
    private void sortStack(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);
            sortStack(low, pivotIndex - 1);
            sortStack(pivotIndex + 1, high);
        }
    }

    /**
     * Partitions the stack such that all elements with a priority greater than
     * the pivot are to the left of the pivot, and all elements with a priority
     * less than the pivot are to the right of the pivot. The pivot is then
     * placed in its correct position.
     * 
     * @param low  the lowest index of the array
     * @param high the highest index of the array
     * @return the index of the pivot
     * @see #sortStack(int, int)
     * @apiNote This method is called by ONLY the sortStack method.
     * @apiNote Helper method for the sortStack method.
     */
    private int partition(int low, int high) {
        Task pivot = tasks[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (tasks[j].getPriority() > pivot.getPriority()) {
                i++;
                Task temp = tasks[i];
                tasks[i] = tasks[j];
                tasks[j] = temp;
            }
        }
        Task temp = tasks[i + 1];
        tasks[i + 1] = tasks[high];
        tasks[high] = temp;
        return i + 1;
    }
}