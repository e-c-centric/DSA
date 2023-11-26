package RestaurantReservationSystem;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReservationSystem reservationSystem = new ReservationSystem(100);
        Waitlist waitlist = new Waitlist();
        OccupancyDisplay occupancyDisplay = new OccupancyDisplay(reservationSystem.getReservationsByCustomer());

        System.out.println("Welcome to the Restaurant Reservation System!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Book a Table");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Add to Waitlist");
            System.out.println("5. Remove from Waitlist");
            System.out.println("6. Display Waitlist");
            System.out.println("7. Display Occupancy Status");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid option.");
                scanner.nextLine(); // Consume the invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    bookTable(reservationSystem, scanner);
                    break;
                case 2:
                    cancelReservation(reservationSystem, scanner);
                    break;
                case 3:
                    viewReservations(reservationSystem);
                    break;
                case 4:
                    addToWaitlist(waitlist, scanner);
                    break;
                case 5:
                    removeFromWaitlist(waitlist);
                    break;
                case 6:
                    displayWaitlist(waitlist);
                    break;
                case 7:
                    displayOccupancyStatus(occupancyDisplay);
                    break;
                case 0:
                    System.out.println("Exiting the Restaurant Reservation System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void bookTable(ReservationSystem reservationSystem, Scanner scanner) {
        try {
            System.out.print("Enter customer first name: ");
            String firstName = scanner.next();
            System.out.print("Enter customer last name: ");
            String lastName = scanner.next();
            System.out.print("Enter customer phone number: ");
            String phoneNumber = scanner.next();
            System.out.print("Enter customer address: ");
            String address = scanner.next();
            System.out.print("Enter customer email: ");
            String email = scanner.next();
            System.out.print("Enter reservation date: ");
            String date = scanner.next();
            System.out.print("Enter reservation time: ");
            String time = scanner.next();

            Customer customer = new Customer(firstName, lastName, phoneNumber, address, email, true, false);
            Table table = new Table(1, 4, true);

            reservationSystem.addReservation(customer, table, date, time);
        } catch (Exception e) {
            System.out.println("Invalid input. Reservation could not be processed.");
            scanner.nextLine(); // Consume the invalid input
        }
    }

    private static void cancelReservation(ReservationSystem reservationSystem, Scanner scanner) {
        System.out.print("Enter customer last name for reservation cancellation: ");
        String customerLastName = scanner.next();
        reservationSystem.cancelReservation(customerLastName);
    }

    private static void viewReservations(ReservationSystem reservationSystem) {
        reservationSystem.displayReservations();
    }

    private static void addToWaitlist(Waitlist waitlist, Scanner scanner) {
        try {
            System.out.print("Enter customer first name: ");
            String firstName = scanner.next();
            System.out.print("Enter customer last name: ");
            String lastName = scanner.next();
            System.out.print("Enter customer phone number: ");
            String phoneNumber = scanner.next();
            System.out.print("Enter customer address: ");
            String address = scanner.next();
            System.out.print("Enter customer email: ");
            String email = scanner.next();

            Customer customer = new Customer(firstName, lastName, phoneNumber, address, email, false, false);
            waitlist.addToWaitlist(customer);
        } catch (Exception e) {
            System.out.println("Invalid input. Customer could not be added to the waitlist.");
            scanner.nextLine(); // Consume the invalid input
        }
    }

    private static void removeFromWaitlist(Waitlist waitlist) {
        waitlist.removeFromWaitlist();
    }

    private static void displayWaitlist(Waitlist waitlist) {
        waitlist.displayWaitlist();
    }

    private static void displayOccupancyStatus(OccupancyDisplay occupancyDisplay) {
        occupancyDisplay.displayOccupancy();
    }
}
