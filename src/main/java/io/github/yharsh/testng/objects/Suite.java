package io.github.yharsh.testng.objects;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * Created by YHarsh on 7/26/2016.
 */
public class Suite {
    private String name;
    private String durationInMs;
    private String startedAt;
    private String finishedAt;
    private List<Group> groupList;
    private Test test;

    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "duration-ms")
    public String getDurationInMs() {
        return durationInMs;
    }

    public void setDurationInMs(String durationInMs) {
        this.durationInMs = durationInMs;
    }

    @XmlAttribute(name = "started-at")
    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    @XmlAttribute(name = "finished-at")
    public String getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(String finishedAt) {
        this.finishedAt = finishedAt;
    }

    @XmlElementWrapper(name = "groups")
    @XmlElement(name = "group")
    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    @XmlElement(name = "test")
    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

}
