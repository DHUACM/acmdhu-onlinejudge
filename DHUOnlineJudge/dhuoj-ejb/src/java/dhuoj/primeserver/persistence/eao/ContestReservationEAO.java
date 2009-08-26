package dhuoj.primeserver.persistence.eao;

import java.util.List;
import dhuoj.primeserver.persistence.entity.ContestReservation;

public interface ContestReservationEAO {

    public List<ContestReservation> findContestReservationList(int contestID);

    public List<ContestReservation> findUserReservedContest(String userId);
}
