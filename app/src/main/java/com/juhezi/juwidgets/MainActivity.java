package com.juhezi.juwidgets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.juhezi.multifunctionbutton.view.MultiFunctionButton;

public class MainActivity extends AppCompatActivity {

    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.btn_click);
        final MultiFunctionButton mfbShow = (MultiFunctionButton) findViewById(R.id.mfb_show);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1 - flag;
                mfbShow.setMode(flag);
            }
        });
        mfbShow.setButtonClickListener(new MultiFunctionButton.ButtonClickListener() {
            @Override
            public void onButtonClickListener() {

            }
        });
    }
}
