package com.polly.program.base;

import com.polly.program.api.RxManager;

public class BasePresenterImpl<V extends IBaseView> implements BasePresenter {

    protected V mView;

    protected RxManager mRxManager;

    public BasePresenterImpl(V mView) {
        this.mView = mView;
        mRxManager = new RxManager();
    }

    @Override
    public void onDetach() {
        mRxManager.clear();
    }

}
