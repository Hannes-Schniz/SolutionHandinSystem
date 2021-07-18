package Texts;

public class HandIn extends Text{

    private boolean correccted;

    public HandIn(String text) {
        super(text);
        this.correccted = false;
    }

    public boolean isCorreccted() {
        return correccted;
    }

    public void setCorreccted(boolean correccted) {
        this.correccted = correccted;
    }
}
