import java.time.Year;

public class Person extends Entity {
    private int weight, height, age, caloriesTaken, caloriesBurned;
    private String gender;

    private Person(int id, String name, String gender, int weight, int height, int birth) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = Year.now().getValue() - birth;
    }

    private int getDailyCalorieNeeds() {
        if (gender == "male") {
            return (int) Math.round(66 + (13.75 * weight) + (5 * height) - (6.8 * age));
        }
        // female
        return (int) Math.round(665 + (9.6 * weight) + (1.7 * height) - (4.7 * age));
    }

    private String getResult() {
        int result = caloriesTaken - caloriesBurned - getDailyCalorieNeeds();
        if (result >= 0) return "+" + result;
        return Integer.toString(result);
    }

    public String getStatus() {
        return name + "\t" + age + "\t" + getDailyCalorieNeeds() + "\t" + caloriesTaken + "\t"
                + caloriesBurned + "\t" + getResult();
    }

    public static int calculateGain(CalorieChanger food, int portions) {
        return food.calories * portions;
    }

    public void eat(int calories) {
        caloriesTaken += calories;
    }

    public static int calculateLoss(CalorieChanger sport, int duration) {
        return (int) Math.round((double) sport.calories * ((double) duration / 60.0));
    }

    public void exercise(int calories) {
        caloriesBurned += calories;
    }

    public static Person parse(String line) {
        String[] args = line.trim().split("\t");
        Person person = new Person(Integer.parseInt(args[0]), args[1], args[2],
                Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5])

        );
        return person;
    }
}
