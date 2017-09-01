package io.github.yharsh.testng.objects;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.lang.*;

/**
 * Created by YHarsh on 7/26/2016.
 */
public class TestMethod {
    private String description;
    private String durationInMS;
    private String finishAt;
    private String name;
    private String signature;
    private String startedAt;
    private String status;
    private Exception exception;

    @XmlAttribute(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlAttribute(name = "duration-ms")
    public String getDurationInMS() {
        return durationInMS;
    }

    public void setDurationInMS(String durationInMS) {
        this.durationInMS = durationInMS;
    }

    @XmlAttribute(name = "finished-at")
    public String getFinishedAt() {
        return finishAt;
    }

    public void setFinishedAt(String finishAt) {
        this.finishAt = finishAt;
    }

    public String getName() {
        return name;
    }

    @XmlAttribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "signature")
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @XmlAttribute(name = "started-at")
    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    @XmlAttribute(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement(name = "exception")
    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestMethod method = (TestMethod) o;

        if (description != null ? !description.equals(method.description) : method.description != null) return false;
        if (name != null ? !name.equals(method.name) : method.name != null) return false;
        if (signature != null ? !signature.equals(method.signature) : method.signature != null) return false;
        return exception != null ? exception.equals(method.exception) : method.exception == null;

    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (signature != null ? signature.hashCode() : 0);
        return result;
    }
}
