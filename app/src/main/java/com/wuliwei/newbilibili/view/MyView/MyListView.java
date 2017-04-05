package com.wuliwei.newbilibili.view.MyView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by 一名程序员 on 2017/2/25.
 * <p>
 * 作用：自定义listView
 */

public class MyListView extends ListView {
    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
