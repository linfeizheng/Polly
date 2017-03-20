package com.polly.program.ui.main.home.list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.polly.program.Constants;
import com.polly.program.R;
import com.polly.program.base.BaseAdapter;
import com.polly.program.bean.response.response.GankIoResponse;
import com.polly.program.ui.article.ArticleActivity;
import com.polly.program.util.OnClickEvent;

import butterknife.Bind;
import butterknife.ButterKnife;

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
        holder.mTvDate.setText(response.getPublishAt() != null ? response.getPublishAt() : "");
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

    class Holder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_home_title)
        TextView mTvTitle;
        @Bind(R.id.iv_home_avatar)
        ImageView mIvAvatar;
        @Bind(R.id.tv_home_date)
        TextView mTvDate;
        @Bind(R.id.iv_home_mark)
        ImageView mIvMark;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
