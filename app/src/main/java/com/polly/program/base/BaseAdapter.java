package com.polly.program.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.polly.program.widget.FooterView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends Adapter<VH> {

    /**
     * 最多一个header一个footer
     */
    public static final int TYPE_HEADER = -1;
    public static final int TYPE_NORMAL = -2;
    public static final int TYPE_FOOTER = -3;

    private View headerView;
    private FooterView footerView;

    protected Context mContext;
    protected List<T> mData;

    protected LayoutInflater mInflater;

    public BaseAdapter(Context mContext) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        if (mData == null) {
            mData = new ArrayList<>();
        }
    }

    public void setData(List<T> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    public void insert(T t) {
        mData.add(t);
        notifyItemInserted(getStart() + mData.size());
    }

    public void remove(int position) {
        if (position >= 0 && position < getItemCount()) {
            mData.remove(position);
            notifyItemRemoved(getStart() + position);
            if (position != mData.size()) {      // 这个判断的意义就是如果移除的是最后一个，就不用管它了，= =whatever
                notifyItemRangeChanged(getStart() + position, mData.size() - position);
            }
        }
    }

    public void setHeaderView(View headerView) {
        this.headerView = headerView;
    }

    protected void setFooterView(FooterView footerView) {
        this.footerView = footerView;
    }

    public FooterView getFooterView() {
        return footerView;
    }

    public void setPullLoadEnabled(boolean enabled) {
        setFooterView(enabled ? new FooterView(mContext) : null);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (headerView != null) {
            count += 1;
        }
        if (footerView != null) {
            count += 1;
        }
        if (mData != null) {
            count += mData.size();
        }
        return count;
    }

    public int getAdapterItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (!isHeader(position) && !isFooter(position)) {
            onBindViewHolderExtend(holder, position - getStart());
        }
    }

    public abstract void onBindViewHolderExtend(VH holder, int position);

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return getViewHolder(headerView);
        } else if (viewType == TYPE_FOOTER) {
            return getViewHolder(footerView);
        } else {
            View itemView = mInflater.inflate(getItemViewLayoutId(), parent, false);
            return getViewHolder(itemView);
        }
    }

    public abstract int getItemViewLayoutId();

    @Override
    public final int getItemViewType(int position) {
        if (isHeader(position)) {
            return TYPE_HEADER;
        } else if (isFooter(position)) {
            return TYPE_FOOTER;
        } else {
            position = getStart() > 0 ? position - 1 : position;
            return getAdapterItemViewType(position);
        }
    }

    public int getAdapterItemViewType(int position) {
        return TYPE_NORMAL;
    }

    public int getStart() {
        return headerView == null ? 0 : 1;
    }

    public boolean isFooter(int position) {
        return footerView != null && position >= getItemCount() - 1;
    }

    public boolean isHeader(int position) {
        return getStart() > 0 && position == 0;
    }

    public abstract VH getViewHolder(View view);

    public abstract class BaseHolder extends RecyclerView.ViewHolder {

        public BaseHolder(View itemView) {
            super(itemView);
            if (itemView != headerView && itemView != footerView) {
                bindViewHolder(itemView);
            }
        }

        public abstract void bindViewHolder(View itemView);
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    protected OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemLongClickListener {
        boolean onLongClick(View view, int position);
    }

    protected OnItemLongClickListener onItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.onItemLongClickListener = listener;
    }

}
