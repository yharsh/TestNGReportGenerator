package io.github.yharsh.util;

import io.github.yharsh.Const;
import io.github.yharsh.testng.TestSuiteResultAggregator;
import io.github.yharsh.testng.objects.TestMethod;

import java.util.Set;

/**
 * Created by YHarsh on 8/23/2017.
 */
public class GetTestStats {
    public static TestStats getTestStats(TestSuiteResultAggregator aggregator) {
        TestStats stats = new TestStats();

        int passed = 0;
        int failed = 0;
        int ignored = 0;

        for (String className : aggregator.getTestMethodMap().keySet()) {
            Set<TestMethod> methods = aggregator.getTestMethodMap().get(className);
            for (TestMethod method : methods) {
                Const.TEST_STATUS test_status = Const.TEST_STATUS.valueOf(method.getStatus());
                switch (test_status) {
                    case PASS:
                        passed++;
                        break;
                    case FAIL:
                        failed++;
                        break;
                    case IGNORED:
                        ignored++;
                        break;
                    case ASSUMPTIONFAILED:
                        ignored++;
                        break;
                    default:
                        break;
                }
            }
        }
        stats.setPassed(passed);
        stats.setFailed(failed);
        stats.setIgnored(ignored);
        stats.setTotal(passed + failed + ignored);
        return stats;
    }
}
