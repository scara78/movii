package com.mythio.movii.contract.activity.searchTvShowActivity;

import android.util.Log;

import com.mythio.movii.model.movie.MovieTmdb;

import java.util.ArrayList;

public class SearchTvShowPresenter implements SearchTvShowContract.Presenter, SearchTvShowContract.Model.OnMoviesSearchListener {

    private SearchTvShowContract.View view;
    private SearchTvShowContract.Model model;

    public SearchTvShowPresenter(SearchTvShowContract.View view) {
        this.view = view;
        model = new SearchTvShowModel();
    }

    @Override
    public void onNoSearchParam() {
        view.showPlate();
    }

    @Override
    public void onSearchParam(String string) {
        model.getSearchResults(this, string);
        view.hidePlate();
    }

    @Override
    public void onFinished(ArrayList<MovieTmdb> movies) {
        view.showRes(movies);
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.v("TAG_TAG", throwable.getLocalizedMessage());
    }
}
