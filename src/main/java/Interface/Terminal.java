package Interface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Terminal {

    BufferedReader reader;

    public Terminal() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void println(String line){
        System.out.println(line);
    }

    public void printlines(String[] lines){
        for (int i = 0; i < lines.length; i++) {
            System.out.println(lines[i]);
        }
    }

    public String readln() throws IOException {
        return reader.readLine();
    }


}
