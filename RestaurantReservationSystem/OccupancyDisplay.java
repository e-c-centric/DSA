package RestaurantReservationSystem;

public class OccupancyDisplay {
    private HashTable<String, Reservation> reservationTable;

    public OccupancyDisplay(HashTable<String, Reservation> reservationTable) {
        this.reservationTable = reservationTable;
    }

    public void displayOccupancy() {
        Entry<String, Reservation>[] entries = reservationTable.getAllEntries();
        for (Entry<String, Reservation> entry : entries) {
            if (entry != null && entry.getValue() != null) {
                Reservation reservation = entry.getValue();
                Table tableId = reservation.getTable();
                Customer customerName = reservation.getCustomer();
                String status = reservation.getStatus();

                System.out.println("Table ID: " + tableId +
                        ", Customer: " + customerName +
                        ", Status: " + status);
            }
        }
    }
}
