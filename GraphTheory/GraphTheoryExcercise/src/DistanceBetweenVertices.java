import java.util.*;

public class DistanceBetweenVertices {

    public static Map<Integer, List<Integer>> graph = new HashMap<>();
    public static Map<Integer, Integer> prevNodes = new HashMap<>();
    public static boolean[] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int pairs = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {

            String[] tokens = scanner.nextLine().split(":");
            int node = Integer.parseInt(tokens[0]);

            graph.put(node, new ArrayList<>());

            if(tokens.length > 1){
                for (String child : tokens[1].split("\\s+")) {
                    graph.get(node).add(Integer.parseInt(child));
                }
            }
        }

        visited = new boolean[100];


        for (int i = 0; i < pairs; i++) {
            String[] tokens = scanner.nextLine().split("-");
            int source = Integer.parseInt(tokens[0]);
            int destination = Integer.parseInt(tokens[1]);

            System.out.println("{" + source + ", " + destination + "} -> " + findPath(source, destination));
        }
    }

    private static int findPath(int source, int destination) {
        Arrays.fill(visited, false);
        Deque<Integer> queue = new ArrayDeque<>();
        int path = 0;

        queue.offer(source);
        visited[source] = true;
        prevNodes.put(source, -1);

        while(!queue.isEmpty()){
            Integer node = queue.poll();

            if(node == destination){
                int count = 0;
                while(prevNodes.get(destination) != -1){
                    count++;
                    destination = prevNodes.get(destination);
                }
                return count;
            }

            boolean firstIteration = true;

            for (Integer child : graph.get(node)) {
                if(firstIteration){
                    firstIteration = false;
                    path++;
                }
                if(!visited[child]){
                    visited[child] = true;
                    prevNodes.put(child, node);
                    queue.offer(child);
                }
            }
        }

        return -1;
    }
}
