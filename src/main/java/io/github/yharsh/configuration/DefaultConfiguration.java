package io.github.yharsh.configuration;

import io.github.yharsh.Const;

/**
 * Created by YHarsh on 8/22/2017.
 */
public class DefaultConfiguration extends Configuration {
    public DefaultConfiguration() {
        super(Const.REPORT_MODE.REPORT_PER_TEST, Const.REPORT_FILE_DIR, Const.TESTNG_FILENAME);
    }
}
