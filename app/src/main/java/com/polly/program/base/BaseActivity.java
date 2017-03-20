package com.polly.program.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.polly.program.Constants;
import com.polly.program.R;
import com.polly.program.ui.main.MainActivity;
import com.polly.program.util.ApplicationUtil;
import com.polly.program.util.OnClickEvent;
import com.polly.program.widget.StatusBarCompat;
import com.polly.program.widget.ToastEx;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenterImpl> extends AppCompatActivity implements View.OnClickListener, IBaseView {

    protected AppCompatActivity mContext;
    protected LinearLayout mTitleLayout;
    protected TextView mTvTitle;

    protected P mPresenter;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mTitleLayout = (LinearLayout) findViewById(R.id.llyt_header);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
//        if (mTvTitle != null) {
//            StatusBarCompat.compat(this);
//        } else {
//            StatusBarCompat.compat(this, android.R.color.transparent);
//        }
        ApplicationUtil.addActivity(this);
        initData();
        initTitleBar();
        initListener();
    }

    protected abstract int getLayoutId();

    protected abstract void initTitleBar();

    protected abstract void initData();

    protected abstract void initListener();

    public void setTitle(CharSequence title) {
        if (mTvTitle != null) {
            mTvTitle.setText(title != null ? title : "");
        }
    }

    public void setBack() {
        TextView imageBack = (TextView) findViewById(R.id.tv_title_back);
        if (imageBack != null) {
            imageBack.setVisibility(View.VISIBLE);
            imageBack.setOnClickListener(new OnClickEvent() {
                @Override
                public void onSingleClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDetach();
        ButterKnife.unbind(this);
        ApplicationUtil.removeActivity(this);
    }

    protected void toast(String msg) {
        ToastEx toast = ToastEx.getInstance(getApplicationContext());
        toast.setText(msg);
        toast.show();
    }

    protected void toActivity(Class<?> activity) {
        startActivity(new Intent(mContext, activity));
    }

    protected void toActivityWithFinish(Class<?> activity) {
        toActivity(activity);
        finish();
    }

    protected void toActivityForResult(Class<?> activity) {
        toActivityForResult(activity, Constants.NORMAL_REQUESTCODE);
    }

    protected void toActivityForResult(Class<?> activity, int requestCode) {
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
//        AnimationUtils.horizontalStart(this);
    }

    @Override
    public void finish() {
        super.finish();
//        if (!(mContext instanceof LoadingActivity || mContext instanceof LoginActivity))
//            AnimationUtils.horizontalFinish(mContext);
    }

    private boolean mIsHidden;

    public void hideOrShowTitlebar() {
        if (mTitleLayout != null) {
            mTitleLayout.animate()
                    .translationY(mIsHidden ? 0 : -mTitleLayout.getHeight())
                    .setInterpolator(new DecelerateInterpolator(2))
                    .start();
            mIsHidden = !mIsHidden;
        }
    }

}
