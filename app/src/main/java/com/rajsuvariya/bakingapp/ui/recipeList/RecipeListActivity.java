package com.rajsuvariya.bakingapp.ui.recipeList;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rajsuvariya.bakingapp.R;
import com.rajsuvariya.bakingapp.data.remote.model.RecipeListResponseModel;
import com.rajsuvariya.bakingapp.idlingResource.SimpleIdlingResource;
import com.rajsuvariya.bakingapp.ui.base.BaseActivity;
import com.rajsuvariya.bakingapp.ui.stepsList.StepListActivity;
import com.rajsuvariya.bakingapp.utils.ViewUtils;
import com.rajsuvariya.bakingapp.widget.BakingAppWidgetProvider;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeListActivity extends BaseActivity implements RecipeListMvpView, RecipeListAdapter.OnInteractionListener {

    @Inject
    RecipeListMvpPresenter<RecipeListMvpView> mPresenter;

    @BindView(R.id.rv_recipe_list)
    RecyclerView rvRecipeList;

    @VisibleForTesting
    @Nullable
    private IdlingResource simpleIdlingResource;

    @Nullable
    public IdlingResource getSimpleIdlingResource() {
        if (simpleIdlingResource == null){
            simpleIdlingResource = new SimpleIdlingResource();
        }
        return simpleIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        setTitle("Baking App");

        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        mPresenter.onAttach(this);

        getSimpleIdlingResource();
    }

    @Override
    public void populateRecyclerView(ArrayList<RecipeListResponseModel> recipeListResponseModel) {
        RecipeListAdapter mAdapter = new RecipeListAdapter(recipeListResponseModel, this);
        rvRecipeList.setAdapter(mAdapter);
        if (ViewUtils.isTablet(this)) {
            rvRecipeList.setLayoutManager(new GridLayoutManager(this, 3));
        } else {
            rvRecipeList.setLayoutManager(new LinearLayoutManager(this));
        }

        setIdlingResource(true);
    }

    @Override
    public void setIdlingResource(boolean idle) {
        if (simpleIdlingResource!=null) {
            ((SimpleIdlingResource) simpleIdlingResource).setIdleState(idle);
        }
    }

    @Override
    public void onRecipeSelected(RecipeListResponseModel recipeListResponseModel) {
        Intent intent = new Intent(this, StepListActivity.class);
        intent.putExtra(StepListActivity.RECIPE_DETAILS, recipeListResponseModel);
        startActivity(intent);
        mPresenter.saveLatestSeenRecipe(recipeListResponseModel);

        Intent widgetIntent = new Intent(this, BakingAppWidgetProvider.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        int[] ids = AppWidgetManager.getInstance(getApplication())
                .getAppWidgetIds(new ComponentName(getApplication(), BakingAppWidgetProvider.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);

        for (int appWidgetId : ids) {
            BakingAppWidgetProvider.updateAppWidget(this, AppWidgetManager.getInstance(this), appWidgetId, recipeListResponseModel);
        }
        sendBroadcast(intent);
    }
}
