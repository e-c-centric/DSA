import java.util.EnumSet;

/**
 * This class represents a task with a unique ID, description, status, and
 * priority.
 * The status can be either pending or completed.
 * The priority is an integer between 0 and 10.
 * The class has a constructor, getters, setters, and an enum for the status.
 * The class also has a toString method that returns a string representation of
 * the task.
 * 
 * @author Elikem Asudo Gale-Zoyiku
 * @throws IllegalArgumentException if the priority is not between 0 and 10
 * @throws IllegalArgumentException if the status is not a valid Status enum
 *                                  value
 */
public class Task {
    private int task_id;
    private String description;
    private Status status;
    private int priority;

    /**
     * Constructs a new Task object with the given task ID, description, status,
     * and priority. Inokes the setPriority and setStatus methods to validate the
     * priority and status.
     * 
     * @see Task#setPriority(int)
     * @see Task#setStatus(Status)
     * @param task_id     the unique ID of the task
     * @param description the description of the task
     * @param status      the status of the task
     * @param priority    the priority of the task
     * @throws IllegalArgumentException if the priority is not between 0 and 10
     * @throws IllegalArgumentException if the status is not a valid Status enum
     *                                  value
     */
    public Task(int task_id, String description, Status status, int priority) {
        this.task_id = task_id;
        this.description = description;
        setStatus(status);
        setPriority(priority);
    }

    /**
     * Returns the task ID of the task.
     * 
     * @return the task ID of the task
     */
    public int getTask_id() {
        return task_id;
    }

    /**
     * Sets the task ID of the task.
     * 
     * @param task_id the task ID of the task
     */
    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    /**
     * Returns the description of the task.
     * 
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     * 
     * @param description the description of the task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the status of the task.
     * 
     * @return the status of the task
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the status of the task.
     * 
     * @param status the status of the task
     * @throws IllegalArgumentException if the status is not a valid Status enum
     *                                  value, i.e., not PENDING or COMPLETED
     */
    public void setStatus(Status status) {
        if (!EnumSet.of(Status.PENDING, Status.COMPLETED).contains(status)) {
            throw new IllegalArgumentException("Invalid status value: " + status);
        }
        this.status = status;
    }

    /**
     * Returns the priority of the task.
     * 
     * @return the priority of the task
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the priority of the task.
     * 
     * @param priority the priority of the task
     * @throws IllegalArgumentException if the priority is not between 0 and 10
     */
    public void setPriority(int priority) {
        if (priority < 0 || priority > 10) {
            throw new IllegalArgumentException("Priority must be between 0 and 10");
        }
        this.priority = priority;
    }

    /**
     * This enum represents the status of a task. A task can either be pending or
     * completed.
     */
    public enum Status {
        PENDING,
        COMPLETED
    }

    /**
     * Returns a string representation of the task.
     * 
     * @return a string representation of the task
     */
    public String toString() {
        return "\n" + task_id + ": " + description + "\nStatus: " + status + "\nPriority: " + priority + "\n";
    }

    /**
     * Returns true if the given object is equal to this task. Two tasks are equal
     * if they have the same task ID and status.
     * 
     * @param otherTask the object to compare to this task
     * @return <code>true</code> if the given object is equal to this task
     */
    public boolean equals(Task newTask) {
        if (this.task_id == newTask.task_id && this.status == newTask.status) {
            return true;
        } else {
            return false;
        }
    }
}