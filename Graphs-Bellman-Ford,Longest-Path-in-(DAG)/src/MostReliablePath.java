import java.util.*;

public class MostReliablePath {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);

        String[] path = scanner.nextLine().split("\\s+");

        int edges = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);

        int source = Integer.parseInt(path[1]);
        int destination = Integer.parseInt(path[3]);

        int[][] graph = new int[nodes][nodes];

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph[tokens[0]][tokens[1]] = tokens[2];
            graph[tokens[1]][tokens[0]] = tokens[2];
        }

        double[] distances = new double[nodes];
        boolean[] visited = new boolean[nodes];

        int[] prev = new int[nodes];

        Arrays.fill(prev, -1);

        distances[source] = 100;

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingDouble(n -> distances[(int) n]).reversed());

        queue.offer(source);

        while(!queue.isEmpty()) {
            int maxNode = queue.poll();

            visited[maxNode] = true;

            for (int i = 0; i < graph[maxNode].length; i++) {
                int weight = graph[maxNode][i];
                if(weight > 0 && !visited[i]) {
                    double newDistance = distances[maxNode] * weight / 100;

                    if(newDistance > distances[i]) {
                        distances[i] = newDistance;
                        prev[i] = maxNode;
                    }
                    queue.offer(i);
                }
            }
        }

        System.out.printf("Most reliable path reliability: %.2f%%%n", distances[destination]);

        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(destination);

        int node = prev[destination];

        while(node != -1) {
            stack.push(node);
            node = prev[node];
        }

        while(!stack.isEmpty()) {
            System.out.print(stack.pop());
            if(stack.size() > 0) {
                System.out.print(" -> ");
            }
        }
    }
}
