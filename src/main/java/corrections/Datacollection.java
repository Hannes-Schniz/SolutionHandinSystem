package main.java.corrections;

import main.java.human.Student;

/**
 * The type Datacollection.
 *
 * @author Hannes Schniz
 * @version 1.0
 */
public class Datacollection {

    private static final int MINUSONE = -1;

    private static final String EMPTY_STRING = "";

    private String biggestString;

    private int length;

    private double percent;

    private Student studentOne;

    private Student studentTwo;

    /**
     * Instantiates a new Datacollection.
     */
    public Datacollection() {

        this.biggestString = EMPTY_STRING;
        this.length = MINUSONE;
        this.percent = MINUSONE;
    }

    /**
     * Gets biggest string.
     *
     * @return the biggest string
     */
    public String getBiggestString() {
        return biggestString;
    }

    /**
     * Sets biggest string.
     *
     * @param biggestString the biggest string
     */
    public void setBiggestString(String biggestString) {
        this.biggestString = biggestString;
    }

    /**
     * Gets length.
     *
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets length.
     *
     * @param length the length
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Gets percent.
     *
     * @return the percent
     */
    public double getPercent() {
        return percent;
    }

    /**
     * Sets percent.
     *
     * @param percent the percent
     */
    public void setPercent(double percent) {
        this.percent = percent;
    }

    /**
     * Gets student one.
     *
     * @return the student one
     */
    public Student getStudentOne() {
        return studentOne;
    }

    /**
     * Sets student one.
     *
     * @param studentOne the student one
     */
    public void setStudentOne(Student studentOne) {
        this.studentOne = studentOne;
    }

    /**
     * Gets student two.
     *
     * @return the student two
     */
    public Student getStudentTwo() {
        return studentTwo;
    }

    /**
     * Sets student two.
     *
     * @param studentTwo the student two
     */
    public void setStudentTwo(Student studentTwo) {
        this.studentTwo = studentTwo;
    }
}
