package com.inrainbows.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author diego on 4/07/2018.
 *
 * This annotation specifies that the lifespan of the injection is the same as that of an Activity.
 */
@Scope //The injector may retain the instance for possible reuse in a later injection.
@Retention(RetentionPolicy.RUNTIME) //The annotation is to be retained at runtime.
public @interface PerActivity {
}
