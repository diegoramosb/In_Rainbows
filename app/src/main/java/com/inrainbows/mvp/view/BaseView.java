package com.inrainbows.mvp.view;

import com.inrainbows.InRainbowsApp;
import com.inrainbows.mvp.presenter.BasePresenter;
import com.inrainbows.persistence.AppDatabase;

/**
 * @author diego on 2/06/2018.
 */
public interface BaseView<T extends BasePresenter> {

    AppDatabase getDb();

    void setPresenter(T presenter);

    void showErrorDialog(int errId, int errMsgId);

    void showLoading();

    void showEmpty();

}
