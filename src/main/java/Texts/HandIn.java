package Texts;

public class HandIn extends Text{

    private boolean correccted;

    private Question original;

    public HandIn(String text, Question original) {
        super(text);
        this.correccted = false;
        this.original = original;
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
}
