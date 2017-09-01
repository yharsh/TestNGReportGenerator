package io.github.yharsh.testng;

import io.github.yharsh.testng.objects.GroupMethod;
import io.github.yharsh.testng.objects.Suite;
import io.github.yharsh.testng.objects.TestMethod;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by YHarsh on 8/15/2017.
 */
public class TestSuiteResultAggregator {
    private Suite suite;

    /*
    Map of Group and associated GroupMethod e.g.

    @Tag(group = {"A"})
    public void test1(){}

    @Tag(group = {"A", "B"})
    public void test2()

    then groupMap = [{A, {test1, test2}},
                     {B, {test2}}]
     */
    private Map<String, Set<GroupMethod>> groupMap;

    /*
    Map of ClassName and associated Methods e.g.

    class A{
        void x(){}
        void y(){}
    }

    class B{
        void p(){}
    }

    then methodMap = [{A, {x, y}},
                      {B, {p}}]
     */
    private Map<String, Set<TestMethod>> methodMap;

    public TestSuiteResultAggregator() {
        suite = new Suite();
        groupMap = new ConcurrentHashMap<String, Set<GroupMethod>>();
        methodMap = new ConcurrentHashMap<String, Set<TestMethod>>();
    }

    /**
     * @param groups
     * @param groupMethod
     */
    public void addGroupMethodToGroups(String[] groups, GroupMethod groupMethod) {
        if (groupMethod != null && groups != null && groups.length > 0) {
            for (String group : groups) {
                Set<GroupMethod> methods = groupMap.get(group);
                if (methods == null) {
                    methods = new HashSet<>(1);
                    groupMap.put(group, methods);
                }
                methods.add(groupMethod);
            }
        }
    }

    /**
     * @param className
     * @param testMethod
     */
    public void addTestMethodToClass(String className, TestMethod testMethod) {
        if (testMethod != null && className != null) {
            Set<TestMethod> methods = methodMap.get(className);
            if (methods == null) {
                methods = new HashSet<TestMethod>(1);
                methodMap.put(className, methods);
            }
            methods.add(testMethod);

        }
    }

    /**
     * @param className
     * @param methodName
     * @return
     */
    public TestMethod getTestMethod(String className, String methodName) {
        TestMethod result = null;
        Set<TestMethod> methods = methodMap.get(className);
        if (methods != null) {
            for (Iterator<TestMethod> it = methods.iterator(); it.hasNext(); ) {
                TestMethod testMethod = it.next();
                if (testMethod.getName().equals(methodName)) {
                    result = testMethod;
                    break;
                }
            }
        }
        return result;
    }

    public Suite getSuite() {
        return suite;
    }

    public Map<String, Set<TestMethod>> getTestMethodMap() {
        return methodMap;
    }

    public Map<String, Set<GroupMethod>> getGroupMethodMap() {
        return groupMap;
    }
}
