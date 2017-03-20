package com.polly.program.ui.main.gif;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.polly.program.Constants;
import com.polly.program.R;
import com.polly.program.base.BaseFragment;
import com.polly.program.bean.response.response.GankIoResponse;
import com.polly.program.ui.picture.PictureActivity;
import com.polly.program.widget.LoadRecyclerView;

import java.util.List;

import butterknife.Bind;
import rx.Observable;
import rx.functions.Action1;

/**
 * @author linfeizheng
 * @date 2017/3/10 16:00
 */

public class GifFragment extends BaseFragment<GifPresenter> implements GifAdapter.OnItemClickListener, GifContract.View {

    @Bind(R.id.recyclerview_gif)
    RecyclerView mRecyclerView;

    private StaggeredGridLayoutManager mLayoutManager;
    private GifAdapter mAdapter;

    private int page = 1;
    private int lastVisibleItem;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gif;
    }

    @Override
    protected void initTitleBar() {
        setTitle("Gif");
    }

    @Override
    protected void initData() {
        mPresenter = new GifPresenter(this);
        mAdapter = new GifAdapter(mContext);
    }

    @Override
    protected void lazyLoad() {
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.getData(page);
    }

    @Override
    protected void initListener() {
        mAdapter.setListener(this);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 2 >= mLayoutManager.getItemCount()) {
                    mPresenter.getData(++page);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int[] positions = mLayoutManager.findLastVisibleItemPositions(null);
                lastVisibleItem = Math.max(positions[0], positions[1]);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void showData(final List<GankIoResponse> responses) {
        if (page == 1) {
            mAdapter.setData(responses);
        } else {
            Observable.from(responses).subscribe(new Action1<GankIoResponse>() {
                @Override
                public void call(GankIoResponse gankIoResponse) {
                    mAdapter.insert(gankIoResponse);
                }
            });
        }
    }

    @Override
    public void onClick(GankIoResponse response, ImageView mImageView) {
        Intent intent = new Intent(mContext, PictureActivity.class);
        intent.putExtra(Constants.INTENT_EXTRA_TITLE, response.getUrl());
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(mContext, mImageView, PictureActivity.TRANSIT_PIC);
        try {
            ActivityCompat.startActivity(mContext, intent, optionsCompat.toBundle());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            startActivity(intent);
        }
    }

}
