import java.io.*;

class Main {
    public static void main(String[] args) {
        // Deletes monitoring.txt if exists
        new File("monitoring.txt").delete();

        HealthTracker healthTracker = new HealthTracker(args[0]);
        healthTracker.readPeople();
        healthTracker.readFoods();
        healthTracker.readSports();
        healthTracker.executeCommands();
    }
}