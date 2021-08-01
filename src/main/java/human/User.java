package main.java.human;

/**
 * The type User.
 *
 * @author Hannes Schniz
 * @version 1.0
 */
public abstract class User {

    private final String name;

    /**
     * Instantiates a new User.
     *
     * @param name the name
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * Get name string.
     *
     * @return the string
     */
    public String getName() {
        return this.name;
    }

}
