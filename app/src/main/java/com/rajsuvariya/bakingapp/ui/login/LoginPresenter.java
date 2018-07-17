

package com.rajsuvariya.bakingapp.ui.login;

import com.rajsuvariya.bakingapp.data.DataManager;
import com.rajsuvariya.bakingapp.ui.base.BasePresenter;

import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by @rajsuvariya on 15/03/17.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V>
        implements LoginMvpPresenter<V> {

    private static final String TAG = "LoginPresenter";

    @Inject
    public LoginPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }




}
