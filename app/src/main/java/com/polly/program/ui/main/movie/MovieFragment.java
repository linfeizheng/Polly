package com.polly.program.ui.main.movie;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.polly.program.Constants;
import com.polly.program.R;
import com.polly.program.base.BaseAdapter;
import com.polly.program.base.BaseFragment;
import com.polly.program.bean.response.SubjectsResponse;
import com.polly.program.ui.moive.MovieDetailActivity;

import java.util.List;

import butterknife.Bind;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by linfeizheng on 2017/4/14.
 */

public class MovieFragment extends BaseFragment<MoviePresenter> implements MovieContract.View {

    @Bind(R.id.recyclerview_movie)
    RecyclerView mRecyclerView;

    private MovieAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie;
    }

    @Override
    protected void initTitleBar() {
        setTitle("影讯");
    }

    @Override
    protected void initData() {
        mPresenter = new MoviePresenter(this);
        mAdapter = new MovieAdapter(mContext);
    }

    @Override
    protected void lazyLoad() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.getMovie();
    }

    @Override
    protected void initListener() {
        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int postion) {
                SubjectsResponse subject = mAdapter.getItem(postion);
                Intent intent = MovieDetailActivity.getIntent(mContext, subject);
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(mContext, view, MovieDetailActivity.TRANSIT_PIC);
                try {
                    ActivityCompat.startActivity(mContext, intent, optionsCompat.toBundle());
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showData(List<SubjectsResponse> responses) {
        if (responses != null && responses.size() > 0) {
            setStatus(Constants.PageStatus.NORMAL);
            Observable.from(responses).subscribe(new Action1<SubjectsResponse>() {
                @Override
                public void call(SubjectsResponse subject) {
                    mAdapter.insert(subject);
                }
            });
        } else {
            setStatus(Constants.PageStatus.EMPTY);
        }
    }
}
