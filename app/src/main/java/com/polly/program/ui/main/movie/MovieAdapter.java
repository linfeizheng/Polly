package com.polly.program.ui.main.movie;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.polly.program.R;
import com.polly.program.base.BaseAdapter;
import com.polly.program.bean.response.PersonResponse;
import com.polly.program.bean.response.SubjectsResponse;
import com.polly.program.util.AnimationUtil;
import com.polly.program.util.ImageUtil;
import com.polly.program.util.OnClickEvent;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by linfeizheng on 2017/4/14.
 */

public class MovieAdapter extends BaseAdapter<SubjectsResponse, MovieAdapter.Holder> {

    public MovieAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public void onBindViewHolderExtend(final MovieAdapter.Holder holder, final int position) {
        SubjectsResponse subject = getItem(position);
        ImageUtil.loadImg(mContext, subject.getImages().getMedium(), holder.mIvPicture);
        holder.mTvTitle.setText(subject.getTitle() != null ? subject.getTitle() : "");
        StringBuffer directors = new StringBuffer();
        if (subject.getDirectors() != null) {
            for (PersonResponse person : subject.getDirectors()) {
                directors.append(person.getName());
                directors.append(" / ");
            }
            holder.mTvDirector.setText(directors.toString().substring(0, directors.length() - 3));
        }
        StringBuffer casts = new StringBuffer();
        if (subject.getCasts() != null) {
            for (PersonResponse person : subject.getCasts()) {
                casts.append(person.getName());
                casts.append(" / ");
            }
            holder.mTvCast.setText(casts.toString().substring(0, casts.length() - 3));
        }
        StringBuffer types = new StringBuffer();
        if (subject.getGenres() != null) {
            for (String genry : subject.getGenres()) {
                types.append(genry);
                types.append(" / ");
            }
            holder.mTvType.setText(types.toString().substring(0, types.length() - 3));
        }
        holder.mTvRate.setText((subject.getRating() != null && subject.getRating().getAverage() != null) ? subject.getRating().getAverage().toString() : "");
        holder.itemView.setOnClickListener(new OnClickEvent() {
            @Override
            public void onSingleClick(View v) {
                if (listener != null) {
                    listener.onClick(holder.mIvPicture, position);
                }
            }
        });
        AnimationUtil.scaleAnim(holder.itemView, 0.8f);
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.listitem_movie;
    }

    @Override
    public MovieAdapter.Holder getViewHolder(View view) {
        return new Holder(view);
    }

    class Holder extends BaseAdapter.BaseHolder {

        @Bind(R.id.iv_picture)
        ImageView mIvPicture;
        @Bind(R.id.tv_title)
        TextView mTvTitle;
        @Bind(R.id.tv_director)
        TextView mTvDirector;
        @Bind(R.id.tv_casts)
        TextView mTvCast;
        @Bind(R.id.tv_type)
        TextView mTvType;
        @Bind(R.id.tv_rate)
        TextView mTvRate;

        public Holder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
