package com.rajsuvariya.bakingapp.ui.stepsList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rajsuvariya.bakingapp.R;

import com.rajsuvariya.bakingapp.data.remote.model.Ingredient;
import com.rajsuvariya.bakingapp.data.remote.model.RecipeListResponseModel;
import com.rajsuvariya.bakingapp.data.remote.model.Step;
import com.rajsuvariya.bakingapp.ui.recipeList.RecipeListActivity;

import java.util.List;

/**
 * An activity representing a list of Steps. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link StepDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class StepListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    public static final String RECIPE_DETAILS = "_Recipe_Details";
    private RecipeListResponseModel mRecipeListResponseModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mRecipeListResponseModel = getIntent().getParcelableExtra(RECIPE_DETAILS);
        setTitle(mRecipeListResponseModel.getName());

        if (findViewById(R.id.step_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.step_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);


    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, mRecipeListResponseModel, mTwoPane));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private final StepListActivity mParentActivity;
        private final List<Step> mStepsList;
        private final List<Ingredient> mIngredientList;
        private final boolean mTwoPane;
        private static final int TYPE_INGREDIENTS = 1;
        private static final int TYPE_STEPS = 2;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Step item = (Step) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putParcelable(StepDetailFragment.STEP_DETAILS, item);
                    StepDetailFragment fragment = new StepDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.step_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, StepDetailActivity.class);
                    intent.putExtra(StepDetailFragment.STEP_DETAILS, item);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(StepListActivity parent,
                                      RecipeListResponseModel items,
                                      boolean twoPane) {
            mStepsList = items.getSteps();
            mIngredientList = items.getIngredients();
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_INGREDIENTS;
            } else {
                return TYPE_STEPS;
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_STEPS) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_step_list, parent, false);
                return new StepsViewHolder(view);
            }
            else if (viewType == TYPE_INGREDIENTS) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_ingredient_list, parent, false);
                return new IngredientsViewHolder(view);

            } else {
                throw new RuntimeException("SimpleItemRecyclerViewAdapter: Unsupported View Type");
            }
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof StepsViewHolder) {
                int stepPosition = position - 1;
                ((StepsViewHolder)holder).mIdView.setText(String.valueOf(mStepsList.get(stepPosition).getId()));
                ((StepsViewHolder)holder).mContentView.setText(mStepsList.get(stepPosition).getShortDescription());

                holder.itemView.setTag(mStepsList.get(stepPosition));
                holder.itemView.setOnClickListener(mOnClickListener);
            }
            if (holder instanceof IngredientsViewHolder) {
                StringBuilder ingredientStringBuilder = new StringBuilder();
                for (Ingredient ingredient : mIngredientList) {
                    ingredientStringBuilder.append(ingredient.getIngredient());
                    ingredientStringBuilder.append(" (");
                    ingredientStringBuilder.append(ingredient.getQuantity());
                    ingredientStringBuilder.append(" ");
                    ingredientStringBuilder.append(ingredient.getMeasure());
                    ingredientStringBuilder.append(")\n");
                }
                ((IngredientsViewHolder)holder).mContentView.setText(ingredientStringBuilder);
            }
        }

        @Override
        public int getItemCount() {
            return mStepsList.size() + 1;
        }

        class StepsViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;

            StepsViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.ingredient_label);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }

        class IngredientsViewHolder extends RecyclerView.ViewHolder {
            final TextView mContentView;

            IngredientsViewHolder(View view) {
                super(view);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }


}
