package com.rajsuvariya.bakingapp.injection.module;

import android.app.Activity;
import android.content.Context;


import com.rajsuvariya.bakingapp.injection.ActivityContext;
import com.rajsuvariya.bakingapp.injection.PerActivity;
import com.rajsuvariya.bakingapp.ui.recipeList.RecipeListMvpPresenter;
import com.rajsuvariya.bakingapp.ui.recipeList.RecipeListMvpView;
import com.rajsuvariya.bakingapp.ui.recipeList.RecipeListPresenter;
import com.rajsuvariya.bakingapp.ui.splash.SplashMvpPresenter;
import com.rajsuvariya.bakingapp.ui.splash.SplashMvpView;
import com.rajsuvariya.bakingapp.ui.splash.SplashPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by @rajsuvariya on 15/03/17.
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    RecipeListMvpPresenter<RecipeListMvpView> provideRecipeListPresenter(RecipeListPresenter<RecipeListMvpView> presenter) {
        return presenter;
    }
}
