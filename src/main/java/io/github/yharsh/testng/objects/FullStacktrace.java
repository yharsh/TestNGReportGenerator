package io.github.yharsh.testng.objects;

import javax.xml.bind.annotation.XmlValue;

/**
 * Created by YHarsh on 7/28/2016.
 */
public class FullStacktrace {
    String nodeText;

    @XmlValue
    public String getNodeText() {
        return nodeText;
    }

    public void setNodeText(String nodeText) {
        this.nodeText = nodeText;
    }
}
