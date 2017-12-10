//package com.example.diego.inrainbows;
//
///**
// * Created by diego on 17/07/2017.
// * https://android-developers.googleblog.com/2007/11/stitch-in-time.html
// */
//
//import android.os.Handler;
//import android.view.View.OnClickListener;
//import android.view.View;
//
//public class Pomodoro {
//
//    private Handler mHandler = new Handler();
//
//    OnClickListener mStartListener = new OnClickListener() {
//        public void onClick(View v) {
//            if (mStartTime == 0L) {
//                mStartTime = System.currentTimeMillis();
//                mHandler.removeCallbacks(mUpdateTimeTask);
//                mHandler.postDelayed(mUpdateTimeTask, 100);
//            }
//        }
//    };
//    OnClickListener mStopListener = new OnClickListener() {
//        public void onClick(View v) {
//            mHandler.removeCallbacks(mUpdateTimeTask);
//        }
//    };
//    private Runnable mUpdateTimeTask = new Runnable() {
//        public void run() {
//            final long start = mStartTime;
//            long millis = SystemClock.uptimeMillis() - start;
//            int seconds = (int) (millis / 1000);
//            int minutes = seconds / 60;
//            seconds     = seconds % 60;
//
//            if (seconds < 10) {
//                mTimeLabel.setText("" + minutes + ":0" + seconds);
//            } else {
//                mTimeLabel.setText("" + minutes + ":" + seconds);
//            }
//
//            mHandler.postAtTime(this,
//                    start + (((minutes * 60) + seconds + 1) * 1000));
//        }
//    };
//}
