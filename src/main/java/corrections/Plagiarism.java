package main.java.corrections;

import main.java.human.Dozent;
import main.java.human.Tutor;
import main.java.texts.HandIn;

/**
 * The type Plagiarism.
 *
 * @author Hannes Schniz
 * @version 1.0
 */
public class Plagiarism extends Correction {

    private static final int MARK_FIVE = 5;

    private Dozent finder;

    /**
     * Instantiates a new Plagiarism.
     *
     * @param finder   the finder
     */
    public Plagiarism(HandIn original, Tutor tutor, String comment, Dozent finder) {
        super(MARK_FIVE, original, tutor, comment);
        this.finder = finder;
    }

    /**
     * Instantiates a new Plagiarism.
     *
     * @param correction the correction
     * @param finder     the finder
     */
    public Plagiarism(Correction correction, Dozent finder) {
        super(MARK_FIVE, correction.getOriginal(), (Tutor) correction.getProducer(), correction.getText());
        this.finder = finder;
    }
}
