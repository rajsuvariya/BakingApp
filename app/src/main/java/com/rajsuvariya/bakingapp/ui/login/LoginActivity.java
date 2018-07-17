package com.rajsuvariya.bakingapp.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.rajsuvariya.bakingapp.R;
import com.rajsuvariya.bakingapp.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by @rajsuvariya on 15/03/17.
 */

public class LoginActivity extends BaseActivity implements LoginMvpView {

    @Inject
    LoginMvpPresenter<LoginMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(LoginActivity.this);
    }

    @Override
    public void openMainActivity() {
        Intent intent = LoginActivity.getStartIntent(LoginActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

}
