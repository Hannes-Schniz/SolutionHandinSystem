package main;

import controller.Controller;
import human.Dozent;
import human.Student;
import human.Tutor;
import human.User;
import userinputs.Parser;
import userinputs.Terminal;
import java.io.IOException;
import java.util.HashMap;
import controller.UserEnum;

/**
 * The type Main.
 *
 * @author Hannes Schniz
 * @version 1.0
 */
public class Main {

    private static final int ONE = 1;

    private static final int ZERO = 0;

    private static final int TWO = 2;

    private static Controller cntrl;

    private static Terminal terminal;

    private static Parser parser;

    private static HashMap<String, CommandsEnum> commands;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        setup();
        try {
            boolean go = true;
            while (go) {
                String input = terminal.readln();
                if (!input.equals("")) {
                    String[] userInput = parser.parseInput(input);
                    if (!commands.containsKey(userInput[ZERO])) {
                        terminal.println("command does not exist");
                    }
                    else {
                        CommandsEnum command = commands.get(userInput[ZERO]);
                        switch (command) {
                            case ADD_INSTRUCTOR:
                                cntrl.addUser(new Dozent(userInput[ONE]));
                                break;
                            case LIST_INSTRUCTOR:
                                listInstructors();
                                break;
                            case ADD_TUTOR:
                                cntrl.addUser(new Tutor(userInput[ONE]));
                                break;
                            case LIST_TUTOR:
                                listTutors();
                                break;
                            case ADD_STUDENT:
                                addStudent(userInput);
                                break;
                            case LIST_STUDENT:
                                listStudents();
                                break;
                            case ADD_ASIGNMENT:
                                break;
                            case SUBMIT:
                                break;
                            case LIST_SOLUTION:
                                break;
                            case ADD_REVIEW:
                                break;
                            case LIST_REVIEW:
                                break;
                            case SEARCH_PLAGIARISM:
                                break;
                            case ADD_PLAGIARISM:
                                break;
                            case SUMMARY_TASKS:
                                break;
                            case RESET:
                                cntrl = new Controller();
                                break;
                            case QUIT:
                                go = false;
                                break;
                            default:
                                break;

                        }
                    }
                }
            }
        }
        catch (IOException | IllegalArgumentException e) {
            terminal.printError(e.getMessage());
        }
    }

    private static void setup() {
        cntrl = new Controller();
        terminal = new Terminal();
        parser = new Parser();
        commands = new HashMap<String, CommandsEnum>();
        fillMap();
    }

    private static void printUser(UserEnum key) {
        User[] listUsers = cntrl.getUserList(key);
        String[] nameString = new String[listUsers.length];
        if (key.equals(UserEnum.STUDENT)) {
            for (int i = 0; i < listUsers.length; i++) {
                nameString[i] = listUsers[i].getName() + " (" + ((Student) listUsers[i]).getId() + ")";
            }
        }
        else {
            for (int i = 0; i < listUsers.length; i++) {
                nameString[i] = listUsers[i].getName();
            }
        }
        terminal.printlines(nameString);

    }

    private static void listInstructors() {
        if (cntrl.hasUser(UserEnum.DOZENT)) {
            printUser(UserEnum.DOZENT);
        }
    }

    private static void listTutors() {
        if (cntrl.hasUser(UserEnum.TUTOR)) {
            printUser(UserEnum.TUTOR);
        }
    }

    private static void addStudent(String[] userInput) {
        if (!userInput[TWO].matches("[0-9]{6}")) {
            terminal.println("id wrong length");
            return;
        }
        cntrl.addUser(new Student(userInput[ONE], parser.parseNumber(userInput[TWO])));
    }

    private static void listStudents() {
        if (cntrl.hasUser(UserEnum.STUDENT)) {
            printUser(UserEnum.STUDENT);
        }
    }

    private static void fillMap() {
        commands.put("instructor", CommandsEnum.ADD_INSTRUCTOR);
        commands.put("list-instructors", CommandsEnum.LIST_INSTRUCTOR);
        commands.put("tutor", CommandsEnum.ADD_TUTOR);
        commands.put("list-tutors", CommandsEnum.LIST_TUTOR);
        commands.put("student", CommandsEnum.ADD_STUDENT);
        commands.put("list-students", CommandsEnum.LIST_STUDENT);
        commands.put("assignment", CommandsEnum.ADD_ASIGNMENT);
        commands.put("list-solutions", CommandsEnum.LIST_SOLUTION);
        commands.put("review", CommandsEnum.ADD_REVIEW);
        commands.put("list-reviews", CommandsEnum.LIST_REVIEW);
        commands.put("search-plagiarism", CommandsEnum.SEARCH_PLAGIARISM);
        commands.put("mark-plagiarism", CommandsEnum.ADD_PLAGIARISM);
        commands.put("summary-tasks", CommandsEnum.SUMMARY_TASKS);
        commands.put("reset", CommandsEnum.RESET);
        commands.put("quit", CommandsEnum.QUIT);
        commands.put("submit", CommandsEnum.SUBMIT);
    }
}
