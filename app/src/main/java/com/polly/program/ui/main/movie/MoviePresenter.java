package com.polly.program.ui.main.movie;

import com.polly.program.Constants;
import com.polly.program.api.RetrofitManager;
import com.polly.program.base.BasePresenterImpl;
import com.polly.program.bean.response.MovieResponse;
import com.polly.program.bean.response.SubjectsResponse;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by linfeizheng on 2017/4/14.
 */

public class MoviePresenter extends BasePresenterImpl<MovieContract.View> implements MovieContract.Presenter {

    public MoviePresenter(MovieContract.View mView) {
        super(mView);
    }

    @Override
    public void getMovie() {
        mRxManager.add(RetrofitManager.getDoubanInstance().getMovie()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<MovieResponse, List<SubjectsResponse>>() {
                    @Override
                    public List<SubjectsResponse> call(MovieResponse movieResponse) {
                        return movieResponse.getSubjects();
                    }
                }).subscribe(new Subscriber<List<SubjectsResponse>>() {

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
                    public void onNext(List<SubjectsResponse> subjectsResponses) {
                        mView.showData(subjectsResponses);
                    }
                }));
    }
}
