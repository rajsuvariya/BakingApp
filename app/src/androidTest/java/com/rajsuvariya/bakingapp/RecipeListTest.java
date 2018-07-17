package com.rajsuvariya.bakingapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.rajsuvariya.bakingapp.ui.recipeList.RecipeListActivity;
import com.rajsuvariya.bakingapp.ui.recipeList.RecipeListAdapter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by @raj on 08/07/18.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecipeListTest {

    private String mStringToBetyped;

    @Rule
    public ActivityTestRule<RecipeListActivity> mActivityRule = new ActivityTestRule<>(
            RecipeListActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        mStringToBetyped = "Espresso";
    }

    @Test
    public void changeText_sameActivity() throws InterruptedException {
        // Type text and then press the button.
        Thread.sleep(5000); // For API response
        onView(withText("Nutella Pie")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.rv_recipe_list))
                .perform(RecyclerViewActions.<RecipeListAdapter.RecipeListViewHolder>scrollToPosition(0))
                .perform(click());
        onView(withText("Ingredients")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.step_list))
                .perform(RecyclerViewActions.<RecipeListAdapter.RecipeListViewHolder>scrollToPosition(0))
                .perform(click());
    }
}