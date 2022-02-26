import java.io.*;
import java.util.*;

public class HealthTracker {
    private String pathCommand;

    // These hashmaps are in the format of <id>: <the object with that id>
    private HashMap<Integer, Person> people;
    private HashMap<Integer, Food> foods;
    private HashMap<Integer, Sport> sports;

    public HealthTracker(String pathCommand) {
        this.pathCommand = pathCommand;
    }

    public void readAllData(){
        readPeople();
        readFoods();
        readSports();
    }

    public void readPeople() {

    }

    public void readFoods() {

    }

    public void readSports() {

    }
}