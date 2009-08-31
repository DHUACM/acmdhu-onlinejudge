package cn.edu.dhu.acm.oj.reading.ppt;

public class ProblemScoreData {

    private int problemID;
    private long attempts;
    private long solutionTime;
    private boolean solved;
    private long score;
    private int lastSolutionID;
    private int lastResult;
    
	public ProblemScoreData()
    {
        problemID = 0;
        attempts = 0L;
        solutionTime = 0L;
        solved = false;
        score = 0L;
    }
	
	public ProblemScoreData(int _problemID, long _attempts, long _solutionTime, boolean _solved, long _score)
	{
		problemID = _problemID;
        attempts = _attempts;
        solutionTime = _solutionTime;
        solved = _solved;
        score = _score;
	}

    public long getAttempts()
    {
        return attempts;
    }

    public int getProblemID()
    {
        return problemID;
    }

    public long getScore()
    {
        return score;
    }

    public long getSolutionTime()
    {
        return solutionTime;
    }

    public boolean isSolved()
    {
        return solved;
    }

    public void setAttempts(long newAttempts)
    {
        attempts = newAttempts;
    }

    public void setProblemID(int newProblemID)
    {
        problemID = newProblemID;
    }

    public void setScore(long newScore)
    {
        score = newScore;
    }

    public void setSolutionTime(long newSolutionTime)
    {
        solutionTime = newSolutionTime;
    }

    public void setSolved(boolean newSolved)
    {
        solved = newSolved;
    }

    public int getLastSolutionID() {
        return lastSolutionID;
    }

    public void setLastSolutionID(int lastSolutionID) {
        this.lastSolutionID = lastSolutionID;
    }

    public int getLastResult() {
        return lastResult;
    }

    public void setLastResult(int lastResult) {
        this.lastResult = lastResult;
    }

}