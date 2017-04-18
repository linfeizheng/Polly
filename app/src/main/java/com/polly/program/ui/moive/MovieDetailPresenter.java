package com.polly.program.ui.moive;

import com.polly.program.Constants;
import com.polly.program.api.RetrofitManager;
import com.polly.program.base.BasePresenterImpl;
import com.polly.program.bean.response.MovieDetailResponse;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by linfeizheng on 2017/4/14.
 */

public class MovieDetailPresenter extends BasePresenterImpl<MovieDetailContract.View> implements MovieDetailContract.Presenter {

    public MovieDetailPresenter(MovieDetailContract.View mView) {
        super(mView);
    }

    @Override
    public void getMovieDetail(String id) {
        mRxManager.add(RetrofitManager.getDoubanInstance().getMovieDetail(id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieDetailResponse>() {

                    @Override
                    public void onStart() {
                        mView.showProgress();
                    }

                    @Override
                    public void onCompleted() {
                        mView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.setStatus(Constants.PageStatus.ERROR);
                        mView.hideProgress();
                    }

                    @Override
                    public void onNext(MovieDetailResponse response) {
                        mView.showData(response);
                    }
                }));
    }
}
