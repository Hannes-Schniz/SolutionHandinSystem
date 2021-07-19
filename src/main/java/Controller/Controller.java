package Controller;

import Human.*;
import Texts.Question;
import Texts.Text;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.HashMap;
import java.util.List;

public class Controller {

    private static String USER_DOZENT = "Dozent";

    private static String USER_TUTOR = "Tutor";

    private static String USER_STUDENT = "Student";

    private HashMap<String, User> userList;

    private HashMap<Question, Text[][]> questions;

    private UI userInterface;

    public Controller( UI userInterface) {
        this.userList = new HashMap<String, User>();
        this.questions = new HashMap<Question, Text[][]>();
        this.userInterface = userInterface;
    }

    public HashMap<String, User> getUserList() {
        return userList;
    }

    public HashMap<Question, Text[][]> getTextList() {
        return questions;
    }

    public UI getUserInterface() {
        return userInterface;
    }

    public void addUser(User newUser){
        if (newUser.getClass().equals(Dozent.class)){
            userList.put(USER_DOZENT , newUser);
        }
        else if (newUser.getClass().equals(Tutor.class)){
            userList.put(USER_TUTOR , newUser);
        }
        else if (newUser.getClass().equals(Student.class)){
            userList.put(USER_STUDENT , newUser);
        }
    }

    public void addQuestion(Question question){
        if (!questions.containsKey(question)){
            questions.put(question, null);
            return;
        }
        throw new KeyAlreadyExistsException("Question already exists");
    }
}
