package io.github.yharsh.testng.objects;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by YHarsh on 7/26/2016.
 */
public class Test {
    private List<TestClass> testClassList;
    private String name;

    @XmlElement(name = "class")
    public List<TestClass> getTestClass() {
        return testClassList;
    }

    public void setTestClass(List<TestClass> testClassList) {
        this.testClassList = testClassList;
    }

    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
