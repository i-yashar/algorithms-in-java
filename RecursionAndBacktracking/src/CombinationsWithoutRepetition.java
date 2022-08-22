import java.util.Scanner;

public class CombinationsWithoutRepetition {
    public static int combinations[];
    public static int n;
    public static int k;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        n = Integer.parseInt(scanner.nextLine());
        k = Integer.parseInt(scanner.nextLine());

        combinations = new int[k];

        findCombinationsWithoutRepetition(0, 1);
    }

    private static void findCombinationsWithoutRepetition(int index, int start) {
        if(index > combinations.length - 1){
            printArray(combinations);
            return;
        }

        for (int i = start; i <= n; i++) {
            combinations[index] = i;
            findCombinationsWithoutRepetition(index + 1, i + 1);
        }
    }

    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
