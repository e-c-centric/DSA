package HashTable;
/**
 * Entry class for the HashTable class.
 * Takes a student's ID as a String and their grade as an int.
 * The key is the student's ID and the value is their grade.
 * 
 * @throws IllegalArgumentException if the value is not between 0 and 100
 */
public class Entry {
    private String key;
    private double value;

    /**
     * Constructor for the Entry class.
     * Invokes the setValue method to check if the value is between 0 and 100, and
     * set if it is.
     * 
     * @param key   the student's ID
     * @param value the student's grade
     * @throws IllegalArgumentException if the value is not between 0 and 100
     * @see #setValue(double value)
     */
    public Entry(String key, double value) {
        this.key = key;
        setValue(value);
    }

    /**
     * Returns the student's ID.
     * 
     * @return the student's ID
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the student's ID.
     * 
     * @param key the student's ID
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Returns the student's grade.
     * 
     * @return the student's grade
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets the student's grade.
     * Checks if the value is between 0 and 100.
     * 
     * @param value the student's grade
     * @throws IllegalArgumentException if the value is not between 0 and 100
     */
    public void setValue(double value) {
        if (value >= 0 && value <= 100) {
            this.value = value;
        } else {
            throw new IllegalArgumentException("Value must be between 0 and 100");
        }
    }

    /**
     * Returns a String representation of the Entry object.
     * 
     * @return a String representation of the Entry object
     */
    @Override
    public String toString() {
        return "Student ID:'" + key + '\'' + ", Grade:" + value + "\n";
    }

}