package com.dhq.gridview.divider;


import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * DESC 分割线工具
 * Created by douhaoqiang on 2017/11/17.
 */

public class RvDivider extends RecyclerView.ItemDecoration {

    private Rect mBounds = new Rect();
    private Drawable mDivider;
    private int mColumnSpace;
    private int mRowSpace;
    private boolean mHideRoundDivider;//是否显示四周分割线
    private int mSpanCount = 1;
    private int mItemWidth;
    private int mItemHeight;


    public RvDivider(DividerBuilder builder) {

        this.mDivider = builder.getDrawable();
        this.mColumnSpace = builder.getColumnSpace();
        this.mRowSpace = builder.getRowSpace();
        this.mHideRoundDivider = builder.isHideLastDivider();

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawDivider(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        calculateItemOffsets(outRect, view, parent, state);
    }

    /**
     * 绘制分割线
     *
     * @param c       画布
     * @param parent  父布局
     * @param state   状态
     */
    public void drawDivider(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() == null || mDivider == null) {
            return;
        }
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        //判断是否是LinearLayoutManager
        if (layoutManager instanceof GridLayoutManager) {
            //获取列数
            mSpanCount = ((GridLayoutManager) layoutManager).getSpanCount();

            drawSpace(c, parent);

        }
    }

    /**
     * 计算偏移量
     *
     * @param outRect   item范围
     * @param view      item 布局
     * @param parent    父布局
     * @param state     状态
     */
    public void calculateItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        mSpanCount = layoutManager.getSpanCount();

        final int position = parent.getChildAdapterPosition(view);

        int column = position % mSpanCount; // item column

        if (mHideRoundDivider) {
            outRect.left = column * mColumnSpace / mSpanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = mColumnSpace - (column + 1) * mColumnSpace / mSpanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= mSpanCount) {
                outRect.top = mColumnSpace; // item top
            }
        } else {
            outRect.left = mColumnSpace - column * mColumnSpace / mSpanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * mColumnSpace / mSpanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < mSpanCount) { // top edge
                outRect.top = mColumnSpace;
            }
            outRect.bottom = mColumnSpace; // item bottom
        }

    }

    /**
     * 添加分割线
     *
     * @param recyclerView target recyclerView
     */
    public void addTo(RecyclerView recyclerView) {
        recyclerView.addItemDecoration(this);
    }

    private void drawSpace(Canvas canvas, RecyclerView parent) {

        drawSpaceTest(canvas, parent);

        return;

//        canvas.save();
//        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
//        mSpanCount = layoutManager.getSpanCount();
//        final int childCount = parent.getChildCount();
//
//        float left = 0;
//        float top = 0;
//        float right = 0;
//        float bottom = 0;
//
//        int rowNum = (childCount - 1) / mSpanCount + 1;
//
//        if (!mHideRoundDivider) {
//            for (int i = 0; i <= rowNum; i++) {
//
//                int parentHeight = parent.getMeasuredHeight() - parent.getPaddingTop() - parent.getPaddingBottom();
//
//                float dividerCenter = parent.getPaddingTop() + parentHeight / rowNum * i;
//
//                top = dividerCenter - mColumnSpace / 2;
//                left = parent.getPaddingLeft();
//                right = parent.getRight() - parent.getPaddingRight();
//                bottom = dividerCenter + mColumnSpace / 2;
//                Drawable drawable = getDivider();
//                drawable.setBounds((int) left, (int) top, (int) right, (int) bottom);
//                drawable.draw(canvas);
//            }
//        } else {
//            for (int i = 1; i < rowNum; i++) {
//
//                int parentHeight = parent.getMeasuredHeight() - parent.getPaddingTop() - parent.getPaddingBottom();
//
//                float dividerCenter = parent.getPaddingTop() + parentHeight / rowNum * i;
//
//                top = dividerCenter - mColumnSpace / 2;
//                left = parent.getPaddingLeft();
//                right = parent.getRight() - parent.getPaddingRight();
//                bottom = dividerCenter + mColumnSpace / 2;
//                Drawable drawable = getDivider();
//                drawable.setBounds((int) left, (int) top, (int) right, (int) bottom);
//                drawable.draw(canvas);
//            }
//        }
//
//        if (!mHideRoundDivider) {
//            for (int i = 0; i <= mSpanCount; i++) {
//
//                int parentWidth = parent.getMeasuredWidth() - parent.getPaddingLeft() - parent.getPaddingRight();
//
//                float dividerCenter = parent.getPaddingLeft() + parentWidth / mSpanCount * i;
//
//                top = parent.getPaddingTop();
//                left = dividerCenter - mColumnSpace / 2;
//                right = dividerCenter + mColumnSpace / 2;
//                bottom = parent.getBottom() - parent.getPaddingBottom();
//
//                Drawable drawable = getDivider();
//                drawable.setBounds((int) left, (int) top, (int) right, (int) bottom);
//                drawable.draw(canvas);
//            }
//        } else {
//            for (int i = 1; i < mSpanCount; i++) {
//
//                int parentWidth = parent.getMeasuredWidth() - parent.getPaddingLeft() - parent.getPaddingRight();
//
//                float dividerCenter = parent.getPaddingLeft() + parentWidth / mSpanCount * i;
//
//                top = parent.getPaddingTop();
//                left = dividerCenter - mColumnSpace / 2;
//                right = dividerCenter + mColumnSpace / 2;
//                bottom = parent.getBottom() - parent.getPaddingBottom();
//
//                Drawable drawable = getDivider();
//                drawable.setBounds((int) left, (int) top, (int) right, (int) bottom);
//                drawable.draw(canvas);
//            }
//        }
//
//
//        canvas.restore();


    }


    private void drawSpaceTest(Canvas canvas, RecyclerView parent) {

        canvas.save();
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        mSpanCount = layoutManager.getSpanCount();
        final int childCount = parent.getChildCount();
        float left = 0;
        float top = 0;
        float right = 0;
        float bottom = 0;

        int rowNum = (childCount - 1) / mSpanCount + 1;
        int parentWidth = parent.getMeasuredWidth() - parent.getPaddingLeft() - parent.getPaddingRight();
        int parentHeight = parent.getMeasuredHeight() - parent.getPaddingTop() - parent.getPaddingBottom();
        int lastSpan = childCount % mSpanCount;
        if (!mHideRoundDivider) {
            //绘制行
            for (int i = 0; i <= rowNum; i++) {

                Drawable drawable = getDivider();

                if (i == rowNum) {
                    float dividerCenter = parent.getPaddingTop() + parentHeight / rowNum * i;
                    top = dividerCenter - mColumnSpace / 2;
                    left = parent.getPaddingLeft();
                    float lastRowdividerWidth = 0;
                    if (lastSpan == 0) {
                        lastRowdividerWidth = parent.getRight() - parent.getPaddingRight();
                    } else {
                        lastRowdividerWidth = parent.getPaddingLeft() + parentWidth / mSpanCount * lastSpan;
                    }
                    right = lastRowdividerWidth;
                    bottom = dividerCenter + mColumnSpace / 2;
                    drawable.setBounds((int) left, (int) top, (int) right, (int) bottom);
                } else {
                    float dividerCenter = parent.getPaddingTop() + parentHeight / rowNum * i;
                    if (rowNum == 1 && i == 0) {
                        top = dividerCenter - mColumnSpace / 2;
                        left = parent.getPaddingLeft();
                        float lastRowdividerWidth = 0;
                        if (lastSpan == 0) {
                            lastRowdividerWidth = parent.getRight() - parent.getPaddingRight();
                        } else {
                            lastRowdividerWidth = parent.getPaddingLeft() + parentWidth / mSpanCount * lastSpan;
                        }
                        right = lastRowdividerWidth;
                        bottom = dividerCenter + mColumnSpace / 2;
                        drawable.setBounds((int) left, (int) top, (int) right, (int) bottom);
                    } else {
                        top = dividerCenter - mColumnSpace / 2;
                        left = parent.getPaddingLeft();
                        right = parent.getRight() - parent.getPaddingRight();
                        bottom = dividerCenter + mColumnSpace / 2;
                        drawable.setBounds((int) left, (int) top, (int) right, (int) bottom);
                    }

                }

                drawable.draw(canvas);

            }
        } else {
            for (int i = 1; i < rowNum; i++) {
                float dividerCenter = parent.getPaddingTop() + parentHeight / rowNum * i;
                top = dividerCenter - mColumnSpace / 2;
                left = parent.getPaddingLeft();
                right = parent.getRight() - parent.getPaddingRight();
                bottom = dividerCenter + mColumnSpace / 2;
                Drawable drawable = getDivider();
                drawable.setBounds((int) left, (int) top, (int) right, (int) bottom);
                drawable.draw(canvas);
            }
        }

        if (!mHideRoundDivider) {
            //绘制列
            int columns = mSpanCount;
            if (rowNum == 1 && lastSpan > 0) {
                columns = lastSpan;
            }
            for (int i = 0; i <= columns; i++) {

                float dividerCenter = parent.getPaddingLeft() + parentWidth / mSpanCount * i;

                top = parent.getPaddingTop();
                left = dividerCenter - mColumnSpace / 2;
                right = dividerCenter + mColumnSpace / 2;
                if (rowNum > 1 && i > lastSpan) {
                    bottom = parent.getChildAt(childCount - 1).getTop();
                } else {
                    bottom = parent.getBottom() - parent.getPaddingBottom();
                }

                Drawable drawable = getDivider();
                drawable.setBounds((int) left, (int) top, (int) right, (int) bottom);
                drawable.draw(canvas);
            }
        } else {
            for (int i = 1; i < mSpanCount; i++) {

                float dividerCenter = parent.getPaddingLeft() + parentWidth / mSpanCount * i;

                top = parent.getPaddingTop();
                left = dividerCenter - mColumnSpace / 2;
                right = dividerCenter + mColumnSpace / 2;
                bottom = parent.getBottom() - parent.getPaddingBottom();

                Drawable drawable = getDivider();
                drawable.setBounds((int) left, (int) top, (int) right, (int) bottom);
                drawable.draw(canvas);
            }
        }

        canvas.restore();
    }

    private Drawable getDivider() {
        return mDivider;

    }

}
