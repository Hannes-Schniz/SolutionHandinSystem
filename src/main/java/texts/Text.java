package texts;

import human.User;

/**
 * The type Text.
 *
 * @author Hannes Schniz
 * @version 1.0
 */
public class Text {

    private String text;

    private User producer;

    /**
     * Instantiates a new Text.
     *
     * @param text the text
     */
    public Text(String text) {
        this.text = text;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets text.
     *
     * @param text     the text
     * @param producer the producer
     */
    public void setText(String text, User producer) {
        this.text = text;
        this.producer = producer;
    }

    /**
     * Get producer user.
     *
     * @return the user
     */
    public User getProducer() {
        return  producer;
    }
}
