package com.mythio.movii.contract.fragment.moviesFragment;

import com.mythio.movii.model.movie.MovieTmdb;

import java.util.ArrayList;

public class MoviesPresenter implements MoviesContract.Presenter {

    private MoviesContract.View view;

    public MoviesPresenter(MoviesContract.View view) {
        this.view = view;
    }

    @Override
    public void initViews() {
        view.initViewPager();
    }

    @Override
    public void setDataToViewPager(ArrayList<MovieTmdb> movies) {
        view.showSlideShow(movies);
    }
}