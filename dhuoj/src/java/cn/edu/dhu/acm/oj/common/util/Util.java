package cn.edu.dhu.acm.oj.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Util
{
    private static SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date getTime() {
        return Calendar.getInstance().getTime();
    }

    public static String getTimeString() {
        return format.format(getTime());
    }

    public static long getTimeLong() {
        Date d = getTime();
        return d.getTime();
    }

    public static long dateToLong(String d) throws Exception
    {
        Date date = format.parse(d);
        return date.getTime();
    }

}
