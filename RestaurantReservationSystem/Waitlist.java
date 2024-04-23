package RestaurantReservationSystem;

/**
 * This class represents the waitlist for the restaurant. The waitlist is a queue of customers. 
 * Customers are added to the waitlist when they request a table and there are no available tables.
 * Customers are removed from the waitlist when a table becomes available.
 * 
 * @see Queue
 */
public class Waitlist {
    private Queue waitlistQueue;

    /**
     * Constructs a new Waitlist object. The waitlist is a queue of customers. 
     * @see Queue
     */
    public Waitlist() {
        waitlistQueue = new Queue();
    }

    /**
     * Adds a customer to the waitlist. 
     * @param customer the customer to be added to the waitlist
     */
    public void addToWaitlist(Customer customer) {
        waitlistQueue.enqueue(customer);
        System.out.println(customer.getName() + " has been added to the waitlist.");
    }

    /**
     * Removes the next customer from the waitlist. 
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
     * @see Queue#toString()
     */
    public void displayWaitlist() {
        if (!waitlistQueue.isEmpty()) {
            System.out.println("Current waitlist:");
            System.out.println(waitlistQueue);
        } else {
            System.out.println("Waitlist is empty.");
        }
    }
}
