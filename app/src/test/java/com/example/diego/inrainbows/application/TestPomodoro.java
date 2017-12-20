package com.example.diego.inrainbows.application;


import org.junit.Test;

import model.application.Pomodoro;

import static org.junit.Assert.*;

/**
 * @author diego on 15/12/2017.
 */

public class TestPomodoro{

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
