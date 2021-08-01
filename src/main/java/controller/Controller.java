package main.java.controller;

import main.java.corrections.Correction;
import main.java.corrections.Datacollection;
import main.java.corrections.Plagiarism;
import main.java.human.*;
import main.java.texts.HandIn;
import main.java.texts.Question;
import main.java.texts.Text;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.security.KeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private PlagiarismFinder finder;

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
        if (!textList.containsKey(handIn.getOriginal())) { //obsolete
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
                throw new IllegalAccessException("Is already review");
            }
            oldText[handinpos][ZERO] = handIn;
            textList.put(handIn.getOriginal(), oldText);
        }

    }

    private int containsHandIn(HandIn input) {
        Text[][] old = textList.get(input.getOriginal());
        for (int i = 0; i < old.length; i++) {
            if (old[i][0].getProducer().equals(input.getProducer())) {
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
        int idx = findStudentSolution(studentId, handIns);
        if (idx == -1) {
            throw new IllegalArgumentException("no such solution");
        }
        handIns[idx][ONE] = new Correction(mark, (HandIn) handIns[idx][ZERO], findTutor(tutor), comment);
        ((HandIn) handIns[idx][ZERO]).setCorrected(true);
        textList.put(key, handIns);
    }

    private int findStudentSolution(int studentId, Text[][] handIns) {
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

    public List<Correction> getCorrections(int keyId) {
        Question key = getQuestion(keyId);
        Text[][] texts = textList.get(key);
        ArrayList<Correction> retCollection = new ArrayList<Correction>();

        for (int i = 0; i < texts.length; i++) {
            if (((HandIn) texts[i][ZERO]).isCorrected()) {
                retCollection.add(((Correction) texts[i][ONE]));
            }
        }
        return  retCollection;
    }

    public ArrayList<Datacollection> search(int questionID) {
        Question key = getQuestion(questionID);
        Text[][] texFiles = textList.get(key);
        if (!isFinished(key)) {
            throw new IllegalArgumentException("not all submissions are corrected yet!");
        }
        ArrayList<Datacollection> dataList = new ArrayList<Datacollection>();
        for (int i = 0; i < texFiles.length; i++) {
            for (int j = 0; j < texFiles.length; j++) {
                if (i != j) {
                    finder = new PlagiarismFinder(key);
                    Datacollection data = finder.findPlagiarism((HandIn) texFiles[i][ZERO], (HandIn) texFiles[j][ZERO]);
                    dataList.add(data);
                }
            }
        }
        return dataList;
    }

    private boolean isFinished(Question key) {
        Text[][] textFiles = textList.get(key);
        for (int i = 0; i < textFiles.length; i++) {
            if (!((HandIn) textFiles[i][ZERO]).isCorrected()) {
                return false;
            }
        }
        return true;
    }

    public String markPlagiarism(int studentId, int questionId, String name) throws IllegalArgumentException {
        Text[][] textFiles = textList.get(getQuestion(questionId));
        int idx = findStudentSolution(studentId , textFiles);
        if (idx == -1) {
            throw new IllegalArgumentException("Student didn't hand a Solution in");
        }
        HandIn plag = (HandIn) textFiles[idx][ZERO];
        Student student = (Student)plag.getProducer();
        Text copiedSolution = textFiles[idx][ONE];
        Tutor tutor = (Tutor) copiedSolution.getProducer();
        String comment = ((Correction) copiedSolution).getText();
        Dozent instructor = findDozent(name);
        if (copiedSolution.getClass().equals(Plagiarism.class)) {
            int mark = ((Plagiarism) copiedSolution).getOldMark();
            Correction oldCorrection = new Correction(mark, (HandIn) textFiles[idx][ZERO], tutor, comment);
            textFiles[idx][ONE] = oldCorrection;
            textList.put(plag.getOriginal(), textFiles);
            return student.getId() + " mark for " + student.getId() + " removed and grade "
                    + oldCorrection.getMark() + " restored";
        }
        Plagiarism plagiarism = new Plagiarism((Correction) copiedSolution, instructor);
        textFiles[idx][ONE] = plagiarism;
        textList.put(plag.getOriginal(), textFiles);
        return ((Question) plag.getOriginal()).getId() + " for "
                + student.getId() + " marked as plagiarism";
    }

    private Dozent findDozent(String name) {
        User[] users = userList.get(UserEnum.DOZENT);
        for (int i = 0; i < users.length; i++) {
            if (users[i].getName().equals(name)) {
                return (Dozent) users[i];
            }
        }
        throw new IllegalArgumentException("Instructor doesn't exist");
    }

}
