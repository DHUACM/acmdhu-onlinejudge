package dhuoj.primeserver.persistence.eao;

import java.util.List;
import dhuoj.primeserver.persistence.entity.Problem;

public interface ProblemEAO {

    public void addProblem(Problem problem);

    public void updateProblem(Problem problem);

    public void removeProblem(Problem problem);

    public Problem findProblem(int problemID);

    public List<Problem> findProblemInRange(int first, int max);

}