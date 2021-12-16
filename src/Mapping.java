import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mapping {

    public static final int INITIAL_LOCATION = 95;

    private static final LocationMap locationMap = new LocationMap();

    private final HashMap<String, String> vocabulary = new HashMap<>();

    private final FileLogger fileLogger = new FileLogger();
    private final ConsoleLogger consoleLogger = new ConsoleLogger();

    public Mapping() {
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("EAST", "E");
        vocabulary.put("WEST", "W");
        vocabulary.put("UP", "U");
        vocabulary.put("DOWN", "D");
        vocabulary.put("SOUTHWEST", "SW");
        vocabulary.put("SOUTHEAST", "SE");
        vocabulary.put("NORTHEAST", "NE");
        vocabulary.put("NORTHWEST", "NW");
        vocabulary.put("QUIT", "Q");
    }

    public void mapping() {
        Scanner scanner = new Scanner(System.in);

        int location = INITIAL_LOCATION;

        while (true) {
            Location currentLocation = locationMap.get(location);
            String description = currentLocation.getDescription();
            fileLogger.log(description);
            consoleLogger.log(description);

            if (currentLocation.getLocationId() == 0) {
                break;
            }

            Map<String, Integer> exits = currentLocation.getExits();

            final StringBuilder exitsString = new StringBuilder();
            exitsString.append("Available exits are ");
            for (String exit : exits.keySet()) {
                exitsString.append(exit).append(", ");
            }
            fileLogger.log(exitsString.toString());
            consoleLogger.log(exitsString.toString());

            String direction = scanner.nextLine().trim().toUpperCase();

            // Convert to shorthand (value of `vocabulary`)
            // `direction` is:
            // 1. Exact value
            // 2. Exact key
            // 3. Contains key surrounded by spaces
            if (vocabulary.containsValue(direction)) {
                // 1. Exact value
            } else if (vocabulary.containsKey(direction)) {
                // 2. Exact key
                direction = vocabulary.get(direction);
            } else {
                // Check if the direction contains the key surrounded by spaces
                String[] words = direction.split(" ");
                boolean found = false;
                // Use last valid word
                for (int i = words.length - 1; i >= 0; i--) {
                    String word = words[i];
                    if (vocabulary.containsKey(word)) {
                        direction = vocabulary.get(word);
                        found = true;
                    }
                }
                if (!found) {
                    fileLogger.log("You cannot go in that direction");
                    consoleLogger.log("You cannot go in that direction");
                    continue;
                }
            }

            if (exits.containsKey(direction)) {
                location = exits.get(direction);
            } else {
                fileLogger.log("You cannot go in that direction");
                consoleLogger.log("You cannot go in that direction");
            }
        }
    }

    public static void main(String[] args) {
        Mapping mapping = new Mapping();
        mapping.mapping();
    }
}
