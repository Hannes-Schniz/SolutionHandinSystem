package Main;

import Controller.Controller;
import Human.Dozent;
import Human.Student;
import Human.Tutor;
import Human.User;
import Interface.Parser;
import Interface.Terminal;
import java.io.IOException;
import java.util.HashMap;
import Controller.UserEnum;

/**
 * The type Main.
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
            while (go){
                String input = terminal.readln();
                if (!input.equals("")){
                    String[] userInput = parser.parseInput(input);
                    if (!commands.containsKey(userInput[ZERO])){
                        terminal.println("command does not exist");
                    }
                    else {
                        CommandsEnum command = commands.get(userInput[ZERO]);
                        switch (command){
                            case ADD_INSTRUCTOR:
                                Dozent newDozent = new Dozent(userInput[ONE]);
                                cntrl.addUser(newDozent);
                                break;
                            case LIST_INSTRUCTOR:
                                if (!cntrl.hasUser(UserEnum.DOZENT)){
                                    break;
                                }
                                printUser(UserEnum.DOZENT);
                                break;
                            case ADD_TUTOR:
                                Tutor newTutor = new Tutor(userInput[ONE]);
                                cntrl.addUser(newTutor);
                                break;
                            case LIST_TUTOR:
                                if (!cntrl.hasUser(UserEnum.TUTOR)){
                                    break;
                                }
                                printUser(UserEnum.TUTOR);
                                break;
                            case ADD_STUDENT:
                                if (!userInput[TWO].matches("[0-9]{6}")){
                                    terminal.println("id wrong length");
                                    break;
                                }
                                Student newStudent = new Student(userInput[ONE], parser.parseNumber(userInput[TWO]));
                                cntrl.addUser(newStudent);
                                break;
                            case LIST_STUDENT:
                                if (!cntrl.hasUser(UserEnum.STUDENT)){
                                    break;
                                }
                                printUser(UserEnum.STUDENT);
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
        catch (IOException e){
            terminal.printError(e.getMessage());
        }
        catch (IllegalArgumentException e){
            terminal.printError(e.getMessage());
        }
    }

    private static void setup(){
        cntrl = new Controller();
        terminal = new Terminal();
        parser = new Parser();
        commands = new HashMap<String, CommandsEnum>();
        fillMap();
    }

    private static void printUser(UserEnum key){
        User[] listUsers = cntrl.getUserList(key);
        String[] nameString = new String[listUsers.length];
        if (key.equals(UserEnum.STUDENT)){
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

    private static void fillMap(){
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
