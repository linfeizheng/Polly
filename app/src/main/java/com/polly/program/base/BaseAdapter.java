package com.polly.program.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends Adapter<VH> {

    protected Context mContext;
    protected List<T> mData;

    protected LayoutInflater mInflater;

    public BaseAdapter(Context mContext) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<T> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    public void insert(T t) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.add(t);
        notifyItemInserted(mData.size() - 1);
    }

    protected void remove(List<T> mList) {
        if (mData != null) {
            mData.removeAll(mList);
        }
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }

    @Override
    public abstract void onBindViewHolder(VH holder, int arg1);

    @Override
    public abstract VH onCreateViewHolder(ViewGroup parent, int position);

}
