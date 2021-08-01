package main.java.corrections;

import main.java.human.Tutor;
import main.java.texts.HandIn;
import main.java.texts.Text;

/**
 * The type Correction.
 *
 * @author Hannes Schniz
 * @version 1.0
 */
public class Correction extends Text {

    /**
     * The Mark.
     */
    protected int mark;

    private HandIn original;

    /**
     * Instantiates a new Correction.
     *
     * @param mark     the mark
     * @param original the original
     * @param tutor    the tutor
     * @param comment  the comment
     */
    public Correction(int mark, HandIn original, Tutor tutor,  String comment) {
        super(comment, tutor);
        this.mark = mark;
        this.inputOriginal(original);
    }

    private void inputOriginal(HandIn original) {
        this.original = original;
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
     * Gets original.
     *
     * @return the original
     */
    public HandIn getOriginal() {
        return original;
    }
}
