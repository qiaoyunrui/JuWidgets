package com.juhezi.juprogressbar.View;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.juhezi.juprogressbar.R;

/**
 * 进度条控件
 * <p/>
 * Created by qiaoyunrui on 16-6-16.
 */
public class JuProgressbar extends FrameLayout {

    private static final String TAG = "JuProgressbar";

    private static final long ANIM_DURATION = 500;

    private RectView mRectView;

    private TextView loadTextView;   //显示加载文字的TextView

    private ImageView shadowImg;    //显示小球阴影的ImageView

    private String loadText = "Loading...";    //加载文字
    private int colorNum = 1;
    private int rectColor1 = 0xf9f;
    private int rectColor2 = 0xfff;
    private int rectColor3 = 0xfff;
    private int rectColor4 = 0xfff;

    private int distance = 100; //上抛的高度
    private AnimatorSet mAnimatorSet = null;

    private Interpolator upThrowInterpolator = new DecelerateInterpolator(1.2f);
    private Interpolator downFallInterpolator = new AccelerateInterpolator(1.2f);

    public JuProgressbar(Context context) {
        super(context);
        init();
    }

    public JuProgressbar(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.ju_progressbar, this, true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.JuProgressbar);
        loadText = typedArray.getString(R.styleable.JuProgressbar_loadingText);
        colorNum = typedArray.getInt(R.styleable.JuProgressbar_colorNum, 1);
        rectColor1 = typedArray.getColor(R.styleable.JuProgressbar_color1, 0xf9ffff);
        rectColor2 = typedArray.getColor(R.styleable.JuProgressbar_color2, 0xffffff);
        rectColor3 = typedArray.getColor(R.styleable.JuProgressbar_color3, 0xffffff);
        rectColor4 = typedArray.getColor(R.styleable.JuProgressbar_color4, 0xffffff);
        typedArray.recycle();
        init();
    }

    private void init() {

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mRectView = (RectView) findViewById(R.id.rectView);
        shadowImg = (ImageView) findViewById(R.id.shadowImg);
        loadTextView = (TextView) findViewById(R.id.loadTextTv);
        if(!TextUtils.isEmpty(loadText)) {
            loadTextView.setText(loadText);
        }
        mRectView.setColorNum(colorNum);
        mRectView.addColor(rectColor1);
        mRectView.addColor(rectColor2);
        mRectView.addColor(rectColor3);
        mRectView.addColor(rectColor4);



        startLoading(300);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        distance = getHeight() - mRectView.getHeight() - 30;
    }

    /**
     * 开始加载动画
     * @param delayTime
     */
    private void startLoading(long delayTime) {
        if(mAnimatorSet != null && mAnimatorSet.isRunning()) {
            return;
        }
        this.removeCallbacks(mRunnable);
        if(delayTime > 0) {
            postDelayed(mRunnable,delayTime);
        } else {
            post(mRunnable);
        }
    }

    /**
     * 停止加载动画
     */
    private void stopLoading() {
        if(mAnimatorSet != null) {
            if(mAnimatorSet.isRunning()) {
                mAnimatorSet.cancel();
            }
            mAnimatorSet = null;
        }
        this.removeCallbacks(mRunnable);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if(visibility == View.VISIBLE) {
            startLoading(300);
        } else {
            stopLoading();
        }
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            upThrow();
        }
    };

    /**
     * 上抛
     */
    private void upThrow() {
        ObjectAnimator rectTranAnim = ObjectAnimator.ofFloat(mRectView,
                "translationY",0,-distance / 2);
        ObjectAnimator rectRotateAnim = ObjectAnimator.ofFloat(mRectView,
                "rotation",0,90);
        ObjectAnimator shadowScaleAnim = ObjectAnimator.ofFloat(shadowImg,
                "scaleX",0.2f,1);

        rectRotateAnim.setInterpolator(upThrowInterpolator);
        rectTranAnim.setInterpolator(upThrowInterpolator);
        shadowScaleAnim.setInterpolator(upThrowInterpolator);

        mAnimatorSet = new AnimatorSet();

        mAnimatorSet.setDuration(ANIM_DURATION);

        mAnimatorSet.playTogether(rectRotateAnim,rectTranAnim,shadowScaleAnim);

        mAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                downFall();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mAnimatorSet.start();
    }

    /**
     * 下落
     */
    private void downFall() {
        ObjectAnimator rectTranAnim = ObjectAnimator.ofFloat(mRectView,
                "translationY",-distance / 2,0);
        ObjectAnimator rectRotateAnim = ObjectAnimator.ofFloat(mRectView,
                "rotation",0,90);
        ObjectAnimator shadowScaleAnim = ObjectAnimator.ofFloat(shadowImg,
                "scaleX",1,0.2f);

        rectRotateAnim.setInterpolator(downFallInterpolator);
        rectTranAnim.setInterpolator(downFallInterpolator);
        shadowScaleAnim.setInterpolator(downFallInterpolator);

        mAnimatorSet = new AnimatorSet();

        mAnimatorSet.playTogether(rectTranAnim,rectRotateAnim,shadowScaleAnim);
        mAnimatorSet.setDuration(ANIM_DURATION);
        mAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                upThrow();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mAnimatorSet.start();
    }

}
