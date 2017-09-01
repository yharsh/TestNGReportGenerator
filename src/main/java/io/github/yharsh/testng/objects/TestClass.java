package io.github.yharsh.testng.objects;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by YHarsh on 7/26/2016.
 */
public class TestClass {
    private List<TestMethod> TestMethodList;
    private String name;

    @XmlElement(name = "test-method")
    public List<TestMethod> getTestMethodList() {
        return TestMethodList;
    }

    public void setTestMethodList(List<TestMethod> TestMethodList) {
        this.TestMethodList = TestMethodList;
    }

    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
