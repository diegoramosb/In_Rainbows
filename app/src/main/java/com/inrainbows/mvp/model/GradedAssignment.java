package com.inrainbows.mvp.model;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;

/**
 * @author diego on 4/07/2018.
 */
public interface GradedAssignment {

    long getId();

    void setId(long id);

    String getName();

    void setName(String name);

    double getGrade();

    void setGrade(double grade);

    double getPercentage();

    void setPercentage(double percentage);

    boolean isGraded();

    void setGraded(boolean graded);
}
