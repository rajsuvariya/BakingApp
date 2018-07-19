package com.rajsuvariya.bakingapp;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.TextView;

import com.rajsuvariya.bakingapp.data.remote.model.Ingredient;
import com.rajsuvariya.bakingapp.data.remote.model.RecipeListResponseModel;
import com.rajsuvariya.bakingapp.data.remote.model.Step;
import com.rajsuvariya.bakingapp.ui.recipeList.RecipeListAdapter;
import com.rajsuvariya.bakingapp.ui.stepsList.StepListActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by @raj on 08/07/18.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class StepListActivityTest {

    private RecipeListResponseModel recipeModel;

    @Rule
    public ActivityTestRule<StepListActivity> mActivityRule = new ActivityTestRule<>(
            StepListActivity.class, true, false);


    @Before
    public void setUp() {
        recipeModel = new RecipeListResponseModel();
        recipeModel.setName("Yellow Cake");
        recipeModel.setId(1);
        recipeModel.setServings(5);
        ArrayList<Ingredient> ingredientArrayList = new ArrayList<>();
        ingredientArrayList.add(new Ingredient(0.5f, "CUP", "Ingredient 1"));
        ingredientArrayList.add(new Ingredient(0.5f, "CUP", "Ingredient 2"));
        ingredientArrayList.add(new Ingredient(0.5f, "CUP", "Ingredient 3"));
        recipeModel.setIngredients(ingredientArrayList);
        ArrayList<Step> stepArrayList = new ArrayList<>();
        stepArrayList.add(new Step(0, "Short Description 1","Long Description 1", "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4", ""));
        stepArrayList.add(new Step(0, "Short Description 2","Long Description 2", "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4", ""));
        stepArrayList.add(new Step(0, "Short Description 3","Long Description 3", "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4", ""));
        stepArrayList.add(new Step(0, "Short Description 4","Long Description 4", "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4", ""));
        recipeModel.setSteps(stepArrayList);
    }

    @Test
    public void clickRecyclerViewItem_opensStepDetailsActivity(){
        Intent intent = new Intent();
        intent.putExtra(StepListActivity.RECIPE_DETAILS, recipeModel);
        mActivityRule.launchActivity(intent);

        onData(is(instanceOf(Step.class)));
        onView(withId(R.id.step_list))
                .perform(RecyclerViewActions.<RecipeListAdapter.RecipeListViewHolder>scrollToPosition(0))
                .check(matches(hasDescendant(withText("Ingredients"))));

        onView(withId(R.id.step_list))
                .perform(RecyclerViewActions.<RecipeListAdapter.RecipeListViewHolder>scrollToPosition(4))
                .check(matches(hasDescendant(withText("Short Description 3"))));

        onView(withId(R.id.step_list)).perform(
                RecyclerViewActions.actionOnItemAtPosition(3, click()));

        onView(allOf(instanceOf(TextView.class),
                withParent(withResourceName("action_bar"))))
                .check(matches(withText("Short Description 3")));
    }

}