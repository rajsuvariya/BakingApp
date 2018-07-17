
package com.rajsuvariya.bakingapp.injection.module;

import android.app.Application;
import android.content.Context;

import com.rajsuvariya.bakingapp.BuildConfig;
import com.rajsuvariya.bakingapp.data.local.PreferencesHelper;
import com.rajsuvariya.bakingapp.data.remote.ApiHeader;
import com.rajsuvariya.bakingapp.data.remote.ApiHelper;
import com.rajsuvariya.bakingapp.data.remote.AppApiHelper;
import com.rajsuvariya.bakingapp.injection.ApiInfo;
import com.rajsuvariya.bakingapp.injection.ApplicationContext;
import com.rajsuvariya.bakingapp.injection.DatabaseInfo;
import com.rajsuvariya.bakingapp.injection.PreferenceInfo;
import com.rajsuvariya.bakingapp.utils.AppConstants;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by @rajsuvariya on 15/03/17.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.PASSWORD;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }


    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
                                                           PreferencesHelper preferencesHelper) {
        //return new ApiHeader.ProtectedApiHeader(
        //        apiKey,
        //        preferencesHelper.getCurrentUserId(),
        //        preferencesHelper.getAccessToken());

        return new ApiHeader.ProtectedApiHeader(
            apiKey,
            "",
            "");
    }


}
