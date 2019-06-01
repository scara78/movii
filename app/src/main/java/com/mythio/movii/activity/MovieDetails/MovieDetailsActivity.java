package com.mythio.movii.activity.MovieDetails;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mythio.movii.R;
import com.mythio.movii.activity.MovieDetails.contract.Contract;
import com.mythio.movii.activity.MovieDetails.contract.Presenter;
import com.mythio.movii.adapter.recyclerViewAdapter.Cast.CastAdapter;
import com.mythio.movii.adapter.recyclerViewAdapter.Cast.CastPresenter;
import com.mythio.movii.adapter.recyclerViewAdapter.RecommendedMovies.RecommendedMoviesAdapter;
import com.mythio.movii.adapter.recyclerViewAdapter.RecommendedMovies.RecommendedMoviesPresenter;
import com.mythio.movii.model.movie.Movie;
import com.mythio.movii.util.ItemDecorator;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mythio.movii.util.Constant.IMAGE_BASE_URL;

public class MovieDetailsActivity extends AppCompatActivity implements Contract.View {

    @Nullable
    @BindView(R.id.img_view_poster)
    ImageView imgViewPoster;

    @Nullable
    @BindView(R.id.img_view_bg_grad)
    ImageView imgViewBgGrad;

    @Nullable
    @BindView(R.id.img_view_play)
    ImageButton imgViewPlay;

    @Nullable
    @BindView(R.id.txt_view_year)
    TextView txtViewYear;

    @Nullable
    @BindView(R.id.txt_view_genre)
    TextView txtViewGenre;

    @Nullable
    @BindView(R.id.txt_view_title_1)
    TextView txtViewTitle1;

    @Nullable
    @BindView(R.id.txt_view_title_2)
    TextView txtViewTitle2;

    @Nullable
    @BindView(R.id.txt_view_runtime)
    TextView txtViewRuntime;

    @Nullable
    @BindView(R.id.txt_view_overview)
    TextView txtViewOverview;

    @Nullable
    @BindView(R.id.rating_bar)
    RatingBar ratingBar;

    @Nullable
    @BindView(R.id.txt_view_rating)
    TextView txtViewRating;

    @Nullable
    @BindView(R.id.txt_view_vote_count)
    TextView txtViewVoteCount;

    @Nullable
    @BindView(R.id.recycler_view_cast)
    RecyclerView recyclerViewCast;

    @Nullable
    @BindView(R.id.recycler_view_recommended)
    RecyclerView recyclerViewRecommended;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ButterKnife.bind(this);

        String currentId = getIntent().getStringExtra("BUNDLED_EXTRA_MOVIE_ID");
        Contract.Presenter presenter = new Presenter(this);

        presenter.getDetails(currentId);
    }

    @Override
    public void showDetails(@NonNull Movie movie) {

        Target target = new Target() {
            @Override
            public void onBitmapLoaded(@NonNull Bitmap bitmap, Picasso.LoadedFrom from) {
                imgViewPoster.setImageBitmap(bitmap);
                Palette.from(bitmap).generate(palette -> {
                    assert palette != null;
                    if (palette.getDarkMutedSwatch() != null) {
                        imgViewBgGrad.setImageTintList(ColorStateList.valueOf(palette.getDarkMutedSwatch().getRgb()));
                        getWindow().getDecorView().setBackgroundColor(palette.getDarkMutedSwatch().getRgb());
                    } else if (palette.getDarkVibrantSwatch() != null) {
                        imgViewBgGrad.setImageTintList(ColorStateList.valueOf(palette.getDarkVibrantSwatch().getRgb()));
                        getWindow().getDecorView().setBackgroundColor(palette.getDarkVibrantSwatch().getRgb());
                    } else if (palette.getMutedSwatch() != null) {
                        imgViewBgGrad.setImageTintList(ColorStateList.valueOf(palette.getMutedSwatch().getRgb()));
                        getWindow().getDecorView().setBackgroundColor(palette.getMutedSwatch().getRgb());
                    }

                    if (palette.getLightMutedSwatch() != null) {
                        imgViewPlay.setImageTintList(ColorStateList.valueOf(palette.getLightMutedSwatch().getRgb()));
                    } else if (palette.getLightVibrantSwatch() != null) {
                        imgViewPlay.setImageTintList(ColorStateList.valueOf(palette.getLightVibrantSwatch().getRgb()));
                    }
                });
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        };

        Picasso.get()
                .load(IMAGE_BASE_URL + "original" + movie.getPosterPath())
                .into(target);

        imgViewPoster.setTag(target);

        if (movie.getTitle2().equals("")) {
            txtViewTitle1.setText(movie.getTitle1());
            txtViewTitle2.setVisibility(View.GONE);
        } else {
            txtViewTitle1.setText(movie.getTitle1());
            txtViewTitle2.setVisibility(View.VISIBLE);
            txtViewTitle2.setText(movie.getTitle2());
        }

        txtViewYear.setText(movie.getYear());
        txtViewGenre.setText(movie.getGenres());
        txtViewRuntime.setText(movie.getRuntime());
        txtViewOverview.setText(movie.getOverview());
        ratingBar.setRating(Float.valueOf(movie.getRating()) / 2);
        txtViewRating.setText(movie.getRating());
        txtViewVoteCount.setText(movie.getVotes());

        recyclerViewCast.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerViewCast.addItemDecoration(new ItemDecorator(32, 1));

        CastPresenter castPresenter = new CastPresenter(movie.getCasts());
        CastAdapter castAdapter = new CastAdapter(castPresenter, null);
        recyclerViewCast.setAdapter(castAdapter);

        recyclerViewRecommended.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerViewRecommended.addItemDecoration(new ItemDecorator(24, 1));

        RecommendedMoviesPresenter recommendedMoviesPresenter = new RecommendedMoviesPresenter(movie.getRecommendations());
        RecommendedMoviesAdapter recommendedMoviesAdapter = new RecommendedMoviesAdapter(recommendedMoviesPresenter, null);
        recyclerViewRecommended.setAdapter(recommendedMoviesAdapter);
    }
}