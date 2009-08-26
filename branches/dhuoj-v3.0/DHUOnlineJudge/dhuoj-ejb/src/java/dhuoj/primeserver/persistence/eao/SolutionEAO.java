package dhuoj.primeserver.persistence.eao;

import java.util.List;
import dhuoj.primeserver.persistence.entity.Solution;

public interface SolutionEAO {

    public void addSolution(Solution sln);

    public void updateSolution(Solution sln);

    public void removeSolution(Solution sln);

    public Solution findSolution(int slnID);

    public List<Solution> findSolutionsInRange(int first, int max);

    public List<Solution> findContestSolutionsInRange(int contestID, int first, int max);

    public List<Solution> findContestLoginStatus(int contestID);

    public List<Solution> findUnjudgedSolutionsInRange(int first, int max);

}