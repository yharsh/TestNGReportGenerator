package io.github.yharsh.junit.teststatus;

import io.github.yharsh.junit.InvalidTestMethodException;
import io.github.yharsh.testng.TestSuiteResultAggregator;

/**
 * Created by YHarsh on 8/15/2017.
 */
public interface IStatusHandler {
    public void handle(String testMethodName, String className, long startTime, long endTime, Throwable t, TestSuiteResultAggregator aggregator) throws NoSuchMethodException, InvalidTestMethodException, ClassNotFoundException;
}
