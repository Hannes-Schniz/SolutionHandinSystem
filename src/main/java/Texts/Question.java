package Texts;

public class Question extends Text{

    private int id;

    public Question(String text, int id) {
        super(text);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
