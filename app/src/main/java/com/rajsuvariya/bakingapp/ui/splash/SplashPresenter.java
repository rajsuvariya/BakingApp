package com.rajsuvariya.bakingapp.ui.splash;
import com.rajsuvariya.bakingapp.data.DataManager;
import com.rajsuvariya.bakingapp.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by @rajsuvariya on 15/03/17.
 */

public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V>
        implements SplashMvpPresenter<V> {

    @Inject
    public SplashPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }


}
