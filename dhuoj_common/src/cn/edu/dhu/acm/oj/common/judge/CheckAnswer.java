package cn.edu.dhu.acm.oj.common.judge;

import cn.edu.dhu.acm.oj.common.config.Const;

public class CheckAnswer {

    public CheckAnswer(String out, String ans) {
        this.out = out;
        this.ans = ans;
    }

    public CheckAnswer() {
        out = "";
        ans = "";
    }

    public void setAnswer(String s) {
        out = s;
    }

    public void setStandarAnswer(String s) {
        ans = s;
    }

    private String removeSP(String s) {
        StringBuffer temp = new StringBuffer();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != ' ' && s.charAt(i) != '\n' && s.charAt(i) != '\t') {
                temp.append(s.charAt(i));
            }
        }
        return temp.toString();
    }

    public void AnswerCheck() {
        verdict = Const.WA;
        int len1 = ans.length();
        int len2 = out.length();
        int samenum = 0;
        System.out.println(len1);
        System.out.println(len2);
        for (int i = 0; i < Math.min(len1, len2); i++) {
            if (ans.charAt(i) == out.charAt(i)) {
                samenum++;
            }
        }
        percent = (int) (samenum * 100L / Math.max(len1, len2));
        System.out.println(percent);
        if (samenum == Math.max(len1, len2)) {
            verdict = Const.AC;
        } else {
            ans = removeSP(ans);
            out = removeSP(out);
            if (ans.equals(out)) {
                verdict = Const.PE;
            }
        }
    }

    public boolean getCheckResult() {
        if (verdict == Const.AC) {
            return true;
        }
        return false;
    }

    public short getVerdict() {
        return verdict;
    }

    public int getPercent() {
        return percent;
    }
    private short verdict;
    private int percent;
    private String ans;
    private String out;
}
