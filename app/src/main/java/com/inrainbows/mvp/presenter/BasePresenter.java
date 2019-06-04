package com.inrainbows.mvp.presenter;

import com.inrainbows.mvp.view.BaseView;

/**
 * Interface for all the presenters
 * @author diego on 2/06/2018.
 */
public interface BasePresenter<T extends BaseView> {

    void onCreate();

    void onStart();

    void onStop();

    void onPause();

//    void attachView(T view);
}
