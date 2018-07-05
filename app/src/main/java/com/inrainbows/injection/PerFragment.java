package com.inrainbows.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author diego on 4/07/2018.
 *
 * This annotation specifies that the lifespan of the injection is the same as that of a Fragment.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}
