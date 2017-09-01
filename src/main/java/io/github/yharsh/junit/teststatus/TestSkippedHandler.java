package io.github.yharsh.junit.teststatus;

import io.github.yharsh.Const;
import io.github.yharsh.junit.InvalidTestMethodException;
import io.github.yharsh.junit.annotation.Tag;
import io.github.yharsh.junit.objects.TaggedMethod;
import io.github.yharsh.testng.TestSuiteResultAggregator;
import io.github.yharsh.testng.objects.GroupMethod;
import io.github.yharsh.testng.objects.TestMethod;

import java.lang.reflect.Method;

/**
 * Created by YHarsh on 8/15/2017.
 */
public class TestSkippedHandler extends AbstractTestStatusHandler {

    @Override
    public void handle(String methodName, String className, long startTime, long endTime, Throwable t, TestSuiteResultAggregator aggregator) throws NoSuchMethodException, InvalidTestMethodException, ClassNotFoundException {
        TestMethod testMethod = null;
        TaggedMethod taggedMethod = getTaggedMethod(className, methodName);

        Method method = taggedMethod.getMethod();
        Tag tag = taggedMethod.getTag();
        String methodSignature = getMethodSignature(method);

        testMethod = getTestMethod(method, methodName, startTime, endTime, t);
        testMethod.setStatus(Const.TEST_STATUS.IGNORED.getStatus());

        aggregator.addTestMethodToClass(className, testMethod);

        String[] groups = taggedMethod.getTag() == null ? null : taggedMethod.getTag().groups();

        if (groups != null && groups.length > 0) {
            GroupMethod groupMethod = new GroupMethod();
            groupMethod.setName(method.getName());
            groupMethod.setSignature(methodSignature);
            groupMethod.setClassName(method.getDeclaringClass().getName());
            aggregator.addGroupMethodToGroups(groups, groupMethod);
        }
    }
}
