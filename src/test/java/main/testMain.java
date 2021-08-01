package test.java.main;

import main.java.controller.Controller;
import main.java.corrections.Correction;
import main.java.corrections.Datacollection;
import main.java.human.Dozent;
import main.java.human.Student;
import main.java.human.Tutor;
import main.java.texts.HandIn;
import main.java.texts.Question;
import main.java.userinputs.Parser;
import main.java.userinputs.Terminal;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class testMain {

    private static Parser parser;
    private static Controller cntrl;
    private static Terminal terminal;

    private void testSetup() {
        parser = new Parser();
        cntrl = new Controller();
        terminal = new Terminal();
        Student Gael = new Student("Gael", 66666);
        Student Dakota = new Student("Dakota", 23442);
        Student Finley = new Student("Finley", 32000);
        cntrl.addUser(new Dozent("Pete"));
        cntrl.addUser(new Tutor("Bryn"));
        cntrl.addUser(new Tutor("Cassidy"));
        cntrl.addUser(Gael);
        cntrl.addUser(Dakota);
        cntrl.addUser(Finley);
        cntrl.addQuestion(new Question("Kompilieren_und_Ausführen", cntrl.getIndex()));
        cntrl.addQuestion(new Question("Ein-_und_Ausgabe", cntrl.getIndex()));
        try {
            cntrl.handIn(new HandIn("Die_Lösung_ist_42", cntrl.getQuestion(1), Gael));
            cntrl.handIn(new HandIn("Glaub_es_ist_42", cntrl.getQuestion(1), Dakota));
            cntrl.handIn(new HandIn("Keine_Ahnung", cntrl.getQuestion(1), Finley));
            cntrl.addReview(1, 66666, 2, "Ganz_gut_gelöst,_weiter_so!", "Cassidy");
            cntrl.addReview(1, 32000, 5, "Das_war_wohl_nix.", "Bryn");
            cntrl.addReview(1, 23442, 2, "Ganz_gut_gelöst,_weiter_so!", "Cassidy");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void addPlagTest() {
        testSetup();
        String[] userInput = {"mark-plagiarism", "Pete", "23442", "1"};
        try {
            int studentId = parser.parseNumber(userInput[2]);
            int questionId = parser.parseNumber(userInput[3]);
            String name = userInput[1];
            String print = cntrl.markPlagiarism(studentId, questionId, name);
            terminal.println(print);
        } catch (IllegalArgumentException e) {
            terminal.printError(e.getMessage());
        }
    }

    @Test
    public void testListSolution() {
        testSetup();
        addPlagTest();
        String[] userInput = {"list-solutions", "1"};
        List<Correction> corrections = cntrl.getCorrections(parser.parseNumber(userInput[1]));
        for (Correction current : corrections) {
            String tutor = current.getProducer().getName() + ": ";
            String review = current.getText() + " ";
            String numbers = "[" + ((Student) current.getOriginal().getProducer()).getId() + " " + current.getMark() + "]";
            terminal.println(tutor + review + numbers);
        }
    }

    @Test
    public void testSummary() {
        testSetup();
        ArrayList<String[]> list = cntrl.getSummary();
        for (String[] lines : list) {
            if (lines[2].length() < 4 && !lines[2].equals("-")) {
                lines[2] = lines[2] + "0";
            }
            terminal.println(lines[0] + ": " + lines[1] + " [" + lines[2] + ", "
                    + lines[3] + " / " + lines[4] + "]");
        }
    }

    @Test
    public void searchPlag() {
        testSetup();
        try {
            ArrayList<Datacollection> plagiarisms = cntrl.search(parser.parseNumber("1"));
            plagiarisms.sort(new Comparator<Datacollection>() {
                @Override
                public int compare(Datacollection o1, Datacollection o2) {
                    return Double.compare(o2.getPercent(), o1.getPercent());
                }
            });
            for (Datacollection current : plagiarisms) {
                if (current.getPercent() > 0) {
                    String studentOne = current.getStudentOne().getId() + " ";
                    String studentTwo = current.getStudentTwo().getId() + " ";
                    String longestString = current.getBiggestString() + " ";
                    String count = current.getLength() + " ";
                    String percent = String.valueOf(current.getPercent());
                    if (percent.substring(percent.indexOf('.')).length() < 3) {
                        percent = percent + "0";
                    }
                    percent = percent + " ";
                    terminal.println(studentOne + studentTwo + longestString + count + percent);
                }
            }
        }
        catch (IllegalArgumentException e) {
            terminal.printError(e.getMessage());
        }
    }

    @Test
    public void testDoubleName() {
        testSetup();
        try {
            cntrl.addUser(new Dozent("Pete"));
        }
        catch (IllegalArgumentException e) {
            terminal.printError(e.getMessage());
        }
    }

    @Test
    public void testDoubleId() {
        testSetup();
        try {
            cntrl.addUser(new Student("Pete", 66666));
        }
        catch (IllegalArgumentException e) {
            terminal.printError(e.getMessage());
        }
    }

}
