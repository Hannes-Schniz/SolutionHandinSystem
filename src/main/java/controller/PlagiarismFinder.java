package main.java.controller;

import main.java.corrections.Datacollection;
import main.java.human.Dozent;
import main.java.texts.HandIn;
import main.java.texts.Question;

/**
 * The type Plagiarism finder.
 *
 * @author Hannes Schniz
 * @version 1.0
 */
public class PlagiarismFinder {

    private static final String EMPTY_STRING = "";

    private static final int ONE_HUNDRET = 100;

    private final Dozent searcher;

    private final Question originalQuestion;

    private final Datacollection plagiarismCollection;

    /**
     * Instantiates a new Plagiarism finder.
     *
     * @param searcher the searcher
     * @param originalQuestion the original
     */
    public PlagiarismFinder(Dozent searcher, Question originalQuestion) {
        this.searcher = searcher;
        this.originalQuestion = originalQuestion;
        this.plagiarismCollection = new Datacollection();
    }

    /**
     * Find plagiarism.
     *
     * @return the Datacollection
     */
    public Datacollection findPlagiarism(HandIn handIn, HandIn original) {

        String find = EMPTY_STRING;

        String originalHandIn = original.getText();

        String newHandIn = handIn.getText();

        for (int i = 0; i < originalHandIn.length(); i++) {
            if (i > newHandIn.length()) {
                break;
            }
            if ((originalHandIn.charAt(i) == newHandIn.charAt(i))) {
                find += originalHandIn.charAt(i);
            }
            else {
                if (plagiarismCollection.getLength() < find.length()) {
                    plagiarismCollection.setBiggestString(find);
                    plagiarismCollection.setLength(find.length());
                    plagiarismCollection.setPercent(calculatePercent(handIn, find));
                }
            }
        }

        return plagiarismCollection;
    }

    private double calculatePercent(HandIn handIn, String find) {

        double calculated = (double) handIn.getText().length() / (double) find.length() * ONE_HUNDRET;

        return (double) java.lang.Math.round(calculated * ONE_HUNDRET) / ONE_HUNDRET;
    }

    /**
     * Gets searcher.
     *
     * @return the searcher
     */
    public Dozent getSearcher() {
        return searcher;
    }

    /**
     * Gets original question.
     *
     * @return the original question
     */
    public Question getOriginalQuestion() {
        return originalQuestion;
    }
}
