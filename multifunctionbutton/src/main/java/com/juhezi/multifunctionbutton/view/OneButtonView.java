package com.juhezi.multifunctionbutton.view;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.juhezi.multifunctionbutton.R;

/**
 * Created by qiaoyunrui on 16-8-25.
 */
public class OneButtonView extends LinearLayout {

    private static final String TAG = "OneButtonView";

    private ImageView mImageView;
    private TextView mTextView;

    public OneButtonView(Context context) {
        this(context, null);
    }

    public OneButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.one_button_view_layout, this, true);
        mImageView = (ImageView) findViewById(R.id.one_button_view_img);
        mTextView = (TextView) findViewById(R.id.one_button_view_tv);
    }


}
