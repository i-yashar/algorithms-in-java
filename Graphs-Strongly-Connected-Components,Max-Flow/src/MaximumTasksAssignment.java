import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class MaximumTasksAssignment {

    public static boolean[][] graph;
    public static int[] parents;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int people = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        int tasks = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);

        int nodes = people + tasks + 2;

        graph = new boolean[nodes][nodes];

        parents = new int[nodes];

        Arrays.fill(parents, -1);

        for (int person = 0; person < people; person++) {
            String line = scanner.nextLine();
            for (int task = 0; task < tasks; task++) {
                char letter = line.charAt(task);
                graph[person][people + task] = letter == 'Y';
            }
        }

        for (int person = 0; person < people; person++) {
            graph[nodes - 2][person] = true;
        }

        for (int task = 0; task < tasks; task++) {
            graph[people + task][nodes - 1] = true;
        }

        while(bfs(nodes - 2, nodes - 1)) {
            int node = nodes - 1;
            while(node != nodes - 2) {
                graph[parents[node]][node] = false;
                graph[node][parents[node]] = true;
                node = parents[node];
            }
        }

        for (int person = 0; person < people; person++) {
            for (int task = 0; task < tasks; task++) {
                if(graph[people + task][person]) {
                    System.out.printf("%s-%d%n", (char) (person + 65), task + 1);
                }
            }
        }
    }

    private static boolean bfs(int source, int target) {
        boolean[] visited = new boolean[graph.length];

        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(source);

        visited[source] = true;

        while(!queue.isEmpty()) {
            int node = queue.poll();
            for (int child = graph[node].length - 1; child >= 0; child--) {
                if(graph[node][child] && !visited[child]) {
                    visited[child] = true;
                    parents[child] = node;
                    queue.offer(child);
                }
            }
        }

        return visited[target];
    }
}
