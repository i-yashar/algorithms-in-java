import java.util.Arrays;
import java.util.Scanner;

public class RecursiveArraySum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        reverseArray(arr, 0);

        for (int number : arr) {
            System.out.print(number + " ");
        }
    }

    public static void reverseArray(int[] arr, int index){
        if(index == arr.length / 2){
            return;
        }

        int temp = arr[index];
        arr[index] = arr[arr.length - index - 1];
        arr[arr.length - index - 1] = temp;

        reverseArray(arr, index + 1);
    }
}
