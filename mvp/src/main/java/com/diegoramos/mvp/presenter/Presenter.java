package com.diegoramos.mvp.presenter;


import com.diegoramos.mvp.view.View;

/**
 * @author diego on 2/06/2018.
 */
public interface Presenter<T extends View> {

    void onCreate();

    void onStart();

    void onStop();

    void onPause();

    void attachView(T view);
}
