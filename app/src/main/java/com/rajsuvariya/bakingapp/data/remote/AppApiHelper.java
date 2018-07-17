
package com.rajsuvariya.bakingapp.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rajsuvariya.bakingapp.BuildConfig;
import com.rajsuvariya.bakingapp.data.remote.model.RecipeListResponseModel;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by @rajsuvariya on 15/03/17.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;
    private Long TIMEOUT = (long) (3 * 60);
    private Retrofit mRetrofit;
    private ApiEndpoints mApiServiceEndPoints;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;

        setUpClient();
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Observable<ArrayList<RecipeListResponseModel>> getRecipeList() {
        return getApiService().getRecipeList();
    }

    private void setUpClient() {
        Gson gson = new GsonBuilder().create();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLogger = new HttpLoggingInterceptor();
        httpLogger.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(httpLogger);
        builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(TIMEOUT, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();

        mApiServiceEndPoints = getApiService();
    }

    private ApiEndpoints getApiService() {
        if (mApiServiceEndPoints == null) {
            mApiServiceEndPoints = mRetrofit.create(ApiEndpoints.class);
        }
        return mApiServiceEndPoints;
    }
}

