package RestaurantReservationSystem;

/**
 * This class represents a table in the restaurant reservation system. A table has
 * a table number, capacity, and availability.
 * 
 * @param tableNumber the table number of the table
 * @param capacity    the capacity of the table
 * @param isAvailable whether or not the table is available
 */
public class Table{
    private int tableNumber;
    private int capacity;
    private boolean isAvailable;

    /**
     * Constructs a new Table object with the specified table number, capacity, and
     * availability.
     * 
     * @param tableNumber the table number of the table
     * @param capacity    the capacity of the table
     * @param isAvailable whether or not the table is available
     */
    public Table(int tableNumber, int capacity, boolean isAvailable){
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.isAvailable = isAvailable;
    }

    public int getTableNumber(){
        return tableNumber;
    }

    public int getCapacity(){
        return capacity;
    }

    public boolean getAvailablity(){
        return isAvailable;
    }

    public void setTableNumber(int tableNumber){
        this.tableNumber = tableNumber;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public void setAvailablility(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    public String toString(){
        return "Table Number: " + tableNumber + "\nCapacity: " + capacity + "\nIs Available: " + isAvailable;
    }

    public boolean equals(Object o){
        if(o instanceof Table){
            Table other = (Table) o;
            return tableNumber == other.getTableNumber() && capacity == other.getCapacity() && isAvailable == other.getAvailablity();
        }
        return false;
    }

}