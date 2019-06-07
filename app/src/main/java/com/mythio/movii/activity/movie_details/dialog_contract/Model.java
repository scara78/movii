package com.mythio.movii.activity.movie_details.dialog_contract;

import com.mythio.movii.model.cast.CastMovies;
import com.mythio.movii.network.EndPointTmdb;
import com.mythio.movii.network.RetrofitBuilder;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.mythio.movii.util.Constant.API_KEY_TMDB;

public class Model implements Contract.Model {

    @Override
    public void getMovies(int id, OnMoviesReceivedListener listener) {

        getMovieTmdbObservable(id)
                .subscribe(new DisposableSingleObserver<CastMovies>() {
                    @Override
                    public void onSuccess(CastMovies castMovies) {
                        listener.onFinished(castMovies.getCast());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private Single<CastMovies> getMovieTmdbObservable(int id) {
        return RetrofitBuilder
                .getClientTmdb()
                .create(EndPointTmdb.class)
                .getCastMovies(id, API_KEY_TMDB)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
