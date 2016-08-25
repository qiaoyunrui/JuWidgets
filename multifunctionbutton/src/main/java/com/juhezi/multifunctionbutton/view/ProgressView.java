package com.juhezi.multifunctionbutton.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by qiaoyunrui on 16-8-25.
 */
public class ProgressView extends View {

    private static final String TAG = "ProgressView";

    private int defHeight = 50;
    private int defWidth = 50;

    private Paint mPaint;
    private float centerX;
    private float centerY;
    private float radius = 20f; //进度圈的半径
    private float startAngle = 90;
    private float endAngle = 270;
    private float strokeWidth = 8;
    private ObjectAnimator rotateAnim;
    private long duration = 500;

    private boolean isRotate = false;

    public ProgressView(Context context) {
        super(context);
        init();
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {

        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(strokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getVisibility() == GONE) {
            return;
        }

        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingBottom = getPaddingBottom();
        final int paddingTop = getPaddingTop();

        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingBottom - paddingTop;

        centerX = width / 2f + paddingLeft;
        centerY = height / 2f + paddingTop;
        radius = height / 3;
        RectF oval = new RectF();
        oval.left = centerX - radius;
        oval.top = centerY - radius;
        oval.right = centerX + radius;
        oval.bottom = centerY + radius;
        canvas.drawArc(oval, startAngle, endAngle, false, mPaint);
    }

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

    private int dip2px(float dipValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        post(runnable);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(runnable);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rotate();
        }
    };

    private void rotate() {
        if(isRotate) {
            return;
        }
        rotateAnim = ObjectAnimator.ofFloat(this, "rotation", 0, 360)
                .setDuration(duration);
        rotateAnim.setInterpolator(new LinearInterpolator());
        rotateAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isRotate = false;
                rotate();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        rotateAnim.start();
        isRotate = true;
    }

}
