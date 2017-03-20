/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.polly.program.ui.picture;

import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.polly.program.Constants;
import com.polly.program.R;
import com.polly.program.base.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PictureActivity extends BaseActivity {

    @Bind(R.id.iv_title_save)
    ImageView mIvSave;
    @Bind(R.id.iv_title_share)
    ImageView mIvShare;
    @Bind(R.id.iv_picture)
    ImageView mImageView;

    private PhotoViewAttacher mPhotoViewAttacher;
    public static final String TRANSIT_PIC = "picture";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_picture;
    }

    @Override
    protected void initTitleBar() {
        setBack();
        mIvSave.setVisibility(View.VISIBLE);
        mIvShare.setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    protected void initData() {
        String mImageUrl = getIntent().getStringExtra(Constants.INTENT_EXTRA_TITLE);
        Picasso.with(this).load(mImageUrl).into(mImageView);
        ViewCompat.setTransitionName(mImageView, TRANSIT_PIC);
        setupPhotoAttacher();
    }

    @Override
    protected void initListener() {
        mIvSave.setOnClickListener(this);
        mIvShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_save:
                saveImageToGallery();
                break;
            case R.id.iv_title_share:
                toast("分享");
                break;
        }
    }

    private void setupPhotoAttacher() {
        mPhotoViewAttacher = new PhotoViewAttacher(mImageView);
        mPhotoViewAttacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                hideOrShowTitlebar();
            }
        });
    }

    private void saveImageToGallery() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPhotoViewAttacher.cleanup();
        ButterKnife.unbind(this);
    }
}
