package com.polly.program.ui.moive;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.polly.program.Constants;
import com.polly.program.R;
import com.polly.program.base.BaseActivity;
import com.polly.program.bean.response.MovieDetailResponse;
import com.polly.program.bean.response.PersonResponse;
import com.polly.program.bean.response.SubjectsResponse;
import com.polly.program.ui.main.movie.MoviePresenter;
import com.polly.program.util.ImageUtil;

import java.util.List;

import butterknife.Bind;

/**
 * Created by linfeizheng on 2017/4/14.
 */

public class MovieDetailActivity extends BaseActivity<MovieDetailPresenter> implements MovieDetailContract.View {

    @Bind(R.id.iv_movie_bg)
    ImageView mIvBackground;
    @Bind(R.id.iv_movie_photo)
    ImageView mIvPhoto;
    @Bind(R.id.tv_movie_rate)
    TextView mTvRate;
    @Bind(R.id.tv_movie_number)
    TextView mTvNumber;
    @Bind(R.id.tv_movie_directors)
    TextView mTvDirector;
    @Bind(R.id.tv_movie_casts)
    TextView mTvCasts;
    @Bind(R.id.tv_movie_genres)
    TextView mTvGenres;
    @Bind(R.id.tv_movie_day)
    TextView mTvDay;
    @Bind(R.id.tv_movie_city)
    TextView mTvCity;
    @Bind(R.id.tv_movie_summary)
    TextView mTvSummary;
    @Bind(R.id.recyclerview_movie)
    RecyclerView mRecyclerView;

    private MovieDetailAdapter mAdapter;

    private SubjectsResponse subject;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie;
    }

    @Override
    protected void initTitleBar() {
        subject = (SubjectsResponse) getIntent().getSerializableExtra(Constants.INTENT_EXTRA_OBJECT);
        if (subject == null) {
            onBackPressed();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setTitle(subject.getTitle());
    }

    @Override
    protected void initData() {
        mPresenter = new MovieDetailPresenter(this);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new MovieDetailAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.getMovieDetail(subject.getId());
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showData(MovieDetailResponse response) {
        ImageUtil.loadImg(mContext, response.getImages().getMedium(), mIvBackground);
        ImageUtil.loadImg(mContext, response.getImages().getMedium(), mIvPhoto);
        mTvRate.setText((response.getRating() != null && response.getRating().getAverage() != null) ? "评分："+response.getRating().getAverage().toString() : "");
        StringBuffer directors = new StringBuffer();
        if (response.getDirectors() != null) {
            for (PersonResponse person : response.getDirectors()) {
                directors.append(person.getName());
                directors.append(" / ");
            }
            mTvDirector.setText(directors.toString().substring(0, directors.length() - 3));
        }
        StringBuffer casts = new StringBuffer();
        if (response.getCasts() != null) {
            for (PersonResponse person : response.getCasts()) {
                casts.append(person.getName());
                casts.append(" / ");
            }
            mTvCasts.setText(casts.toString().substring(0, casts.length() - 3));
        }
        StringBuffer types = new StringBuffer();
        if (response.getGenres() != null) {
            for (String genry : response.getGenres()) {
                types.append(genry);
                types.append(" / ");
            }
            mTvGenres.setText(types.toString().substring(0, types.length() - 3));
        }
        mAdapter.setData(response.getDirectors());
    }

    public static Intent getIntent(Activity activity, SubjectsResponse subject) {
        Intent intent = new Intent(activity, MovieDetailActivity.class);
        intent.putExtra(Constants.INTENT_EXTRA_OBJECT, subject);
        return intent;
    }
}
