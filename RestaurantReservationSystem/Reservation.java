package RestaurantReservationSystem;

public class Reservation extends Entry<String, Reservation> {
    private Customer customer;
    private Table table;
    private String date;
    private String time;
    private String status = "Available";

    public Reservation(Customer customer, Table table, String date, String time) {
        super(customer.getLastName(), null);
        this.customer = customer;
        this.table = table;
        this.date = date;
        this.time = time;
        this.status = "Booked";
    }

    public Customer getCustomer() {
        return customer;
    }

    public Table getTable() {
        return table;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getKey() {
        return customer.getLastName();
    }

    public String getStatus() {
        return status;
    }
}