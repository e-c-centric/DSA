import java.util.Scanner;
import java.util.Random;

public class HangmanGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] words = {
                "program", "java", "hangman", "array", "computer", "language", "algorithm", "object", "oriented",
                "class",
                "method", "variable", "constant", "loop", "conditional", "statement", "expression", "operator",
                "function",
                "recursion", "iteration", "data", "structure", "queue", "stack", "tree", "graph", "list", "set", "map",
        };

        System.out.println("Welcome to Hangman!");

        do {
            String wordToGuess = words[random.nextInt(words.length)];
            char[] guessedLetters = new char[wordToGuess.length()];
            int misses = 0;

            for (int i = 0; i < guessedLetters.length; i++) {
                guessedLetters[i] = '*';
            }

            while (true) {
                displayWord(guessedLetters);
                System.out.println();
                System.out.print("Guess and enter a letter in the word ");
                displayWord(guessedLetters);
                System.out.print(" > ");
                char guess = scanner.next().charAt(0);

                if (!isAlreadyGuessed(guess, guessedLetters)) {
                    boolean found = updateGuessedLetters(guess, wordToGuess, guessedLetters);
                    if (!found) {
                        misses++;
                        System.out.println(guess + " is not in the word.\nGuess again:");
                    }

                    if (isWordGuessed(guessedLetters)) {
                        System.out.println("The word is " + wordToGuess + ". You missed " + misses + " time"
                                + (misses == 1 ? "" : "s"));
                        break;
                    }
                } else {
                    System.out.println(guess + " is already in the word");
                }
            }

            System.out.print("Do you want to play another word? (yes/no): ");
        } while (scanner.next().equalsIgnoreCase("yes"));

        scanner.close();
    }

    /**
     * Displays the current state of the guessed word.
     *
     * @param guessedLetters An array containing the guessed letters and asterisks.
     */
    private static void displayWord(char[] guessedLetters) {
        for (char c : guessedLetters) {
            System.out.print(c);
        }
    }

    /**
     * Checks if the guessed letter is already in the guessedLetters array.
     *
     * @param guess          The letter guessed by the user.
     * @param guessedLetters An array containing the guessed letters and asterisks.
     * @return true if the letter is already guessed, false otherwise.
     */
    private static boolean isAlreadyGuessed(char guess, char[] guessedLetters) {
        for (char c : guessedLetters) {
            if (c == guess) {
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the guessedLetters array with the correct guesses.
     *
     * @param guess          The letter guessed by the user.
     * @param wordToGuess    The word that needs to be guessed.
     * @param guessedLetters An array containing the guessed letters and asterisks.
     * @return true if the guess is correct, false
     *         otherwise.
     */
    private static boolean updateGuessedLetters(char guess, String wordToGuess, char[] guessedLetters) {
        boolean found = false;
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guess) {
                guessedLetters[i] = guess;
                found = true;
            }
        }
        return found;
    }

    /**
     * Checks if the word has been fully guessed.
     *
     * @param guessedLetters An array containing the guessed letters and asterisks.
     * @return {@code true} if the word is fully guessed, false
     *         otherwise.
     */
    private static boolean isWordGuessed(char[] guessedLetters) {
        for (char c : guessedLetters) {
            if (c == '*') {
                return false;
            }
        }
        return true;
    }
}
/*
 * I used arrays in tis program for their efficiency in representing the state
 * of the Hangman game at any stage in play. They provide direct
 * access to elements based on their index, making letter updates and checks
 * efficient. For this relatively simple problem, where maintaining the word's
 * state and accessing elements are key operations, arrays offer a
 * straightforward solution that balances readability with functionality. While
 * more advanced data structures, like Sets or StringBuilders could provide
 * optimization, arrays are suitable
 * and well-suited for this particular application.
 */