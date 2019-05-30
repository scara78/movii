package com.mythio.movii.activity.Discover.activity.contract;

import com.mythio.movii.activity.Discover.fragment.BaseDiscoverFragment;
import com.mythio.movii.activity.Discover.fragment.contract.FragmentNavigation;

public class Presenter implements Contract.Presenter, FragmentNavigation.Presenter {

    private final Contract.View view;
    private final Contract.Model.MoviesModel moviesModel;
    private final Contract.Model.TvShowsModel tvShowsModel;

    public Presenter(Contract.View view) {
        this.view = view;
        moviesModel = new MoviesModel();
        tvShowsModel = new TvShowsModel();
    }

    @Override
    public void setFragment(BaseDiscoverFragment fragment) {
        view.showFragment(fragment);
    }

    @Override
    public void getData() {
        moviesModel.getMovies(view::sendToMoviesFragment);
        tvShowsModel.getTvShows(view::sendToTvShowsFragment);
    }
}