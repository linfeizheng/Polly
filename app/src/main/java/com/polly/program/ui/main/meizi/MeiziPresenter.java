package com.polly.program.ui.main.meizi;

import com.polly.program.api.ProgressDialogSubscriber;
import com.polly.program.api.RetrofitManager;
import com.polly.program.base.BasePresenterImpl;
import com.polly.program.bean.response.GankIoResponse;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author linfeizheng
 * @date 2017/3/11 14:05
 */

public class MeiziPresenter extends BasePresenterImpl<MeiziContract.View> implements MeiziContract.Presenter {

    public MeiziPresenter(MeiziContract.View mView) {
        super(mView);
    }

    @Override
    public void getData(int page) {
        mRxManager.add(RetrofitManager.getGankInstance().getArticle("福利", String.valueOf(page))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressDialogSubscriber<List<GankIoResponse>>(mView) {
                    @Override
                    public void onBizSuccess(List<GankIoResponse> response) {
                        mView.showData(response);
                    }
                }));
    }
}