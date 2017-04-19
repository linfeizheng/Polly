package com.polly.program.api;

import com.orhanobut.logger.Logger;
import com.polly.program.Constants;
import com.polly.program.base.IBaseView;
import com.polly.program.bean.response.BaseResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

public abstract class ProgressDialogSubscriber<T> extends Subscriber<BaseResponse<T>> {

    IBaseView mView;

    public ProgressDialogSubscriber(IBaseView mView) {
        this.mView = mView;
    }

    @Override
    public void onStart() {
        mView.showProgress();
    }

    @Override
    public void onCompleted() {
        mView.hideProgress();
    }

    @Override
    public void onError(Throwable t) {
        mView.hideProgress();
        Logger.e(t, "获取数据失败");
        if (t instanceof SocketTimeoutException) {
            mView.showToast("请求超时，请稍后重试...");
            setStatus(Constants.PageStatus.ERROR);
        } else if (t instanceof ConnectException) {
            mView.showToast("请检查网络是否连接");
            setStatus(Constants.PageStatus.NO_NETWORK);
        } else {
            mView.showToast("请检查网络是否连接");
            setStatus(Constants.PageStatus.NO_NETWORK);
        }
    }

    @Override
    public void onNext(BaseResponse<T> response) {
        if (!response.isError()) {
            onBizSuccess(response.getResults());
        } else {
            onBizFailure("");
        }
    }

    public abstract void onBizSuccess(T t);

    public void onBizFailure(String msg) {
        if (msg != null) {
            Logger.e(msg);
            mView.showToast(msg);
        }
    }

    public abstract void setStatus(int status);

}
