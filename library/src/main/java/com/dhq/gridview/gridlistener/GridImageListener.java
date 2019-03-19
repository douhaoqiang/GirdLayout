package com.dhq.gridview.gridlistener;

import android.widget.ImageView;

import com.dhq.gridview.BaseRvHolder;
import com.dhq.gridview.GridLayout;

/**
 * DESC 只是简单的图片
 * Created by douhaoqiang on 2019/3/19.
 */
public class GridImageListener<T> implements BaseGridListener<T> {


    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void convert(BaseRvHolder holder, T item, int position) {

    }

    @Override
    public void addNewView(int position, int count) {

    }

    @Override
    public int getAddImgResId() {
        return 0;
    }
}
