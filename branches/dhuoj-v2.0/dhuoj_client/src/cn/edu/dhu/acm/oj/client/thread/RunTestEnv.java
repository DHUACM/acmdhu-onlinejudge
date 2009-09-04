package cn.edu.dhu.acm.oj.client.thread;

import cn.edu.dhu.acm.oj.client.*;
import cn.edu.dhu.acm.oj.common.config.Const;

public class RunTestEnv implements Runnable {

	int problemNo;
	String problemName;

	public RunTestEnv() {
	}

	public void run() {
		String out = "";
		String oriL = Control.getLanguage();
		Control.setCode("");
		for (int i = 1; i < Const.LANGUAGE.length; i++) {
			String L = Const.LANGUAGE[i];
			Control.setLanguage(L);
			Control.Compile(false);
			if (Control.getCompileOut().startsWith("Please")) {
				out += "Check " + L + " : " + "not find compiler.\n";
			} else {
				out += "Check " + L + " : " + "check OK.\n";
			}
		}
		Control.setLanguage(oriL);
		Control.getMainFrame().smallDialog(out, "Check Compiler", 0);

	}
}

