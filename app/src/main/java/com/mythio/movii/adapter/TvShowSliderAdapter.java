package com.mythio.movii.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mythio.movii.R;
import com.mythio.movii.contract.fragment.moviesFragment.OnItemClickListener;
import com.mythio.movii.model.tvShow.TvShowTmdb;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.mythio.movii.util.Constant.IMAGE_BASE_URL;

public class TvShowSliderAdapter extends PagerAdapter {

    private final Context mContext;
    private final ArrayList<TvShowTmdb> tvShows;
    private OnItemClickListener listener;

    public TvShowSliderAdapter(Context mContext, ArrayList<TvShowTmdb> tvShows) {
        this.mContext = mContext;
        this.tvShows = tvShows;
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return tvShows.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int i) {
        final TvShowTmdb tvShow = tvShows.get(i);

        final LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_slideshow, null);
        final ImageView imageViewBackdrop = view.findViewById(R.id.image_view_backdrop);
        final ImageView im = view.findViewById(R.id.image_view_overlay);
        TextView textViewTitle1 = view.findViewById(R.id.text_view_title1);
        TextView textViewTitle2 = view.findViewById(R.id.text_view_title2);

        im.setImageDrawable(mContext.getDrawable(R.drawable.bg_gradient_movie));


//        if (title_arr.length == 2) {
//            textViewTitle1.setText(title_arr[0].trim());
//            textViewTitle2.setVisibility(View.VISIBLE);
//            textViewTitle2.setText(title_arr[1].trim());
//        } else {
        textViewTitle1.setText(tvShow.getName());
        textViewTitle2.setVisibility(View.GONE);
//        }

        String url = IMAGE_BASE_URL + "w780" + tvShow.getBackdropPath();

        Picasso.get()
                .load(url)
                .into(imageViewBackdrop);

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}