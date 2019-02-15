package com.mythio.movii.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mythio.movii.model.Movie;
import com.mythio.movii.R;
import com.mythio.movii.model.Rounded;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.mythio.movii.constant.Constants.TMDB_IMAGE;
import static com.mythio.movii.model.Rounded.Corners.ALL;

public class SimilarMovieAdapter extends RecyclerView.Adapter<SimilarMovieAdapter.MovieHolder> {

    private Context mContext;
    private List<Movie> mMovieList;

    public SimilarMovieAdapter(Context mContext, List<Movie> mMovieList) {
        this.mContext = mContext;
        this.mMovieList = mMovieList;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie_similar, viewGroup, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieHolder, int i) {
        Movie movie = mMovieList.get(i);
        movieHolder.mTextViewTitle.setText(movie.getTitle1());

        String url = TMDB_IMAGE + "w500" + movie.getPoster_path();

        Transformation transformation = new Rounded(16, ALL);

        Picasso.get()
                .load(url)
                .fit()
                .transform(transformation)
                .placeholder(R.drawable.movie_placeholder)
                .into(movieHolder.mImageViewPoster);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder {

        private ImageView mImageViewPoster;
        private TextView mTextViewTitle;

        MovieHolder(@NonNull View itemView) {
            super(itemView);

            mImageViewPoster = itemView.findViewById(R.id.list_image_movie);
            mTextViewTitle = itemView.findViewById(R.id.list_text_view_title);
        }
    }

    public static class ItemDecorator extends RecyclerView.ItemDecoration {

        private final int space;

        public ItemDecorator(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                                   @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            outRect.right = space;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.left = space;
            }
        }
    }
}