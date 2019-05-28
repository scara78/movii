package com.mythio.movii.activity.SearchMovie.contract;

public class SearchMoviePresenter implements SearchMovieContract.Presenter {

    private SearchMovieContract.View view;
    private SearchMovieContract.Model model;

    public SearchMoviePresenter(SearchMovieContract.View view) {
        this.view = view;
        model = new SearchMovieModel();
    }

    @Override
    public void onNoSearchParam() {
        view.showPlate();
    }

    @Override
    public void onSearchParam(String string) {
        model.getSearchResults(movies -> view.showRes(movies), string);
        view.hidePlate();
    }
}
