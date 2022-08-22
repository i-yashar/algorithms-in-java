import java.util.Scanner;

public class BinomialCoefficients {
    public static long[][] matrix;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());

        matrix = new long[n + 1][k + 1];

        System.out.println(binomial(n, k));
    }

    private static long binomial(int n, int k) {
        if (k == 0 || n == k) {
            return 1;
        }

        if (matrix[n][k] != 0) {
            return matrix[n][k];
        }

        return matrix[n][k] = binomial(n - 1, k) + binomial(n - 1, k - 1);
    }
}
