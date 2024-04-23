package RestaurantReservationSystem;

/**
 * This class represents the waitlist for the restaurant. The waitlist is a
 * queue of customers.
 * Customers are added to the waitlist when they request a table and there are
 * no available tables.
 * Customers are removed from the waitlist when a table becomes available.
 * 
 * @see Queue
 *      The time complexity of the Waitlist class methods addToWaitlist(),
 *      removeFromWaitlist(), displayWaitlist(), isEmpty(), and peek() is O(1),
 *      which
 *      means they have constant time complexity. This is because these methods
 *      all
 *      perform a fixed number of operations, regardless of the size of the
 *      waitlist.
 * 
 */
public class Waitlist {
    private Queue waitlistQueue;

    /**
     * Constructs a new Waitlist object. The waitlist is a queue of customers.
     * 
     * @see Queue
     */
    public Waitlist() {
        waitlistQueue = new Queue();
    }

    /**
     * Adds a customer to the waitlist. Invokes the enqueue method of the queue,
     * which has a time complexity of O(1).
     * 
     * @param customer the customer to be added to the waitlist
     * 
     *                 Time complexity is O(1)
     */

    public void addToWaitlist(Customer customer) {
        waitlistQueue.enqueue(customer);
        System.out.println(customer.getName() + " has been added to the waitlist.");
    }

    /**
     * Removes the next customer from the waitlist. Invokes the dequeue method of
     * the queue, which has a time complexity of O(1).
     * 
     * Time complexity is O(1)
     */
    public void removeFromWaitlist() {
        if (!waitlistQueue.isEmpty()) {
            Customer nextCustomer = waitlistQueue.peek();
            waitlistQueue.dequeue();
            System.out.println(nextCustomer.getName() + " has been removed from the waitlist.");
        } else {
            System.out.println("Waitlist is empty.");
        }
    }

    /**
     * Displays the waitlist.
     * 
     * Time complexity is O(n)
     * 
     * @see Queue#toString()
     * 
     */
    public void displayWaitlist() {
        if (!waitlistQueue.isEmpty()) {
            System.out.println("Current waitlist:");
            System.out.println(waitlistQueue);
        } else {
            System.out.println("Waitlist is empty.");
        }
    }

    /**
     * Time complexity is O(1). Invokes the isEmpty method of the queue, which has a
     * time complexity of O(1).
     * 
     * @return true if the waitlist is empty, false otherwise
     */
    public boolean isEmpty() {
        return waitlistQueue.isEmpty();
    }

    /**
     * Time complexity is O(1). Invokes the peek method of the queue, which has a
     * time complexity of O(1).
     * 
     * @return the customer at the front of the waitlist
     */
    public Customer peek() {
        return waitlistQueue.peek();
    }
}
