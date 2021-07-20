package Corrections;

import Human.Tutor;
import Texts.HandIn;
import Texts.Text;

/**
 * The type Correction.
 */
public class Correction extends Text {

    private int mark;

    private int question_id;

    private HandIn original;

    private Tutor tutor;

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
        super("");
        this.mark = mark;
        this.tutor = tutor;
        this.version = version;
        this.inputOriginal(original);
    }

    private void inputOriginal(HandIn original){
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
     * Gets original.
     *
     * @return the original
     */
    public HandIn getOriginal() {
        return original;
    }

    /**
     * Gets tutor.
     *
     * @return the tutor
     */
    public Tutor getProducer() {
        return tutor;
    }

    /**
     * Gets version.
     *
     * @return the version
     */
    public int getVersion() {
        return version;
    }
}
