package RestaurantReservationSystem;

public class ReservationSystem {
    private HashTable<String, Reservation> reservationsByCustomer;

    public ReservationSystem(int initialCapacity) {
        this.reservationsByCustomer = new HashTable<>(initialCapacity);
    }

    public void addReservation(Customer customer, Table table, String date, String time) {
        Reservation newReservation = new Reservation(customer, table, date, time);
        reservationsByCustomer.insert(newReservation);
        System.out.println("Reservation added successfully.");
    }

    public void cancelReservation(String customerName) {
        Reservation reservationToDelete = reservationsByCustomer.search(customerName);

        if (reservationToDelete != null) {
            reservationsByCustomer.delete(customerName);
            System.out.println("Reservation canceled successfully.");
        } else {
            System.out.println("Reservation not found for customer: " + customerName);
        }
    }

    public void displayReservations() {
        System.out.println("Current Reservations:");
        System.out.println(reservationsByCustomer);
    }
}
