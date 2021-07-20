package Controller;

import Human.Dozent;
import Texts.Question;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * The type Plagiarism finder.
 */
public class PlagiarismFinder {

    private Dozent searcher;

    private Question originalQuestion;

    /**
     * Instantiates a new Plagiarism finder.
     *
     * @param searcher the searcher
     * @param originalQuestion the original
     */
    public PlagiarismFinder(Dozent searcher, Question originalQuestion) {
        this.searcher = searcher;
        this.originalQuestion = originalQuestion;
    }

    /**
     * Find plagiarism boolean.
     *
     * @return the boolean
     */
    public boolean findPlagiarism(){
        throw new NotImplementedException();
    }
}
