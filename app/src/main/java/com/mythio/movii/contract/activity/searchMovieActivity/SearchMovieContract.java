package com.mythio.movii.contract.activity.searchMovieActivity;

import com.mythio.movii.model.movie.MovieTmdb;

import java.util.ArrayList;

public interface SearchMovieContract {

    interface View {

        void showPlate();

        void hidePlate();

        void showRes(ArrayList<MovieTmdb> movies);
    }

    interface Presenter {

        void onNoSearchParam();

        void onSearchParam(String string);
    }

    interface Model {

        void getSearchResults(OnMoviesSearchListener listener, String query);

        interface OnMoviesSearchListener {

            void onFinished(ArrayList<MovieTmdb> movies);

            void onFailure(Throwable throwable);
        }
    }
}