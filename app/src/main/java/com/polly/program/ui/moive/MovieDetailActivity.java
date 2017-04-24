package com.polly.program.ui.moive;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.polly.program.Constants;
import com.polly.program.R;
import com.polly.program.base.BaseActivity;
import com.polly.program.bean.response.MovieDetailResponse;
import com.polly.program.bean.response.SubjectsResponse;
import com.polly.program.util.ImageUtil;
import com.polly.program.util.StringUtil;
import com.polly.program.util.Utils;

import butterknife.Bind;

/**
 * Created by linfeizheng on 2017/4/14.
 */

public class MovieDetailActivity extends BaseActivity<MovieDetailPresenter> implements MovieDetailContract.View {

    @Bind(R.id.iv_title_share)
    ImageView mIvShare;
    @Bind(R.id.scrollview_movie)
    NestedScrollView mScrollView;
    @Bind(R.id.iv_movie_bg)
    ImageView mIvBackground;
    @Bind(R.id.iv_movie_photo)
    ImageView mIvPhoto;
    @Bind(R.id.tv_movie_name)
    TextView mTvName;
    @Bind(R.id.tv_movie_original_name)
    TextView mTvOriginalName;
    @Bind(R.id.tv_movie_genres)
    TextView mTvGenres;
    @Bind(R.id.tv_movie_country)
    TextView mTvCountry;
    @Bind(R.id.tv_movie_day)
    TextView mTvDay;
    @Bind(R.id.tv_movie_rate_title)
    TextView mTvRateTitle;
    @Bind(R.id.tv_movie_number)
    TextView mTvNumber;
    @Bind(R.id.tv_movie_rate)
    TextView mTvRate;
    @Bind(R.id.rate_movie_detail)
    RatingBar mRatingBar;
    @Bind(R.id.tv_movie_summary)
    TextView mTvSummary;
    @Bind(R.id.llyt_movie_detail_arrow)
    LinearLayout mLlytArrow;
    @Bind(R.id.iv_movie_detail_arrow)
    ImageView mIvArrow;
    @Bind(R.id.recyclerview_movie_director)
    RecyclerView mRvDirector;
    @Bind(R.id.recyclerview_movie_cast)
    RecyclerView mRvCast;

    private MovieDetailAdapter mDirectorAdapter;
    private MovieDetailAdapter mCastAdapter;

    private SubjectsResponse subject;
    private MovieDetailResponse movieDetail;

    public static final String TRANSIT_PIC = "picture";

    private int maxLine;
    public static final int minLine = 5;
    private int maxHeight;
    private int minHeight;
    private boolean isOpen;
    private int offset;

    private int RED;
    private int GREEN;
    private int BLUE;

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
        int backgroundColor = ContextCompat.getColor(mContext, R.color.colorPrimary);
        RED = Color.red(backgroundColor);
        GREEN = Color.green(backgroundColor);
        BLUE = Color.blue(backgroundColor);
        mToolbar.setBackgroundColor(Color.argb(0, RED, GREEN, BLUE));
        setBack();
        setTitle(subject.getTitle());
    }

    @Override
    protected void initData() {
        ImageUtil.loadImg(mContext, subject.getImages().getMedium(), mIvPhoto);
        ImageUtil.loadBlurImg(mContext, subject.getImages().getLarge(), mIvBackground);
        ViewCompat.setTransitionName(mIvPhoto, TRANSIT_PIC);
        mPresenter = new MovieDetailPresenter(this);
        LinearLayoutManager directorManager = new LinearLayoutManager(mContext);
        directorManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager castManager = new LinearLayoutManager(mContext);
        castManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvDirector.setLayoutManager(directorManager);
        mRvCast.setLayoutManager(castManager);
        mDirectorAdapter = new MovieDetailAdapter(mContext);
        mCastAdapter = new MovieDetailAdapter(mContext);
        mRvDirector.setAdapter(mDirectorAdapter);
        mRvCast.setAdapter(mCastAdapter);
        mPresenter.getMovieDetail(subject.getId());
    }

    @Override
    protected void initListener() {
        mIvShare.setOnClickListener(this);
        mLlytArrow.setOnClickListener(this);
        mToolbar.post(new Runnable() {
            @Override
            public void run() {
                offset = getResources().getDimensionPixelOffset(R.dimen.movie_header_height) - mToolbar.getHeight();
            }
        });
        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY <= 0) {  //alpha为0
                    mToolbar.setBackgroundColor(Color.argb(0, RED, GREEN, BLUE));
                } else if (scrollY > 0 && scrollY < offset) { //alpha为0到255
                    float precent = (float) scrollY / offset;
                    int alpha = (int) (precent * 255);
                    mToolbar.setBackgroundColor(Color.argb(alpha, RED, GREEN, BLUE));
                    mTvTitle.setVisibility(View.INVISIBLE);
                } else if (scrollY >= offset) {  //alpha为255
                    mToolbar.setBackgroundColor(Color.argb(255, RED, GREEN, BLUE));
                    mTvTitle.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_share:
                if (movieDetail != null && !StringUtil.isTrimBlank(movieDetail.getAlt())) {
                    Utils.share(mContext, movieDetail.getAlt());
                } else {
                    toast("分享失败");
                }
                break;
            case R.id.llyt_movie_detail_arrow:
                ValueAnimator animator;
                if (isOpen) {
                    animator = ValueAnimator.ofInt(maxHeight, minHeight);
                } else {
                    animator = ValueAnimator.ofInt(minHeight, maxHeight);
                }
                isOpen = !isOpen;
                mTvSummary.setMaxLines(maxLine);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mTvSummary.getLayoutParams();
                        int height = (int) animation.getAnimatedValue();
                        params.height = height;
                        mTvSummary.setLayoutParams(params);
                        mIvArrow.setRotation((height - minHeight) * 180 / (maxHeight - minHeight));
                    }
                });
                animator.setDuration(500);
                animator.start();
                break;
            default:
                break;
        }
    }

    @Override
    public void showData(MovieDetailResponse response) {
        setStatus(Constants.PageStatus.NORMAL);
        movieDetail = response;
        mTvName.setText(response.getTitle() != null ? response.getTitle() : "");
        mTvOriginalName.setText(response.getOriginal_title() != null ? response.getOriginal_title() : "");
        StringBuilder types = new StringBuilder();
        if (response.getGenres() != null) {
            for (String genry : response.getGenres()) {
                types.append(genry);
                types.append(" / ");
            }
            mTvGenres.setText(types.toString().substring(0, types.length() - 3));
        }
        StringBuilder countries = new StringBuilder();
        if (response.getCountries() != null) {
            for (String country : response.getCountries()) {
                countries.append(country);
                countries.append(" / ");
            }
            mTvCountry.setText(countries.toString().substring(0, countries.length() - 3));
        }
        mTvDay.setText(response.getYear() != null ? response.getYear() + "年上映" : "");
        mTvRateTitle.setText("用户评分");
        mTvNumber.setText(response.getRatings_count() != null ? "(" + response.getRatings_count() + ")" : "");
        mTvRate.setText((response.getRating() != null && response.getRating().getAverage() != null) ? response.getRating().getAverage().toString() : "");
        mRatingBar.setRating(response.getRating().getAverage().floatValue() / 2);
        mTvSummary.setText(response.getSummary() != null ? "\u3000\u3000" + response.getSummary() : "");

        //展开收缩的初始化
        maxLine = mTvSummary.getLineCount();
        maxHeight = maxLine * mTvSummary.getLineHeight();
        minHeight = minLine * mTvSummary.getLineHeight();
        if (minLine >= maxLine) {
            mLlytArrow.setVisibility(View.GONE);
        } else {
            mTvSummary.setMaxLines(minLine);
        }

        mDirectorAdapter.setData(response.getDirectors());
        response.getCasts().addAll(response.getCasts());
        mCastAdapter.setData(response.getCasts());
    }

    public static Intent getIntent(Activity activity, SubjectsResponse subject) {
        Intent intent = new Intent(activity, MovieDetailActivity.class);
        intent.putExtra(Constants.INTENT_EXTRA_OBJECT, subject);
        return intent;
    }
}
