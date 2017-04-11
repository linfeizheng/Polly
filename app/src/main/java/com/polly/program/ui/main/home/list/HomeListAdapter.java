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
import com.polly.program.bean.response.GankIoResponse;
import com.polly.program.ui.article.ArticleActivity;
import com.polly.program.util.ImageUtil;
import com.polly.program.util.OnClickEvent;
import com.polly.program.util.StringUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author linfeizheng
 * @date 2017/3/11 13:45
 */

public class HomeListAdapter extends BaseAdapter<GankIoResponse, HomeListAdapter.ViewHolder> {

    public HomeListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public void onBindViewHolderExtend(ViewHolder holder, final int position) {
        final GankIoResponse response = mData.get(position);
        holder.mTvTitle.setText(response.getDesc() != null ? response.getDesc() : "");
        String publishedAt = response.getCreatedAt();
        if (!StringUtil.isTrimBlank(publishedAt)) {
            int index = publishedAt.indexOf(".");
            publishedAt = publishedAt.substring(0, index).replace("T", "\t");
            holder.mTvDate.setText(publishedAt);
        }
        holder.mTvWho.setText(response.getWho() != null ? response.getWho() : "");
        if (response.getImages() != null && response.getImages().length > 0) {
            holder.mIvAvatar.setVisibility(View.VISIBLE);
            ImageUtil.loadImg(mContext, response.getImages()[0], holder.mIvAvatar);
        } else {
            holder.mIvAvatar.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new OnClickEvent() {
            @Override
            public void onSingleClick(View v) {
                Intent intent = new Intent(mContext, ArticleActivity.class);
                intent.putExtra(Constants.INTENT_EXTRA_ID, response.getUrl());
                intent.putExtra(Constants.INTENT_EXTRA_TITLE, response.getDesc());
                mContext.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                remove(position);
                return true;
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolderExtend(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.listitem_home, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public ViewHolder getViewHolder(View view) {
        return new ViewHolder(view);
    }

    class ViewHolder extends BaseAdapter.Holder {

        @Bind(R.id.iv_home_avatar)
        ImageView mIvAvatar;
        @Bind(R.id.tv_home_title)
        TextView mTvTitle;
        @Bind(R.id.tv_home_who)
        TextView mTvWho;
        @Bind(R.id.tv_home_date)
        TextView mTvDate;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }

    }
}
