package main.java.human;

/**
 * The type Student.
 *
 * @author Hannes Schniz
 * @version 1.0
 */
public class Student extends User {

    private final int id;

    /**
     * Instantiates a new Student.
     *
     * @param name the name
     * @param id   the id
     */
    public Student(String name, int id) {
        super(name);
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }
}
