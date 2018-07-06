package com.inrainbows.mvp.model;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author diego on 14/12/2017.
 * Implementation based on: https://stackoverflow.com/questions/14393423/how-to-make-a-countdown-timer-in-java
 */

public class Pomodoro extends Thread {

    int interval;

    Timer timer;

    public void set(int pSeconds){
        interval = pSeconds;
    }

    public void run(){
        System.out.println("running");
        int period = 1000;

        timer = new Timer();

        timer.scheduleAtFixedRate(new task(),0, period);
    }

    /**
     * Decreases the time left by a second and stops the timer when the time is up.
     * @return Time left in the timer
     */
    private int setInterval(){
        if( interval == 1 )
            timer.cancel();
        return --interval;
    }

    private class task extends TimerTask{
        @Override
        public void run() {
            System.out.println(setInterval());
        }
    }
}
