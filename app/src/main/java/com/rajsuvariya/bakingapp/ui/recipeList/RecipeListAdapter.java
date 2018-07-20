package com.rajsuvariya.bakingapp.ui.recipeList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rajsuvariya.bakingapp.R;
import com.rajsuvariya.bakingapp.data.remote.model.RecipeListResponseModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @raj on 06/07/18.
 */
public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder> {

    private ArrayList<RecipeListResponseModel> mList;
    private OnInteractionListener mListener;

    public RecipeListAdapter(ArrayList<RecipeListResponseModel> recipeList, OnInteractionListener listener) {
        this.mList = recipeList;
        this.mListener = listener;
    }

    @Override
    public RecipeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe_list, parent, false);
        return new RecipeListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecipeListViewHolder holder, int position) {
        Context mContext = holder.itemView.getContext();
        holder.mTvRecipeName.setText(mList.get(position).getName());
        holder.mTvServing.setText(mContext.getResources().getString(R.string.serving, mList.get(position).getServings()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onRecipeSelected(mList.get(holder.getAdapterPosition()));
                }
            }
        });
        if (!TextUtils.isEmpty(mList.get(position).getImage())) {
            Glide.with(mContext).load(mList.get(position).getImage()).placeholder(R.drawable.app_icon).into(holder.mIvRecipeImage);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    interface OnInteractionListener {

        void onRecipeSelected(RecipeListResponseModel recipeListResponseModel);
    }

    public class RecipeListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_recipe_name)
        TextView mTvRecipeName;

        @BindView(R.id.tv_serving)
        TextView mTvServing;

        @BindView(R.id.iv_recipe_image)
        ImageView mIvRecipeImage;

        public RecipeListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
