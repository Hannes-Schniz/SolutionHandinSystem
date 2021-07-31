package texts;

import human.Student;
import human.User;

/**
 * The type Hand in.
 *
 * @author Hannes Schniz
 * @version 1.0
 */
public class HandIn extends Text {

    private boolean corrected;

    private final Question original;

    private final Student student;

    /**
     * Instantiates a new Hand in.
     *
     * @param text     the text
     * @param original the original
     * @param student  the student
     */
    public HandIn(String text, Question original, Student student) {
        super(text);
        this.corrected = false;
        this.original = original;
        this.student = student;
    }

    /**
     * Is corrected boolean.
     *
     * @return the boolean
     */
    public boolean isCorrected() {
        return corrected;
    }

    /**
     * Sets corrected.
     *
     * @param corrected the corrected
     */
    public void setCorrected(boolean corrected) {
        this.corrected = corrected;
    }

    /**
     * Gets original.
     *
     * @return the original
     */
    public Question getOriginal() {
        return original;
    }

    /**
     * Returns Producer
     *
     * @return the original
     */
    public User getProducer() {
        return student;
    }
}
