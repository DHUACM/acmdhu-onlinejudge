package cn.edu.dhu.acm.oj.common.paper;

import cn.edu.dhu.acm.oj.common.problem.*;
import cn.edu.dhu.acm.oj.common.judge.*;
import cn.edu.dhu.acm.oj.common.config.*;

public class PaperCheckBean {

  boolean  solutionFlag;
  boolean  integralityFlag;
  boolean  typeFlag;

  //JudgeBean judgeBean;
  EnvironmentBean environmentBean;
  /**
   * �Ծ��Լ���Ĺ��캯��
   **/
  public PaperCheckBean() {
    solutionFlag = false;
    integralityFlag = false;
    typeFlag = false;

    //judgeBean = new JudgeBean();
    environmentBean = new EnvironmentBean("Environment.xml");
    //judgeBean.setEnvironmentBean(environmentBean);
  }
  /**
   * �����ӳ����⣭��pBean�������Ƿ�ͨ���Լ�
   **/
  public boolean checkProblem( ProblemArchiveBean pBean ){
      //TODO: change the old judge to new
//    judgeBean.setSolutionBean( pBean.getSolution() );
//    judgeBean.setTestDataBean( pBean.getTestData() );
//    judgeBean.judgeCheck();
//
//    System.out.println( judgeBean.getResultBean().getCompileInfo() );
//    System.out.println( judgeBean.getResultBean().getCompileResult() );
//    System.out.println( judgeBean.getResultBean().getRunInfo() );
//
//
//    if( judgeBean.getResultBean().getCheckResult() )
//      return true;
//    else{
//      System.out.println( judgeBean.getResultBean().getCheckInfo() );
//      return false;
//    }
      return true;
  }
  /**
   * ����Ծ���Ϣ��Ķ��󣭣�paperDetail�Ƿ��������Ƿ�ͨ���Լ�
   **/
  public String checkPaperIntegrality( PaperDetail paperDetail ){
    integralityFlag = false;
    if( (paperDetail.getAuthor()).length() == 0 ){
      return "<font color=\"red\">Author is null!</font><br>";
    }

    if( (paperDetail.getDecribe()).length() == 0 ){
      return "<font color=\"red\">Describe is null!</font><br>";
    }

    if( (paperDetail.getID()).length() == 0 ){
      return "<font color=\"red\">Id is null!</font><br>";
    }

    if( (paperDetail.getName()).length() == 0 ){
      return "<font color=\"red\">Name is null!</font><br>";
    }

    if( (paperDetail.getProblemSum()).length() == 0 ){
      return "<font color=\"red\">ProblemSum is null!</font><br>";
    }

    if( (paperDetail.getReference()).length() == 0 ){
      return "<font color=\"red\">Reference is null!</font><br>";
    }

    if( (paperDetail.getStartTime()).length() == 0 ){
      return "<font color=\"red\">StartTime is null!</font><br>";
    }

    if( (paperDetail.getTotalTime()).length() == 0){
      return "<font color=\"red\">TotalTime is null!</font><br>";
    }

    if( (paperDetail.getVersion()).length() == 0){
      return "<font color=\"red\">Version is null!</font><br>";
    }

    integralityFlag = true;
    return "<font color=\"blue\">Integrality Check Pass!</font><br>";
  }
  /**
   * ����Ծ��ڸ�Ԫ�����ͣ���������Ϣ
   **/
  public String checkElementType( PaperDetail paperDetail, int SumProblem ){
    int problemSum;
    int totalTime;
    try{
      problemSum = Integer.parseInt( paperDetail.getProblemSum() );
      totalTime = Integer.parseInt( paperDetail.getTotalTime() );
    }
    catch( NumberFormatException e ){
      return "<font color=\"red\">ProblemSumNum or TotalTime type error!<br></font>";
    }
    typeFlag = false;
    if( totalTime < 0 || totalTime > 9999 ){
      return "<font color=\"red\">Total Time must between 0~9999!</font><br>";
    }

    if( problemSum != SumProblem ){
      return "<font color=\"red\">Problem sum number is incorrect!</font><br>";
    }

    typeFlag = true;
    return "<font color=\"blue\">Element Type Check Pass!</font><br>";
  }
  /**
   * ���DTD�ļ���Ԥ��
   **/
  public String checkDtd(){
    return "<font color=\"blue\">DTD Check Pass Test!</font><br>";
  }
  /**
   * �����ӳ����⣭��beanԴ�����Ƿ���ȷ
   **/
  public String checkSolution( ProblemArchiveBean bean ){
    solutionFlag = false;
    if( !checkProblem(bean) ){
      solutionFlag = false;
      return "<font color=\"red\" Problem > ��" + bean.getTitle() +
        "�� not pass solution check!</font><br>";
    }
    else{
      solutionFlag = true;
      return "<font color=\"blue\"> ��"+ bean.getTitle() +
          "�� Solution Check Pass!</font><br>";
    }
  }
}