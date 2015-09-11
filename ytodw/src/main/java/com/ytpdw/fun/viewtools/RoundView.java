package com.ytpdw.fun.viewtools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * projectdcode
 *
 * @version V1.0
 * @Package: com.ytpdw.fun.viewtools
 * @company: byb
 * @author: ollie
 * @date 2015/9/7 14:33
 */
public class RoundView extends View {

    private Paint mPaint;
    private int width;
    private int heigth;
    private Paint roundPaint;

    public RoundView(Context context) {
        super(context);
        initView();
    }

    public RoundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView() {
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(android.R.color.holo_blue_dark));
        mPaint.setStrokeWidth(12);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        roundPaint = new Paint();
        roundPaint.setColor(getResources().getColor(android.R.color.holo_red_dark));
        roundPaint.setStrokeWidth(12);
        roundPaint.setAntiAlias(true);
        roundPaint.setStyle(Paint.Style.STROKE);
        width = getWidth();
        heigth = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(185, 170, 135, mPaint);
        RectF rectF = new RectF(50, 35, 320, 305);
        canvas.drawArc(rectF, 140, 270, false, roundPaint);

    }
}
