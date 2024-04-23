package RestaurantReservationSystem;

/**
 * The OccupancyDisplay class displays the occupancy of the restaurant by
 * iterating over the entries in the reservationTable hash table and printing
 * the table ID, customer name, and status of each reservation.
 * 
 * The time complexity of the displayOccupancy() method is O(n), where n is the
 * number of entries in the reservationTable hash table. This is because the
 * method iterates over each entry in the hash table, performing constant-time
 * operations to access the table, customer, and status values and print them.
 * Since the number of entries in the hash table determines the number of
 * iterations, the time complexity is linear with respect to the number of
 * entries.
 * 
 * @apiNote This class is not used in the final implementation of the
 *         Restaurant Reservation System, but it is included here to demonstrate
 *        the time complexity of the displayOccupancy() method.
 */
public class OccupancyDisplay {
    private HashTable<String, Reservation> reservationTable;

    public OccupancyDisplay(HashTable<String, Reservation> reservationTable) {
        this.reservationTable = reservationTable;
    }

    /**
     * The time complexity is O(n). This is because the method iterates over each
     * entry in the hash table, performing constant-time operations to access the
     * table, customer, and status values and print them. Since the number of
     * entries in the hash table determines the number of iterations, the time
     * complexity is linear with respect to the number of entries.
     */
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
