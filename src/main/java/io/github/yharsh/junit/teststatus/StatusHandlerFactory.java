package io.github.yharsh.junit.teststatus;

import io.github.yharsh.Const;

/**
 * Created by YHarsh on 8/15/2017.
 */
public class StatusHandlerFactory {
    public IStatusHandler getResultHandler(Const.TEST_STATUS status) {
        switch (status) {
            case INITIALIZED:
                return new TestInitializedHandler();
            case PASS:
                return new TestSuccessHandler();
            case FAIL:
                return new TestFailedHandler();
            case IGNORED:
                return new TestSkippedHandler();
            case ASSUMPTIONFAILED:
                return new TestSkippedHandler();
            default:
                return null;
        }
    }
}
