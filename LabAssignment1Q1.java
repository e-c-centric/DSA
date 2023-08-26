import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class LabAssignment1Q1 {
    public static void main(String args[]) {
        // Using a HashMap to store the countries and their capitals. Reason at the end
        // of program.
        Map<String, String> countries = new HashMap<>();
        countries.put("Ghana", "Accra");
        countries.put("Nigeria", "Abuja");
        countries.put("South Africa", "Cape Town");
        countries.put("Egypt", "Cairo");
        countries.put("Kenya", "Nairobi");
        countries.put("Tanzania", "Dodoma");
        countries.put("Uganda", "Kampala");
        countries.put("Morocco", "Rabat");
        countries.put("Algeria", "Algiers");
        countries.put("Tunisia", "Tunis");
        countries.put("Mali", "Bamako");
        countries.put("Senegal", "Dakar");
        countries.put("Ivory Coast", "Yamoussoukro");
        countries.put("Cameroon", "Yaounde");
        countries.put("Zimbabwe", "Harare");
        countries.put("Botswana", "Gaborone");
        countries.put("Namibia", "Windhoek");
        countries.put("Mauritius", "Port Louis");
        countries.put("Sudan", "Khartoum");
        countries.put("Ethiopia", "Addis Ababa");

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int correctAnswers = 0;
        for (int i = 0; i < 20; i++) {
            // Getting a random country from the HashMap. I have to convert the keySet to an
            // array because the keySet is a Set, which is unordered. This means that the
            // keySet cannot be accessed by index, so I have to convert it to an array
            // first. I then get a random index from the array and use it to get a random
            // country from the HashMap.
            String country = (String) countries.keySet().toArray()[random.nextInt(countries.size())];
            String capital = countries.get(country);
            System.out.print("What is the capital of " + country + "? ");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase(capital)) {
                System.out.println("Correct!");
                correctAnswers++;
            } else {
                System.out.println("Incorrect. The capital of " + country + " is " + capital + ".");
            }
        }

        System.out.println("You got " + correctAnswers + " out of 20 correct.");

        scanner.close();
    }
}

/*
 * I used a HashMap to store the countries and their capitals, which
 * is faster than searching through an array because a HashMap uses a hash table
 * to store the data, which allows for constant-time access to elements. This
 * means that no matter how many elements are in the HashMap, it will always
 * take the same amount of time to access an element. On the other hand, an
 * array has to be searched through sequentially, which means that the time it
 * takes to access an element increases as the number of elements in the array
 * increases.
 */