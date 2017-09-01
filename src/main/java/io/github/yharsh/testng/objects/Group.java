package io.github.yharsh.testng.objects;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by YHarsh on 7/26/2016.
 */
public class Group {
    private String name;
    private List<GroupMethod> groupMethodList;

    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "method")
    public List<GroupMethod> getGroupMethodList() {
        return groupMethodList;
    }

    public void setGroupMethodList(List<GroupMethod> groupMethodList) {
        this.groupMethodList = groupMethodList;
    }
}
