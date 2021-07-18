package Controller;

import Human.*;
import Texts.Text;

import java.util.HashMap;

public class Controller {

    private static String USER_DOZENT = "Dozent";

    private static String USER_TUTOR = "Tutor";

    private static String USER_STUDENT = "Student";

    private HashMap<String, User> userList;

    private HashMap<String, Text> textList;

    private UI userInterface;

    public Controller( UI userInterface) {
        this.userList = new HashMap<String, User>();
        this.textList = new HashMap<String, Text>();
        this.userInterface = userInterface;
    }

    public HashMap<String, User> getUserList() {
        return userList;
    }

    public HashMap<String, Text> getTextList() {
        return textList;
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
}
