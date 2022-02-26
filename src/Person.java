public class Person implements Parsable<Person> {
    private int id, weight, height, birth;
    private String name, gender;

    public Person(int id, String name, String gender, int weight, int height, int birth) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.birth = birth;
    }

    @Override
    public Person parse(String line){
        
    }
}
