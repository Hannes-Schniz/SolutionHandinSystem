package Human;

public class Student extends User{

    private int id;

    public Student(String name, int id){
        super(name);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
