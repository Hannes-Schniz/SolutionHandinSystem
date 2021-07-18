package Controller;

import Human.Dozent;
import Texts.Question;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class PlagiarismFinder {

    private Dozent searcher;

    private Question original;

    public PlagiarismFinder(Dozent searcher, Question original) {
        this.searcher = searcher;
        this.original = original;
    }

    public boolean findPlagiarism(){
        throw new NotImplementedException();
    }
}
