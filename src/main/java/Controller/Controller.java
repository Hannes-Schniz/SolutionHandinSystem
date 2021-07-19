package Controller;

import Human.*;
import Texts.HandIn;
import Texts.Question;
import Texts.Text;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.HashMap;
import java.util.List;

public class Controller {

    private static String USER_DOZENT = "Dozent";

    private static String USER_TUTOR = "Tutor";

    private static String USER_STUDENT = "Student";

    private static int ZERO = 0;

    private HashMap<String, User> userList;

    private HashMap<Question, List<Text[]>> questions;

    private UI userInterface;

    public Controller( UI userInterface) {
        this.userList = new HashMap<String, User>();
        this.questions = new HashMap<Question, List<Text[]>>();
        this.userInterface = userInterface;
    }

    public HashMap<String, User> getUserList() {
        return userList;
    }

    public HashMap<Question, List<Text[]>> getTextList() {
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

    public void handIn(HandIn handIn){
        int check = checkForHandIn(questions.get(handIn.getOriginal()) , handIn);
        if (questions.containsKey(handIn.getOriginal())){
            List<Text[]> working = questions.get(handIn.getOriginal());
            Text[] input = genInput(handIn);
            if (check == -1){
                working.add(input);
            }
            else
            {
                working.set(check, input);
            }
            questions.put(handIn.getOriginal(), working);
        }

    }

    private Text[] genInput(HandIn handIn){
        Text[] input = new Text[2];
        input[0] = handIn;
        input[1] = null;
        return input;
    }

    private int checkForHandIn(List<Text[]> input, HandIn handIn){

        for (int i = 0; i < input.size(); i++) {
            if (input.get(i)[ZERO].equals(handIn)){
                return i;
            }
        }
        return -1;

    }

}
