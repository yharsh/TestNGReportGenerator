package io.github.yharsh.testng.objects;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by YHarsh on 7/28/2016.
 */
public class Exception {
    private String className;
    private ExceptionMessage message;
    private FullStacktrace fullStacktrace;

    @XmlAttribute(name = "class")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @XmlElement(name = "full-stacktrace")
    public FullStacktrace getFullStacktrace() {
        return fullStacktrace;
    }

    public void setFullStacktrace(FullStacktrace fullStacktrace) {
        this.fullStacktrace = fullStacktrace;
    }

    @XmlElement(name = "message")
    public ExceptionMessage getMessage() {
        return message;
    }

    public void setMessage(ExceptionMessage message) {
        this.message = message;
    }
}
