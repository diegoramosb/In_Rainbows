package com.inrainbows.model.model;


import com.inrainbows.mvp.model.Pomodoro;

import org.junit.Test;

/**
 * @author diego on 15/12/2017.
 */

public class PomodoroTest {

    Pomodoro pomodoro;

    public void setup1(){
        pomodoro = new Pomodoro();
    }

    @Test
    public void testPomodoro(){
        setup1();
        pomodoro.set(10);
        pomodoro.start();
    }
}
