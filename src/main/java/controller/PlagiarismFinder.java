package main.java.controller;

import main.java.corrections.Datacollection;
import main.java.human.Student;
import main.java.texts.HandIn;

/**
 * The type Plagiarism finder.
 *
 * @author Hannes Schniz
 * @version 1.0
 */
public class PlagiarismFinder {

    private static final int ONE_HUNDRET = 100;

    private final Datacollection plagiarismCollection;

    /**
     * Instantiates a new Plagiarism finder.
     */
    public PlagiarismFinder() {
        this.plagiarismCollection = new Datacollection();
    }

    /**
     * Find plagiarism.
     *
     * @param handIn   the hand in
     * @param original the original
     * @return the Datacollection
     */
    public Datacollection findPlagiarism(HandIn handIn, HandIn original) {

        String originalHandIn = original.getText();

        String newHandIn = handIn.getText();

        for (int i = 0; i < newHandIn.length(); i++) {
            int count = 0;
            String aproxLongest = "";
            for (int j = 0; j < originalHandIn.length(); j++) {
                char current = newHandIn.charAt(i + count);
                if (current == originalHandIn.charAt(j)) {
                    aproxLongest = aproxLongest + current;
                    count++;
                    if (aproxLongest.length() > plagiarismCollection.getLength()) {
                        plagiarismCollection.setBiggestString(aproxLongest);
                        plagiarismCollection.setLength(aproxLongest.length());
                    }
                }
                else {
                    aproxLongest = "";
                    count = 0;
                }
                if (i + count > newHandIn.length() - 1) {
                    if (aproxLongest.length() > plagiarismCollection.getLength()) {
                        plagiarismCollection.setBiggestString(aproxLongest);
                        plagiarismCollection.setLength(aproxLongest.length());
                    }
                    aproxLongest = "";
                    count = 0;
                }
            }
        }
        plagiarismCollection.setPercent(calculatePercent(handIn, plagiarismCollection.getBiggestString()));
        plagiarismCollection.setStudentOne((Student) handIn.getProducer());
        plagiarismCollection.setStudentTwo((Student) original.getProducer());

        return plagiarismCollection;
    }

    private double calculatePercent(HandIn handIn, String find) {

        double calculated = (double) find.length() / (double) handIn.getText().length() * ONE_HUNDRET;

        double preReturn =  java.lang.Math.round(calculated * ONE_HUNDRET);
        return preReturn / ONE_HUNDRET;
    }
}
