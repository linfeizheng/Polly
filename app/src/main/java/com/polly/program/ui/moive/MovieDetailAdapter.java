package com.polly.program.ui.moive;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.polly.program.R;
import com.polly.program.base.BaseAdapter;
import com.polly.program.bean.response.PersonResponse;
import com.polly.program.util.ImageUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by linfeizheng on 2017/4/17.
 */

public class MovieDetailAdapter extends BaseAdapter<PersonResponse, MovieDetailAdapter.Holder> {

    public MovieDetailAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public void onBindViewHolderExtend(Holder holder, int position) {
        PersonResponse person = getItem(position);
        ImageUtil.loadImg(mContext, person.getAvatars().getMedium(), holder.mIvPhoto);
        holder.mTvName.setText(person.getName() != null ? person.getName() : "");
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.listitem_movie_detail;
    }

    @Override
    public Holder getViewHolder(View view) {
        return new Holder(view);
    }

    class Holder extends BaseAdapter.BaseHolder {

        @Bind(R.id.iv_movie_detail_photo)
        ImageView mIvPhoto;
        @Bind(R.id.tv_movie_detail_name)
        TextView mTvName;

        public Holder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
