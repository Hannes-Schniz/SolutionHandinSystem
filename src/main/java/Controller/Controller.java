package Controller;

import Human.User;
import Texts.Text;

import java.util.HashMap;

public class Controller {

    private HashMap<String, User> userList;

    private HashMap<String, Text> textList;

    private UI userInterface;

    public Controller(HashMap<String, User> userList, HashMap<String, Text> textList, UI userInterface) {
        this.userList = userList;
        this.textList = textList;
        this.userInterface = userInterface;
    }
}
