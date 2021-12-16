import java.io.*;
import java.util.*;

public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME = "locations.txt";
    private static final String DIRECTIONS_FILE_NAME = "directions.txt";

    private static HashMap<Integer, Location> locations = new HashMap<>();

    static {
        FileLogger fileLogger = new FileLogger();
        ConsoleLogger consoleLogger = new ConsoleLogger();

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

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        locations.putAll(m);
    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
