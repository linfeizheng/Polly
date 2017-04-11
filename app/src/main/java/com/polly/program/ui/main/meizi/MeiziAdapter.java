package com.polly.program.ui.main.meizi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.polly.program.R;
import com.polly.program.base.BaseAdapter;
import com.polly.program.bean.response.GankIoResponse;
import com.polly.program.util.ImageUtil;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author linfeizheng
 * @date 2017/3/14 13:20
 */

public class MeiziAdapter extends BaseAdapter<GankIoResponse, MeiziAdapter.Holder> {

    public MeiziAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public void onBindViewHolderExtend(final Holder holder, int position) {
        final GankIoResponse response = mData.get(position);
        Picasso.with(mContext).load(response.getUrl()).into(holder.mIvGif);
        holder.mIvGif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(response, holder.mIvGif);
                }
            }
        });
    }

    @Override
    public Holder onCreateViewHolderExtend(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.listitem_gif, parent, false);
        return new Holder(itemView);
    }

    @Override
    public Holder getViewHolder(View view) {
        return null;
    }

    class Holder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_gif)
        ImageView mIvGif;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onClick(GankIoResponse response, ImageView mImageView);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
