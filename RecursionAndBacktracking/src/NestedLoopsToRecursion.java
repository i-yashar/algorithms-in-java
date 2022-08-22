import java.util.Scanner;

public class NestedLoopsToRecursion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            sb.append('*');
        }

        printCombinations(sb, n, 1);
    }

    public static void printCombinations(StringBuilder sb, int n, int count){
        if(count > n){
            for (char ch : sb.toString().toCharArray()) {
                System.out.print(ch + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= n; i++) {
            sb.setCharAt(count - 1, (char) (i + 48));
            printCombinations(sb, n, count + 1);
        }
    }
}
