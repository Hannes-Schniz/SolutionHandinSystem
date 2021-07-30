package Controller;

import Corrections.Correction;
import Human.*;
import Texts.HandIn;
import Texts.Question;
import Texts.Text;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.HashMap;
import java.util.List;

/**
 * The type Controller.
 *
 * @author Hannes Schniz
 */
public class Controller {

    private static String USER_DOZENT = "Dozent";

    private static String USER_TUTOR = "Tutor";

    private static String USER_STUDENT = "Student";

    private static int ZERO = 0;

    private static int ONE = 1;

    private HashMap<UserEnum, User[]> userList;

    private HashMap<Question, List<Text[]>> questions;

    /**
     * Instantiates a new Controller.
     *
     */
    public Controller() {
        this.userList = new HashMap<UserEnum,User[]>();
        this.questions = new HashMap<Question, List<Text[]>>();
    }

    /**
     * Gets user list.
     *
     * @return the user list
     */
    public User[] getUserList(UserEnum key) {
        return userList.get(key);
    }

    public boolean hasUser(UserEnum key){
        return userList.containsKey(key);
    }

    /**
     * Gets text list.
     *
     * @return the text list
     */
    public HashMap<Question, List<Text[]>> getTextList() {
        return questions;
    }

    /**
     * Add user.
     *
     * @param newUser the new user
     */
    public void addUser(User newUser){
        if (newUser.getClass().equals(Dozent.class)){
            addToHashMap(UserEnum.DOZENT, newUser);
        }
        else if (newUser.getClass().equals(Tutor.class)){
            addToHashMap(UserEnum.TUTOR, newUser);
        }
        else if (newUser.getClass().equals(Student.class)){
            addToHashMap(UserEnum.STUDENT, newUser);
        }
    }

    private void addToHashMap(UserEnum key, User newUser){
        if (!userList.containsKey(key)){
            User[] in = {newUser};
            userList.put(key,in);
        }
        else {
            User[] new_User = extendUser(userList.get(key));
            new_User[new_User.length - 1] = newUser;
            userList.put(key, new_User);
        }
    }

    private User[] extendUser(User[] input){
        User[] old_User = input;
        input = new User[input.length + 1];
        for (int i = 0; i < old_User.length; i++) {
            input[i] = old_User[i];
        }
        return input;
    }

    /**
     * Add question to the Hashmap.
     *
     * @param question the question
     */
    public void addQuestion(Question question){
        if (!questions.containsKey(question)){
            questions.put(question, null);
            return;
        }
        throw new KeyAlreadyExistsException("Question already exists");
    }

    /**
     * Lets a student hand in a Solution and adds it to the Hashmap.
     *
     * @param handIn the handIn
     */
    public void handIn(HandIn handIn){
        if (questions.containsKey(handIn.getOriginal())){ //if the question exists proceed

            List<Text[]> working = questions.get(handIn.getOriginal());
            int check = checkForHandIn(working , handIn);

            if (check == -1){ //if there isnt a handIn from the student it adds a new entry
                working.add(genInput(handIn, null));
            }
            else //if there is a handIn it changes it
            {
                working.set(check, genInput(handIn, (Correction)working.get(check)[ONE]));
            }
            questions.put(handIn.getOriginal(), working);
        }

    }

    /**
     * generates a tuple of a handIn and null for the correction.
     *
     * @param handIn new handIn
     * @param correction existing correction
     * @return generated input array
     */
    private Text[] genInput(HandIn handIn, Correction correction){
        Text[] input = new Text[2];
        input[0] = handIn;
        input[1] = correction;
        return input;
    }

    /**
     * finds the position of the existing hand in of the user if there is none returns -1
     *
     * @param allHandIns all existing handIns
     * @param handIn new handIn
     * @return position of the previous hand in
     */
    private int checkForHandIn(List<Text[]> allHandIns, HandIn handIn){

        for (int i = 0; i < allHandIns.size(); i++) {
            if (allHandIns.get(i)[ZERO].getProducer().equals(handIn.getProducer())){
                return i;
            }
        }
        return -1;

    }

}
