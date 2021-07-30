package Main;

import Controller.Controller;
import Human.Dozent;
import Interface.Parser;
import Interface.Terminal;
import java.io.IOException;
import java.util.HashMap;

/**
 * The type Main.
 */
public class Main {

    private static final int ZERO = 0;

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
                        CommandsEnum command = commands.get(userInput[0]);
                        switch (command){
                            case ADD_INSTRUCTOR:
                                Dozent newDozent = new Dozent(userInput[1]);
                                cntrl.addUser(newDozent);
                                break;
                            case LIST_INSTRUCTOR:
                                break;
                            case ADD_TUTOR:
                                break;
                            case LIST_TUTOR:
                                break;
                            case ADD_STUDENT:
                                break;
                            case LIST_STUDENT:
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

    private static void fillMap(){
        commands.put("instructor", CommandsEnum.ADD_INSTRUCTOR);
        commands.put("list.instructor", CommandsEnum.LIST_INSTRUCTOR);
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
