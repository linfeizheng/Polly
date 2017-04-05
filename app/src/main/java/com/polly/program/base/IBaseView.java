package com.polly.program.base;

public interface IBaseView {

    void showProgress();

    void hideProgress();

    void setStatus(int status);

    void showToast(String msg);

}
