import java.io.*;

public class Logger {
    public static final String STARS = "***************";
    
    public static void log(String data){
        File file = new File("monitoring.txt");
        boolean isNewFile = !file.exists();
        try (FileWriter writer = new FileWriter("monitoring.txt", true)) {
            if (!isNewFile) writer.write("\n");
            writer.write(data);
        } catch (Exception e) {
            System.out.println("An error occurred while writing to monitoring.txt}\n" + e.toString());
        }
    }
}