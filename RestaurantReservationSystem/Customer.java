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
 * @param hasDependents  whether or not the customer has dependents
 */
public class Customer {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String email;
    private boolean hasReservation;
    private boolean hasDependents;

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
     * @param hasDependents  whether or not the customer has dependents
     */
    public Customer(String firstName, String lastName, String phoneNumber, String address, String email,
            boolean hasReservation, boolean hasDependents) {
        this.firstName = firstName;
        this.lastName = lastName;
        setNumber(phoneNumber);
        this.address = address;
        setEmail(email);
        this.hasReservation = hasReservation;
        this.hasDependents = hasDependents;
    }

    public void setNumber(String number) {
        if (isValidNumber(number)) {
            this.phoneNumber = number;
        } else {
            throw new IllegalArgumentException("Invalid phone number format");
        }
    }

    public void setEmail(String email) {
        if (isValidEmail(email)) {
            this.email = email;
        } else {
            this.email = null;
        }
    }

    /**
     * Returns true if the phone number is valid, false otherwise. A valid phone
     * number is a sequence of digits.
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
     * characters, then a period then some characters.
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
     * Returns true if the customer has dependents, false otherwise.
     *
     * @return true if the customer has dependents, false otherwise
     */
    public boolean hasDependents() {
        return hasDependents;
    }

    /**
     * Sets whether or not the customer has dependents.
     *
     * @param hasDependents whether or not the customer has dependents
     */
    public void setHasDependents(boolean hasDependents) {
        this.hasDependents = hasDependents;
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
        output += cyan + "Has Dependents: " + reset + hasDependents() + "\n";
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
                    && hasDependents == other.hasDependents;
        } else {
            return false;
        }
    }

}