package com.rajsuvariya.bakingapp;

import android.support.annotation.NonNull;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.TextView;

import com.rajsuvariya.bakingapp.data.remote.model.RecipeListResponseModel;
import com.rajsuvariya.bakingapp.ui.recipeList.RecipeListActivity;
import com.rajsuvariya.bakingapp.ui.recipeList.RecipeListAdapter;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by @raj on 08/07/18.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecipeListActivityTest {

    private IdlingResource mIdlingResource;

    @Rule
    public ActivityTestRule<RecipeListActivity> mActivityRule = new ActivityTestRule<>(
            RecipeListActivity.class);

    @Before
    public void registerIdlingResource() {
        mIdlingResource = mActivityRule.getActivity().getSimpleIdlingResource();
        if (mIdlingResource != null) {
            Espresso.registerIdlingResources(mIdlingResource);
        }
    }

    @Test
    public void clickRecyclerViewItem_opensStepListActivity(){
        onData(is(instanceOf(RecipeListResponseModel.class)));
        onView(withId(R.id.rv_recipe_list))
                .perform(RecyclerViewActions.<RecipeListAdapter.RecipeListViewHolder>scrollToPosition(0))
                .check(matches(hasDescendant(withText("Nutella Pie"))));

        onView(withId(R.id.rv_recipe_list)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(allOf(instanceOf(TextView.class),
                withParent(withResourceName("action_bar"))))
                .check(matches(withText("Nutella Pie")));
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

}