package RestaurantReservationSystem;

public class Waitlist {
    private Queue waitlistQueue;

    public Waitlist() {
        waitlistQueue = new Queue();
    }

    public void addToWaitlist(Customer customer) {
        waitlistQueue.enqueue(customer);
        System.out.println(customer.getName() + " has been added to the waitlist.");
    }

    public void removeFromWaitlist() {
        if (!waitlistQueue.isEmpty()) {
            Customer nextCustomer = waitlistQueue.peek();
            waitlistQueue.dequeue();
            System.out.println(nextCustomer.getName() + " has been removed from the waitlist.");
        } else {
            System.out.println("Waitlist is empty.");
        }
    }

    public void displayWaitlist() {
        if (!waitlistQueue.isEmpty()) {
            System.out.println("Current waitlist:");
            System.out.println(waitlistQueue);
        } else {
            System.out.println("Waitlist is empty.");
        }
    }
}
