import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class HealthTracker {
    private String pathCommand;

    // These hashmaps are in the format of <id>: <the object with that id>
    private HashMap<Integer, Person> people = new HashMap<Integer, Person>();
    private HashMap<Integer, CalorieChanger> calorieChangers =
            new HashMap<Integer, CalorieChanger>();
    private ArrayList<Integer> orderOfPrinting = new ArrayList<Integer>();

    public HealthTracker(String pathCommand) {
        this.pathCommand = pathCommand;
    }

    private Stream<String> genericReader(String path) {
        File file = new File(path);
        ArrayList<String> list = new ArrayList<String>();
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                list.add(line);
            }
        } catch (Exception e) {
            System.out.println(
                    "An error occurred while reading from " + path + "\n" + e.toString());
        }
        return list.stream();
    }

    public void readPeople() {
        genericReader("people.txt").forEach(line -> {
            Person person = Person.parse(line);
            people.put(person.getId(), person);
        });
    }

    public void readFoods() {
        genericReader("food.txt").forEach(line -> {
            CalorieChanger food = CalorieChanger.parse(line);
            calorieChangers.put(food.getId(), food);
        });
    }

    public void readSports() {
        genericReader("sport.txt").forEach(line -> {
            CalorieChanger sport = CalorieChanger.parse(line);
            calorieChangers.put(sport.getId(), sport);
        });
    }

    private void print(String cmd) {
        int id = Integer.parseInt(cmd.substring(6, cmd.length() - 1));
        Person person = people.get(id);
        Logger.log(person.getStatus());
    }

    private void printList() {
        for (int id : orderOfPrinting) {
            Person person = people.get(id);
            Logger.log(person.getStatus());
        }
    }

    private void printWarn() {
        boolean hasWarnedAnyone = false;
        for (int id : orderOfPrinting) {
            Person person = people.get(id);
            if (Integer.parseInt(person.getNetCalories()) > 0) {
                hasWarnedAnyone = true;
                Logger.log(person.getStatus());
            }
        }
        if (!hasWarnedAnyone) {
            Logger.log("there\tis\tno\tsuch\tperson");
        }
    }

    private void eatOrExercise(String cmd) {
        String[] args = cmd.split("\t");
        int personId = Integer.parseInt(args[0]);
        if (!orderOfPrinting.contains(personId)) {
            orderOfPrinting.add(personId);
        }
        Person person = people.get(personId);
        int activityId = Integer.parseInt(args[1]); // foodId or sportId
        int coefficient = Integer.parseInt(args[2]); // portions or duration
        CalorieChanger activity = calorieChangers.get(activityId); // food or sport

        if (args[1].charAt(0) == '1') { // food ids start with 1
            int calories = Person.calculateGain(activity, coefficient);
            person.eat(calories);

            Logger.log(personId + "\thas\ttaken\t" + calories + "kcal\tfrom\t" + activity.name);
        } else { // sport ids start with 2
            int calories = Person.calculateLoss(activity, coefficient);
            person.exercise(calories);

            Logger.log(
                    personId + "\thas\tburned\t" + calories + "kcal\tthanks\tto\t" + activity.name);
        }
    }

    public void executeCommands() {
        String[] commands = genericReader(pathCommand).toArray(String[] ::new);

        try {
            for (int i = 0; i < commands.length; i++) {
                String cmd = commands[i];
                boolean isTheLastCommand = i == commands.length - 1;

                if (cmd.startsWith("print(")) {
                    print(cmd);
                } else if (cmd.equals("printList")) {
                    printList();
                } else if (cmd.equals("printWarn")) {
                    printWarn();
                } else { // <person> <food> <portions> or <person> <sport> <duration>
                    eatOrExercise(cmd);
                }

                if (!isTheLastCommand) Logger.log(Logger.STARS);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while executing the commands in command.txt\t"
                    + e.toString());
        }
    }
}