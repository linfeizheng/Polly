package com.polly.program.ui.main.home.list;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.polly.program.Constants;
import com.polly.program.R;
import com.polly.program.base.BaseAdapter;
import com.polly.program.bean.response.GankIoResponse;
import com.polly.program.ui.article.ArticleActivity;
import com.polly.program.util.OnClickEvent;
import com.polly.program.util.StringUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.holder.AnimateViewHolder;

/**
 * @author linfeizheng
 * @date 2017/3/11 13:45
 */

public class HomeListAdapter extends BaseAdapter<GankIoResponse, HomeListAdapter.Holder> {

    public HomeListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final GankIoResponse response = mData.get(position);
        holder.mTvTitle.setText(response.getDesc() != null ? response.getDesc() : "");
        String publishedAt = response.getPublishAt();
        if (!StringUtil.isTrimBlank(publishedAt)) {
            int index = publishedAt.indexOf(".");
            publishedAt = publishedAt.substring(0, index).replace("T", "\t");
            holder.mTvDate.setText(publishedAt);
        }
        holder.mTvWho.setText(response.getWho() != null ? response.getWho() : "");
        holder.itemView.setOnClickListener(new OnClickEvent() {
            @Override
            public void onSingleClick(View v) {
                Intent intent = new Intent(mContext, ArticleActivity.class);
                intent.putExtra(Constants.INTENT_EXTRA_ID, response.getUrl());
                intent.putExtra(Constants.INTENT_EXTRA_TITLE, response.getDesc());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public HomeListAdapter.Holder onCreateViewHolder(ViewGroup parent, int position) {
        View itemView = mInflater.inflate(R.layout.listitem_home, parent, false);
        return new Holder(itemView);
    }

    class Holder extends RecyclerView.ViewHolder implements AnimateViewHolder {

        @Bind(R.id.iv_home_avatar)
        ImageView mIvAvatar;
        @Bind(R.id.tv_home_title)
        TextView mTvTitle;
        @Bind(R.id.tv_home_who)
        TextView mTvWho;
        @Bind(R.id.tv_home_date)
        TextView mTvDate;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
            ViewCompat.setTranslationY(itemView, -itemView.getHeight() * 0.3f);
            ViewCompat.setAlpha(itemView, 0);
        }

        @Override
        public void preAnimateRemoveImpl(RecyclerView.ViewHolder holder) {

        }

        @Override
        public void animateAddImpl(RecyclerView.ViewHolder holder, ViewPropertyAnimatorListener listener) {
            ViewCompat.animate(itemView)
                    .translationY(0)
                    .alpha(1)
                    .setDuration(300)
                    .setListener(listener)
                    .start();
        }

        @Override
        public void animateRemoveImpl(RecyclerView.ViewHolder holder, ViewPropertyAnimatorListener listener) {
            ViewCompat.animate(itemView)
                    .translationY(-itemView.getHeight() * 0.3f)
                    .alpha(0)
                    .setDuration(300)
                    .setListener(listener)
                    .start();
        }
    }
}
