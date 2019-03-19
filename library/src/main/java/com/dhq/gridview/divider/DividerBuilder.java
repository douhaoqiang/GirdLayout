package com.dhq.gridview.divider;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;

/**
 * DESC 分割线工具类
 * Created by douhaoqiang on 2019/3/19.
 */
public class DividerBuilder {

    private Context context;
    private Drawable drawable;
    private int columnSpace = 0;
    private int rowSpace = 0;
    private boolean hideLastDivider = true;

    private DividerBuilder(Context context) {
        this.context = context;
    }

    public static DividerBuilder getInstance(Context context){
        return new DividerBuilder(context);
    }

    public RvDivider build() {
        return new RvDivider(this);
    }


    public DividerBuilder setSpaceColor(@ColorRes int id, @DimenRes int strokeWidth) {
        this.drawable = new ColorDrawable(ContextCompat.getColor(context, id));
        this.columnSpace = this.rowSpace = context.getResources().getDimensionPixelSize(strokeWidth);
        return this;
    }

    public DividerBuilder setSpaceColor(@ColorRes int id) {
        this.drawable = new ColorDrawable(ContextCompat.getColor(context, id));
        return this;
    }

    public DividerBuilder setColumnSpace(@DimenRes int strokeWidth) {
        this.columnSpace = context.getResources().getDimensionPixelSize(strokeWidth);
        return this;
    }

    public DividerBuilder setRowSpace(@DimenRes int strokeWidth) {
        this.rowSpace = context.getResources().getDimensionPixelSize(strokeWidth);
        return this;
    }

    public DividerBuilder setSpace(@DimenRes int strokeWidth) {
        this.columnSpace = this.rowSpace = context.getResources().getDimensionPixelSize(strokeWidth);
        return this;
    }


    public Context getContext() {
        return context;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public DividerBuilder setDrawable(@DrawableRes int id) {
        this.drawable = ResourcesCompat.getDrawable(context.getResources(), id, context.getTheme());
        return this;
    }

    public int getColumnSpace() {
        return columnSpace;
    }

    public int getRowSpace() {
        return rowSpace;
    }

    public boolean isHideLastDivider() {
        return hideLastDivider;
    }

    public DividerBuilder setHideLastDivider(boolean hideLastDivider) {
        this.hideLastDivider = hideLastDivider;
        return this;
    }

}
