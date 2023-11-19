package PhoneBook;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a contact in a phone book. A contact has a name, phone number,
 * email address, and address. The phone number and email address must be valid
 * in order to be set. A valid phone number is a sequence of digits. A valid
 * email
 * address is a sequence of characters that contains an @ symbol followed by
 * some characters, then a period then some characters. The constructor throws
 * an error if the phone number is invalid, but sets the email address to null
 * if its invalid nad creates the contact.
 */
public class Contact {
    private String name;
    private String number;
    private String email;
    private String address;

    /**
     * Constructs a new Contact object with the specified name, number, email, and
     * address. The phone number must be valid in order to be set. The email address
     * must be valid in order to be set. If the email address is invalid, it is set
     * to null and the contact is created regardless.
     *
     * @param name    the name of the contact
     * @param number  the phone number of the contact
     * @param email   the email address of the contact
     * @param address the address of the contact
     */
    public Contact(String name, String number, String email, String address) {
        this.name = name;
        setNumber(number);
        setEmail(email);
        this.address = address;
    }

    /**
     * Constructs a new Contact object with the specified name and number. The phone
     * number must be valid in order to be set. The email address is set to null and
     * the contact is created regardless. Invokes the 4-argument constructor.
     *
     * @param name   the name of the contact
     * @param number the phone number of the contact
     * @see #Contact(String, String, String, String)
     */
    public Contact(String name, String number) {
        this(name, number, "", "");
    }

    /**
     * Constructs a new Contact object with the specified name, number, and email.
     * The phone number must be valid in order to be set. The email address must be
     * valid in order to be set. If the email address is invalid, it is set to null
     * and the contact is created regardless. Invokes the 4-argument constructor.
     *
     * @param name   the name of the contact
     * @param number the phone number of the contact
     * @param email  the email address of the contact
     * @see #Contact(String, String, String, String)
     */
    public Contact(String name, String number, String email) {
        this(name, number, email, "");
    }

    /**
     * Returns the name of the contact.
     *
     * @return the name of the contact
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the contact.
     *
     * @param name the name of the contact
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the phone number of the contact.
     *
     * @return the phone number of the contact
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the phone number of the contact. The phone number must be valid in order
     * to be set. A valid phone number is a sequence of digits. If the phone number
     * is
     * invalid, an IllegalArgumentException is thrown.
     *
     * @param number the phone number of the contact
     * @throws IllegalArgumentException if the phone number is invalid
     */
    public void setNumber(String number) {
        if (isValidNumber(number)) {
            this.number = number;
        } else {
            throw new IllegalArgumentException("Invalid phone number format");
        }
    }

    /**
     * Returns the email address of the contact.
     *
     * @return the email address of the contact
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the contact. The email address must be valid in
     * order to be set. A valid email address is a sequence of characters that
     * contains
     * an @ symbol followed by some characters, then a period then some characters.
     * If
     * the email address is invalid, it is set to null and the contact is created
     * regardless.
     *
     * @param email the email address of the contact
     */
    public void setEmail(String email) {
        if (isValidEmail(email)) {
            this.email = email;
        } else {
            System.err.println("Invalid email address format.\nCreating contact without email address");
            this.email = "";
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
     * Returns the address of the contact.
     *
     * @return the address of the contact
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the contact.
     *
     * @param address the address of the contact
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns a string representation of the contact.
     *
     * @return a string representation of the contact
     */
    @Override
    public String toString() {
        return "name=" + name + ", number=" + number + ", email=" + email + ", address=" + address;
    }

    /**
     * Returns true if the specified object is equal to the current contact, false
     * otherwise. Two contacts are equal if they have the same name, number, email,
     * and address.
     *
     * @param obj the object to compare to the current contact
     * @return true if the specified object is equal to the current contact, false
     * otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Contact) {
            Contact other = (Contact) obj;
            return name.equals(other.name) && number.equals(other.number) && email.equals(other.email)
                    && address.equals(other.address);
        } else {
            return false;
        }
    }
}