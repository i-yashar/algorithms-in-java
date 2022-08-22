import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Cinema {
    public static String[] combinations;
    public static List<String> friends;
    public static Set<String> used = new HashSet<>();
    public static Map<Integer, String> seats = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        friends = Arrays.stream(reader.readLine().split(",\\s+")).collect(Collectors.toList());

        for (int i = 0; i < friends.size(); i++) {
            seats.put(i, null);
        }

        while (true) {
            String line = reader.readLine();

            if (line.equals("generate")) {
                break;
            }

            int position = Integer.parseInt(line.split(" - ")[1]) - 1;
            String name = line.split(" - ")[0];
            seats.put(position, name);
            friends.remove(name);
        }

        combinations = new String[friends.size()];
        generate(0);
    }

    private static void generate(int index) {
        if (index > combinations.length - 1) {
            printArray(combinations);
            return;
        }

        for (int i = 0; i < combinations.length; i++) {
            if (!used.contains(friends.get(i))) {
                combinations[index] = friends.get(i);
                used.add(friends.get(i));
                generate(index + 1);
                used.remove(friends.get(i));
            }
        }
    }

    private static void printArray(String[] combinations) {
        int index = 0;

        List<String> output = new ArrayList<>();

        for (Integer seat : seats.keySet()) {
            if(seats.get(seat) != null){
                output.add(seats.get(seat));
            } else{
                output.add(combinations[index++]);
            }
        }

        System.out.println(String.join(" ", output));
    }
}
