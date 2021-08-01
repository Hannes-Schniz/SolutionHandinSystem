package main.java.userinputs;

import java.util.Scanner;

/**
 * The type Terminal.
 *
 * @author Hannes Schniz
 * @version 1.0
 */
public class Terminal {

    /**
     * The Reader.
     */
    Scanner scanner;

    /**
     * Instantiates a new Terminal.
     */
    public Terminal() {
        scanner = new Scanner(System.in);
    }

    /**
     * Println.
     *
     * @param line the line
     */
    public void println(String line) {
        System.out.println(line);
    }

    /**
     * Printlines.
     *
     * @param lines the lines
     */
    public void printlines(String[] lines) {
        for (String line : lines) {
            System.out.println(line);
        }
    }

    /**
     * Readln string.
     *
     * @return the string
     */
    public String readln() {
        return scanner.nextLine();
    }

    /**
     * Print error.
     *
     * @param input the input
     */
    public void printError(String input) {
        System.out.println("Error: " + input);
    }
}
