import java.util.*;

public class BigTrip {
    public static boolean[] visited;
    public static int[][] graph;
    public static int[] prev;
    public static int[] distances;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine());
        int edges = Integer.parseInt(scanner.nextLine());

        graph = new int[nodes + 1][nodes + 1];

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph[tokens[0]][tokens[1]] = tokens[2];
        }

        int source = Integer.parseInt(scanner.nextLine());
        int destination = Integer.parseInt(scanner.nextLine());
        
        visited = new boolean[graph.length];
        prev = new int[graph.length];
        distances = new int[graph.length];

        Arrays.fill(prev, -1);
        
        ArrayDeque<Integer> sorted = new ArrayDeque<>();

        for (int i = 1; i < graph.length; i++) {
            topologicalSort(i, sorted);
        }

        int totalWeight = 0;

        while(!sorted.isEmpty()) {
            int node = sorted.pop();

            for (int i = 1; i < graph[node].length; i++) {
                int weight = graph[node][i];

                if(weight > 0) {
                    if(distances[node] + weight > distances[i]) {
                        distances[i] = distances[node] + weight;
                        prev[i] = node;
                    }
                }
            }
        }

        List<Integer> path = new ArrayList<>();

        path.add(destination);

        int node = prev[destination];

        while(node != -1) {
            path.add(node);
            node = prev[node];
        }

        System.out.println(distances[destination]);

        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }

        System.out.println();
    }

    private static void topologicalSort(int node, ArrayDeque<Integer> sorted) {
        if(visited[node]) {
            return;
        }

        visited[node] = true;

        for (int i = 1; i < graph[node].length; i++) {
            if(graph[node][i] != 0) {
                topologicalSort(i, sorted);
            }
        }

        sorted.push(node);
    }
}
