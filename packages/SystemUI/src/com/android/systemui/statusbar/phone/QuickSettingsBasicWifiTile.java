/*
 * Copyright (C) 2013 The Android Open Source Project
 * This code has been modified. Portions copyright (C) 2013, OmniRom Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.systemui.R;

public class QuickSettingsBasicWifiTile extends QuickSettingsTileView {
    private final TextView mTextView;
    private final ImageView mImageView;
    private final ProgressBar mLoadingView;

    public QuickSettingsBasicWifiTile(Context context) {
        this(context, null);
    }

    public QuickSettingsBasicWifiTile(Context context, AttributeSet attrs) {
        this(context, attrs, R.layout.quick_settings_tile_wifi);
    }

    public QuickSettingsBasicWifiTile(Context context, AttributeSet attrs, int layoutId) {
        super(context, attrs);

        setLayoutParams(new FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            context.getResources().getDimensionPixelSize(R.dimen.quick_settings_cell_height)
        ));
        setBackgroundResource(R.drawable.qs_tile_background);
        addView(LayoutInflater.from(context).inflate(layoutId, null),
                new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT));
        mTextView = (TextView) findViewById(R.id.wifi_textview);
        mImageView = (ImageView) findViewById(R.id.wifi_image);
        mLoadingView = (ProgressBar) findViewById(R.id.wifi_loading);
    }

    @Override
    public void setContent(int layoutId, LayoutInflater inflater) {
        throw new RuntimeException("why?");
    }

    @Override
    public void setEditMode(boolean enabled) {
        // No hover on edit mode
        changeCurrentBackground(enabled);
        super.setEditMode(enabled);
    }

    public ImageView getImageView() {
        return mImageView;
    }

    public TextView getTextView() {
        return mTextView;
    }

    public void setImageDrawable(Drawable drawable) {
        mImageView.setImageDrawable(drawable);
    }

    public void setImageResource(int resId) {
        mImageView.setImageResource(resId);
    }

    public void setText(CharSequence text) {
        mTextView.setText(text);
    }

    @Override
    public void setTextSizes(int size) {
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    @Override
    public void callOnColumnsChange() {
        mTextView.invalidate();
    }

    @Override
    protected void changeCurrentUiColor(int ic_color) {
        if (mTextView != null) {
            if (ic_color != -3) {
                mTextView.setTextColor(ic_color);
            } else {
                mTextView.setTextColor(getDefaultColor());
            }
        }
        if (mImageView != null) {
            if (ic_color != -3) {
                mImageView.setColorFilter(ic_color, PorterDuff.Mode.MULTIPLY);
            } else {
                mImageView.clearColorFilter();
            }
        }
    }

    @Override
    public void changeColorIconBackground(int bg_color, int ic_color) {
        super.changeColorIconBackground(bg_color, ic_color);
        changeCurrentUiColor(ic_color);
    }

    public void setTextResource(int resId) {
        mTextView.setText(resId);
    }

    public void setLoading(boolean loading) {
        mLoadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
        mImageView.setVisibility(loading ? View.GONE : View.VISIBLE);
    }
}
