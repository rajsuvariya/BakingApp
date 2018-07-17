
package com.rajsuvariya.bakingapp.data;

import com.google.gson.Gson;
import com.rajsuvariya.bakingapp.data.local.LocalDataHelper;
import com.rajsuvariya.bakingapp.data.local.PreferencesHelper;
import com.rajsuvariya.bakingapp.data.remote.ApiHeader;
import com.rajsuvariya.bakingapp.data.remote.ApiHelper;
import com.rajsuvariya.bakingapp.data.remote.AppApiHelper;
import com.rajsuvariya.bakingapp.data.remote.model.RecipeListResponseModel;
import com.rajsuvariya.bakingapp.utils.AppConstants;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by @rajsuvariya on 15/03/17.
 */

@Singleton
public class DataManager implements ApiHelper, LocalDataHelper {

    private static final String TAG = "DataManager";

    private final PreferencesHelper mPreferencesHelper;
    private final AppApiHelper mApiHelper;

    @Inject
    public DataManager( PreferencesHelper preferencesHelper,
                       AppApiHelper apiHelper) {
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }


    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public Observable<ArrayList<RecipeListResponseModel>> getRecipeList() {
        return mApiHelper.getRecipeList();
    }

    @Override
    public void saveLatestSeenRecipe(RecipeListResponseModel recipeListResponseModel) {
        mPreferencesHelper.putString(AppConstants.LAST_SEEN_RECIPE_DATA, new Gson().toJson(recipeListResponseModel));
    }

    @Override
    public RecipeListResponseModel getLatestSeenRecipe() {
        return new Gson().fromJson(mPreferencesHelper.getString(AppConstants.LAST_SEEN_RECIPE_DATA, ""), RecipeListResponseModel.class);
    }
}
