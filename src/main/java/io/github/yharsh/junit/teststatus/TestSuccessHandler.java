package io.github.yharsh.junit.teststatus;

import io.github.yharsh.Const;
import io.github.yharsh.junit.InvalidTestMethodException;
import io.github.yharsh.testng.TestSuiteResultAggregator;
import io.github.yharsh.testng.objects.TestMethod;

/**
 * Created by YHarsh on 8/15/2017.
 */
public class TestSuccessHandler extends AbstractTestStatusHandler {
    @Override
    public void handle(String methodName, String className, long startTime, long endTime, Throwable t, TestSuiteResultAggregator aggregator) throws NoSuchMethodException, InvalidTestMethodException, ClassNotFoundException {
        TestMethod testMethod = aggregator.getTestMethod(className, methodName);
        if (testMethod != null && Const.TEST_STATUS.INITIALIZED.getStatus().equals(testMethod.getStatus())) {
            testMethod.setStatus(Const.TEST_STATUS.PASS.getStatus());
        }
    }
}
