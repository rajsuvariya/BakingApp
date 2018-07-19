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
import com.rajsuvariya.bakingapp.ui.stepsList.StepDetailActivity;
import com.rajsuvariya.bakingapp.ui.stepsList.StepDetailFragment;
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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
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
public class StepDetailsActivityTest {

    private Step step1;
    private Step step2;

    @Rule
    public ActivityTestRule<StepDetailActivity> mActivityRule = new ActivityTestRule<>(
            StepDetailActivity.class, true, false);


    @Before
    public void setUp() {
        step1 = new Step(0, "Short Description 1","Long Description 1", "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4", "");
        step2 = new Step(0, "Short Description 2","Long Description 2", "", "https://hips.hearstapps.com/del.h-cdn.co/assets/16/32/1470773544-delish-nutella-cool-whip-pie-1.jpg");
    }

    @Test
    public void videoStep_UITest(){
        Intent intent = new Intent();
        intent.putExtra(StepDetailFragment.STEP_DETAILS, step1);
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.epv_player_view))
                .check(matches(isDisplayed()));

        onView(withId(R.id.tv_long_description))
                .check(matches(withText("Long Description 1")));
    }

    @Test
    public void imageStep_UITest(){
        Intent intent = new Intent();
        intent.putExtra(StepDetailFragment.STEP_DETAILS, step2);
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.iv_step_thumbnail))
                .check(matches(isDisplayed()));

        onView(withId(R.id.tv_long_description))
                .check(matches(withText("Long Description 2")));
    }

}