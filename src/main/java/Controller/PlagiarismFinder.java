package Controller;

import Human.Dozent;
import Texts.Question;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * The type Plagiarism finder.
 */
public class PlagiarismFinder {

    private Dozent searcher;

    private Question original;

    /**
     * Instantiates a new Plagiarism finder.
     *
     * @param searcher the searcher
     * @param original the original
     */
    public PlagiarismFinder(Dozent searcher, Question original) {
        this.searcher = searcher;
        this.original = original;
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
