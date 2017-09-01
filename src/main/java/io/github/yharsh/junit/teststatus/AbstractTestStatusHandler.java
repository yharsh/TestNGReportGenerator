package io.github.yharsh.junit.teststatus;

import io.github.yharsh.Const;
import io.github.yharsh.junit.InvalidTestMethodException;
import io.github.yharsh.junit.Util;
import io.github.yharsh.junit.annotation.Tag;
import io.github.yharsh.junit.objects.TaggedMethod;
import io.github.yharsh.testng.objects.TestMethod;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

/**
 * Created by YHarsh on 8/15/2017.
 */
abstract class AbstractTestStatusHandler implements IStatusHandler {
    protected String testDir;

    protected TaggedMethod getTaggedMethod(String className, String methodName) throws ClassNotFoundException, NoSuchMethodException {
        Class clazz = Class.forName(className);
        Method method = clazz.getMethod(methodName);
        Tag tag = method.getAnnotation(Tag.class);
        return new TaggedMethod(tag, method);
    }

    protected String getMethodSignature(Method method) throws InvalidTestMethodException {
        Type returnType = method.getReturnType();
        int accessSpecifier = method.getModifiers();
        Type[] formalParamTypes = method.getGenericExceptionTypes();

        if (Modifier.PUBLIC != accessSpecifier || Void.TYPE != returnType || formalParamTypes.length > 0) {
            throw new InvalidTestMethodException("Invalid JUnit method");
        } else {
            return method.getDeclaringClass().getName() + "." + method.getName() + "()";
        }
    }

    protected TestMethod getTestMethod(Method method, String desc, long startTime, long endTime, Throwable t) throws InvalidTestMethodException {
        TestMethod testMethod = new TestMethod();
        testMethod.setName(method.getName());
        testMethod.setDescription(desc);
        if (Const.NOT_SET != startTime) {
            testMethod.setStartedAt(Util.getTimeInTestNgReportFormat(startTime));
        }
        if (Const.NOT_SET != endTime) {
            testMethod.setFinishedAt(Util.getTimeInTestNgReportFormat(endTime));
        }
        testMethod.setSignature(getMethodSignature(method));
        return testMethod;
    }
}
