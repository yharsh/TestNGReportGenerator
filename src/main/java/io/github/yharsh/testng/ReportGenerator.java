package io.github.yharsh.testng;

import io.github.yharsh.configuration.Configuration;
import io.github.yharsh.jaxb.CDataXMLStreamWriter;
import io.github.yharsh.testng.objects.*;
import io.github.yharsh.util.DumpListenerLogs;
import io.github.yharsh.util.GetTestStats;
import io.github.yharsh.util.TestStats;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by YHarsh on 7/26/2016.
 */
public class ReportGenerator {
    public static void generateReport(Configuration configuration, TestSuiteResultAggregator aggregator) {
        Suite suite = aggregator.getSuite();
        Map<String, Set<GroupMethod>> groupMethodMap = aggregator.getGroupMethodMap();
        Map<String, Set<TestMethod>> testMethodMap = aggregator.getTestMethodMap();

        //Build TestngResults in bottom up fashion
        List<Group> groupList = new ArrayList<Group>();
        if (groupMethodMap != null && groupMethodMap.size() > 0) {
            for (String groupName : groupMethodMap.keySet()) {
                Group group = new Group();
                group.setName(groupName);
                group.setGroupMethodList(new ArrayList<GroupMethod>(groupMethodMap.get(groupName)));
                groupList.add(group);
            }
        }

        List<TestClass> testClassList = new ArrayList<TestClass>();
        if (testMethodMap != null && testMethodMap.size() > 0) {
            for (String className : testMethodMap.keySet()) {
                TestClass testClass = new TestClass();
                testClass.setName(className);
                testClass.setTestMethodList(new ArrayList<>(testMethodMap.get(className)));
                testClassList.add(testClass);
            }
        }

        Test test = new Test();
        test.setName("Command line test");
        test.setTestClass(testClassList);

        suite.setGroupList(groupList);
        suite.setTest(test);

        TestngResults results = new TestngResults();
        results.setSuite(suite);

        TestStats stats = GetTestStats.getTestStats(aggregator);

        results.setFailed(String.valueOf(stats.getFailed()));
        results.setPassed(String.valueOf(stats.getPassed()));
        results.setSkipped(String.valueOf(stats.getIgnored()));
        results.setTotal(String.valueOf(stats.getTotal()));

        //Dump result to a file
        DumpListenerLogs.dumpLogInfo("ReportGenerator", stats);
        if (stats.getTotal() > 0) {
            String file = configuration.getReportsDir() + File.separator + System.currentTimeMillis() + File.separator + configuration.getReportFileName();
            dumpToFile(results, file);
        } else {
            DumpListenerLogs.dumpLogInfo("ReportGenerator", "INFO: Not generating report as total testcount is zero");
        }
    }

    private static void dumpToFile(TestngResults results, String file) {
        File resultFile = new File(file);
        resultFile.getParentFile().mkdirs();

        DumpListenerLogs.dumpLogInfo("ReportGenerator", "Total test count is non-zero hence generating report: " + resultFile.getAbsolutePath());

        try {
            JAXBContext context = JAXBContext.newInstance(TestngResults.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            XMLOutputFactory xof = XMLOutputFactory.newInstance();
            XMLStreamWriter streamWriter = xof.createXMLStreamWriter(new FileOutputStream(resultFile));
            CDataXMLStreamWriter cdataStreamWriter = new CDataXMLStreamWriter(streamWriter);
            marshaller.marshal(results, cdataStreamWriter);
            cdataStreamWriter.flush();
            cdataStreamWriter.close();
        } catch (Exception e) {
            DumpListenerLogs.dumpLogInfo("ReportGenerator", e.getMessage());
        }
    }
}
