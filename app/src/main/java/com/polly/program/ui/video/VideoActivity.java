package com.polly.program.ui.video;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.TextView;

import com.polly.program.Constants;
import com.polly.program.R;
import com.polly.program.base.BaseActivity;
import com.polly.program.bean.response.VideoResponse;
import com.polly.program.util.ImageUtil;
import com.polly.program.widget.CustomVideoPlayer;
import com.polly.program.widget.StatusBarCompat;

import butterknife.Bind;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoActivity extends BaseActivity {

    public static final String TAG = "Video";

    @Bind(R.id.videoplayer_video)
    CustomVideoPlayer mVideoPlayer;
    @Bind(R.id.tv_video_title)
    TextView mTvTitle;
    @Bind(R.id.tv_video_desc)
    TextView mTvDesc;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video;
    }

    @Override
    protected void initTitleBar() {
        StatusBarCompat.compat(this);
    }

    @Override
    protected void initData() {
        ViewCompat.setTransitionName(mVideoPlayer, TAG);
        VideoResponse.Video video = (VideoResponse.Video) getIntent().getSerializableExtra(Constants.INTENT_EXTRA_OBJECT);
        mTvTitle.setText(video.getTitle() != null ? video.getTitle() : "");
        mTvDesc.setText(video.getTopicDesc() != null ? video.getTopicDesc() : "");
        mVideoPlayer.setUp(video.getMp4_url(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
        ImageUtil.loadImg(mContext, video.getCover(), mVideoPlayer.thumbImageView);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
