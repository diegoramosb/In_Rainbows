package com.inrainbows.mvp.view;

import com.inrainbows.mvp.presenter.BasePresenter;

/**
 * @author diego on 2/06/2018.
 */
public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);

    void showError();

    void showLoading();

    void showEmpty();

}
