package RestaurantReservationSystem;

public class OccupancyDisplay {
    private HashTable<String, Reservation> reservationTable;

    public OccupancyDisplay(HashTable<String, Reservation> reservationTable) {
        this.reservationTable = reservationTable;
    }

    public void displayOccupancy() {
        for (Reservation reservation : (Reservation[])reservationTable.getAllEntries()) {
            Table tableId = reservation.getTable();
            Customer customerName = reservation.getCustomer();
            String status = reservation.getStatus();

            System.out.println("Table ID: " + tableId +
                    ", Customer: " + customerName +
                    ", Status: " + status);
        }
    }
}