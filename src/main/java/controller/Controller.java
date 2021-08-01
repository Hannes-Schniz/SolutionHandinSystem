package main.java.controller;

import main.java.corrections.Correction;
import main.java.corrections.Datacollection;
import main.java.corrections.Plagiarism;
import main.java.human.*;
import main.java.texts.HandIn;
import main.java.texts.Question;
import main.java.texts.Text;
import java.util.*;

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

    private static final int THREE = 3;

    private static final int FOUR = 4;

    private static final int FIVE = 5;

    private final HashMap<UserEnum, User[]> userList;

    private final HashMap<Question, Text[][]> textList;

    /**
     * Instantiates a new Controller.
     */
    public Controller() {
        this.userList = new HashMap<>();
        this.textList = new HashMap<>();
    }

    /**
     * Gets user list.
     *
     * @param key the Key
     * @return user list
     */
    public User[] getUserList(UserEnum key) {
        return userList.get(key);
    }

    /**
     * Has user boolean.
     *
     * @param key the key
     * @return the boolean
     */
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
            if (userExist(newUser, key)) {
                throw new IllegalArgumentException("User already exists");
            }
            User[] newerUser = extendUser(userList.get(key));
            newerUser[newerUser.length - ONE] = newUser;
            userList.put(key, newerUser);
        }
    }

    private boolean userExist(User user, UserEnum key) {
        User[] list = userList.get(key);
        if (key.equals(UserEnum.STUDENT)) {
            for (User value : list) {
                if (((Student) value).getId() == ((Student) user).getId()) {
                    return true;
                }
            }
        }
        else {
            for (User value : list) {
                if (value.getName().equals(user.getName())) {
                    return true;
                }
            }
        }
        return false;
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
     * @throws IllegalArgumentException the key already exists exception
     */
    public void addQuestion(Question question) throws IllegalArgumentException {
        if (textList.containsKey(question)) {
            throw new IllegalArgumentException("Question already exists");
        }
        textList.put(question, new Text[ZERO][TWO]);
    }

    /**
     * Lets a student hand in a Solution and adds it to the Hashmap.
     *
     * @param handIn the handIn
     * @throws IllegalArgumentException           the key exception
     * @throws IllegalAccessException the illegal access exception
     */
    public void handIn(HandIn handIn) throws IllegalArgumentException, IllegalAccessException {
        if (!textList.containsKey(handIn.getOriginal())) { //obsolete
            throw new IllegalArgumentException("Question does not exist");
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

    /**
     * Gets question.
     *
     * @param id the id
     * @return the question
     */
    public Question getQuestion(int id) {
        Set<Question> questionArray = textList.keySet();
        for (Question question : questionArray) {
            if (question.getId() == id) {
                return question;
            }
        }
        throw new IllegalArgumentException("Question id does not exist");
    }

    /**
     * Gets question.
     *
     * @return the question
     */
    public ArrayList<Question> getQuestion() {
        Set<Question> questionArray = textList.keySet();
        ArrayList<Question> returnList = new ArrayList<>();
        returnList.addAll(questionArray);
        returnList.sort(new Comparator<>() {
            @Override
            public int compare(Question o1, Question o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });
        return returnList;
    }

    /**
     * Gets index.
     *
     * @return the index
     */
    public int getIndex() {
        return textList.size() + 1;
    }

    /**
     * Get solutions hand in [ ].
     *
     * @param questionId the question id
     * @return the hand in [ ]
     */
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

    /**
     * Add review.
     *
     * @param questionId the question id
     * @param studentId  the student id
     * @param mark       the mark
     * @param comment    the comment
     * @param tutor      the tutor
     */
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
        for (User user : tutorList) {
            if (user.getName().equals(name)) {
                return (Tutor) user;
            }
        }
        throw new IllegalArgumentException("tutor doesn't exist");
    }

    /**
     * Gets corrections.
     *
     * @param keyId the key id
     * @return the corrections
     */
    public List<Correction> getCorrections(int keyId) {
        Question key = getQuestion(keyId);
        Text[][] texts = textList.get(key);
        ArrayList<Correction> retCollection = new ArrayList<>();

        for (Text[] text : texts) {
            if (((HandIn) text[ZERO]).isCorrected()) {
                retCollection.add(((Correction) text[ONE]));
            }
        }
        return  retCollection;
    }

    /**
     * Search array list.
     *
     * @param questionID the question id
     * @return the array list
     */
    public ArrayList<Datacollection> search(int questionID) {
        Question key = getQuestion(questionID);
        Text[][] texFiles = textList.get(key);
        if (!isFinished(key)) {
            throw new IllegalArgumentException("not all submissions are corrected yet!");
        }
        ArrayList<Datacollection> dataList = new ArrayList<>();
        for (int i = 0; i < texFiles.length; i++) {
            for (int j = 0; j < texFiles.length; j++) {
                if (i != j) {
                    PlagiarismFinder finder = new PlagiarismFinder();
                    Datacollection data = finder.findPlagiarism((HandIn) texFiles[i][ZERO], (HandIn) texFiles[j][ZERO]);
                    dataList.add(data);
                }
            }
        }
        return dataList;
    }

    private boolean isFinished(Question key) {
        Text[][] textFiles = textList.get(key);
        for (Text[] textFile : textFiles) {
            if (!((HandIn) textFile[ZERO]).isCorrected()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Mark plagiarism string.
     *
     * @param studentId  the student id
     * @param questionId the question id
     * @param name       the name
     * @return the string
     * @throws IllegalArgumentException the illegal argument exception
     */
    public String markPlagiarism(int studentId, int questionId, String name) throws IllegalArgumentException {
        Text[][] textFiles = textList.get(getQuestion(questionId));
        int idx = findStudentSolution(studentId , textFiles);
        if (idx == -1) {
            throw new IllegalArgumentException("Student didn't hand a Solution in");
        }
        HandIn plag = (HandIn) textFiles[idx][ZERO];
        Student student = (Student) plag.getProducer();
        Text copiedSolution = textFiles[idx][ONE];
        Tutor tutor = (Tutor) copiedSolution.getProducer();
        String comment = copiedSolution.getText();
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
        return plag.getOriginal().getId() + " for "
                + student.getId() + " marked as plagiarism";
    }

    private Dozent findDozent(String name) {
        User[] users = userList.get(UserEnum.DOZENT);
        if (!userList.containsKey(UserEnum.DOZENT)) {
            throw new IllegalArgumentException("No Instructors present");
        }
        for (User user : users) {
            if (user.getName().equals(name)) {
                return (Dozent) user;
            }
        }
        throw new IllegalArgumentException("Instructor doesn't exist");
    }

    /**
     * Gets summary.
     *
     * @return the summary
     */
    public ArrayList<String[]> getSummary() {
        ArrayList<Question> questionList = getQuestion();
        ArrayList<String[]> returnList = new ArrayList<>();
        for (Question question : questionList) {
            String[] workingString = new String[FIVE];
            workingString[ZERO] = String.valueOf(question.getId());
            workingString[ONE] = question.getText();
            double arit = arithmic(question);
            if (arit == -1) {
                workingString[TWO] = "-";
            } else {
                workingString[TWO] = String.valueOf(arit);
            }
            workingString[THREE] = String.valueOf(getCorrected(question));
            workingString[FOUR] = String.valueOf(textList.get(question).length);
            returnList.add(workingString);

        }
        return returnList;
    }

    private double arithmic(Question question) {
        Text[][] textFiles = textList.get(question);
        double mark = 0;
        if (textFiles.length < 1) {
            return -1;
        }
        for (Text[] textFile : textFiles) {
            if (((HandIn) textFile[ZERO]).isCorrected()) {
                mark = mark + ((Correction) textFile[ONE]).getMark();
            }
        }
        mark = mark / (textFiles.length - 1);
        mark = java.lang.Math.round(mark * 100);
        return mark / 100;
    }

    private int getCorrected(Question question) {
        Text[][] textFiles = textList.get(question);
        int corrected = 0;
        for (Text[] textFile : textFiles) {
            if (((HandIn) textFile[ZERO]).isCorrected()) {
                corrected++;
            }
        }
        return corrected;
    }

}
