package com.rajsuvariya.bakingapp.widget;

import android.app.Application;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import com.rajsuvariya.bakingapp.R;
import com.rajsuvariya.bakingapp.data.DataManager;
import com.rajsuvariya.bakingapp.data.remote.model.Ingredient;
import com.rajsuvariya.bakingapp.data.remote.model.RecipeListResponseModel;
import com.rajsuvariya.bakingapp.injection.component.DaggerWidgetComponent;
import com.rajsuvariya.bakingapp.injection.module.ApplicationModule;
import com.rajsuvariya.bakingapp.ui.recipeList.RecipeListActivity;
import com.rajsuvariya.bakingapp.ui.stepsList.StepListActivity;

import javax.inject.Inject;

/**
 * Implementation of App Widget functionality.
 */
public class BakingAppWidgetProvider extends AppWidgetProvider {

    static final String TAG = BakingAppWidgetProvider.class.getSimpleName();

    @Inject
    DataManager dataManager;

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, RecipeListResponseModel lastSeenRecipeModel) {
        Log.d(TAG, "updateAppWidget");

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget_provider);

        if (lastSeenRecipeModel!=null) {
            Intent intent = new Intent(context, StepListActivity.class);
            intent.putExtra(StepListActivity.RECIPE_DETAILS, lastSeenRecipeModel);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.aw_container, pendingIntent);

            StringBuilder ingredientStringBuilder = new StringBuilder();
            for (Ingredient ingredient : lastSeenRecipeModel.getIngredients()) {
                ingredientStringBuilder.append(ingredient.getIngredient());
                ingredientStringBuilder.append(" (");
                ingredientStringBuilder.append(ingredient.getQuantity());
                ingredientStringBuilder.append(" ");
                ingredientStringBuilder.append(ingredient.getMeasure());
                ingredientStringBuilder.append(")\n");
            }

            views.setTextViewText(R.id.aw_title, lastSeenRecipeModel.getName());
            views.setTextViewText(R.id.aw_content, ingredientStringBuilder);
            views.setViewVisibility(R.id.aw_title, View.VISIBLE);
            views.setViewVisibility(R.id.aw_no_recipe_available, View.GONE);
            views.setViewVisibility(R.id.aw_content, View.VISIBLE);
        } else {
            Intent intent = new Intent(context, RecipeListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.aw_container, pendingIntent);

            views.setViewVisibility(R.id.aw_title, View.GONE);
            views.setViewVisibility(R.id.aw_no_recipe_available, View.VISIBLE);
            views.setViewVisibility(R.id.aw_content, View.GONE);
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        Log.d(TAG, "onUpdate");
        DaggerWidgetComponent.builder()
                .applicationModule(new ApplicationModule((Application) context.getApplicationContext()))
                .build()
                .inject(this);

        RecipeListResponseModel lastSeenRecipeModel = dataManager.getLatestSeenRecipe();

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, lastSeenRecipeModel);
        }
    }

    @Override
    public void onEnabled(Context context) {
        Log.d(TAG, "onEnabled");
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        Log.d(TAG, "onDisabled");
        // Enter relevant functionality for when the last widget is disabled
    }

}

