
package com.rajsuvariya.bakingapp.data.remote;

import com.rajsuvariya.bakingapp.data.remote.model.RecipeListResponseModel;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by @rajsuvariya on 15/03/17.
 */

public interface ApiHelper {

    ApiHeader getApiHeader();

    Observable<ArrayList<RecipeListResponseModel>> getRecipeList();
}
