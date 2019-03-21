package com.mythio.movii.contract.MovieFragment;

import android.util.Log;

import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.model.movie.MovieOmdb;
import com.mythio.movii.model.movie.MovieResponse;
import com.mythio.movii.model.movie.MovieTmdb;
import com.mythio.movii.network.ApiClientOmdb;
import com.mythio.movii.network.ApiClientTmdb;
import com.mythio.movii.network.ApiEndOmdb;
import com.mythio.movii.network.ApiEndTmdb;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model implements Contract.Model {

    private OnFinishedListener onFinishedListener;
    private ApiEndTmdb apiServiceTmdb = ApiClientTmdb.getClient().create(ApiEndTmdb.class);
    private ApiEndOmdb apiServiceOmdb = ApiClientOmdb.getClient().create(ApiEndOmdb.class);
    private List<MovieTmdb> movieTmdbList = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();

    @Override
    public void getData(final OnFinishedListener onFinishedListener) {

        this.onFinishedListener = onFinishedListener;

        Call<MovieResponse> call = apiServiceTmdb.getPopularMovies("2e901364c3d103dcb00ced520e9bca3c");
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<MovieTmdb> movieTmdbList = response.body().getResults();
                getTMDB(movieTmdbList);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

    private void getTMDB(final List<MovieTmdb> movies) {

        Call<MovieTmdb> call;
        final int[] pos = {0};
        for (final MovieTmdb movieTmdb : movies) {

            call = apiServiceTmdb.getTmdbDetail(movieTmdb.getId(), "2e901364c3d103dcb00ced520e9bca3c", "");
            call.enqueue(new Callback<MovieTmdb>() {
                @Override
                public void onResponse(Call<MovieTmdb> call, Response<MovieTmdb> response) {

                    movieTmdbList.add(response.body());
                    ++pos[0];
                    if (pos[0] == 20) {
                        getOMDB();
                    }
                }

                @Override
                public void onFailure(Call<MovieTmdb> call, Throwable t) {

                }
            });
        }
    }

    private void getOMDB() {

        final int[] pos = {0};
        for (final MovieTmdb movieTmdb : movieTmdbList) {

            Call<MovieOmdb> call = apiServiceOmdb.getOmdbDetail("e403207b", movieTmdb.getImdb());
            call.enqueue(new Callback<MovieOmdb>() {
                @Override
                public void onResponse(Call<MovieOmdb> call, Response<MovieOmdb> response) {

                    Movie movie = new Movie(
                            movieTmdb.getPosterPath(),
                            movieTmdb.getOverview(),
                            String.valueOf(movieTmdb.getId()),
                            movieTmdb.getTitle(),
                            movieTmdb.getBackdropPath(),
                            (response.body().getImdbVotes().equals("N/A") ? String.valueOf(movieTmdb.getVoteCount()) : response.body().getImdbVotes()),
                            (response.body().getImdbRating().equals("N/A") ? String.valueOf(movieTmdb.getVoteAverage()) : response.body().getImdbRating()),
                            movieTmdb.getImdb(),
                            response.body().getGenre(),
                            response.body().getYear(),
                            response.body().getRuntime()
                    );

                    movies.add(movie);
                    ++pos[0];

                    if (pos[0] == 20) {
                        onFinishedListener.onFinished(movies);
                    }
                }

                @Override
                public void onFailure(Call<MovieOmdb> call, Throwable t) {
                    Log.v("TAG_TAG", t.getLocalizedMessage());
                }
            });
        }
    }
}