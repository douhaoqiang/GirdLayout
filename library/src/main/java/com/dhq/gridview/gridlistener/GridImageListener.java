package com.dhq.gridview.gridlistener;

import android.view.View;
import android.widget.ImageView;

import com.dhq.gridview.BaseRvHolder;
import com.dhq.gridview.R;

/**
 * DESC 只是简单的图片
 * Created by douhaoqiang on 2019/3/19.
 */
public abstract class GridImageListener<T> implements BaseGridListener<T> {


    @Override
    public int getLayoutId() {
        return R.layout.item_image_view;
    }

    @Override
    public void convert(BaseRvHolder holder, T item, int position) {
        ImageView imageView = holder.getView(R.id.iv_photo);
        imageView.setImageResource(R.mipmap.image_add_g);
        loadImage(imageView,item);
    }

    @Override
    public int getAddImgResId() {
        return R.mipmap.image_add_g;
    }


    public abstract void loadImage(ImageView imageView, T item);


}
