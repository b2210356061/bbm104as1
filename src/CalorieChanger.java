public class CalorieChanger {
    // A CalorieChanger may refer to a food or a sport
    private int id, calories;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public static CalorieChanger parse(String line) {
        CalorieChanger cc = new CalorieChanger();
        String[] args = line.split("\t");
        cc.id = Integer.parseInt(args[0]);
        cc.name = args[1];
        cc.calories = Integer.parseInt(args[2]);

        return cc;
    }
}
