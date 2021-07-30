package Interface;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static final int ONE = 1;

    private static final int ZERO = 0;

    public Parser() {

    }

    public String[] parseInput(String input) throws IllegalArgumentException{

        String parted = "";
        int spaces = ZERO;
        String[] returnArray = new String[ONE];

        if (!input.matches("[-a-zA-Z0-9_\\s.!?,]*")){
            throw new IllegalArgumentException("wrong Input");
        }

        for (int i = ZERO; i < input.length(); i++) {
            if (input.charAt(i) == ' '){
                if (spaces == ZERO){
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
            if (i == input.length() - ONE){
                returnArray = extendArray(returnArray);
                returnArray[spaces] = parted;
            }
        }
        return returnArray;
    }

    private String[] extendArray(String[] input){

        String[] workingArray = input;
        input = new String[workingArray.length + ONE];

        for (int i = 0; i < workingArray.length; i++) {
            input[i] = workingArray[i];
        }

        return input;
    }

    public int parseNumber(String input) throws IllegalArgumentException{

        if (!input.matches("[0-9]*")){
            throw new IllegalArgumentException("wrong input");
        }

        return Integer.parseInt(input);

    }
}
