import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mapping {

    public static final int INITIAL_LOCATION = 95;

    /* TODO
     * create a static LocationMap object
     */
    private static LocationMap locationMap = new LocationMap();

    /* TODO
     * create a vocabulary HashMap to store all directions a user can go
     */
    private final HashMap<String, String> vocabulary = new HashMap<>();

    /* TODO
     * create a FileLogger object
     */
    private final FileLogger fileLogger = new FileLogger();

    /* TODO
     * create a ConsoleLogger object
     */
    private final ConsoleLogger consoleLogger = new ConsoleLogger();

    public Mapping() {
        //vocabulary.put("QUIT", "Q"); //example
        /* TODO
         * complete the vocabulary HashMap <Key, Value> with all directions.
         * use the directions.txt file and crosscheck with the ExpectedInput and ExpectedOutput files to find the keys and the values
         */
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("EAST", "E");
        vocabulary.put("WEST", "W");
        vocabulary.put("UP", "U");
        vocabulary.put("DOWN", "D");
        vocabulary.put("QUIT", "Q");
        vocabulary.put("SOUTHWEST", "SW");
        vocabulary.put("SOUTHEAST", "SE");
        vocabulary.put("NORTHEAST", "NE");
        vocabulary.put("NORTHWEST", "NW");
    }

    public void mapping() {

        /* TODO
         * create a Scanner object
         */
        Scanner scanner = new Scanner(System.in);

        /*
         * initialise a location variable with the INITIAL_LOCATION
         */
        int location = INITIAL_LOCATION;

        while (true) {

            /* TODO
             * get the location and print its description to both console and file
             * use the FileLogger and ConsoleLogger objects
             */
            Location currentLocation = locationMap.get(location);
            String description = currentLocation.getDescription();
            fileLogger.log(description);
            consoleLogger.log(description);

            /* TODO
             * verify if the location is exit
             */
            if (currentLocation.getLocationId() == 0) {
                break;
            }

            /* TODO
             * get a map of the exits for the location
             */
            Map<String, Integer> exits = currentLocation.getExits();

            /* TODO
             * print the available exits (to both console and file)
             * crosscheck with the ExpectedOutput files
             * Hint: you can use a StringBuilder to append the exits
             */
            StringBuilder exitsString = new StringBuilder();
            exitsString.append("Available exits are ");
            for (String exit : exits.keySet()) {
                exitsString.append(exit + ", ");
            }
            fileLogger.log(exitsString.toString());
            consoleLogger.log(exitsString.toString());

            /* TODO
             * input a direction
             * ensure that the input is converted to uppercase
             */
            String direction = scanner.nextLine().toUpperCase();

            /* TODO
             * are we dealing with a letter / word for the direction to go to?
             * available inputs are: a letter(the HashMap value), a word (the HashMap key), a string of words that contains the key
             * crosscheck with the ExpectedInput and ExpectedOutput files for examples of inputs
             * if the input contains multiple words, extract each word
             * find the direction to go to using the vocabulary mapping
             * if multiple viable directions are specified in the input, choose the last one
             */
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
                Boolean found = false;
                for (String word : words) {
                    if (vocabulary.containsKey(word)) {
                        direction = vocabulary.get(word);
                        found = true;
                    }
                }
                if (!found) {
                    // Invalid
                    // TODO
                    // fileLogger.log("Invalid direction, not valid direction: " + direction);
                    // consoleLogger.log("Invalid direction, not valid direction: " + direction);
                    fileLogger.log("You cannot go in that direction");
                    consoleLogger.log("You cannot go in that direction");
                    continue;
                }
            }

            /* TODO
             * if user can go in that direction, then set the location to that direction
             * otherwise print an error message (to both console and file)
             * check the ExpectedOutput files
             */
            if (exits.containsKey(direction)) {
                location = exits.get(direction);
            } else {
                // fileLogger.log("Invalid direction, can't go in direction: " + direction);
                // consoleLogger.log("Invalid direction, can't go in direction: " + direction);
                fileLogger.log("You cannot go in that direction");
                consoleLogger.log("You cannot go in that direction");
            }
        }
    }

    public static void main(String[] args) {
        /* TODO
         * run the program from here
         * create a Mapping object
         * start the game
         */
        Mapping mapping = new Mapping();
        mapping.mapping();
    }

}
