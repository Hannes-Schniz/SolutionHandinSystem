package Corrections;

public class Datacollection {

    private static int MINUS_ONE = -1;

    private static String EMPTY_STRING = "";

    private String biggestString;

    private int length;

    private double percent;

    public Datacollection() {

        this.biggestString = EMPTY_STRING;
        this.length = MINUS_ONE;
        this.percent = MINUS_ONE;
    }

    public static int getMinusOne() {
        return MINUS_ONE;
    }

    public static void setMinusOne(int minusOne) {
        MINUS_ONE = minusOne;
    }

    public static String getEmptyString() {
        return EMPTY_STRING;
    }

    public static void setEmptyString(String emptyString) {
        EMPTY_STRING = emptyString;
    }

    public String getBiggestString() {
        return biggestString;
    }

    public void setBiggestString(String biggestString) {
        this.biggestString = biggestString;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public boolean hasPlagiarism(){
        return this.length != MINUS_ONE;
    }

}
