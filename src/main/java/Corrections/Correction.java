package Corrections;

import Human.Tutor;
import Texts.HandIn;

public class Correction {

    private int mark;

    private int question_id;

    private HandIn original;

    private Tutor tutor;

    private int version;

    public Correction(int mark, HandIn original, Tutor tutor, int version) {
        this.mark = mark;
        this.tutor = tutor;
        this.version = version;
        this.inputOriginal(original);
    }

    private void inputOriginal(HandIn original){
        this.original = original;
        this.question_id = original.getOriginal().getId();
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public HandIn getOriginal() {
        return original;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public int getVersion() {
        return version;
    }
}
