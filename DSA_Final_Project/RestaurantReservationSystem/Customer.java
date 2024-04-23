package RestaurantReservationSystem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents a customer in the restaurant reservation system. A
 * customer has a first name, last name, phone number, address, and email
 * address.
 * We also keep track of whether or not the customer has a reservation and
 * whether
 * or not the customer has dependents.The phone number and email address must be
 * valid in order to be set. A valid
 * phone number is a sequence of digits. A valid email address is a sequence of
 * characters that contains an arobase (@) symbol followed by
 * some characters, then a period then some characters. The constructor throws
 * an error if the phone number is invalid, but sets the email address to null
 * if its invalid and creates the Customer regardless.
 * 
 * @throws IllegalArgumentException if the phone number is invalid
 * @param firstName      the first name of the customer
 * @param lastName       the last name of the customer
 * @param phoneNumber    the phone number of the customer
 * @param address        the address of the customer
 * @param email          the email address of the customer
 * @param hasReservation whether or not the customer has a reservation
 * @param numGuests      how many guests the customer has
 * 
 *                       The time complexity of the Customer class constructors,
 *                       setters, and getters
 *                       is O(1) because each operation performs a constant
 *                       number of operations
 *                       regardless of the size of the input. The isValidNumber
 *                       and
 *                       isValidEmail methods have a time
 *                       complexity of O(1) as well since they each perform a
 *                       single regular
 *                       expression match.
 */
public class Customer {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String email;
    private boolean hasReservation;
    private int numGuests;

    /**
     * Constructs a new Customer object with the specified first name, last name,
     * phone number, address, email address, whether or not the customer has a
     * reservation, and whether or not the customer has dependents. The phone number
     * must be valid in order to be set. The email address must be valid in order to
     * be set. If the email address is invalid, it is set to null and the customer
     * is created regardless.
     * 
     * @throws IllegalArgumentException if the phone number is invalid
     * @param firstName      the first name of the customer
     * @param lastName       the last name of the customer
     * @param phoneNumber    the phone number of the customer
     * @param address        the address of the customer
     * @param email          the email address of the customer
     * @param hasReservation whether or not the customer has a reservation
     * @param numGuests      how many guests the customer has
     */

    public Customer(String firstName, String lastName, String phoneNumber, String address, String email,
            boolean hasReservation, int numGuests) {
        this.firstName = firstName;
        this.lastName = lastName;
        setNumber(phoneNumber);
        this.address = address;
        setEmail(email);
        this.hasReservation = hasReservation;
        this.numGuests = numGuests;
    }

    /**
     * Sets the phone number of the customer. The phone number must be valid in
     * order to be set.
     * 
     * @throws IllegalArgumentException if the phone number is invalid
     * @param number the phone number of the customer
     */
    public void setNumber(String number) {
        if (isValidNumber(number)) {
            this.phoneNumber = number;
        } else {
            throw new IllegalArgumentException("Invalid phone number format");
        }
    }

    /**
     * Sets the email address of the customer. The email address must be valid in
     * order to be set. If the email address is invalid, it is set to null and the
     * customer is created regardless. The time complexity of this method is O(1)
     * because it performs a single regular expression match.
     * 
     * @param email the email address of the customer
     */
    public void setEmail(String email) {
        if (isValidEmail(email)) {
            this.email = email;
        } else {
            this.email = null;
        }
    }

    /**
     * Returns true if the phone number is valid, false otherwise. A valid phone
     * number is a sequence of digits. The time complexity of this method is O(1)
     * because it performs a single regular expression match.
     *
     * @param number the phone number to check
     * @return true if the phone number is valid, false otherwise
     */
    private boolean isValidNumber(String number) {
        return number.matches("\\d+");
    }

    /**
     * Returns true if the email address is valid, false otherwise. A valid email
     * address is a sequence of characters that contains an @ symbol followed by
     * some
     * characters, then a period then some characters. The time complexity of this
     * method is O(1) because it performs a single regular expression match.
     *
     * @param email the email address to check
     * @return true if the email address is valid, false otherwise
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Returns the full name of the customer.
     *
     * @return the full name of the customer
     */
    public String getName() {
        return firstName + " " + lastName;
    }

    /**
     * Returns the first name of the customer.
     *
     * @return the first name of the customer
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the customer.
     *
     * @param firstName the first name of the customer
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the customer.
     *
     * @return the last name of the customer
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the customer.
     *
     * @param lastName the last name of the customer
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the phone number of the customer.
     *
     * @return the phone number of the customer
     */
    public String getNumber() {
        return phoneNumber;
    }

    /**
     * Returns the email address of the customer.
     *
     * @return the email address of the customer
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the address of the customer.
     *
     * @return the address of the customer
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the customer.
     *
     * @param address the address of the customer
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns true if the customer has a reservation, false otherwise.
     *
     * @return true if the customer has a reservation, false otherwise
     */
    public boolean hasReservation() {
        return hasReservation;
    }

    /**
     * Sets whether or not the customer has a reservation.
     *
     * @param hasReservation whether or not the customer has a reservation
     */
    public void setHasReservation(boolean hasReservation) {
        this.hasReservation = hasReservation;
    }

    /**
     * Returns the number of guests the customer has.
     *
     * @return the number of guests the customer has
     */
    public int getNumGuests() {
        return numGuests;
    }

    /**
     * Sets the number of guests the customer has.
     *
     * @param numGuests the number of guests the customer has
     */
    public void setNumGuests(int numGuests) {
        this.numGuests = numGuests;
    }

    /**
     * Returns a string representation of the customer.
     *
     * @return a string representation of the customer
     */
    @Override
    public String toString() {
        String reset = "\u001B[0m";
        String bold = "\u001B[1m";
        String cyan = "\u001B[36m";
        String yellow = "\u001B[33m";
        String output = bold + "Customer Information:" + reset + "\n";
        output += cyan + "Name: " + reset + getName() + "\n";
        output += cyan + "Phone Number: " + reset + getNumber() + "\n";
        output += cyan + "Email: " + reset + getEmail() + "\n";
        output += yellow + "Address: " + reset + getAddress() + "\n";
        output += cyan + "Has Reservation: " + reset + hasReservation() + "\n";
        output += cyan + "Number of Guests: " + reset + getNumGuests() + "\n";
        return output;
    }

    /**
     * Returns true if the specified object is equal to the current customer, false
     * otherwise. Two customers are equal if they have the same first name, last
     * name, phone number, address, email address, whether or not the customer has a
     * reservation, and whether or not the customer has dependents.
     *
     * @param obj the object to compare to the current customer
     * @return true if the specified object is equal to the current customer, false
     *         otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Customer) {
            Customer other = (Customer) obj;
            return firstName.equals(other.firstName) && lastName.equals(other.lastName)
                    && phoneNumber.equals(other.phoneNumber) && address.equals(other.address)
                    && email.equals(other.email) && hasReservation == other.hasReservation
                    && numGuests == other.getNumGuests();
        } else {
            return false;
        }
    }

}