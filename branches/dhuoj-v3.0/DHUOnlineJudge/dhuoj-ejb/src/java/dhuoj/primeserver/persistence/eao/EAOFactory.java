package dhuoj.primeserver.persistence.eao;

public class EAOFactory {

    public static final String ENTITY_MANAGER_NAME = "java:comp/env/dhuojPU";

    public EAOFactory() { }

    public static CompileinfoEAO getCompileinfoEAO() {
        return (new CompileinfoEAOImpl());
    }

    public static ContestEAO getContestEAO() {
        return (new ContestEAOImpl());
    }

    public static ContestProblemEAO getContestProblemEAO() {
        return (new ContestProblemEAOImpl());
    }

    public static ContestReservationEAO getContestReservationEAO() {
        return (new ContestReservationEAOImpl());
    }

    public static MessageEAO getMessageEAO() {
        return (new MessageEAOImpl());
    }

    public static ProblemEAO getProblemEAO() {
        return (new ProblemEAOImpl());
    }

    public static SolutionEAO getSolutionEAO() {
        return (new SolutionEAOImpl());
    }

    public static SourceCodeEAO getSourceCodeEAO() {
        return (new SourceCodeEAOImpl());
    }

    public static UserEAO getUserEAO() {
        return (new UserEAOImpl());
    }

}
