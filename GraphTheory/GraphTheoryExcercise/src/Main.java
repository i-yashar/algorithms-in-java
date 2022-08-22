import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static int[] arr;
    public static int count = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        findSolutions(0);
        System.out.println("Total Solutions: " + count);
    }

    private static void findSolutions(int index) {
        if(index > arr.length - 1) {
            if(sumIsZero()){
                count++;
                printArr();
            }
        } else {
            for (int i = 0; i < 2; i++) {
                arr[index] = i == 0 ? arr[index] : -arr[index];
                findSolutions(index + 1);
            }
        }
    }

    private static void printArr() {
        System.out.println(Arrays.stream(arr)
                .mapToObj(String::valueOf)
                .map(n -> n.charAt(0) != '-' ? "+" + n : n)
                .collect(Collectors.joining(" ")));
    }

    private static boolean sumIsZero() {
        int sum = 0;

        for (int num : arr) {
            sum += num;
        }

        return sum == 0;
    }
}
