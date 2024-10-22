import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    public static void log(String message) {
        try {
            File file = new File("log.txt");
            FileWriter writer = new FileWriter(file, true);
            writer.write(message + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
