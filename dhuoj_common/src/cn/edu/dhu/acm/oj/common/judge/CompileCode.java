// Decompiled by Jad v1.5.7f. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3)
// Source File Name:   CompileCode.java
package cn.edu.dhu.acm.oj.common.judge;

import java.io.*;
import cn.edu.dhu.acm.oj.common.config.Const;
import java.util.*;

// Referenced classes of package com.sjn:
//            ReadInputStream
public class CompileCode {

    public CompileCode() {
        sourcefilepath = "";
        sourcefilename = "";
        fname = "";
        exefpath = "";
        commandline = "";
        compileresult = false;
        compileinfo = "";
        sourcecode = "";
        sourcefilesuffix = "";
        errortype = 0;
    }

    public void setSaveFilePath(String path) {
        sourcefilepath = path;
    }

    public void setExeFilePath(String path) {
        exefpath = path;
        File filepath = new File(exefpath);
        if (!filepath.exists()) {
            filepath.mkdirs();
        }
    }

    public void setCode(String code) {
        sourcecode = code;
    }

    public void setFilename(String name) {
        fname = name;
    }

    public void setSourceFileSuffix(String suffix) {
        sourcefilesuffix = suffix;
    }

    public void setErrorType(int type) {
        errortype = type;
    }

    private void saveFile(String fp, String fname, String fc) {
        File fpath = new File(fp);
        if (!fpath.exists()) {
            fpath.mkdirs();
        }
        File f = new File(fp, fname);
        try {
            FileOutputStream fout = new FileOutputStream(f);
            fout.write(fc.getBytes());
            fout.close();
        } catch (IOException IOE) {
            System.out.println(IOE.toString());
        }
    }

    public void saveCodeFile() {
        sourcefilename = String.valueOf(sourcefilename) + String.valueOf(String.valueOf(fname) + String.valueOf(sourcefilesuffix));
        saveFile(sourcefilepath, sourcefilename, sourcecode);
    }

    public void setCompileCommand(String s) {
        commandline = s;
    }

    public void runcompile() {
        //System.out.println(sourcefilesuffix);
        String language = sourcefilesuffix.substring(1);
        //���б��������
        Watcher watch = new Watcher(commandline);
        Thread t = new Thread(watch);
        t.start();

        //�ȴ�ֱ����ʱ
        try {
            if (language.equalsIgnoreCase("java")) {
                //Java���볬ʱΪ��ͨ��2��
                t.join(Const.COMPILE_TIME * 2);
            } else {
                t.join(Const.COMPILE_TIME);
            }
        } catch (Exception e) {
        }

        //��ý�̾��ǿ�ƽ���
        Process p = watch.getProcess();
        p.destroy();

        //��÷���ֵ
        int ret = 0;

        try {
            ret = p.exitValue();
            if (ret != 0) {
                throw new Exception();
            } else {
                compileinfo = String.valueOf(String.valueOf(compileinfo)).concat("Compile successfully\n");
                compileresult = true;
            //return true;
            }
        }//end try ��ȡ����ֵ
        catch (Exception e) {
            //��ȡexitValue�����쳣�����߽�̷���ֵ������0����Ϊ�������
            err = p.getErrorStream();
            Scanner s = new Scanner(err);
            try {
                for (int i = 0; s.hasNextLine() && i < 10; i++) {
                    content += s.nextLine() + "\n";
                }
            } catch (Exception IOE) {
                System.out.println(IOE.toString());
            } finally {
                try {
                    err.close();
                    s.close();
                    System.gc();
                } catch (Exception exc) {
                }
            }
        //return false;
        }//end catch ��ȡ����ֵ
    }

    public boolean getCompileResult() {
        return compileresult;
    }

    public String getCompileInfo() {
        if (compileresult) {
            return compileinfo;
        } else {
            return content;
        }
    }
    private String commandline;
    private String sourcefilesuffix;
    private String sourcefilepath;
    private String sourcecode;
    private String fname;
    private String sourcefilename;
    private String exefpath;
    private String content;
    private InputStream err;
    private ReadInputStream ris;
    private int errortype;
    private boolean compileresult;
    private String compileinfo;
}
