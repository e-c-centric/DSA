package PhoneBook;

public class PhoneBookTest {

    public static void main(String[] args) {
        // Create a new TelephoneDirectory object
        TelephoneDirectory telephoneDirectory = new TelephoneDirectory();

        // Insert 128 contacts into the telephone directory
        for (int i = 1; i <= 3; i++) {
            String contactName = "Contact" + i;
            String phoneNumber = "0557585" + i;
            String email = "contact" + i + "@elikemsserver.com";
            String address = i + " High Street, Accra";

            Contact contact = new Contact(contactName, phoneNumber, email, address);
            telephoneDirectory.insert(contact);
        }

        // Search for a contact in the telephone directory
        String searchResult = telephoneDirectory.search("Contact50");
        System.out.println("Search Result: " + searchResult);

        // Delete a contact from the telephone directory
        telephoneDirectory.delete("Contact25");

        // Print the telephone directory
        System.out.println("Telephone Directory:");
        telephoneDirectory.print();
    }
}
