
package judge;

public class CheckAnswer {

    public CheckAnswer() {
        checkresult = false;
        checkinfo = "";
        stdans = "";
        checkedans = "";
        offset = 0;
    }

    public void setStandarAnswer(String s) {
        stdans = s;
    }

    public void setAnswer(String s) {
        checkedans = s;
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

    private void compare(String std, String ans) {
        int stdlen = std.length();
        int anslen = ans.length();
        int len = stdlen <= anslen ? stdlen : anslen;
        for (int i = 0; i < len; i++) {
            offset++;
            if (std.charAt(i) != ans.charAt(i)) {
                return;
            }
        }

    }

    public void AnswerCheck() {
        int len1 = stdans.length();
        int len2 = checkedans.length();
        if (stdans.compareTo(checkedans) == 0) {
            checkresult = true;
            checkinfo = String.valueOf(String.valueOf(checkinfo)).concat("Accepted");
            return;
        }
        if (len2 == len1) {
            if (stdans.compareTo(checkedans) != 0) {
                checkinfo = String.valueOf(String.valueOf(checkinfo)).concat("Wrong Answer");
            }
        } else {
            stdans = removeSP(stdans);
            checkedans = removeSP(checkedans);
            if (stdans.compareTo(checkedans) != 0) {
                checkinfo = String.valueOf(String.valueOf(checkinfo)).concat("Wrong Answer");
            } else {
                checkinfo = String.valueOf(String.valueOf(checkinfo)).concat("Presentation Error");
            }
            return;
        }
    }

    public int getFirstWrongplace() {
        compare(stdans, checkedans);
        return offset;
    }

    public boolean getCheckResult() {
        return checkresult;
    }

    public String getCheckInfo() {
        return checkinfo;
    }
    private boolean checkresult;
    private String checkinfo;
    private String stdans;
    private String checkedans;
    private int offset;
}
