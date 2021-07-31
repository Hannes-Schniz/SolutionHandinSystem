package userinputs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    BufferedReader reader;

    /**
     * Instantiates a new Terminal.
     */
    public Terminal() {
        reader = new BufferedReader(new InputStreamReader(System.in));
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
     * @throws IOException the io exception
     */
    public String readln() throws IOException {
        return reader.readLine();
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
