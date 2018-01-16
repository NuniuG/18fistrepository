package com.example.a110.myprcearray;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by 110 on 2018/1/11.
 */

public class PrceArray extends TextView implements Runnable {
    private int currentScrollX;// 当前滚动的位置

    private boolean isStop = false;

    private int textWidth;

    private boolean isMeasure = false;



    public PrceArray(Context context) {
        super(context);
    }


    public PrceArray(Context context, AttributeSet attrs) {

        super(context, attrs);


    }

    public PrceArray(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override

    protected void onDraw(Canvas canvas) {

        // TODO Auto-generated method stub

        super.onDraw(canvas);

        if (!isMeasure) {// 文字宽度只需获取一次就可以了

            getTextWidth();

            isMeasure = true;

        }

    }



    public void getTextWidth() {
        Paint paint = this.getPaint();

        String str = this.getText().toString();

        textWidth = (int) paint.measureText(str);
    }


    @Override
    public void run() {
        currentScrollX -= 9;// 滚动速度

        scrollTo(currentScrollX, 0);

        if (isStop) {

            return;
        }
        if (getScrollX() <= -(this.getWidth())) {

            scrollTo(textWidth, 0);

            currentScrollX = textWidth;

//   return;

        }

        postDelayed(this, 5);

    }

    // 开始滚动

    public void startScroll() {

        isStop = false;

        this.removeCallbacks(this);

        post(this);

    }

    // 停止滚动

    public void stopScroll() {

        isStop = true;

    }

    // 从头开始滚动

    public void startFor0() {

        currentScrollX = 0;

        startScroll();

    }



}
