import java.util.Scanner;
public class LabSession2 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string.\nHit Enter twice when you're done: ");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            sb.append(line);
        }
        for (int i = sb.length() - 1; i >= 0; i--) {
            System.out.print(sb[i]);
        }

    scanner.close();
}

}
