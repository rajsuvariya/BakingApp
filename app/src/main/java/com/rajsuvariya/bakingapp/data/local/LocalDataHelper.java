package com.rajsuvariya.bakingapp.data.local;

import com.rajsuvariya.bakingapp.data.remote.model.RecipeListResponseModel; /**
 * Created by @raj on 17/07/18.
 */
public interface LocalDataHelper {
    void saveLatestSeenRecipe(RecipeListResponseModel recipeListResponseModel);

    RecipeListResponseModel getLatestSeenRecipe();
}
