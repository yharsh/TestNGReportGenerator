package io.github.yharsh.junit.objects;

import io.github.yharsh.junit.annotation.Tag;

import java.lang.reflect.Method;

/**
 * Created by YHarsh on 8/15/2017.
 */
public class TaggedMethod {
    Tag tag;
    Method method;

    public TaggedMethod(Tag tag, Method method) {
        this.tag = tag;
        this.method = method;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
