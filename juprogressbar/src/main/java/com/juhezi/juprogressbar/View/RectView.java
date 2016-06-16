package com.juhezi.juprogressbar.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.juhezi.juprogressbar.DataStructure.LoopQueue;
import com.juhezi.juprogressbar.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 旋转跳跃的方块
 *
 * Created by qiaoyunrui on 16-6-15.
 */
public class RectView extends View {

    private static final String TAG = "RectView";

    private int defHeight = 50;  //默认高度和宽度
    private int defWidth = 50;
    private LoopQueue<Integer> colors;
    private int colorNum = 1;   //颜色的数量
    private Paint mPaint;
    private float centerX = 0;  //进度条的中心位置
    private float centerY = 0;
    private float mAnimPercent = 0f; //动画完成百分比
    private float radius = 5f; //圆角矩形的
    private float lineLen = 35f;   //圆角矩形的边长

    private int rectColor = Color.BLUE;

    public RectView(Context context) {
        super(context);
        init();
    }

    public RectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    /**
     * 初始化
     */
    private void init() {
        colors = new LoopQueue<>(colorNum);
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    /**
     * 该函数会多次执行。。。
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(dip2px(defWidth), dip2px(defHeight));
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(dip2px(defWidth), heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, dip2px(defHeight));
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(getVisibility() == GONE) {
            return;
        }

        //支持padding
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingBottom = getPaddingBottom();
        final int paddingTop = getPaddingTop();

        //获取真正的width、height
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingBottom - paddingTop;

        centerX = width / 2f + paddingLeft;
        centerY = height / 2f + paddingBottom;

        canvas.drawRoundRect(centerX - dip2px(lineLen / 2),
                centerY - dip2px(lineLen / 2),
                centerX + dip2px(lineLen / 2),
                centerY + dip2px(lineLen / 2),
                dip2px(radius),
                dip2px(radius),
                mPaint);
    }

    private int dip2px(float dipValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public void setColorNum(int colorNum) {
        this.colorNum = colorNum;
        colors.setLength(colorNum);
    }

    public void addColor(int color) {
        colors.add(color);
    }

    public void setRectColor(int color) {

        if(mPaint != null) {
            mPaint.setColor(color);
            invalidate();
        }

    }

    public int getRectColor() {
        return rectColor;
    }

    public int getNextColor() {
        return colors.next();
    }
}
