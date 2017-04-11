package com.polly.program.ui.main.meizi;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.polly.program.Constants;
import com.polly.program.R;
import com.polly.program.base.BaseFragment;
import com.polly.program.bean.response.GankIoResponse;
import com.polly.program.ui.picture.PictureActivity;
import com.polly.program.widget.LoadRecyclerView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import rx.Observable;
import rx.functions.Action1;

/**
 * @author linfeizheng
 * @date 2017/3/10 16:00
 */

public class MeiziFragment extends BaseFragment<MeiziPresenter> implements MeiziAdapter.OnItemClickListener, MeiziContract.View {

    @Bind(R.id.recyclerview_gif)
    LoadRecyclerView mRecyclerView;

    private MeiziAdapter mAdapter;

    private int page = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gif;
    }

    @Override
    protected void initTitleBar() {
        setTitle("美女");
    }

    @Override
    protected void initData() {
        mPresenter = new MeiziPresenter(this);
        mAdapter = new MeiziAdapter(mContext);
    }

    @Override
    protected void lazyLoad() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.getData(page);
    }

    @Override
    protected void initListener() {
        mAdapter.setListener(this);
        mRecyclerView.setOnLoadMoreListener(new LoadRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.getData(++page);
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
    public void onClick(final GankIoResponse response, final ImageView mImageView) {
        Picasso.with(mContext).load(response.getUrl()).fetch(new Callback() {
            @Override
            public void onSuccess() {
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

            @Override
            public void onError() {

            }
        });
    }

}
