package Texts;

import Human.Student;

public class HandIn extends Text{

    private boolean correccted;

    private Question original;

    private Student student;

    public HandIn(String text, Question original, Student student) {
        super(text);
        this.correccted = false;
        this.original = original;
        this.student = student;
    }

    public boolean isCorreccted() {
        return correccted;
    }

    public void setCorreccted(boolean correccted) {
        this.correccted = correccted;
    }

    public Question getOriginal() {
        return original;
    }

    public Student getStudent() {
        return student;
    }
}
