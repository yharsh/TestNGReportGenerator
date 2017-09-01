package io.github.yharsh.util;

import io.github.yharsh.testng.objects.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YHarsh on 7/26/2016.
 */
public class JaxbEX {
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        GroupMethod gm = new GroupMethod();
        gm.setClassName("1");
        Group group = new Group();
        List groupMethodList = new ArrayList(1);
        groupMethodList.add(gm);
        group.setGroupMethodList(groupMethodList);

        List groupList = new ArrayList(1);
        groupList.add(group);

        TestMethod tm = new TestMethod();
        tm.setDescription("FirstTest");

        List testMethodList = new ArrayList(1);
        testMethodList.add(tm);

        TestClass cl = new TestClass();
        cl.setTestMethodList(testMethodList);

        List<TestClass> testClassList = new ArrayList<TestClass>();
        testClassList.add(cl);

        Test test = new Test();
        test.setTestClass(testClassList);

        Suite suite = new Suite();
        suite.setGroupList(groupList);
        suite.setTest(test);

        TestngResults results = new TestngResults();
        results.setFailed("1");
        results.setSuite(suite);

        File file = new File("textng-results.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(TestngResults.class);

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(results, new FileOutputStream(file));
    }
}
