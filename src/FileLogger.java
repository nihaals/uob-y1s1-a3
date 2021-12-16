import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    private static final String FILE_LOGGER_NAME = "StudentFileOutput.txt";

    static {
        try {
            File file = new File(FILE_LOGGER_NAME);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            // noop
        }
    }

    @Override
    public void log(String message) {
        try (FileWriter fileWriter = new FileWriter(FILE_LOGGER_NAME, true)) {
            fileWriter.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
