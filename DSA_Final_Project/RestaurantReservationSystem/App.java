package RestaurantReservationSystem;

import java.util.Scanner;

/**
 * The time complexity of the main method in the App class is O(n),
 * where n is the number of iterations in the while loop. The while loop
 * runs indefinitely until the program is exited, but each iteration of the loop
 * takes
 * constant time to execute the menu display and handle the user's choice.
 * The other methods in the App class have time complexity that is independent
 * of
 * the input size and can be considered O(1) as well.
 */

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ReservationSystem reservationSystem = new ReservationSystem(13);
        BinarySearchTree tableAvailabilityTree = new BinarySearchTree();
        initializeTables(tableAvailabilityTree);
        Waitlist waitlist = new Waitlist();

        while (true) {
            displayMenu();

            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    makeReservation(scanner, reservationSystem, tableAvailabilityTree, waitlist);
                    break;
                case 2:
                    cancelReservation(scanner, reservationSystem, waitlist, tableAvailabilityTree);
                    break;
                case 3:
                    reservationSystem.displayReservations();
                    break;
                case 4:
                    waitlist.displayWaitlist();
                    break;
                case 5:
                    displayOccupancyOverview(reservationSystem, waitlist);
                    break;
                case 6:
                    System.out.println("Exiting the program. Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Time complexity of O(1)
     */
    private static void displayMenu() {
        System.out.println("\n===== Restaurant Reservation System =====");
        System.out.println("1. Make a Reservation");
        System.out.println("2. Cancel a Reservation");
        System.out.println("3. View Reservations");
        System.out.println("4. View Waitlist");
        System.out.println("5. View Occupancy Overview");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
        return scanner.nextInt();
    }

    /**
     * The time complexity of the makeReservation function is O(log n), where n is the
     * number of input elements. This is because the function performs a series of
     * constant-time operations (such as input reading and variable assignment) and
     * a single call to the findAvailableTable function, which has a time complexity
     * of O(log n) when using a binary search tree. Therefore, the dominant time
     * complexity is O(log n).
     * 
     * @param scanner
     * @param reservationSystem
     * @param tableAvailabilityTree
     * @param waitlist
     */
    private static void makeReservation(Scanner scanner, ReservationSystem reservationSystem,
            BinarySearchTree tableAvailabilityTree, Waitlist waitlist) {
        System.out.print("Enter customer first name: ");
        String firstName = scanner.next();

        System.out.print("Enter customer last name: ");
        String lastName = scanner.next();

        System.out.print("Enter customer phone number: ");
        String phoneNumber = scanner.next();

        System.out.print("Enter customer address: ");
        String address = scanner.nextLine();
        scanner.nextLine();

        System.out.print("Enter customer email address: ");
        String email = scanner.next();
        scanner.nextLine();

        System.out.print("How many guests will you be expecting: ");
        int guestNum = scanner.nextInt();
        scanner.nextLine();

        Customer customer = new Customer(firstName, lastName, phoneNumber, address, email, true, guestNum);

        System.out.print("Enter reservation date (YYYY-MM-DD): ");
        String date = scanner.next();

        System.out.print("Enter reservation time: ");
        String time = scanner.next();

        Table availableTable = findAvailableTable(tableAvailabilityTree, guestNum);

        if (availableTable != null) {
            reservationSystem.addReservation(customer, availableTable, date, time);
            System.out.println("Reservation added successfully.");
        } else {
            waitlist.addToWaitlist(customer);
            System.out.println("No available tables. Added to the waitlist.");
        }
    }

    /**
     * The time complexity of the initializeTables method is O(1) because the for
     * loop iterates a fixed number of times (10 times in this case), which does not
     * depend on the size of the input.
     * 
     * @param tableAvailabilityTree
     */
    private static void initializeTables(BinarySearchTree tableAvailabilityTree) {
        for (int i = 1; i <= 10; i++) {
            Table table = new Table(i, i, true);
            tableAvailabilityTree.insert(table);
        }
    }

    /**
     * The time complexity of the cancelReservation function depends on the search
     * operation in the BinarySearchTree. Assuming the BinarySearchTree is balanced,
     * the search operation has a time complexity of O(log n), where n is the number
     * of nodes in the tree. The rest of the operations in the function (reservation
     * cancellation and waitlist processing) have constant-time complexity.
     * 
     * @param scanner
     * @param reservationSystem
     * @param waitlist
     * @param tableAvailabilityTree
     */
    private static void cancelReservation(Scanner scanner, ReservationSystem reservationSystem, Waitlist waitlist,
            BinarySearchTree tableAvailabilityTree) {
        System.out.print("Enter customer last name for cancellation: ");
        String lastName = scanner.next();

        Reservation reservationToDelete = reservationSystem.getReservationsByCustomer().search(lastName);

        if (reservationToDelete != null) {
            reservationSystem.cancelReservation(lastName);
            System.out.println("Reservation canceled successfully.");
            processWaitlist(reservationSystem, tableAvailabilityTree, waitlist);
        } else {
            System.out.println("Reservation not found for customer: " + lastName);
        }
    }

    /**
     * The time complexity of the processWaitlist function depends on the number of
     * customers in the waitlist and the number of tables in the
     * tableAvailabilityTree. The function uses a while loop to iterate over the
     * waitlist, which takes O(n) time, where n is the number of customers in the
     * waitlist. Inside the loop, the function calls the findAvailableTable
     * function, which searches for an available table in the tableAvailabilityTree.
     * The time complexity of this operation depends on the size of the binary
     * search tree and is typically O(log n), where n is the number of nodes in the
     * tree. Therefore, the overall time complexity of the function is O(n log n).
     * 
     * @param reservationSystem
     * @param tableAvailabilityTree
     * @param waitlist
     */
    private static void processWaitlist(ReservationSystem reservationSystem, BinarySearchTree tableAvailabilityTree,
            Waitlist waitlist) {
        while (!waitlist.isEmpty()) {
            Customer nextCustomer = waitlist.peek();
            Table availableTable = findAvailableTable(tableAvailabilityTree, 4);

            if (availableTable != null) {
                waitlist.removeFromWaitlist();
                reservationSystem.addReservation(nextCustomer, availableTable, "2023-12-01", "19:00");
                System.out.println("Reservation for " + nextCustomer.getName() + " added from waitlist.");
            } else {
                break; // No more available tables
            }
        }
    }

    /**
     * The displayOccupancyOverview function has a time complexity of O(n), where n
     * is the number of elements in the reservationsByCustomer hashtable. This is
     * because it calls the getAllEntries method, which iterates over all the
     * entries in the hashtable and prints them. The waitlist displayWaitlist method
     * also has a time
     * complexity of O(n), where n is the number of elements in the waitlist, as it
     * iterates over all the elements and prints them.
     * 
     * @param reservationsByCustomer
     * @param waitlist
     */
    private static void displayOccupancyOverview(ReservationSystem reservationsByCustomer,
            Waitlist waitlist) {
        System.out.println("Current Reservations:");
        reservationsByCustomer.displayReservations();
        
        System.out.println("\nWaitlist:");
        waitlist.displayWaitlist();
    }

    /**
     * The time complexity of the findAvailableTable function is O(log n), where n
     * is the number of nodes in the binary search tree. This is because the
     * function uses a binary search tree to search for a table with the given
     * capacity. In a balanced binary search tree, the height is logarithmic in the
     * number of nodes, so the search operation takes O(log n) time.
     * 
     * @param tableAvailabilityTree
     * @param capacity
     * @return
     */
    private static Table findAvailableTable(BinarySearchTree tableAvailabilityTree, int capacity) {
        return findTableWithCapacity(tableAvailabilityTree.getRoot(), capacity);
    }

    /**
     * The time complexity of the findTableWithCapacity function is O(n), where n is
     * the number of nodes in the binary tree. This is because the function
     * recursively traverses the tree, visiting each node once. In the worst case,
     * the function visits every node in the tree, resulting in a time complexity of
     * O(n).
     * 
     * @param node
     * @param capacity
     * @return
     */
    private static Table findTableWithCapacity(Node node, int capacity) {
        if (node != null) {
            if (node.getTable().getCapacity() >= capacity && node.getTable().getAvailablity()) {
                return node.getTable();
            } else {
                Table leftResult = findTableWithCapacity(node.getLeft(), capacity);
                Table rightResult = findTableWithCapacity(node.getRight(), capacity);
                return (leftResult != null) ? leftResult : rightResult;
            }
        }
        return null;
    }
}