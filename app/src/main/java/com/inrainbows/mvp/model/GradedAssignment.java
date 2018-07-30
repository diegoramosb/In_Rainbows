package com.inrainbows.mvp.model;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;

/**
 * @author diego on 4/07/2018.
 */
public interface GradedAssignment {
    double getGrade();

    double getPercentage();

    DateTime getDueDate();

    boolean isGraded();
}
