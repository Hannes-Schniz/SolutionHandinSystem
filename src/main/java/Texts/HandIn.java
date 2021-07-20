package Texts;

import Human.Student;
import Human.User;

public class HandIn extends Text{

    private boolean corrected;

    private Question original;

    private Student student;

    public HandIn(String text, Question original, Student student) {
        super(text);
        this.corrected = false;
        this.original = original;
        this.student = student;
    }

    public boolean isCorrected() {
        return corrected;
    }

    public void setCorrected(boolean corrected) {
        this.corrected = corrected;
    }

    public Question getOriginal() {
        return original;
    }

    public User getProducer(){
        return student;
    }
}
