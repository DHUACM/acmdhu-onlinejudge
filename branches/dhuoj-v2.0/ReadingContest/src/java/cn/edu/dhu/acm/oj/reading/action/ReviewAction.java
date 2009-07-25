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

public class ReviewAction extends ActionSupport {

    private static final long serialVersionUID = 572146812454l ;
    private static final int BUFFER_SIZE = 16 * 1024 ;

    private String solutionId;
    private String result;
    private File upload;
    private String uploadContentType;
    private String uploadFileName;
    private String savePath;

    public ReviewAction() {
    }

    public String execute() throws Exception {
        int sid = 0;
        try{
            sid = Integer.parseInt(solutionId);
        } catch(Exception e) {
            addActionError("Invalid SolutionID!");
            return INPUT;
        }

        SolutionDAO sdao = new SolutionDAO();
        SolutionBean sbean = sdao.findSolution(sid);
        if (sbean == null) {
            addActionError("SolutionID not exist!");
            return INPUT;
        }
        if (result.equals("yes")) sbean.setResult((short)2);
        else sbean.setResult((short)3);
        sdao.updateSolution(sbean);

        uploadFileName = sbean.getSolutionId() + ".doc";
        FileOutputStream fos = new FileOutputStream(savePath + File.separator + uploadFileName);
        FileInputStream fis = new FileInputStream(upload);
        byte[] buffer = new byte[BUFFER_SIZE];
        int len = 0;
        while ((len = fis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }
        return SUCCESS;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(String solutionId) {
        this.solutionId = solutionId;
    }

}