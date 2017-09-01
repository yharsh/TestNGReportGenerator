package io.github.yharsh;

import java.io.File;

/**
 * Created by YHarsh on 8/22/2017.
 */
public final class Const {
    public final static String JUNIT_LOGGING = "JUNIT_LOGGING";
    public final static String REPORT_FILE_DIR = "build" + File.separator + "test-reports";
    public final static String TESTNG_FILENAME = "testng-results.xml";

    public enum REPORT_MODE {
        REPORT_PER_TEST,
        REPORT_PER_CLASS;

        public String getMode() {
            return this.toString();
        }
    }

    public final static long NOT_SET = -1;

    public enum TEST_STATUS {
        INITIALIZED("INITIALIZED"),
        FAIL("FAIL"),
        PASS("PASS"),
        IGNORED("IGNORED"),
        ASSUMPTIONFAILED("ASSUMPTIONFAILED");

        private String status;

        TEST_STATUS(String status) {
            this.status = status;
        }

        public String getStatus() {
            return this.status;
        }
    }
}
