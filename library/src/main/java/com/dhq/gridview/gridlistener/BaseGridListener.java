package com.dhq.gridview.gridlistener;

import com.dhq.gridview.BaseRvHolder;

/**
 * DESC grid布局监听类
 * Created by douhaoqiang on 2018/11/13.
 */
public interface BaseGridListener<T> {


    /**
     * 设置itemview的资源id
     *
     * @return
     */
    int getLayoutId();

    /**
     * 显示有itemView的位置
     *
     * @param holder
     * @param item
     * @param position
     */
    void convert(BaseRvHolder holder, T item, int position);

    /**
     * 显示添加新的gridItem
     *
     * @param position 位置
     * @param count    现在图片数量
     */
    void addNewView(int position, int count);

    /**
     * 获取添加图片位置图片资源id
     *
     * @return
     */
    int getAddImgResId();

}
