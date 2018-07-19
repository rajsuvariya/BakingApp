package com.rajsuvariya.bakingapp;

import android.app.Application;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.rajsuvariya.bakingapp.data.DataManager;
import com.rajsuvariya.bakingapp.injection.component.ApplicationComponent;
import com.rajsuvariya.bakingapp.injection.component.DaggerApplicationComponent;
import com.rajsuvariya.bakingapp.injection.module.ApplicationModule;

import javax.inject.Inject;

/**
 * Created by @rajsuvariya on 15/03/17.
 */

public class IfaApp extends Application {
  @Inject
  DataManager mDataManager;

  private ApplicationComponent mApplicationComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    mApplicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this)).build();

    mApplicationComponent.inject(this);

    AndroidNetworking.initialize(getApplicationContext());
    if (BuildConfig.DEBUG) {
      AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
    }
  }

  public ApplicationComponent getComponent() {
    return mApplicationComponent;
  }


  // Needed to replace the component with a test specific one
  public void setComponent(ApplicationComponent applicationComponent) {
    mApplicationComponent = applicationComponent;
  }
}
