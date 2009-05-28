package cn.edu.dhu.acm.oj.common.judge;

import cn.edu.dhu.acm.oj.common.bean.*;
import cn.edu.dhu.acm.oj.common.config.*;

/**
 *
 * @author try
 */
public class CheckMaliciousCode {

    public CheckMaliciousCode(RunBean rb) {
        runbean = rb;
    }

    boolean isValidCharacter(char c) {
        boolean isvalid = false;
        if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || (c == '_')) {
            isvalid = true;
        }
        return isvalid;
    }

    boolean searchKeyWord(String s) {
        boolean isequal = false;
        for (int i = 0; i < Const.MALICIOUS.length; i++) {
            if (s.equals(Const.MALICIOUS[i])) {
                isequal = true;
                break;
            }
        }
        return isequal;
    }

    boolean checkKeyWord() {
        if (runbean.getLanguage() != Const.C && runbean.getLanguage() != Const.CPP) {
            return true;
        }
        boolean checkedresult = true;
        String checkinfo = "";
        try {
            String sourceCode = runbean.getSourceCode();
            StringBuffer varfunName = new StringBuffer();
            boolean ignore = false;
            for (int i = 0; i < sourceCode.length(); i++) {
                // ESC mark, ignore the next charater
                if (sourceCode.charAt(i) == '\\') {
                    i++;
                    continue;
                }
                // quotation mark, change the state
                if (sourceCode.charAt(i) == '\"') {
                    ignore = !ignore;
                }
                if (ignore) {
                    continue;
                }
                // comment mark , ignore the charaters in this line
                if (sourceCode.charAt(i) == '/' && sourceCode.charAt(i + 1) == '/') {
                    i += 2;
                    while (sourceCode.charAt(i) != '\n') {
                        i++;
                    }
                    continue;
                }
                // comment mark , ignore all of the charaters until meet the the next mark
                if (sourceCode.charAt(i) == '/' && sourceCode.charAt(i + 1) == '*') {
                    i += 2;
                    while (sourceCode.charAt(i) != '*' || sourceCode.charAt(i + 1) != '/') {
                        i++;
                    }
                    i++;
                    continue;
                }
                // fetch the key word and compare it with the const table
                while (isValidCharacter(sourceCode.charAt(i))) {
                    varfunName.append(sourceCode.charAt(i));
                    i++;
                }
                if (!varfunName.toString().equals("") && searchKeyWord(varfunName.toString())) {
                    checkedresult = false;
                    checkinfo = varfunName.toString();
                    break;
                }
                varfunName = new StringBuffer();
            }
        } catch (Exception e) {
            e.printStackTrace();
            checkedresult = false;
        }
        if (!checkedresult) {
            System.out.println("Dangerous");
            runbean.setResult(Const.CE);
            runbean.setCompileInfo("Variable or function name \"" + checkinfo + "\" restricted\n");
        }
        return checkedresult;
    }
    private RunBean runbean;
}
