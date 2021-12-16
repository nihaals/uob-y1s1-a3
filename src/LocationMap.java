import java.io.*;
import java.util.*;

// Class that behaves like a map
public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME = "locations.txt";
    private static final String DIRECTIONS_FILE_NAME = "directions.txt";

    /* TODO
     * create a static locations HashMap
     */
    private static HashMap<Integer, Location> locations = new HashMap<>();

    static {
        /* TODO
         * create a FileLogger object
         */
        FileLogger fileLogger = new FileLogger();

        /* TODO
         * create a ConsoleLogger object
         */
        ConsoleLogger consoleLogger = new ConsoleLogger();

        /* TODO
         * Read from LOCATIONS_FILE_NAME so that a user can navigate from one location to another
         * use try-with-resources/catch block for the FileReader
         * extract the location and the description on each line
         * print all locations and descriptions to both console and file
         * check the ExpectedOutput files
         * put each location in the locations HashMap using temporary empty hashmaps for exits
         */
        try (FileReader fileReader = new FileReader(LOCATIONS_FILE_NAME);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            fileLogger.log("Available locations:");
            consoleLogger.log("Available locations:");
            while ((line = bufferedReader.readLine()) != null) {
                String[] locationAndDescription = line.split(",", 2);
                int locationId = Integer.parseInt(locationAndDescription[0]);
                String description = locationAndDescription[1];
                Location location = new Location(locationId, description, new HashMap<>());
                locations.put(location.getLocationId(), location);

                String out = location.getLocationId() + ": " + location.getDescription();
                fileLogger.log(out);
                consoleLogger.log(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* TODO
         * Read from DIRECTIONS_FILE_NAME so that a user can move from A to B, i.e. current location to next location
         * use try-with-resources/catch block for the FileReader
         * extract the 3 elements on each line: location, direction, destination
         * print all locations, directions and destinations to both console and file
         * check the ExpectedOutput files
         * for each location, create a new location object and add its exit
         */
        try (FileReader fileReader = new FileReader(DIRECTIONS_FILE_NAME);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            fileLogger.log("Available directions:");
            consoleLogger.log("Available directions:");
            while ((line = bufferedReader.readLine()) != null) {
                String[] locationAndDirection = line.split(",");
                int locationId = Integer.parseInt(locationAndDirection[0]);
                String direction = locationAndDirection[1];
                int destinationId = Integer.parseInt(locationAndDirection[2]);
                Location location = locations.get(locationId);
                location.addExit(direction, destinationId);

                String out = location.getLocationId() + ": " + direction + ": " + destinationId;
                fileLogger.log(out);
                consoleLogger.log(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* TODO
     * implement all methods for Map
     */
    @Override
    public int size() {
        // TODO
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        // TODO
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        // TODO
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        // TODO
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        // TODO
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        // TODO
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        // TODO
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        // TODO
        locations.putAll(m);
    }

    @Override
    public void clear() {
        // TODO
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        // TODO
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        // TODO
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        // TODO
        return locations.entrySet();
    }
}
