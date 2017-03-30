package com.polly.program.ui.main.video;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.polly.program.R;
import com.polly.program.base.BaseAdapter;
import com.polly.program.bean.response.VideoResponse;
import com.polly.program.util.ImageUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author linfeizheng
 * @date 2017/3/21 11:30
 */

public class VideoAdapter extends BaseAdapter<VideoResponse.Video, VideoAdapter.Holder> {

    public VideoAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public void onBindViewHolderExtend(final Holder holder, int position) {
        final VideoResponse.Video video = mData.get(position);
        ImageUtil.loadImg(mContext, video.getCover(), holder.mIvVideo);
        ImageUtil.loadCircleImg(mContext, video.getTopicImg(), holder.mIvLogo);
        holder.mTvTopicName.setText(video.getTopicName() != null ? video.getTopicName() : "");
        holder.mIvVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(video, holder.mIvVideo);
                }
            }
        });
    }

    @Override
    public Holder onCreateViewHolderExtend(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.listitem_video, parent, false);
        return new Holder(itemView);
    }

    @Override
    public Holder getViewHolder(View view) {
        return new Holder(view);
    }

    class Holder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_video)
        ImageView mIvVideo;
        @Bind(R.id.iv_logo)
        ImageView mIvLogo;
        @Bind(R.id.tv_video_topic_name)
        TextView mTvTopicName;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onClick(VideoResponse.Video video, ImageView mImageView);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
