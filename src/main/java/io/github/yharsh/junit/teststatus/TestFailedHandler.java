package io.github.yharsh.junit.teststatus;

import io.github.yharsh.Const;
import io.github.yharsh.junit.InvalidTestMethodException;
import io.github.yharsh.testng.TestSuiteResultAggregator;
import io.github.yharsh.testng.objects.ExceptionMessage;
import io.github.yharsh.testng.objects.Exception;
import io.github.yharsh.testng.objects.FullStacktrace;
import io.github.yharsh.testng.objects.TestMethod;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by YHarsh on 8/15/2017.
 */
public class TestFailedHandler extends AbstractTestStatusHandler {

    @Override
    public void handle(String methodName, String className, long startTime, long endTime, Throwable t, TestSuiteResultAggregator aggregator) throws NoSuchMethodException, InvalidTestMethodException, ClassNotFoundException {
        TestMethod testMethod = aggregator.getTestMethod(className, methodName);
        testMethod.setStatus(Const.TEST_STATUS.FAIL.getStatus());
        FullStacktrace stacktrace = new FullStacktrace();
        stacktrace.setNodeText("<" + getStackTrace(t) + ">");
        Exception exception = new Exception();
        exception.setClassName(t.getClass().getName());
        if(t.getMessage() != null)
        {
            ExceptionMessage message = new ExceptionMessage();
            message.setMessage(t.getMessage());
            exception.setMessage(message);
        }
        exception.setFullStacktrace(stacktrace);
        testMethod.setException(exception);
    }

    private String getStackTrace(Throwable t)
    {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        return stringWriter.toString();
    }
}
