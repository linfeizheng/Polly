package com.polly.program.ui.main.video;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.polly.program.Constants;
import com.polly.program.R;
import com.polly.program.base.BaseAdapter;
import com.polly.program.base.BaseFragment;
import com.polly.program.bean.response.VideoResponse;
import com.polly.program.ui.video.VideoActivity;

import java.util.List;

import butterknife.Bind;
import rx.Observable;
import rx.functions.Action1;

/**
 * @author linfeizheng
 * @date 2017/3/10 16:00
 */

public class VideoFragment extends BaseFragment<VideoPresenter> implements VideoContract.View {

    @Bind(R.id.recyclerview_video)
    RecyclerView mRecyclerView;

    private VideoAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initTitleBar() {
        setTitle("视频");
    }

    @Override
    protected void initData() {
        mPresenter = new VideoPresenter(this);
        mAdapter = new VideoAdapter(mContext);
    }

    @Override
    protected void lazyLoad() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.getVideo("V9LG4B3A0");
    }

    @Override
    protected void initListener() {
        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int postion) {
                VideoResponse.Video video = mAdapter.getItem(postion);
                Intent intent = VideoActivity.getIntent(mContext, video);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void showData(final List<VideoResponse.Video> responses) {
        if (responses != null && responses.size() > 0) {
            setStatus(Constants.PageStatus.NORMAL);
            Observable.from(responses).subscribe(new Action1<VideoResponse.Video>() {
                @Override
                public void call(VideoResponse.Video video) {
                    mAdapter.insert(video);
                }
            });
        } else {
            setStatus(Constants.PageStatus.EMPTY);
        }
    }
}
