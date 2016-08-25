package com.juhezi.multifunctionbutton.view;

import android.content.Context;
import android.net.sip.SipAudioCall;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by qiaoyunrui on 16-8-25.
 */
public class MultiFunctionButton extends RelativeLayout {

    private static final int MODE_BUTTON = 1;
    private static final int MODE_PROGRESSS = 0;

    private int mode = MODE_BUTTON;

    private ButtonClickListener mListener;

    private OneButtonView mOneButtonView;
    private ProgressView mProgressView;

    public MultiFunctionButton(Context context) {
        this(context, null);
    }

    public MultiFunctionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mOneButtonView = new OneButtonView(context);
        mProgressView = new ProgressView(context);
        mOneButtonView.getChildAt(0).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onButtonClickListener();
                }
            }
        });
        changeView(mode);
    }

    public void setMode(int mode) {
        if (this.mode != mode) {
            this.mode = mode;
            changeView(mode);
        }
    }

    public void setButtonClickListener(ButtonClickListener listener) {
        this.mListener = listener;
    }

    private void changeView(int mode) {
        switch (mode) {
            case MODE_BUTTON:
                removeAllViews();
                addView(mOneButtonView);
                break;
            case MODE_PROGRESSS:
                removeAllViews();
                addView(mProgressView);
                break;
        }
    }

    public interface ButtonClickListener {
        public void onButtonClickListener();
    }

}
