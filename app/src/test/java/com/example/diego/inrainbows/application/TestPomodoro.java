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
        pomodoro = new Pomodoro(5);
    }

    @Test
    public void testPomodoro(){
        setup1();
        pomodoro.start();
    }
}
