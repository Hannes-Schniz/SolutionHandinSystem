package main.java.controller;

import main.java.corrections.Correction;
import main.java.human.*;
import main.java.texts.HandIn;
import main.java.texts.Question;
import main.java.texts.Text;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.security.KeyException;
import java.util.HashMap;
import java.util.Set;

/**
 * The type Controller.
 *
 * @author Hannes Schniz
 * @version 0.1
 */
public class Controller {

    private static final int ZERO = 0;

    private static final int ONE = 1;

    private static final int TWO = 2;

    private HashMap<UserEnum, User[]> userList;

    private HashMap<Question, Text[][]> textList;

    /**
     * Instantiates a new Controller.
     *
     */
    public Controller() {
        this.userList = new HashMap<>();
        this.textList = new HashMap<>();
    }

    /**
     * Gets user list.
     *
     * @param key the Key
     *
     * @return user list
     */
    public User[] getUserList(UserEnum key) {
        return userList.get(key);
    }

    public boolean hasUser(UserEnum key) {
        return userList.containsKey(key);
    }

    /**
     * Add user.
     *
     * @param newUser the new user
     */
    public void addUser(User newUser) {
        if (newUser.getClass().equals(Dozent.class)) {
            addToHashMap(UserEnum.DOZENT, newUser);
        }
        else if (newUser.getClass().equals(Tutor.class)) {
            addToHashMap(UserEnum.TUTOR, newUser);
        }
        else if (newUser.getClass().equals(Student.class)) {
            addToHashMap(UserEnum.STUDENT, newUser);
        }
    }

    private void addToHashMap(UserEnum key, User newUser) {
        if (!userList.containsKey(key)) {
            User[] in = {newUser};
            userList.put(key, in);
        }
        else {
            User[] newerUser = extendUser(userList.get(key));
            newerUser[newerUser.length - ONE] = newUser;
            userList.put(key, newerUser);
        }
    }

    private User[] extendUser(User[] input) {
        User[] oldUser = new User[input.length + ONE];
        System.arraycopy(input, ZERO, oldUser, ZERO, input.length);
        return oldUser;
    }

    /**
     * Add question to the Hashmap.
     *
     * @param question the question
     */
    public void addQuestion(Question question) throws KeyAlreadyExistsException {
        if (textList.containsKey(question)) {
            throw new KeyAlreadyExistsException("Question already exists");
        }
        textList.put(question, new Text[ZERO][TWO]);
    }

    /**
     * Lets a student hand in a Solution and adds it to the Hashmap.
     *
     * @param handIn the handIn
     */
    public void handIn(HandIn handIn) throws KeyException, IllegalAccessException {
        if (!textList.containsKey(handIn.getOriginal())) {
            throw new KeyException("Question does not exist");
        }
        int handinpos = containsHandIn(handIn);
        Text[][] oldText = textList.get(handIn.getOriginal());
        if (handinpos == -1) {
            int oldHandIns = oldText.length;
            Text[][] newText = new Text[oldHandIns + ONE][TWO];
            System.arraycopy(oldText, ZERO, newText, ZERO, oldText.length);
            newText[newText.length - 1][ZERO] = handIn;
            textList.put(handIn.getOriginal(), newText);
        }
        else {
            HandIn searched = (HandIn) oldText[handinpos][ZERO];
            if (searched.isCorrected()) {
                throw new IllegalAccessException("Is already corrected");
            }
            oldText[handinpos][ZERO] = handIn;
            textList.put(handIn.getOriginal(), oldText);
        }

    }

    private int containsHandIn(HandIn input) {
        Text[][] old = textList.get(input.getOriginal());
        for (int i = 0; i < old.length; i++) {
            if (old[i][0].equals(input)) {
                return i;
            }
        }
        return -1;
    }

    public Question getQuestion(int id) {
        Set<Question> questionArray = textList.keySet();
        for (Question question : questionArray) {
            if (question.getId() == id) {
                return question;
            }
        }
        throw new IllegalArgumentException("Question id does not exist");
    }

    public int getIndex() {
        return textList.size() + 1;
    }

    public HandIn[] getSolutions(int questionId) {
        if (textList.containsKey(getQuestion(questionId))) {
            Text[][] working = textList.get(getQuestion(questionId));
            HandIn[] ret = new HandIn[working.length];
            for (int i = 0; i < working.length; i++) {
                ret[i] = (HandIn) working[i][ZERO];
            }
            return ret;
        }
        throw new IllegalArgumentException("no such Question");
    }

    public void addReview(int questionId, int studentId, int mark, String comment, String tutor) {
        Question key = getQuestion(questionId);
        Text[][] handIns = textList.get(key);
        int idx = findStudentSolution(studentId, key, handIns);
        if (idx == -1) {
            throw new IllegalArgumentException("no such solution");
        }
        handIns[idx][ONE] = new Correction(mark, (HandIn) handIns[idx][ZERO], findTutor(tutor), comment);
        ((HandIn) handIns[idx][ZERO]).setCorrected(true);
        textList.put(key, handIns);
    }

    private int findStudentSolution(int studentId, Question key, Text[][] handIns) {
        for (int i = 0; i < handIns.length; i++) {
            Student student = (Student) handIns[i][ZERO].getProducer();
            if (student.getId() == studentId) {
                return i;
            }
        }
        return -1;
    }

    private Tutor findTutor(String name) {
        User[] tutorList = userList.get(UserEnum.TUTOR);
        for (int i = 0; i < tutorList.length; i++) {
            if (tutorList[i].getName().equals(name)) {
                return (Tutor) tutorList[i];
            }
        }
        throw new IllegalArgumentException("tutor doesn't exist");
    }

}
