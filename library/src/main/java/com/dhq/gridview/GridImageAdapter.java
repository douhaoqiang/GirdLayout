package com.dhq.gridview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dhq.gridview.gridlistener.BaseGridListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * DESC RecycleView 的公用GridAdapter
 * Created by douhaoqiang on 2016/9/6.
 */

public class GridImageAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<T> datas = new ArrayList<>();
    private int maxCount;//最大数量
    private boolean isCanAdd = true;//表示是否可以添加图片
    private int noImgResId = -1;//表示是否可以添加图片
    private BaseGridListener mNineListener;//九宫格数据监听


    public GridImageAdapter(int maxCount, BaseGridListener nineListener) {
        this.maxCount = maxCount;
        this.mNineListener = nineListener;
    }

    public void setCanAdd(boolean isCanAdd) {
        this.isCanAdd = isCanAdd;
        notifyDataSetChanged();
    }

    public void setShowNoImg(int imgSrcId) {
        noImgResId = imgSrcId;
    }

    public List<T> getDatas() {
        return datas;
    }

    /**
     * 设置列表数据
     *
     * @param datas 列表数据
     */
    public void setDatas(List<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    /**
     * 添加单个数据
     *
     * @param data 列表数据
     */
    public void addData(T data) {
        this.datas.add(data);
        notifyDataSetChanged();
    }

    /**
     * 添加单个数据到一个位置
     *
     * @param data 列表数据
     */
    public void addData(T data, int position) {
        this.datas.add(position, data);
        notifyItemInserted(position);
    }

    /**
     * 添加多个数据
     *
     * @param datas 要添加的多个数据
     */
    public void addDatas(List<T> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 移除单个item
     *
     * @param position 要移除的item下标
     */
    public void removeData(int position) {
        this.datas.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 拖拽交换数据
     *
     * @param dragPosition   拖拽的item下标
     * @param targetPosition 要交换的item的下标
     */
    public void changeItem(int dragPosition, int targetPosition) {
        Collections.swap(datas, dragPosition, targetPosition);
        notifyItemMoved(dragPosition, targetPosition);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(mNineListener.getLayoutId(), parent, false);

        return new BaseRvHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        int size = datas.size();

        final View imageView = ((BaseRvHolder) holder).getRootView();

        imageView.setOnClickListener(null);
        if (isCanAdd){
            //表示是添加视图
            if (position == datas.size()) {
                imageView.setBackgroundResource(mNineListener.getAddImgResId());
//                imageView.setImageResource(mNineListener.getAddImgResId());
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mNineListener.addClick(position, datas.size());
                    }
                });
            } else {
                mNineListener.convert((BaseRvHolder) holder, datas.get(position), position);
            }
        }else {
            //表示是展示视图
            if (size==0){
                imageView.setBackgroundResource(noImgResId);
//                imageView.setImageResource(noImgResId);
            }else {
                mNineListener.convert((BaseRvHolder) holder, datas.get(position), position);
            }
        }
    }

    @Override
    public int getItemCount() {

        int size = datas.size();

        if (isCanAdd) {
            if (size >= maxCount) {
                isCanAdd = false;
                return maxCount;
            } else {
                return size + 1;
            }
        } else {
            if (size >= maxCount) {
                return maxCount;
            } else {
                if (size == 0 && noImgResId!=-1) {
                    return size + 1;
                } else {
                    return size;
                }
            }
        }

    }

}
