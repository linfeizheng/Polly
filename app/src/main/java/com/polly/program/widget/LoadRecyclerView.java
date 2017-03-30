package com.polly.program.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import com.polly.program.base.BaseAdapter;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

/**
 * @author linfeizheng
 * @date 2017/3/14 15:03
 */

public class LoadRecyclerView extends RecyclerView {

    private int lastVisibleItem;
    private boolean hasLoadAll;

    public LoadRecyclerView(Context context) {
        this(context, null);
    }

    public LoadRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!hasLoadAll) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 2 >= (getLayoutManager()).getItemCount()) {
                        if (mListener != null)
                            mListener.onLoadMore();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (getLayoutManager() instanceof LinearLayoutManager) {
                    lastVisibleItem = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
                } else if (getLayoutManager() instanceof GridLayoutManager) {
                    lastVisibleItem = ((GridLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
                } else if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
                    int[] positions = ((StaggeredGridLayoutManager) getLayoutManager()).findLastVisibleItemPositions(null);
                    lastVisibleItem = Math.max(positions[0], positions[1]);
                }
            }
        });
    }

    public void setLoadComplete(boolean flag) {
        hasLoadAll = flag;
        BaseAdapter adapter = null;
        if (getAdapter() instanceof BaseAdapter) {
            adapter = (BaseAdapter) getAdapter();
        } else if (getAdapter() instanceof ScaleInAnimationAdapter) {
            adapter = (BaseAdapter) ((ScaleInAnimationAdapter) getAdapter()).getWrappedAdapter();
        }
        if (adapter != null && adapter.getFooterView() != null)
            adapter.getFooterView().setText(flag ? "- - - - - 我是底线 - - - - -" : "加载中...");
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    private OnLoadMoreListener mListener;

    public void setOnLoadMoreListener(OnLoadMoreListener mListener) {
        this.mListener = mListener;
    }
}
