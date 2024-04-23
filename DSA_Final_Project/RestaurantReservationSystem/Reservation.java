package RestaurantReservationSystem;

/**
 * This class represents a reservation in the restaurant reservation system. A
 * reservation has a customer, table, date, time, and status. The status of a
 * reservation can be either "Available" or "Booked".
 * 
 * The Reservation class does not contain any loops or recursive calls, so the
 * time complexity of each method is O(1), meaning it takes a constant amount of
 * time to
 * execute regardless of the size of the input.
 */
public class Reservation extends Entry<String, Reservation> {
    private Customer customer;
    private Table table;
    private String date;
    private String time;
    private String status = "Available";

    /**
     * Constructs a new Reservation object with the specified customer, table, date,
     * and time. The status of the reservation is set to "Booked" and the
     * availability of the table is set to false. The key of the reservation is the
     * customer's last name. The value of the reservation is null.
     * 
     * @param customer the customer of the reservation
     * @param table    the table of the reservation
     * @param date     the date of the reservation
     * @param time     the time of the reservation
     */
    public Reservation(Customer customer, Table table, String date, String time) {
        super(customer.getLastName(), null);
        this.customer = customer;
        this.table = table;
        this.date = date;
        this.time = time;
        this.status = "Booked";
        this.table.setAvailablility(false);
    }

    /**
     * Time complexity is O(1)
     * 
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Time complexity is O(1)
     * 
     * @return the table
     */
    public Table getTable() {
        return table;
    }

    /**
     * Time complexity is O(1)
     * 
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Time complexity is O(1)
     * 
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * Time complexity is O(1)
     * 
     * @return the key
     */
    public String getKey() {
        return customer.getLastName();
    }

    /**
     * Time complexity is O(1)
     * 
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    public String toString() {
        return "Customer: " + customer.getName() + ", Table: " + table.getTableNumber() + ", Date: " + date + ", Time: " + time
                + ", Status: "
                + status;
    }
}