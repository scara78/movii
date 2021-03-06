package com.mythio.movii.adapter.searchmovie;

import androidx.annotation.NonNull;

import com.mythio.movii.adapter.Contract;
import com.mythio.movii.model.movie.Movie;

import java.util.ArrayList;

public class SearchMoviePresenter implements Contract.Presenter<Movie> {
    private final ArrayList<Movie> movies;

    public SearchMoviePresenter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public void onBindViewAtPosition(@NonNull Contract.View<Movie> view, int position) {
        view.show(movies.get(position));
    }

    @Override
    public int getCount() {
        return movies.size();
    }
}
