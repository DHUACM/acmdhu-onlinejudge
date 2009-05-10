package cn.edu.dhu.acm.oj.logic.rank;

import java.util.TreeMap;

public class ClientScoreData implements Comparable<ClientScoreData> {
	
	private String userID;
    private int rank;
    private long score;
    private long numberOfSolvedProblems;
    private long timeOfLastSolvedProblem;
    private TreeMap<Integer, ProblemScoreData> problemScoreDataTreeMap;
    
	public ClientScoreData()
    {
        userID = "";
        rank = 0;
        score = 0L;
        numberOfSolvedProblems = 0L;
        timeOfLastSolvedProblem = 0L;
        problemScoreDataTreeMap = new TreeMap<Integer, ProblemScoreData>();
    }
	
	public int compareTo(ClientScoreData o)
	{
		int status = 0;
        if((this instanceof ClientScoreData) && (o instanceof ClientScoreData))
        {
            ClientScoreData userA = (ClientScoreData)this;
            ClientScoreData userB = (ClientScoreData)o;
            long a1 = userA.getNumberOfSolvedProblems();
            float a2 = userA.getScore();
            long a3 = userA.getTimeOfLastSolvedProblem();
            String a4 = userA.getUserID();
            long b1 = userB.getNumberOfSolvedProblems();
            float b2 = userB.getScore();
            long b3 = userB.getTimeOfLastSolvedProblem();
            String b4 = userB.getUserID();
            long compare = userA.getUserID().toLowerCase().compareTo(userB.getUserID().toLowerCase());
            if(b1 == a1 && b2 == a2 && b3 == a3 && compare == 0L && b4 == a4)
                status = 0;
            else
            if(b1 > a1 || b1 == a1 && b2 < a2 || b1 == a1 && b2 == a2 && b3 < a3 || b1 == a1 && b2 == a2 && b3 == a3 && compare > 0L || b1 == a1 && b2 == a2 && b3 == a3 && compare == 0L || b1 == a1 && b2 == a2 && b3 == a3 && compare == 0L && b4 == a4 )
                status = 1;
            else
                status = -1;
        } 
        else
        {
            throw new ClassCastException("only compares ClientScoreData");
        }
        return status;
	}

    public long getNumberOfSolvedProblems()
    {
        return numberOfSolvedProblems;
    }

    public ProblemScoreData getProblemScoreData(int probId)
    {
        ProblemScoreData psd = problemScoreDataTreeMap.get(probId);
        return psd;
    }
    
    public TreeMap<Integer, ProblemScoreData> getAllProblemScoreData()
    {
    	return this.problemScoreDataTreeMap;
    }

    public int getRank()
    {
        return rank;
    }

    public long getScore()
    {
        return score;
    }

    public long getTimeOfLastSolvedProblem()
    {
        return timeOfLastSolvedProblem;
    }

    public String getUserID()
    {
        return userID;
    }

    public void setNumberOfSolvedProblems(long newNumberOfSolvedProblems)
    {
        numberOfSolvedProblems = newNumberOfSolvedProblems;
    }

    public void setProblemScoreData(ProblemScoreData newProblemScoreData)
    {
        if(newProblemScoreData != null)
        {
            int pID = newProblemScoreData.getProblemID();
            problemScoreDataTreeMap.put(pID, newProblemScoreData);
        }
    }

    public void setRank(int newRank)
    {
        rank = newRank;
    }

    public void setScore(long newScore)
    {
        score = newScore;
    }

    public void setTimeOfLastSolvedProblem(long newTimeOfLastSolvedProblem)
    {
        timeOfLastSolvedProblem = newTimeOfLastSolvedProblem;
    }

    public void setUserID(String newUserID)
    {
        userID = newUserID;
    }
}
