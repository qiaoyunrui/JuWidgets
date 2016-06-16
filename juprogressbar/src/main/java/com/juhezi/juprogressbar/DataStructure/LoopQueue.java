package com.juhezi.juprogressbar.DataStructure;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 循环队列
 *
 * Created by qiaoyunrui on 16-6-15.
 */
public class LoopQueue<T> {

    private static final String TAG = "LoopQueue";

    private int length = 1; //默认长度为4
    private int sign = 0; //标志位

    private List<T> mList = new ArrayList<>();

    public LoopQueue(int length) {
        this.length = length;
        fixLength();
    }

    /**
     * 获取循环队列中的下一个元素
     * @return
     */
    public T next() {
        if(sign == length - 1) {
            sign = -1;
        }
        sign++;
        return mList.get(sign);
    }

    public void add(T t) {
        mList.add(t);
    }

    public T get(int i) {
        return mList.get(i);
    }

    public void setLength(int length) {
        this.length = length;
        fixLength();
    }

    private void fixLength() {
        if (length > 4) {
            length = 4;
        }
        if(length < 1) {
            length = 1;
        }
    }

}
