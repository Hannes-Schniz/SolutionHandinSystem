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
                    if (!commands.containsKey(userInput[0])){
                        terminal.println("command does not exist");
                    }
                    else {
                        CommandsEnum command = commands.get(userInput[0]);
                        switch (command){
                            case ADDINSTRUCTOR:
                                Dozent newDozent = new Dozent(userInput[1]);
                                break;
                            case LISTINSTRUCTOR:
                                break;
                            case ADDTUTOR:
                                break;
                            case LISTTUTOR:
                                break;
                            case ADDSTUDENT:
                                break;
                            case LISTSTUDENT:
                                break;
                            case ADDASIGNMENT:
                                break;
                            case SUBMIT:
                                break;
                            case LISTSOLUTION:
                                break;
                            case ADDREVIEW:
                                break;
                            case LISTREVIEW:
                                break;
                            case SEARCHPLAGIARISM:
                                break;
                            case ADDPLAGIARISM:
                                break;
                            case SUMMARYTASKS:
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
            terminal.println("error");
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
        commands.put("instructor", CommandsEnum.ADDINSTRUCTOR);
        commands.put("list.instructor", CommandsEnum.LISTINSTRUCTOR);
        commands.put("tutor", CommandsEnum.ADDTUTOR);
        commands.put("list-tutors", CommandsEnum.LISTTUTOR);
        commands.put("student", CommandsEnum.ADDSTUDENT);
        commands.put("list-students", CommandsEnum.LISTSTUDENT);
        commands.put("assignment", CommandsEnum.ADDASIGNMENT);
        commands.put("list-solutions", CommandsEnum.LISTSOLUTION);
        commands.put("review", CommandsEnum.ADDREVIEW);
        commands.put("list-reviews", CommandsEnum.LISTREVIEW);
        commands.put("search-plagiarism", CommandsEnum.SEARCHPLAGIARISM);
        commands.put("mark-plagiarism", CommandsEnum.ADDPLAGIARISM);
        commands.put("summary-tasks", CommandsEnum.SUMMARYTASKS);
        commands.put("reset", CommandsEnum.RESET);
        commands.put("quit", CommandsEnum.QUIT);
        commands.put("submit", CommandsEnum.SUBMIT);
    }
}
