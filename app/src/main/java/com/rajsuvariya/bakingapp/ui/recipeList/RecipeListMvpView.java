package com.rajsuvariya.bakingapp.ui.recipeList;

import com.rajsuvariya.bakingapp.data.remote.model.RecipeListResponseModel;
import com.rajsuvariya.bakingapp.ui.base.MvpView;

import java.util.ArrayList;

/**
 * Created by @raj on 06/07/18.
 */
public interface RecipeListMvpView extends MvpView {
    void populateRecyclerView(ArrayList<RecipeListResponseModel> recipeListResponseModel);

    void setIdlingResource(boolean idle);
}
