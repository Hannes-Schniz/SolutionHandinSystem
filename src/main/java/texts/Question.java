package main.java.texts;

/**
 * The type Question.
 *
 * @author Hannes Schniz
 * @version 1.0
 */
public class Question extends Text {

    private int id;

    /**
     * Instantiates a new Question.
     *
     * @param text the text
     * @param id   the id
     */
    public Question(String text, int id) {
        super(text, null);
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

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }
}
