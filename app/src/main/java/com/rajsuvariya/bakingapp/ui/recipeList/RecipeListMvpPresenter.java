package com.rajsuvariya.bakingapp.ui.recipeList;

import com.rajsuvariya.bakingapp.data.remote.model.RecipeListResponseModel;
import com.rajsuvariya.bakingapp.ui.base.MvpPresenter;

/**
 * Created by @raj on 06/07/18.
 */
public interface RecipeListMvpPresenter<T extends RecipeListMvpView> extends MvpPresenter<T> {
    void saveLatestSeenRecipe(RecipeListResponseModel recipeListResponseModel);

    void getRecipeList();
}
