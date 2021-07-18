package Corrections;

import Human.Dozent;
import Human.Tutor;
import Texts.HandIn;

public class Plagiarism extends Correction{

    private static final int MARK_FIVE = 5;

    private Dozent finder;

    public Plagiarism(HandIn original, Tutor tutor, int version, Dozent finder) {
        super(MARK_FIVE, original, tutor, version);
        this.finder = finder;
    }

    public Plagiarism(Correction correction, Dozent finder){
        super(MARK_FIVE, correction.getOriginal(), correction.getTutor(), correction.getVersion());
        this.finder = finder;
    }

}
