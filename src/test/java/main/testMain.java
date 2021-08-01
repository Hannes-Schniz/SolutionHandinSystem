package test.java.main;

import main.java.controller.Controller;
import main.java.corrections.Correction;
import main.java.corrections.Datacollection;
import main.java.human.Dozent;
import main.java.human.Student;
import main.java.human.Tutor;
import main.java.human.User;
import main.java.main.Main;
import main.java.texts.HandIn;
import main.java.texts.Question;
import main.java.userinputs.Parser;
import main.java.userinputs.Terminal;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class testMain {

    private static Parser parser;
    private static Controller cntrl;
    private static Terminal terminal;


    @Test
    public void testSearchPlag() {
        testSetup();
        ArrayList<Datacollection> plags = cntrl.search(1);

        Collections.sort(plags, new Comparator<Datacollection>() {
            @Override
            public int compare(Datacollection o1, Datacollection o2) {
                return Double.compare(o2.getPercent(), o1.getPercent());
            }
        });
        return;
    }

    private void testSetup() {
        parser = new Parser();
        cntrl = new Controller();
        terminal = new Terminal();
        Dozent Pete = new Dozent("Pete");
        Tutor Bryn = new Tutor("Bryn");
        Tutor Cassidy = new Tutor("Cassidy");
        Student Gael = new Student("Gael", 66666);
        Student Dakota = new Student("Dakota", 23442);
        Student Finley = new Student("Finley", 32000);
        cntrl.addUser(Pete);
        cntrl.addUser(Bryn);
        cntrl.addUser(Cassidy);
        cntrl.addUser(Gael);
        cntrl.addUser(Dakota);
        cntrl.addUser(Finley);
        cntrl.addQuestion(new Question("kajhfkaghf", cntrl.getIndex()));
        try {
            cntrl.handIn(new HandIn("Die_Lösung_ist_42", cntrl.getQuestion(1), Gael));
            cntrl.handIn(new HandIn("Glaub_es_ist_42", cntrl.getQuestion(1), Dakota));
            cntrl.handIn(new HandIn("Kein_Ahnung", cntrl.getQuestion(1), Finley));
            cntrl.addReview(1, 66666, 2, "Ganz_gut_gelöst,_weiter_so!", "Cassidy");
            cntrl.addReview(1, 32000, 5, "Das_war_wohl_nix.", "Bryn");
            cntrl.addReview(1, 23442, 2, "Ganz_gut_gelöst,_weiter_so!", "Cassidy");
        } catch (Exception e) {
            System.out.println(e);
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
        for (int i = 0; i < corrections.size(); i++) {
            Correction current = corrections.get(i);
            String tutor = current.getProducer().getName() + ": ";
            String review = current.getText() + " ";
            String numbers = "[" + ((Student) current.getOriginal().getProducer()).getId() + " " + current.getMark() + "]";
            terminal.println(tutor + review + numbers);
        }
    }
}
