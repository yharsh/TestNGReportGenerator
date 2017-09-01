package io.github.yharsh.junit;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by YHarsh on 7/26/2016.
 */
public class Util {
    private static final String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static String getTimeInTestNgReportFormat(long currentTimeInMillis) {
        Date date = new Date(currentTimeInMillis);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static String getCurrentTimeInTestNgReportFormat() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static long getTotalDuration(String testNgFormatStartTime, String testNgFormatEndTime) {
        long duration = 0;
        try {
            Date startTime = new SimpleDateFormat(pattern).parse(testNgFormatStartTime);
            Date endTime = new SimpleDateFormat(pattern).parse(testNgFormatEndTime);
            duration = endTime.getTime() - startTime.getTime();
        } finally {
            return duration;
        }
    }
}
