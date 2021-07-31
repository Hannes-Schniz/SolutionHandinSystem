package main.java.userinputs;


import java.util.InputMismatchException;

/**
 * The type Parser.
 *
 * @author Hannes Schniz
 * @version 1.0
 */
public class Parser {

    private static final int ONE = 1;

    private static final int ZERO = 0;

    /**
     * Instantiates a new Parser.
     */
    public Parser() {

    }

    /**
     * Parse input string [ ].
     *
     * @param input the input
     * @return the string [ ]
     * @throws IllegalArgumentException the illegal argument exception
     */
    public String[] parseInput(String input) throws IllegalArgumentException {

        String parted = "";
        int spaces = ZERO;
        String[] returnArray = new String[ONE];

        if (!input.matches("[-a-zA-Z0-9_\\s.!?,]*")) {
            throw new IllegalArgumentException("wrong Input");
        }

        for (int i = ZERO; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                if (spaces == ZERO) {
                    returnArray[ZERO] = parted;
                    parted = "";
                }
                else {
                    returnArray = extendArray(returnArray);
                    returnArray[returnArray.length - ONE] = parted;
                    parted = "";
                }
                spaces++;
            }
            else {
                parted += input.charAt(i);
            }
            if (i == input.length() - ONE) {
                returnArray = extendArray(returnArray);
                returnArray[spaces] = parted;
            }
        }
        return returnArray;
    }

    private String[] extendArray(String[] input) {

        String[] workingArray = new String[input.length + ONE];
        System.arraycopy(input, 0, workingArray, 0, input.length);
        return workingArray;
    }

    /**
     * Parse number int.
     *
     * @param input the input
     * @return the int
     * @throws IllegalArgumentException the illegal argument exception
     */
    public int parseNumber(String input) throws IllegalArgumentException {

        if (!input.matches("[0-9]+[0-9]*")) {
            throw new IllegalArgumentException("wrong input");
        }

        return Integer.parseInt(input);

    }

    public void checkName(String input) {
        if (input.matches("[A-Z]+[a-z]*")) {
            return;
        }
        throw new InputMismatchException("Name has to start with upper case letter");
    }
}
