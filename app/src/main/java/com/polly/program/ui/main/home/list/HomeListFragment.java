package com.polly.program.ui.main.home.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.polly.program.Constants;
import com.polly.program.R;
import com.polly.program.base.BaseAdapter;
import com.polly.program.base.BaseFragment;
import com.polly.program.bean.response.GankIoResponse;
import com.polly.program.ui.article.ArticleActivity;
import com.polly.program.ui.main.home.HomeContract;
import com.polly.program.ui.main.home.HomePresenter;
import com.polly.program.widget.Banner;
import com.polly.program.widget.BottomSheetDialogWrapper;
import com.polly.program.widget.LoadRecyclerView;

import java.util.List;

import butterknife.Bind;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import rx.Observable;
import rx.functions.Action1;

/**
 * @author linfeizheng
 * @date 2017/3/11 16:14
 */

public class HomeListFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    @Bind(R.id.recyclerview_home)
    LoadRecyclerView mRecyclerView;

    private HomeListAdapter mAdapter;
    private BannerAdapter mBannerAdapter;

    private Banner banner;

    private String tabName;

    private int page;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabName = getArguments().getString(Constants.INTENT_EXTRA_ID);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homelist;
    }

    @Override
    protected void initTitleBar() {

    }

    @Override
    protected void initData() {
        mPresenter = new HomePresenter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new HomeListAdapter(mContext);
        mAdapter.setPullLoadEnabled(true);
        ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(mAdapter);
        scaleInAnimationAdapter.setFirstOnly(false);
        mRecyclerView.setAdapter(scaleInAnimationAdapter);
    }

    @Override
    protected void lazyLoad() {
        page = 0;
        if ("Android".equals(tabName)) {
            mPresenter.getBannerImage();
        } else {
            mPresenter.getData(tabName, page);
        }
    }

    @Override
    protected void initListener() {
        mRecyclerView.setOnLoadMoreListener(new LoadRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.getData(tabName, page);
            }
        });
        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int postion) {
                GankIoResponse response = mAdapter.getItem(postion);
                Intent intent = new Intent(mContext, ArticleActivity.class);
                intent.putExtra(Constants.INTENT_EXTRA_ID, response.getUrl());
                intent.putExtra(Constants.INTENT_EXTRA_TITLE, response.getDesc());
                startActivity(intent);
            }
        });
        mAdapter.setOnItemLongClickListener(new BaseAdapter.OnItemLongClickListener() {
            @Override
            public boolean onLongClick(View view, final int position) {
                final BottomSheetDialogWrapper bottomSheetDialogWrapper = new BottomSheetDialogWrapper(mContext, new BottomSheetDialogWrapper.Callback() {
                    @Override
                    public void callback(BottomSheetDialog dialog, String text) {
                        dialog.dismiss();
                        if ("确定".equals(text)) {
                            mAdapter.remove(position);
                        } else if ("取消".equals(text)) {

                        }
                    }
                });
                bottomSheetDialogWrapper.show();
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void showBanner(String[] images) {
        banner = new Banner(mContext);
        mBannerAdapter = new BannerAdapter(mContext, images);
        banner.setLoop(true);
        banner.setAdapter(mBannerAdapter);
        mPresenter.getData(tabName, page);
    }

    @Override
    public void showData(List<GankIoResponse> responses) {
        page += 1;
        if (page == 1) {
            if (responses == null || responses.size() <= 0) {
                setStatus(Constants.PageStatus.EMPTY);
            } else {
                setStatus(Constants.PageStatus.NORMAL);
            }
            if (banner != null) {
                mAdapter.setHeaderView(banner);
            }
            mAdapter.setData(responses);
        } else {
            Observable.from(responses).subscribe(new Action1<GankIoResponse>() {
                @Override
                public void call(GankIoResponse gankIoResponse) {
                    mAdapter.insert(gankIoResponse);
                }
            });
        }
        if (page >= 4) {
            mRecyclerView.setLoadComplete(true);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (banner != null && banner.isLoop()) {
            banner.stop();
        }
    }
}
