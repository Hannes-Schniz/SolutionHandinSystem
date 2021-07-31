package corrections;

import human.Dozent;
import human.Tutor;
import texts.HandIn;

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
     * @param original the original
     * @param tutor    the tutor
     * @param version  the version
     * @param finder   the finder
     */
    public Plagiarism(HandIn original, Tutor tutor, int version, Dozent finder) {
        super(MARK_FIVE, original, tutor, version);
        this.finder = finder;
    }

    /**
     * Instantiates a new Plagiarism.
     *
     * @param correction the correction
     * @param finder     the finder
     */
    public Plagiarism(Correction correction, Dozent finder) {
        super(MARK_FIVE, correction.getOriginal(), correction.getProducer(), correction.getVersion());
        this.finder = finder;
    }

}
