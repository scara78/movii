package com.mythio.movii.activity.SearchTvShow.contract;

public class Presenter implements Contract.Presenter {

    private final Contract.View view;
    private final Contract.Model model;

    public Presenter(Contract.View view) {
        this.view = view;
        model = new Model();
    }

    @Override
    public void onNoSearchParam() {
        view.showPlate();
    }

    @Override
    public void onSearchParam(String string) {
        model.getSearchResults(view::showRes, string);
        view.hidePlate();
    }
}