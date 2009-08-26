package dhuoj.primeserver.persistence.eao;

import java.util.List;
import dhuoj.primeserver.persistence.entity.Contest;

public interface ContestEAO {

	public void addContest(Contest contest);

    public void updateContest(Contest contest);

    public void removeContest(Contest contest);

    public Contest findContest(int contestID);

    public List<Contest> findContestInRange(int first, int max);

    public List<Contest> findContestByStatus(int status);

}