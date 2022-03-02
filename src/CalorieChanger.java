public class CalorieChanger extends Entity {
    // A CalorieChanger may refer to a food or a sport
    
    protected int calories;

    public static CalorieChanger parse(String line) {
        CalorieChanger cc = new CalorieChanger();
        String[] args = line.split("\t");
        cc.id = Integer.parseInt(args[0]);
        cc.name = args[1];
        cc.calories = Integer.parseInt(args[2]);
        
        return cc;
    }
}
