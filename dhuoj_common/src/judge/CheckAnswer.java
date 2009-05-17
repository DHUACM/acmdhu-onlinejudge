package judge;

import config.Const;

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
        String temp = "";
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != ' ' && s.charAt(i) != '\n') {
                temp = String.valueOf(temp) + String.valueOf(s.charAt(i));
            }
        }

        return temp;
    }

    public void AnswerCheck() {
        verdict = Const.WA;
        int len1 = ans.length();
        int len2 = out.length();
        int samenum = 0;
        for (int i = 0; i < Math.min(len1, len2); i++) {
            if (ans.charAt(i) == out.charAt(i)) {
                samenum++;
            }
        }
        percent = samenum * 100 / Math.max(len1, len2);
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

    public int getFirstWrongplace() {
        int offset = 0;
        int stdlen = out.length();
        int anslen = ans.length();
        int len = Math.min(stdlen, anslen);
        for (int i = 0; i < len; i++) {
            offset++;
            if (out.charAt(i) != ans.charAt(i)) {
                break;
            }
        }
        return offset;
    }

    public boolean getCheckResult() {
        if (verdict.equals(Const.AC)) {
            return true;
        }
        return false;
    }

    public String getVerdict() {
        return verdict;
    }

    public String getCheckInfo() {
        return verdict;
    }

    public int getPercent() {
        return percent;
    }
    private String verdict;
    private int percent;
    private String ans;
    private String out;
}
