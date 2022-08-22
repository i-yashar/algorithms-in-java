import java.util.*;

public class AreasInMatrix {

    public static char[][] matrix;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        matrix = new char[n][];

        Map<Character, Integer> areas = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            matrix[i] = scanner.nextLine().toCharArray();
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] != 'V'){
                    areas.putIfAbsent(matrix[i][j], 0);
                    areas.put(matrix[i][j], areas.get(matrix[i][j]) + 1);
                    traverseArea(matrix[i][j], i, j);
                    count++;
                }
            }
        }

        System.out.println("Areas: " + count);
        areas.keySet().forEach(l -> System.out.println("Letter " + "'" + l + "'" + " -> " + areas.get(l)));
    }

    private static void traverseArea(char letter, int row, int col) {
        if(isOutOfBounds(row, col) || matrix[row][col] == 'V' || matrix[row][col] != letter){
            return;
        }

        matrix[row][col] = 'V';
        traverseArea(letter, row, col + 1);
        traverseArea(letter, row, col - 1);
        traverseArea(letter, row - 1, col);
        traverseArea(letter, row + 1, col);
    }

    private static boolean isOutOfBounds(int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length;
    }
}
