package model.application;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author diego on 14/12/2017.
 * Implementation based on: https://stackoverflow.com/questions/14393423/how-to-make-a-countdown-timer-in-java
 */

public class Pomodoro extends Thread {

    /**
     * Timer
     */
    private Timer timer;

    /**
     * Duration of timer
     */
    private int interval;

    /**
     * Creates a new pomodoro that lasts pInterval seconds
     * @param pInterval Duration of pomodoro in seconds
     */
    public Pomodoro( int pInterval ){
        interval = pInterval;
        timer = new Timer(true);
    }

    /**
     * Creates a new timer and runs it
     */
    @Override
    public void run(){
        System.out.println("running");
        long period = 1;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("current: " + setInterval());
            }
        }, 0, period*1000);
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
}
