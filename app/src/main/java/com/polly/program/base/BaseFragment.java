package com.polly.program.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.polly.program.Constants;
import com.polly.program.R;
import com.polly.program.ui.main.MainActivity;
import com.polly.program.util.AnimationUtil;
import com.polly.program.util.OnClickEvent;
import com.polly.program.util.SharedPreferencesUtil;
import com.polly.program.widget.StatusBarCompat;
import com.polly.program.widget.ToastEx;

import butterknife.ButterKnife;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements View.OnClickListener, IBaseView {

    protected Activity mContext;
    protected View rootView;
    protected TextView mTvTitle;

    protected P mPresenter;

    protected SharedPreferencesUtil spUtil;

    private ProgressDialog mProgressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        mContext = getActivity();
        ButterKnife.bind(this, rootView);
        mTvTitle = (TextView) rootView.findViewById(R.id.tv_title);
        if (mTvTitle != null) {
            StatusBarCompat.compat(mContext);
        } else {
            StatusBarCompat.compat(mContext, android.R.color.transparent);
        }
        spUtil = new SharedPreferencesUtil(mContext);
        needRefresh = true;
        initTitleBar();
        initData();
        if (getUserVisibleHint()) {
            needRefresh = false;
            lazyLoad();
        }
        initListener();
        return rootView;
    }

    protected abstract int getLayoutId();

    protected abstract void initTitleBar();

    protected abstract void initData();

    protected abstract void lazyLoad();

    protected abstract void initListener();

    public void setTitle(String title) {
        if (mTvTitle != null)
            mTvTitle.setText(title != null ? title : "");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDetach();
    }

    protected boolean needRefresh = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            if (needRefresh) {
                needRefresh = false;
                lazyLoad();
            }
        }
    }

    @Override
    public void setStatus(int status) {
        LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.llyt_status);
        if (layout != null) {
            if (status == Constants.PageStatus.NORMAL) {
                layout.setVisibility(View.GONE);
            } else {
                layout.setVisibility(View.VISIBLE);
                ImageView imageView = (ImageView) rootView.findViewById(R.id.iv_status);
                TextView textView = (TextView) rootView.findViewById(R.id.tv_status);
                TextView button = (TextView) rootView.findViewById(R.id.btn_status);
                if (status == Constants.PageStatus.EMPTY) {
                    imageView.setImageResource(R.mipmap.ws_ic_no_data);
                    textView.setText("暂无数据");
                } else if (status == Constants.PageStatus.ERROR) {
                    imageView.setImageResource(R.mipmap.ws_server_error_exception);
                    textView.setText("加载失败，请稍后重试···");
                } else if (status == Constants.PageStatus.NO_NETWORK) {
                    imageView.setImageResource(R.mipmap.ws_no_net_exception);
                    textView.setText("无网络连接，请检查网络···");
                }
                button.setOnClickListener(new OnClickEvent() {
                    @Override
                    public void onSingleClick(View v) {
                        lazyLoad();
                    }
                });
            }
        }
    }

    protected void toast(String msg) {
        ToastEx toast = ToastEx.getInstance(mContext);
        toast.setText(msg);
        toast.show();
    }

    protected void toActivity(Class<? extends BaseActivity> activity) {
        startActivity(new Intent(mContext, activity));
    }

    protected void toActivityForResult(Class<? extends BaseActivity> activity) {
        toActivityForResult(activity, Constants.NORMAL_REQUESTCODE);
    }

    protected void toActivityForResult(Class<? extends BaseActivity> activity, int requestCode) {
        startActivityForResult(new Intent(mContext, activity), requestCode);
    }

    /**
     * 如果已经启动了四个Activity：A，B，C和D。在D Activity里，我们要跳到B Activity
     */
    protected void toMainActivity() {
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//如果不加这句，则B Activity会finished掉，再启动一个新的Activity B。
        startActivity(intent);//重用之前的B Activity，同时调用B Activity的onNewIntent()方法
    }

    protected void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setMessage("正在加载...");
        }
        mProgressDialog.show();
    }

    protected void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    @Override
    public void showToast(String msg) {
        toast(msg);
    }

    @Override
    public void showProgress() {
        showProgressDialog();
    }

    @Override
    public void hideProgress() {
        dismissProgressDialog();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        AnimationUtil.verticalStart(mContext);
    }
}
