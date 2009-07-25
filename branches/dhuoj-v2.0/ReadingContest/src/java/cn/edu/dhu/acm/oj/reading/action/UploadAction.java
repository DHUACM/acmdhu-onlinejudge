package cn.edu.dhu.acm.oj.reading.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Calendar;
import java.util.TreeMap;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.edu.dhu.acm.oj.persistence.beans.*;
import cn.edu.dhu.acm.oj.persistence.dao.*;

public class UploadAction extends ActionSupport {

    private static final long serialVersionUID = 572146812454l ;
    private static final int BUFFER_SIZE = 16 * 1024 ;

    private String userId;
    private String password;
    private int problemId;
    private int contestId;
    private File upload;
    private String uploadContentType;
    private String uploadFileName;
    private String savePath;

    public UploadAction() {
    }

    public String execute() throws Exception {
        UserDAO udao = new UserDAO();
        UserBean ubean = udao.chkLogin(userId, password);
        if (ubean == null) {
            addActionError("Login Failed!");
            return INPUT;
        }

        ContestBean cbean = null;
        try {
            ContestDAO cdao = new ContestDAO();
            cbean = cdao.findContest(contestId);
        } catch(Exception e) {
            addActionError("ContestID does not exist!");
            return INPUT;
        }

        ContestProblemBean cpbean = null;
        try {
            ContestProblemDAO cpdao = new ContestProblemDAO();
            TreeMap<Integer, ContestProblemBean> problem_map = cpdao.findProblemTreeMapByContest(contestId);
            cpbean = problem_map.get(problemId);
        } catch(Exception e) {
            addActionError("ProblemID does not exist!");
            return INPUT;
        }

        SolutionBean sbean = new SolutionBean(userId, contestId,
                    cpbean.getProblemId(), 0, 0, Calendar.getInstance().getTime(),
                    (short)1, (byte)0, (short)0);
        try {
            int cmpStartTime = Calendar.getInstance().getTime().compareTo(cbean.getStartTime());
            int cmpEndTime = Calendar.getInstance().getTime().compareTo(cbean.getEndTime());
            if (cmpStartTime < 0) throw new Exception();
            if (cmpEndTime > 0) {
                sbean.setResult(cn.edu.dhu.acm.oj.common.config.Const.TLE);
            }
            SolutionDAO sdao = new SolutionDAO();
            sdao.addSolution(sbean);
        } catch(Exception e) {
            addActionError("Submit Failed!");
            return INPUT;
        }

        uploadFileName = sbean.getSolutionId() + ".ppt";
        FileOutputStream fos = new FileOutputStream(savePath + File.separator + uploadFileName);
        FileInputStream fis = new FileInputStream(upload);
        byte[] buffer = new byte[BUFFER_SIZE];
        int len = 0;
        while ((len = fis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }

        return SUCCESS;
    }

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}