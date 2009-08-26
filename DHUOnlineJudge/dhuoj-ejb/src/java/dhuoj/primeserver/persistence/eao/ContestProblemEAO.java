package dhuoj.primeserver.persistence.eao;

import java.util.List;
import java.util.TreeMap;

import dhuoj.primeserver.persistence.entity.ContestProblem;

public interface ContestProblemEAO {

    public void addContestProblem(ContestProblem cp);

    public void updateContestProblem(ContestProblem cp);

    public List<ContestProblem> findProblemListByContest(int contestID);

    public TreeMap<Integer, ContestProblem> findProblemTreeMapByContest(int contestID);
    
}
