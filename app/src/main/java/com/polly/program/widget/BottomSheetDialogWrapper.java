package com.polly.program.widget;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.polly.program.R;

/**
 * Created by linfeizheng on 2017/4/21.
 */

public class BottomSheetDialogWrapper extends BottomSheetDialog implements View.OnClickListener {

    private BottomSheetDialog dialog;
    private Callback callback;

    public BottomSheetDialogWrapper(Context mContext, final Callback callback) {
        super(mContext);
        this.callback = callback;
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_bottom_sheet, null);
        dialog = new BottomSheetDialog(mContext);
        dialog.setContentView(view);
        TextView mTvMark = (TextView) view.findViewById(R.id.tv_bottom_sheet_mark);
        TextView mTvDelete = (TextView) view.findViewById(R.id.tv_bottom_sheet_delete);
        mTvMark.setOnClickListener(this);
        mTvDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        callback.callback(dialog, ((TextView) v).getText().toString());
    }

    public interface Callback {
        void callback(BottomSheetDialog dialog, String text);
    }
}
