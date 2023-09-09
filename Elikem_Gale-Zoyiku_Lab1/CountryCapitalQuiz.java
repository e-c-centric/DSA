import java.util.Scanner;
import java.util.Random;

/**
 * This program simulates a Country-Capital Quiz where the user is prompted to
 * guess the capital of randomly chosen African countries.
 * The program provides a "did you mean" functionality to handle minor spelling
 * mistakes in user input.
 *
 * The user is presented with 20 questions. After each question, the program
 * checks if the user's answer matches the correct capital.
 * If the answer is incorrect, the program checks if the user's answer is close
 * in terms of spelling to the correct capital. If it is,
 * the program suggests the correct spelling to the user.
 *
 * The "did you mean" functionality is implemented using Levenshtein distance,
 * which calculates the number of edits required to
 * transform one string into another. If the Levenshtein distance between the
 * user's answer and the correct capital is small (up to 2 edits),
 * the program considers the answer to be close and suggests the correct
 * spelling.
 *
 * The program uses arrays to store African countries and their corresponding
 * capitals. The user's input is case-insensitive.
 *
 * @author Elikem Asudo Gale-Zoyiku
 */

public class CountryCapitalQuiz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] countries = {
                "Algeria", "Angola", "Benin", "Botswana", "Burkina Faso", "Cameroon", "Chad", "Comoros", "Congo",
                "Djibouti",
                "Egypt", "Equatorial Guinea", "Eritrea", "Eswatini", "Ethiopia", "Gabon", "Gambia", "Ghana", "Guinea",
                "Ivory Coast",
                "Kenya", "Lesotho", "Liberia", "Libya", "Madagascar", "Malawi", "Mali", "Mauritania", "Mauritius",
                "Morocco",
                "Mozambique", "Namibia", "Niger", "Nigeria", "Rwanda", "Sao Tome and Principe", "Senegal", "Seychelles",
                "Sierra Leone",
                "Somalia", "South Africa", "South Sudan", "Sudan", "Tanzania", "Togo", "Tunisia", "Uganda", "Zambia",
                "Zimbabwe"
        };

        String[] capitals = {
                "Algiers", "Luanda", "Porto-Novo", "Gaborone", "Ouagadougou", "Yaoundé", "N'Djamena", "Moroni",
                "Brazzaville", "Djibouti",
                "Cairo", "Malabo", "Asmara", "Mbabane", "Addis Ababa", "Libreville", "Banjul", "Accra", "Conakry",
                "Yamoussoukro",
                "Nairobi", "Maseru", "Monrovia", "Tripoli", "Antananarivo", "Lilongwe", "Bamako", "Nouakchott",
                "Port Louis", "Rabat",
                "Maputo", "Windhoek", "Niamey", "Abuja", "Kigali", "São Tomé", "Dakar", "Victoria", "Freetown",
                "Mogadishu",
                "Pretoria", "Juba", "Khartoum", "Dodoma", "Lomé", "Tunis", "Kampala", "Lusaka", "Harare"
        };

        int numQuestions = 20;
        int numCorrectAnswers = 0;

        System.out.println("Welcome to the Country-Capital Quiz!");

        for (int i = 0; i < numQuestions; i++) {
            int randomIndex = random.nextInt(countries.length);

            String country = countries[randomIndex];
            String capital = capitals[randomIndex];

            System.out.println("Question " + (i + 1) + ": What is the capital of " + country + "?");
            String userAnswer = scanner.nextLine();

            if (userAnswer.equalsIgnoreCase(capital)) {
                System.out.println("Correct!");
                numCorrectAnswers++;
            } else {
                if (isClose(userAnswer, capital)) {
                    System.out.println("You had a typo! The correct spelling is: " + capital);
                    numCorrectAnswers++;
                } else {
                    System.out.println("Incorrect. The correct answer is: " + capital);
                }
            }
        }

        System.out.println("Quiz completed! You answered " + numCorrectAnswers + " out of " + numQuestions
                + " questions correctly.");

        scanner.close();
    }

    /**
     * The <code>isClose</code> method checks if the user's input is close to the
     * correct answer using Levenshtein distance.
     * Levenshtein distance calculates the minimum number of single-character edits
     * (insertions, deletions, or substitutions)
     * required to transform one string into another.
     *
     * @param userInput     The user's input answer.
     * @param correctAnswer The correct answer.
     * @return true if the user's input is close to the correct answer,
     *         false otherwise.
     */

    private static boolean isClose(String userInput, String correctAnswer) {
        userInput = userInput.toLowerCase();
        correctAnswer = correctAnswer.toLowerCase();

        int[][] dp = new int[userInput.length() + 1][correctAnswer.length() + 1];

        for (int i = 0; i <= userInput.length(); i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= correctAnswer.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= userInput.length(); i++) {
            for (int j = 1; j <= correctAnswer.length(); j++) {
                int cost = (userInput.charAt(i - 1) == correctAnswer.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(dp[i - 1][j] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + cost));
            }
        }

        return dp[userInput.length()][correctAnswer.length()] <= 2; // Allow up to 2 edits (i.e. th threshold for an
                                                                    // acceptable answer is <= 2 errors)
    }
}

/*
 * Data Structure Used: Array
 * 
 * Explanation:
 * In this program, the data structure used to store the countries and their
 * capitals is an array. Two separate arrays are used: one for countries and one
 * for capitals. The index of each array corresponds to the same country's
 * capital, allowing easy pairing between them.
 * 
 * Arrays were chosen because they provide a simple and efficient way to store
 * related data in a linear structure. Given that the program's requirements
 * only allow the use of arrays as the data structure, they are a suitable
 * choice for this scenario. However, for larger
 * applications, a hashmap would offer more efficient
 * lookups and better organization of the data because it s not accessed
 * sequentally.
 */