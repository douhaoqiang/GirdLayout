package com.dhq.gridview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.dhq.gridview.divider.RvDivider;
import com.dhq.gridview.gridlistener.BaseGridListener;

import java.util.List;

/**
 * DESC 九宫格图片展示
 * Created by douhaoqiang on 2017/9/6.
 */

public class GridLayout<T> extends LinearLayout {


    private RecyclerView mRecyclerView;
    private GridImageAdapter<T> mAdapter;
    /**
     * 显示列数
     */
    private int mColumnCount = 3;
    /**
     * 是否可以显示添加布局
     */
    private boolean mIsCanAdd = false;
    /**
     * 没有图片时显示的图片资源id
     */
    private int noImgSrcId = -1;
    private GridImageBuild mGridImageBuild;

    public GridLayout(Context context) {
        this(context, null);
    }

    public GridLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttrs(attrs);
    }

    private void parseAttrs(AttributeSet attrs) {
        TypedArray arr = getContext().obtainStyledAttributes(attrs, R.styleable.GridLayout);

        //分为几列
        mColumnCount = arr.getInteger(R.styleable.GridLayout_gridImageColumn, 3);
        mIsCanAdd = arr.getBoolean(R.styleable.GridLayout_isCanAdd, false);
        noImgSrcId = arr.getResourceId(R.styleable.GridLayout_noImgSrc, -1);
        arr.recycle();
        initView();

    }

    protected void initView() {

        inflate(getContext(), R.layout.nine_image_view, this);

        mRecyclerView = findViewById(R.id.rv_mul_img);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), mColumnCount));

    }


    private void buildView() {

        mAdapter = new GridImageAdapter<T>(mGridImageBuild.getMaxCount(), mGridImageBuild.getImageListener());

        if (mGridImageBuild.isSetCanAdd) {
            mAdapter.setCanAdd(mGridImageBuild.isCanAdd);
        } else {
            mAdapter.setCanAdd(mIsCanAdd);
        }


        if (noImgSrcId != -1) {
            mAdapter.setShowNoImg(noImgSrcId);
        }
        mRecyclerView.setAdapter(mAdapter);

        if (mGridImageBuild.divider == null || !mGridImageBuild.isSetDivider) {
            return;
        }
        mGridImageBuild.divider.addTo(mRecyclerView);
    }


    public void addData(T data) {
        mAdapter.addData(data);
    }

    public void addDatas(List<T> datas) {
        mAdapter.addDatas(datas);
    }

    public void setDatas(List<T> datas) {
        mAdapter.setDatas(datas);
    }

    public List<T> getDatas() {
        return mAdapter.getDatas();
    }


    public GridImageBuild getGridImageBuild() {
        if (mGridImageBuild == null) {
            mGridImageBuild = new GridImageBuild(this);
        }
        return mGridImageBuild;
    }

    public void notifyDataSetChanged() {
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 九宫格数据监听
     */
    public static class GridImageBuild {

        private GridLayout gridImageView = null;//gridview视图
        private int maxCount = 9;//图片最大数量
        private BaseGridListener imageListener = null;//gridview监听
        private boolean isSetDivider = true;//表示是否可以设置分割线，防止adapter中分割线重复添加多次的问题
        private RvDivider divider = null;//Grid分割线
        private boolean isSetCanAdd = false;
        private boolean isCanAdd = false;


        public GridImageBuild(GridLayout gridImageView) {
            this.gridImageView = gridImageView;
        }

        public int getMaxCount() {
            return maxCount;
        }

        public BaseGridListener getImageListener() {
            return imageListener;
        }


        public GridImageBuild setCanAdd(boolean canAdd) {
            isSetCanAdd = true;
            isCanAdd = canAdd;
            return this;
        }

        /**
         * 设置最大数量
         *
         * @param maxCount
         * @return
         */
        public GridImageBuild setMaxCount(int maxCount) {
            this.maxCount = maxCount;
            return this;
        }

        /**
         * 设置分割线
         *
         * @param gridDivider
         * @return
         */
        public GridImageBuild setDivider(RvDivider gridDivider) {
            if (divider != null) {
                isSetDivider = false;
            }
            divider = gridDivider;
            return this;
        }

        /**
         * 设置监听
         *
         * @param imageListener
         * @return
         */
        public GridImageBuild setImageListener(BaseGridListener imageListener) {
            this.imageListener = imageListener;

            return this;
        }


        public void build() {
            gridImageView.buildView();
        }
    }


}
