package com.inrainbows.mvp.view;

import com.inrainbows.InRainbowsApp;
import com.inrainbows.mvp.presenter.BasePresenter;
import com.inrainbows.persistence.AppDatabase;

/**
 * Interface for all views to implement
 * @author diego on 2/06/2018.
 */
public interface BaseView<T extends BasePresenter> {

    /**
     * Returns the application database
     * @return application database
     */
    AppDatabase getDb();

    /**
     * Binds the presenter to the view
     * @param presenter presenter that the view will use
     */
    void setPresenter(T presenter);

    /**
     * Initializes the UI components
     */
    void setUIComponents();

    /**
     * Shows an error message with custom title and body
     * @param errId resource id of message title
     * @param errMsgId resource id of message body
     */
    void showErrorDialog(int errId, int errMsgId);

    /**
     * Shows a loading screen
     */
    void showLoading();

    /**
     * Shows an empty screen
     */
    void showEmpty();

}
