import java.util.*;
import java.util.stream.Collectors;

public class Undefined {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine());
        int edgesCount = Integer.parseInt(scanner.nextLine());

        List<int[]> edges = new ArrayList<>();
        int[][] graph = new int[nodes + 1][nodes + 1];

        for (int i = 0; i < edgesCount; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph[tokens[0]][tokens[1]] = tokens[2];
            edges.add(new int[] {tokens[0], tokens[1]});
        }

        int[] distances = new int[nodes + 1];

        Arrays.fill(distances, Integer.MAX_VALUE);

        int[] prev = new int[nodes + 1];

        Arrays.fill(prev, -1);

        int source = Integer.parseInt(scanner.nextLine());
        distances[source] = 0;
        int destination = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < nodes - 1; i++) {
            for (int[] edge : edges) {
                int from = edge[0];
                int to = edge[1];
                if(distances[from] != Integer.MAX_VALUE){
                    int newDistance = distances[from] + graph[from][to];

                    if(newDistance < distances[to]) {
                        distances[to] = newDistance;
                        prev[to] = from;
                    }
                }
            }
        }

        boolean negativeCycle = false;

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            if(distances[from] != Integer.MAX_VALUE){
                int newDistance = distances[from] + graph[from][to];

                if(newDistance < distances[to]) {
                    negativeCycle = true;
                    prev[to] = from;
                }
            }
        }

        if(negativeCycle) {
            System.out.println("Undefined");
        } else {
            List<Integer> path = new ArrayList<>();

            path.add(destination);

            int node = prev[destination];

            while(node != -1) {
                path.add(node);
                node = prev[node];
            }
            Collections.reverse(path);
            System.out.println(String.join(" ", path.stream().map(String::valueOf).collect(Collectors.toList())));
            System.out.println(distances[destination]);
        }

    }
}
