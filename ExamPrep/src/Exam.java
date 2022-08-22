import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Exam {

    public static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());

        matrix = new int[n][];

        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int[][] dp = new int[n][m];
        dp[0][0] = matrix[0][0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }

        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + matrix[0][i];
        }

        for (int row = 1; row < n; row++) {
            for (int col = 1; col < m; col++) {
                if (dp[row][col - 1] >= dp[row - 1][col]) {
                    dp[row][col] = dp[row][col - 1] + matrix[row][col];
                } else {
                    dp[row][col] = dp[row - 1][col] + matrix[row][col];
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        int sum = 0;

        int row = n - 1;
        int col = m - 1;

        path.add(matrix[row][col]);
        sum += matrix[row][col];

        while (row > 0 || col > 0) {
            int top = 0;
            int left = 0;

            if(row == 0){
                col--;
            } else if (col == 0) {
                row--;
            } else {
                top = dp[row - 1][col];
                left = dp[row][col - 1];
                if (top > left) {
                    row--;
                } else {
                    col--;
                }
            }

            path.add(matrix[row][col]);
            sum += matrix[row][col];
        }


        System.out.println(sum);
        System.out.println(path.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
