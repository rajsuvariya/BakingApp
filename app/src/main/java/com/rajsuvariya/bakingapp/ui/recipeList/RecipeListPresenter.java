package com.rajsuvariya.bakingapp.ui.recipeList;

import android.util.Log;

import com.rajsuvariya.bakingapp.data.DataManager;
import com.rajsuvariya.bakingapp.data.remote.model.RecipeListResponseModel;
import com.rajsuvariya.bakingapp.ui.base.BasePresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by @raj on 06/07/18.
 */
public class RecipeListPresenter<T extends RecipeListMvpView> extends BasePresenter<T> implements RecipeListMvpPresenter<T> {

    @Inject
    public RecipeListPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getCompositeDisposable().dispose();
    }

    @Override
    public void onAttach(T mvpView) {
        super.onAttach(mvpView);
        getRecipeList();
    }

    private void getRecipeList() {
        getMvpView().setIdlingResource(false);
        getCompositeDisposable().add(
                getDataManager().getRecipeList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ArrayList<RecipeListResponseModel>>() {
                            @Override
                            public void accept(ArrayList<RecipeListResponseModel> recipeListResponseModel) throws Exception {
                                getMvpView().populateRecyclerView(recipeListResponseModel);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getMvpView().setIdlingResource(true);
                            }
                        })
        );
    }

    @Override
    public void saveLatestSeenRecipe(RecipeListResponseModel recipeListResponseModel) {
        getDataManager().saveLatestSeenRecipe(recipeListResponseModel);
    }
}
