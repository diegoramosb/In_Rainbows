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
    }

    /**
     * Creates a new timer and runs it
     */
    @Override
    public void run(){
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        timer.scheduleAtFixedRate(new Task(), delay, period );
    }

    /**
     * Decreases the time left by a second and stops the timer when the time is up.
     * @return Time left in the timer
     */
    public int setInterval(){
        if( interval == 1 )
            timer.cancel();
        return --interval;
    }

    /**
     * Task class for the timer
     */
    private class Task extends TimerTask{

        /**
         * Calls the setInterval method, which decreases the time interval.
         * It also prints the time left.
         */
        @Override
        public void run() {
            System.out.println( setInterval() );
        }
    }
}
