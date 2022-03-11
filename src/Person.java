import java.time.Year;

public class Person {
    private int id, weight, height, age, caloriesTaken, caloriesBurned;
    private String name, gender;

    private Person(int id, String name, String gender, int weight, int height, int birth) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = Year.now().getValue() - birth;
    }

    private int getDailyCalorieNeeds() {
        if (gender.equals("male")) {
            return (int) Math.round(66.0 + (13.75 * weight) + (5 * height) - (6.8 * age));
        }
        // female
        return (int) Math.round(665.0 + (9.6 * weight) + (1.7 * height) - (4.7 * age));
    }

    public int getId() {
        return id;
    }

    public String getNetCalories() {
        int result = -getDailyCalorieNeeds() + caloriesTaken - caloriesBurned;
        if (result >= 0) return "+" + result;
        return Integer.toString(result);
    }

    public String getStatus() {
        return name + "\t" + age + "\t" + getDailyCalorieNeeds() + "kcal\t" + caloriesTaken
                + "kcal\t" + caloriesBurned + "kcal\t" + getNetCalories() + "kcal";
    }

    public static int calculateGain(CalorieChanger food, int portions) {
        return food.getCalories() * portions;
    }

    public void eat(int calories) {
        caloriesTaken += calories;
    }

    public static int calculateLoss(CalorieChanger sport, int duration) {
        return (int) Math.round((double) sport.getCalories() * ((double) duration / 60.0));
    }

    public void exercise(int calories) {
        caloriesBurned += calories;
    }

    public static Person parse(String line) {
        String[] args = line.trim().split("\t");
        int id = Integer.parseInt(args[0]);
        String name = args[1];
        String gender = args[2];
        int weight = Integer.parseInt(args[3]);
        int height = Integer.parseInt(args[4]);
        int birth = Integer.parseInt(args[5]);

        return new Person(id, name, gender, weight, height, birth);
    }
}
