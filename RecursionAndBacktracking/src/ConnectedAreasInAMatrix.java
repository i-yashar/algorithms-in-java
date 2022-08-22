import java.util.*;

public class ConnectedAreasInAMatrix {

    public static char[][] matrix;
    public static List<int[]> areas;
    public static int counter = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int r = Integer.parseInt(scanner.nextLine());
        int c = Integer.parseInt(scanner.nextLine());

        matrix = new char[r][c];
        areas = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            matrix[i] = scanner.nextLine().toCharArray();
        }

        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {
                if (matrix[row][col] == '-') {
                    areas.add(new int[]{row, col, 0});
                    findAreas(row, col);
                }
            }
        }

        System.out.println("Total areas found: " + areas.size());

        areas.stream()
                .sorted((f, s) -> Integer.compare(s[2], f[2]))
                .forEach(a -> {
                    System.out.println("Area #" + (++counter) + " at (" + a[0] + ", " + a[1] + "), size: " + a[2]);
                });
    }

    private static void findAreas(int row, int col) {
        if (outOfBounds(row, col) || isNotTraversable(row, col)) {
            return;
        }

        areas.get(areas.size() - 1)[2]++;
        matrix[row][col] = 'V';

        findAreas(row + 1, col);
        findAreas(row, col + 1);
        findAreas(row - 1, col);
        findAreas(row, col - 1);
    }

    private static boolean isNotTraversable(int row, int col) {
        return matrix[row][col] == '*' || matrix[row][col] == 'V';
    }

    private static boolean outOfBounds(int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length;
    }
}
