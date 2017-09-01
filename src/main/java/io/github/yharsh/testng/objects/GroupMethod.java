package io.github.yharsh.testng.objects;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by YHarsh on 7/26/2016.
 */
public class GroupMethod {
    private String className;
    private String name;
    private String signature;

    @XmlAttribute(name = "class")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupMethod that = (GroupMethod) o;

        if (className != null ? !className.equals(that.className) : that.className != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return signature != null ? signature.equals(that.signature) : that.signature == null;

    }

    @Override
    public int hashCode() {
        int result = className != null ? className.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (signature != null ? signature.hashCode() : 0);
        return result;
    }
}
