package com.zxp;

import com.dyf.*;
import com.sjn.*;
import com.lrf.*;

public class PaperCheckBean {

  boolean  solutionFlag;
  boolean  integralityFlag;
  boolean  typeFlag;

  JudgeBean judgeBean;
  EnvironmentBean environmentBean;
  /**
   * 试卷自检类的构造函数
   **/
  public PaperCheckBean() {
    solutionFlag = false;
    integralityFlag = false;
    typeFlag = false;

    judgeBean = new JudgeBean();
    environmentBean = new EnvironmentBean("Environment.xml");
    judgeBean.setEnvironmentBean(environmentBean);
  }
  /**
   * 检查电子程序题－－pBean，返回是否通过自检
   **/
  public boolean checkProblem( ProblemArchiveBean pBean ){
    judgeBean.setSolutionBean( pBean.getSolution() );
    judgeBean.setTestDataBean( pBean.getTestData() );
    judgeBean.judgeCheck();

    System.out.println( judgeBean.getResultBean().getCompileInfo() );
    System.out.println( judgeBean.getResultBean().getCompileResult() );
    System.out.println( judgeBean.getResultBean().getRunInfo() );


    if( judgeBean.getResultBean().getCheckResult() )
      return true;
    else{
      System.out.println( judgeBean.getResultBean().getCheckInfo() );
      return false;
    }
  }
  /**
   * 检查试卷信息类的对象－－paperDetail是否完整，返回是否通过自检
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
   * 检查试卷内各元素类型，及部分信息
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
   * 检查DTD文件，预留
   **/
  public String checkDtd(){
    return "<font color=\"blue\">DTD Check Pass Test!</font><br>";
  }
  /**
   * 检查电子程序题－－bean源代码是否正确
   **/
  public String checkSolution( ProblemArchiveBean bean ){
    solutionFlag = false;
    if( !checkProblem(bean) ){
      solutionFlag = false;
      return "<font color=\"red\" Problem > “" + bean.getTitle() +
        "” not pass solution check!</font><br>";
    }
    else{
      solutionFlag = true;
      return "<font color=\"blue\"> “"+ bean.getTitle() +
          "” Solution Check Pass!</font><br>";
    }
  }
}