import java.util.Scanner;

public class LabSession1 {
    public int sumToN(int n) {
        int sumOfN = 0;
        for (int i = 1; i <= n; i += 2) {
            sumOfN += i;
        }
        return sumOfN;
    }

    public double sumToNSquare(int n) {
        double sumSquareN = 0;
        for (int i = 0; i < n; i++) {
            sumSquareN += (i * i);
        }
        return sumSquareN;
    }
    public static void main(String args[]) {
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter n: ");
        int nofi = kb.nextInt();
        LabSession1 labSession1 = new LabSession1();
        System.out.println(labSession1.sumToN(nofi));
        System.out.println(labSession1.sumToNSquare(nofi));
        kb.close();
    }
}