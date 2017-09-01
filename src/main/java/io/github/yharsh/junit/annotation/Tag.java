package io.github.yharsh.junit.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation for junit testcase to generate test objects
 * Sample Usage: @Tag(groups = {"UT", "Story-ID"})
 * Created by YHarsh on 7/26/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Tag
{
    public String[] groups();
}
