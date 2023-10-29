/**
 * This class (Queue) implements a queue of tasks. It uses an array to store
 * the tasks. The queue is bounded, i.e., it has a maximum capacity. In the
 * TaskManager system, this queue is used to store the tasks that are of normal
 * priority. The tasks are stored in the order of their arrival, i.e., the
 * task that arrived first is at the front of the queue, and the tasks are
 * processed in this order (first come first served).
 * 
 * @see Task
 */
public class Queue {
    private Task[] tasks;
    private int front, rear, size;

    /**
     * Constructs a new Queue object with the given size.
     * front and rear are initialized to -1 to indicate that the queue is
     * empty. front is the index of the front of the queue, and rear is the
     * index of the rear of the queue.
     * 
     * @param size the maximum capacity of the queue
     */
    public Queue(int size) {
        tasks = new Task[size];
        front = -1;
        rear = -1;
        this.size = size;
    }

    /**
     * Enqueues a task, i.e. adds a task to the rear of the queue. If the queue
     * is full, it prints <strong>"Queue is full"</strong>.
     * 
     * @apiNote If the queue is empty, <code>front</code> is set to 0 to indicate
     *          that the
     *          queue
     *          is not empty. This is because <code>front</code> is initialized to
     *          -1. Also,
     *          <code>front</code> is a
     *          pointer to the index of the front element of the queue. So once
     *          there is an
     *          element in the queue, <code>front</code> is set to 0 to point to the
     *          first
     *          element in the
     *          array. <code>rear</code> is then incremented to point to the next
     *          available
     *          position in
     *          the array. <code>rear</code> is a pointer to the index of the rear
     *          element of the
     *          queue.
     * 
     * 
     * @param task the task to be enqueued
     */
    public void enqueue(Task task) {
        if (rear == size - 1) {
            System.out.println("Queue is full");
        } else {
            if (front == -1) {
                front = 0;
            }
            tasks[++rear] = task;// rear is pre-incremented to point to the next available position in the array,
                                 // i.e., rear is incremented before it is used in the expression.
        }
    }

    /**
     * Dequeues a task, i.e. removes a task from the front of the queue. If the
     * queue is empty, it prints <strong>"Queue is empty"</strong>.
     * 
     * @apiNote If the queue is not empty, the task at the front of the queue is
     *          returned. front is then incremented to point to the next element in
     *          the
     *          queue. If front is greater than rear, it means that the queue is
     *          empty.
     * 
     * @return the task that was dequeued
     */
    public Task dequeue() {
        if (front == -1 || front > rear) {
            System.out.println("Queue is empty");
            return null;
        } else {
            Task task = tasks[front++];// The reason for using front++ instead of front is that front++ post-increments
                                       // the value of front after it is used in the expression. This means that the
                                       // value of front is incremented after the task object is assigned to the task
                                       // variable, so the method ends with the correct values of the task object and
                                       // front.
            if (front > rear) {
                front = rear = -1;
            }
            return task;
        }
    }

    /**
     * Returns true if the queue is empty, i.e., if front is greater than rear.
     * 
     * @return <code>true</code> if the queue is empty
     */
    public boolean isEmpty() {
        return front > rear;
    }

    /**
     * Returns the tasks in the queue.
     * 
     * @return the tasks in the queue
     */
    public Task[] getTasks() {
        return tasks;
    }
}