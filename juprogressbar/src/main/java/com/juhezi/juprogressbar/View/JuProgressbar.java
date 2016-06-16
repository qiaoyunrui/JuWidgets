package com.juhezi.juprogressbar.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private RectView mRectView;

    private TextView loadTextView;   //显示加载文字的TextView

    private ImageView shadowImg;    //显示小球阴影的ImageView

    private String loadText = "Loading...";    //加载文字
    private int colorNum = 1;
    private int rectColor1 = 0xf9f;
    private int rectColor2 = 0xfff;
    private int rectColor3 = 0xfff;
    private int rectColor4 = 0xfff;

    private int defHeight = 170;
    private int defWidth = 170;


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
    }

}
