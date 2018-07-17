
package com.rajsuvariya.bakingapp.data.remote;


import com.rajsuvariya.bakingapp.data.remote.model.RecipeListResponseModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by @rajsuvariya on 15/03/17.
 */

public interface ApiEndpoints {

    @GET("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json")
    Observable<ArrayList<RecipeListResponseModel>> getRecipeList();
}
