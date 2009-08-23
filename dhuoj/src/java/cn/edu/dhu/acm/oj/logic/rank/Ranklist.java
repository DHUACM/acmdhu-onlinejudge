package cn.edu.dhu.acm.oj.logic.rank;

import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import cn.edu.dhu.acm.oj.persistence.beans.ContestBean;
import cn.edu.dhu.acm.oj.persistence.beans.SolutionBean;
import cn.edu.dhu.acm.oj.persistence.beans.UserBean;

public class Ranklist {
	private final static long punishment_time = 1200000L; // 20 minutes.

    private ContestBean contest;
    private long start;

    public Ranklist(ContestBean cbean) {
        contest = cbean;
        start = contest.getStartTime().getTime();
    }
	
	// this function is used when the user has not tried this problem before.
	private ProblemScoreData calcProblemScoreData(SolutionBean runBean)
	{
		ProblemScoreData psd = new ProblemScoreData();
		psd.setProblemID(runBean.getProblemId());
		psd.setAttempts(psd.getAttempts() + 1);
		psd.setSolutionTime(runBean.getSubmitDate().getTime() - start);
		// this problem is AC.
        if (runBean.getResult() == 2) {
			psd.setScore(runBean.getSubmitDate().getTime() - start);
			psd.setSolved(true);
		}
		else{
			psd.setScore(psd.getScore() + punishment_time);
			psd.setSolved(false);
		}
		return psd;
	}
	
	
	// this function is used to update the problem status that the user has already try.
	private ProblemScoreData calProblemScoreData(ProblemScoreData oldPsd, SolutionBean runBean)
	{
		ProblemScoreData psd = new ProblemScoreData(oldPsd.getProblemID(), oldPsd.getAttempts(), 
				oldPsd.getSolutionTime(), oldPsd.isSolved(), oldPsd.getScore());
		
        long submitTime = runBean.getSubmitDate().getTime() - start;

		// this problem has already been solved.
		if ( psd.isSolved() ){
			// recalculate the score.
            if (submitTime < psd.getSolutionTime()) {
				psd.setScore( psd.getScore() - psd.getSolutionTime() + submitTime );
				psd.setSolutionTime( submitTime );
			}
		}
		// this problem is not AC before.
		else{
            psd.setAttempts(psd.getAttempts() + 1);
			// this problem is solved.
            if (runBean.getResult() == 2) {
				psd.setScore(psd.getScore() + submitTime);
				psd.setSolved(true);
				psd.setSolutionTime(submitTime);
			}
			else
			{
				psd.setScore(psd.getScore() + punishment_time);
				psd.setSolved(false);
			}
		}
		
		return psd;
	}
	
	
	public ClientScoreData[] getStandings(ArrayList<SolutionBean> al, Set<String> userSet) throws Exception
	{
		TreeMap<String, ClientScoreData> csd_map = new TreeMap<String, ClientScoreData>();

		for ( int i=0; i<al.size(); i++ )
		{
			SolutionBean bean = al.get(i);
			String userId = bean.getUserId();
			ClientScoreData csd = (ClientScoreData)csd_map.get(userId);
            userSet.remove(userId);
			
			// the user is not in the csd_map.
			if ( csd == null )
			{
				csd = new ClientScoreData();
				csd.setUserID(userId);
			}
			
			int probId = bean.getProblemId();
            ProblemScoreData psd = csd.getProblemScoreData(probId);
			// user has not try this problem
			if ( psd == null ){
				ProblemScoreData newPsd = calcProblemScoreData(bean);
				csd.setProblemScoreData(newPsd);
			}
			else{
				ProblemScoreData updatedPsd = calProblemScoreData(psd, bean);
				csd.setProblemScoreData(updatedPsd);
			}
			csd_map.put(userId, csd);
		}

        for (Iterator iter = userSet.iterator(); iter.hasNext(); ) {
            String userId = (String)iter.next();
            ClientScoreData csd = new ClientScoreData();
            csd.setUserID(userId);
            csd_map.put(userId, csd);
        }
		
		TreeMap<ClientScoreData, ClientScoreData> ranklist_map = new TreeMap<ClientScoreData, ClientScoreData>();
		Collection allUserInfoColl = csd_map.values();
		Iterator allUserInfoIterator = allUserInfoColl.iterator();
		while ( allUserInfoIterator.hasNext() ){
			long numOfSolved = 0L;
			long score = 0L;
			long timeOfLastSolved = 0L;
			ClientScoreData csd = (ClientScoreData)allUserInfoIterator.next();
			Collection userProblemStatusColl = csd.getAllProblemScoreData().values();
			Iterator psIterator = userProblemStatusColl.iterator();
			while ( psIterator.hasNext() ){
				ProblemScoreData psd = (ProblemScoreData)psIterator.next();
                // problem(0):a+b should not be included in the ranklist.
				if ( psd.getProblemID() != 0 && psd.isSolved() ){
					numOfSolved++;
					score += psd.getScore();
				}
			}
			csd.setNumberOfSolvedProblems(numOfSolved);
			csd.setScore(score);
			csd.setTimeOfLastSolvedProblem(timeOfLastSolved);
			ranklist_map.put(csd, csd);
		}
		
		ClientScoreData csd_array[] = new ClientScoreData[csd_map.size()];
		Collection ranklistColl = ranklist_map.values();
		Iterator iterator = ranklistColl.iterator();
		long numSolved = -1L;
		long score = 0L;
		long lastSolved = 0L;
		int rank = 0;
		int indexRank = 0;
		int index = 0;
		while(iterator.hasNext()) 
		{
			Object o = iterator.next();
			if(o instanceof ClientScoreData)
			{
				ClientScoreData csd = (ClientScoreData)o;
				String userId = csd.getUserID();
				indexRank++;
				if(numSolved != csd.getNumberOfSolvedProblems() || score != csd.getScore() || lastSolved != csd.getTimeOfLastSolvedProblem())
				{
					numSolved = csd.getNumberOfSolvedProblems();
					score = csd.getScore();
					lastSolved = csd.getTimeOfLastSolvedProblem();
					rank = indexRank;
					csd.setRank(rank);
				} else
				{
					csd.setRank(rank);
				}
				csd_array[index++] = csd;
			}
		}
		return csd_array;
	}
}
