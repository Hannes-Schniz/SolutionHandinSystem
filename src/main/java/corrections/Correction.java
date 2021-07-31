package main.java.corrections;

import main.java.human.Student;
import main.java.human.Tutor;
import main.java.human.User;
import main.java.texts.HandIn;
import main.java.texts.Question;
import main.java.texts.Text;

/**
 * The type Correction.
 *
 * @author Hannes Schniz
 * @version 1.0
 */
public class Correction extends Text {

    private int mark;

    private int question_id;

    private HandIn original;

    private int version;

    /**
     * Instantiates a new Correction.
     *
     * @param mark     the mark
     * @param original the original
     * @param tutor    the tutor
     * @param version  the version
     */
    public Correction(int mark, HandIn original, Tutor tutor, int version) {
        super("", tutor);
        this.mark = mark;
        this.version = version;
        this.inputOriginal(original);
    }

    private void inputOriginal(HandIn original) {
        this.original = original;
        this.question_id = original.getOriginal().getId();
    }

    /**
     * Gets mark.
     *
     * @return the mark
     */
    public int getMark() {
        return mark;
    }

    /**
     * Sets mark.
     *
     * @param mark the mark
     */
    public void setMark(int mark) {
        this.mark = mark;
    }

    /**
     * Gets question id.
     *
     * @return the question id
     */
    public int getQuestion_id() {
        return question_id;
    }


    /**
     * Gets version.
     *
     * @return the version
     */
    public int getVersion() {
        return version;
    }

    public HandIn getOriginal() {
        return original;
    }
}
