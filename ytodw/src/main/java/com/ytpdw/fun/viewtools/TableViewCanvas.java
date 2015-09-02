package com.ytpdw.fun.viewtools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * projectdcode
 *
 * @version V1.0
 * @Package: com.ytpdw.fun.viewtools
 * @company: byb
 * @author: ollie
 * @date 2015/9/1 16:24
 */
public class TableViewCanvas extends View {

    private Paint paint;
    private int width;
    private int heigth;
    private Paint mPonitPaint;
    private Paint mLinePaint;


    public TableViewCanvas(Context context) {
        super(context);
        initView();
    }

    public TableViewCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TableViewCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        paint = new Paint();
        paint.setColor(getResources().getColor(android.R.color.holo_blue_dark));
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);//外边框的画笔

        width = getWidth();
        heigth = getHeight();

        mPonitPaint = new Paint();
        mPonitPaint.setColor(getResources().getColor(android.R.color.holo_red_dark));
        mPonitPaint.setStyle(Paint.Style.STROKE);
        mPonitPaint.setStrokeWidth(25);//点的画笔

        mLinePaint = new Paint();
        mLinePaint.setColor(getResources().getColor(android.R.color.black));
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setStrokeWidth(4);//线条的画笔
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(100, 100, 1000, 1000, paint);
        for (int i = 200; i < 1000; i = i + 100) {
            canvas.drawLine(100, i, 1000, i, mLinePaint);
            canvas.drawLine(i, 100, i, 1000, mLinePaint);
        }
        canvas.drawPoints(new float[]{120, 230, 340, 450, 380, 340}, mPonitPaint);
        canvas.drawLine(120, 230, 340, 450, mLinePaint);
        canvas.drawLine(340, 450, 380, 340, mLinePaint);
    }
}
