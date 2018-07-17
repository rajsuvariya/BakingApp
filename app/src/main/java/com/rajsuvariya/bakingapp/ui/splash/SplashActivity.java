package com.rajsuvariya.bakingapp.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.rajsuvariya.bakingapp.R;
import com.rajsuvariya.bakingapp.ui.recipeList.RecipeListActivity;
import com.rajsuvariya.bakingapp.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity implements SplashMvpView{

    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(SplashActivity.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, RecipeListActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void openLoginActivity() {

    }

    @Override
    public void openMainActivity() {

    }

}
