package com.polly.program.ui.main.home.list;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.polly.program.R;
import com.polly.program.base.BaseAdapter;
import com.polly.program.bean.response.GankIoResponse;
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
    public void onBindViewHolderExtend(final ViewHolder holder, final int position) {
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
                if (onItemClickListener != null)
                    onItemClickListener.onClick(holder.itemView, position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemLongClickListener != null)
                    return onItemLongClickListener.onLongClick(holder.itemView, position);
                return false;
            }
        });
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.listitem_home;
    }

    @Override
    public ViewHolder getViewHolder(View view) {
        return new ViewHolder(view);
    }

    class ViewHolder extends BaseAdapter.BaseHolder {

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
