package com.dhq.gridview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * DESC 比例ImageView
 * Created by douhaoqiang on 2017/10/24.
 */

public class RatioImageView extends android.support.v7.widget.AppCompatImageView {

    private float mRatio = 1;//宽高相等
    private int mRatioMode = 1;//宽高比例计算方式 1--dependWidth  2--dependHeight

    public RatioImageView(Context context) {
        super(context);
    }

    public RatioImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RatioImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    private void init(AttributeSet attrs){
        TypedArray arr = getContext().obtainStyledAttributes(attrs, R.styleable.RatioImageView);

        mRatio = arr.getFloat(R.styleable.RatioImageView_ratio, 1.0f);

        mRatioMode = arr.getInt(R.styleable.RatioImageView_ratioMode, 1);

    }

    /**
     * 设置ImageView的宽高比
     *
     * @param ratio
     */
    public void setRatio(float ratio) {
        mRatio = ratio;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (mRatioMode==1){
            //dependWidth
            if (mRatio != 0) {
                float height = measureWidth / mRatio;
                heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) height, MeasureSpec.EXACTLY);
            }
        }else if (mRatioMode==2){
            //dependHeight
            if (mRatio != 0) {
                float width = measureHeight / mRatio;
                widthMeasureSpec = MeasureSpec.makeMeasureSpec((int) width, MeasureSpec.EXACTLY);
            }
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }


}
