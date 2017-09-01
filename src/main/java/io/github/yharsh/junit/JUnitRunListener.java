package io.github.yharsh.junit;

import io.github.yharsh.configuration.Configuration;
import io.github.yharsh.configuration.DefaultConfiguration;
import io.github.yharsh.Const;
import io.github.yharsh.junit.teststatus.IStatusHandler;
import io.github.yharsh.junit.teststatus.StatusHandlerFactory;
import io.github.yharsh.testng.ReportGenerator;
import io.github.yharsh.testng.TestSuiteResultAggregator;
import io.github.yharsh.testng.objects.Suite;
import io.github.yharsh.util.DumpListenerLogs;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import static io.github.yharsh.Const.NOT_SET;

/**
 * Created by YHarsh on 7/26/2016.
 * Order of calling method started -> failure -> finished
 */
public class JUnitRunListener extends RunListener {
    //May be require to clear this once report is generated to make listener unusable
    private TestSuiteResultAggregator aggregator;
    private Configuration configuration;
    private StatusHandlerFactory statusHandlerFactory;

    public JUnitRunListener() {
        this.configuration = new DefaultConfiguration();
        aggregator = new TestSuiteResultAggregator();
        statusHandlerFactory = new StatusHandlerFactory();
    }

    public JUnitRunListener(Configuration configuration) {
        this.configuration = configuration;
        aggregator = new TestSuiteResultAggregator();
        statusHandlerFactory = new StatusHandlerFactory();
    }

    @Override
    public void testRunFinished(Result result) {
        try {
            DumpListenerLogs.dumpLogInfo("testRunFinished", result);
            aggregator.getSuite().setFinishedAt(String.valueOf(System.currentTimeMillis()));
            if (configuration.getReportMode() == Const.REPORT_MODE.REPORT_PER_CLASS) {
                aggregator.getSuite().setFinishedAt(Util.getCurrentTimeInTestNgReportFormat());
                aggregator.getSuite().setDurationInMs(String.valueOf(Util.getTotalDuration(aggregator.getSuite().getStartedAt(), aggregator.getSuite().getFinishedAt())));
                ReportGenerator.generateReport(configuration, aggregator);
                resetAggregator();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void testStarted(Description description) throws Exception {
        try {
            if (description.isTest()) {
                DumpListenerLogs.dumpLogInfo("testStarted", description);
                IStatusHandler resultHandler = statusHandlerFactory.getResultHandler(Const.TEST_STATUS.INITIALIZED);
                if (resultHandler != null) {
                    resultHandler.handle(description.getMethodName(), description.getClassName(), System.currentTimeMillis(), NOT_SET, null, aggregator);
                    if (configuration.getReportMode() == Const.REPORT_MODE.REPORT_PER_TEST) {
                        aggregator.getSuite().setStartedAt(Util.getCurrentTimeInTestNgReportFormat());
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void testFinished(Description description) {
        try {
            if (description.isTest()) {
                DumpListenerLogs.dumpLogInfo("testFinished", description);
                IStatusHandler resultHandler = statusHandlerFactory.getResultHandler(Const.TEST_STATUS.PASS);
                if (resultHandler != null) {
                    resultHandler.handle(description.getMethodName(), description.getClassName(), NOT_SET, System.currentTimeMillis(), null, aggregator);
                }

                generateReport();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        try {
            Description description = failure.getDescription();
            if (failure.getDescription().isTest()) {
                DumpListenerLogs.dumpLogInfo("testFailure", failure);
                IStatusHandler resultHandler = statusHandlerFactory.getResultHandler(Const.TEST_STATUS.FAIL);
                if (resultHandler != null) {
                    resultHandler.handle(description.getMethodName(), description.getClassName(), NOT_SET, System.currentTimeMillis(), failure.getException(), aggregator);
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        try {
            Description description = failure.getDescription();
            if (failure.getDescription().isTest()) {
                DumpListenerLogs.dumpLogInfo("testAssumptionFailure", failure);
                IStatusHandler resultHandler = statusHandlerFactory.getResultHandler(Const.TEST_STATUS.ASSUMPTIONFAILED);
                if (resultHandler != null) {
                    resultHandler.handle(description.getMethodName(), description.getClassName(), NOT_SET, System.currentTimeMillis(), failure.getException(), aggregator);
                }
                generateReport();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void testIgnored(Description description) throws Exception {
        try {
            if (description.isTest()) {
                DumpListenerLogs.dumpLogInfo("testIngored", description);
                IStatusHandler resultHandler = statusHandlerFactory.getResultHandler(Const.TEST_STATUS.IGNORED);
                if (resultHandler != null) {
                    resultHandler.handle(description.getMethodName(), description.getClassName(), NOT_SET, NOT_SET, null, aggregator);
                }
            }
        } catch (Exception e) {
        }
    }

    private void generateReport() {
        if (configuration.getReportMode() == Const.REPORT_MODE.REPORT_PER_TEST) {
            aggregator.getSuite().setFinishedAt(Util.getCurrentTimeInTestNgReportFormat());
            aggregator.getSuite().setDurationInMs(String.valueOf(Util.getTotalDuration(aggregator.getSuite().getStartedAt(), aggregator.getSuite().getFinishedAt())));
            ReportGenerator.generateReport(configuration, aggregator);
            resetAggregator();
        }
    }

    @Override
    public void testRunStarted(Description description) throws Exception {
        try {
            DumpListenerLogs.dumpLogInfo("testRunStarted", description);
            Suite suite = aggregator.getSuite();
            suite.setName("Command line suite");
            suite.setStartedAt(Util.getCurrentTimeInTestNgReportFormat());
        } catch (Exception e) {
        }
    }

    private void resetAggregator() {
        Suite currentSuite = aggregator.getSuite();
        this.aggregator = new TestSuiteResultAggregator();
        aggregator.getSuite().setName(currentSuite.getName());
        aggregator.getSuite().setStartedAt(Util.getCurrentTimeInTestNgReportFormat());
    }
}
