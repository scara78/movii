package com.mythio.movii.adapter.recyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mythio.movii.R;
import com.mythio.movii.model.cast.Cast;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.mythio.movii.util.Constant.IMAGE_BASE_URL;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastHolder> {
    private final ArrayList<Cast> mCasts;
    private final Context mContext;
//    private final ListItemClickListener mOnClickListener;

//    public interface ListItemClickListener {
//        void onClick(View view, int position);
//    }

    public CastAdapter(ArrayList<Cast> mCasts, Context mContext/*, ListItemClickListener mOnClickListener*/) {
        this.mCasts = mCasts;
        this.mContext = mContext;
//        this.mOnClickListener = mOnClickListener;
    }

    @NonNull
    @Override
    public CastHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cast, viewGroup, false);
        return new CastHolder(view/*, mOnClickListener*/);
    }

    @Override
    public void onBindViewHolder(@NonNull CastHolder castHolder, int i) {

        Cast cast = mCasts.get(i);

        String url = IMAGE_BASE_URL + "w185" + cast.getProfilePath();
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.bg)
                .into(castHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return mCasts.size();
    }

    class CastHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final CircleImageView imageView;

        CastHolder(@NonNull View itemView/*, ListItemClickListener mOnClickListener*/) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cast);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            mOnClickListener.onClick(v, getAdapterPosition());
        }
    }
}