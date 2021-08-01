package main.java.corrections;

import main.java.human.Student;

/**
 * The type Datacollection.
 *
 * @author Hannes Schniz
 * @version 1.0
 */
public class Datacollection {

    private static int MINUS_ONE = -1;

    private static String EMPTY_STRING = "";

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
        this.length = MINUS_ONE;
        this.percent = MINUS_ONE;
    }

    /**
     * Gets minus one.
     *
     * @return the minus one
     */
    public static int getMinusOne() {
        return MINUS_ONE;
    }

    /**
     * Sets minus one.
     *
     * @param minusOne the minus one
     */
    public static void setMinusOne(int minusOne) {
        MINUS_ONE = minusOne;
    }

    /**
     * Gets empty string.
     *
     * @return the empty string
     */
    public static String getEmptyString() {
        return EMPTY_STRING;
    }

    /**
     * Sets empty string.
     *
     * @param emptyString the empty string
     */
    public static void setEmptyString(String emptyString) {
        EMPTY_STRING = emptyString;
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
     * Has plagiarism boolean.
     *
     * @return the boolean
     */
    public boolean hasPlagiarism() {
        return this.length != MINUS_ONE;
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
