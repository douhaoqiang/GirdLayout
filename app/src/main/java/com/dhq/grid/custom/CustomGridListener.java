package com.dhq.grid.custom;

import android.view.View;
import android.widget.TextView;

import com.dhq.grid.R;
import com.dhq.gridview.BaseRvHolder;
import com.dhq.gridview.gridlistener.BaseGridListener;

/**
 * DESC
 *
 * @author douhaoqiang on 2019/3/19.
 */
public class CustomGridListener<T> implements BaseGridListener<T> {

    @Override
    public int getLayoutId() {
        return R.layout.item_custom_grid_view;
    }

    @Override
    public void convert(BaseRvHolder holder, T item, int position) {

        TextView textView = holder.getView(R.id.tv_name);
        textView.setText(""+position);
    }

    @Override
    public void addClick(int position, int count) {

    }

    @Override
    public int getAddImgResId() {
        return R.mipmap.image_add_g;
    }
}
