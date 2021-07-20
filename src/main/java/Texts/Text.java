package Texts;

import Human.User;

public class Text {

    private String text;

    private User producer;

    public Text(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text, User producer) {
        this.text = text;
        this.producer = producer;
    }

    public User getProducer(){
        return  producer;
    }
}
