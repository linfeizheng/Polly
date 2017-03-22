package com.polly.program.ui.main.video;

import com.polly.program.api.RetrofitManager;
import com.polly.program.base.BasePresenterImpl;
import com.polly.program.bean.response.VideoResponse;

import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author linfeizheng
 * @date 2017/3/11 14:05
 */

public class VideoPresenter extends BasePresenterImpl<VideoContract.View> implements VideoContract.Presenter {

    public VideoPresenter(VideoContract.View mView) {
        super(mView);
    }

    @Override
    public void getVideo(final String type) {
        mRxManager.add(RetrofitManager.get163Instance().getVideo(type)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Map<String, List<VideoResponse.Video>>>() {

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
                        mView.hideProgress();
                    }

                    @Override
                    public void onNext(Map<String, List<VideoResponse.Video>> map) {
                        mView.showData(map.get(type));
                    }
                }));
    }
}