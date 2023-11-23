package PhoneBook;

import java.util.Random;

public class PhoneBookTest {

    /**
     * Arrays of possible first names, surnames, addresses, and email servers
     * These arrays of random names, email servers, addresses were generated with
     * the help of ChatGPT.
     */
    private static final String[] FIRST_NAMES = { "John", "Alice", "Bob", "Emma", "David", "Olivia", "James",
            "Sophia" };
    private static final String[] SURNAMES = { "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller",
            "Wilson" };
    private static final String[] ADDRESSES = { "123 Main St", "456 Oak Ave", "789 Pine Blvd", "101 Elm Ln",
            "202 Cedar Dr" };
    private static final String[] EMAIL_SERVERS = { "gmail.com", "yahoo.com", "outlook.com", "elikemsserver.com" };

    public static void main(String[] args) {
        TelephoneDirectory telephoneDirectory = new TelephoneDirectory();
        Random random = new Random();

        int numContacts = 15;

        for (int i = 1; i <= numContacts; i++) {
            // Randomly selecting first name, surname, address, and email server to
            // construct contact information to populate the telephone directory
            String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
            String surname = SURNAMES[random.nextInt(SURNAMES.length)];
            String address = ADDRESSES[random.nextInt(ADDRESSES.length)];
            String emailServer = EMAIL_SERVERS[random.nextInt(EMAIL_SERVERS.length)];

            //Constructing contact information
            String contactName = firstName + " " + surname;
            String phoneNumber = "0557585" + i;
            String email = firstName.toLowerCase() + "." + surname.toLowerCase() + "@" + emailServer;

            Contact contact = new Contact(contactName, phoneNumber, email, address);
            telephoneDirectory.insert(contact);
        }

        //Searching for some random contact
        String randomContactName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)] + " " +
                SURNAMES[random.nextInt(SURNAMES.length)];
        String searchResult = telephoneDirectory.search(randomContactName);
        System.out.println("Search Result for \n" + randomContactName + ": " + searchResult);

        for (int j = 0; j < 5; j++) {
            String contactToSearch = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)] + " " +
                    SURNAMES[random.nextInt(SURNAMES.length)];
            String searchResultAdditional = telephoneDirectory.search(contactToSearch);
            System.out.println("Search Result for \n" + contactToSearch + ": " + searchResultAdditional);
        }

        //Deleting some random contact
        String contactToDelete = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)] + " " +
                SURNAMES[random.nextInt(SURNAMES.length)];
        telephoneDirectory.delete(contactToDelete);
        System.out.println("Deleted: " + contactToDelete);

        //Printing the telephone directory
        System.out.println("\nTelephone Directory:");
        telephoneDirectory.print();
    }
}
