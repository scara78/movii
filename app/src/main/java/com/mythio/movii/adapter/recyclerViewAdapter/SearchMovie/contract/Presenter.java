package com.mythio.movii.adapter.recyclerViewAdapter.SearchMovie.contract;

import com.mythio.movii.model.movie.MovieTmdb;

import java.util.ArrayList;

public class Presenter implements Contract.Presenter {

    public final ArrayList<MovieTmdb> movies;

    public Presenter(ArrayList<MovieTmdb> movies) {
        this.movies = movies;
    }

    @Override
    public void onBindViewAtPosition(Contract.View view, int position) {
        view.show(movies.get(position));
    }

    @Override
    public int getCount() {
        return movies.size();
    }
}
