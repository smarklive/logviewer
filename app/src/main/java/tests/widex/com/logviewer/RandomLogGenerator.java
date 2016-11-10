package tests.widex.com.logviewer;

import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by mark on 08-11-2016.
 */

public class RandomLogGenerator {

    public static final int HAPPY = 1;

    public static final int NEUTRAL = 2;

    public static final int SAD = 3;

    Random mRandom;
    LogListener mLogListener;
    boolean mIsLogging;

    public static interface LogListener {
        void receiveLog(int logValue);
    }


    public RandomLogGenerator() {
        mRandom = new Random();
    }

    public void run() {
        mIsLogging = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mIsLogging) {

                    addLog();

                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void stop() {
        mIsLogging = false;
    }

    public void setLogListener(LogListener listener) {
        mLogListener = listener;
    }

    public void removeLogListener() {
        mLogListener = null;
    }

    private void addLog() {
        int logValue = mRandom.nextInt(3) + 1;
        if (mLogListener != null)
            mLogListener.receiveLog(logValue);
    }
}
