import java.util.*;

public class Salaries {

    public static Map<Integer, List<Integer>> graph = new HashMap<>();
    public static Map<Integer, Integer> salaries = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();

            graph.put(i, new ArrayList<>());

            for (int j = 0; j < line.length(); j++) {
                char ch = line.charAt(j);

                if(ch == 'Y'){
                    graph.get(i).add(j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            salaries.put(i, 0);
        }

        int total = 0;

        for (Integer node : graph.keySet()) {
            if(salaries.get(node) == 0){
                findSalary(node);
            }

            total += salaries.get(node);
        }

        System.out.println(total);
    }

    private static void findSalary(Integer node) {
        if(graph.get(node).size() == 0){
            salaries.put(node, 1);
            return;
        }

        for (Integer child : graph.get(node)) {
            if(salaries.get(child) == 0){
                findSalary(child);
            }
            salaries.put(node, salaries.get(node) + salaries.get(child));
        }
    }
}
