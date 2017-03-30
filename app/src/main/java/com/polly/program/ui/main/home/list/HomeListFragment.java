package com.polly.program.ui.main.home.list;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.polly.program.Constants;
import com.polly.program.R;
import com.polly.program.base.AnimationAdapterWrapper;
import com.polly.program.base.BaseFragment;
import com.polly.program.bean.response.GankIoResponse;
import com.polly.program.ui.main.home.HomeContract;
import com.polly.program.ui.main.home.HomePresenter;
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

    private String tabName;

    private int page = 1;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
    }

    @Override
    protected void lazyLoad() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new HomeListAdapter(mContext);
        mAdapter.setPullLoadEnabled(true);
        ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(mAdapter);
        scaleInAnimationAdapter.setFirstOnly(false);
        mRecyclerView.setAdapter(scaleInAnimationAdapter);
        mPresenter.getData(tabName, page);
    }

    @Override
    protected void initListener() {
        mRecyclerView.setOnLoadMoreListener(new LoadRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page += 1;
                mPresenter.getData(tabName, page);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void showData(List<GankIoResponse> responses) {
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
        if (page >= 4) {
            mRecyclerView.setLoadComplete(true);
        }
    }
}
