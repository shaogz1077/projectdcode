package com.ytpdw.fun.viewtools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import java.util.ArrayList;
import java.util.List;

/**
 * projectdcode
 *
 * @version V1.0
 * @Package: com.ytpdw.fun.viewtools
 * @company: byb
 * @author: ollie
 * @date 2015/8/25 17:18
 */
public class ViewHorizontalScrollView extends View {
    private Paint paint;
    private Context mContext;
    private int width;
    private int height;
    private int canvasWidth;
    private float scale;
    private float cx;
    private List<ItemInfo> items;
    private float d;
    private float scrollOffset;
    private float unnecessaryOffset;

    private Scroller scroller;
    GestureDetector.SimpleOnGestureListener getureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//            return super.onScroll(e1, e2, distanceX, distanceY);
            smoothScrollBy((int) distanceX, 0);
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            return super.onFling(e1, e2, velocityX, velocityY);
            return true;
        }


        @Override
        public boolean onDown(MotionEvent e) {
//            return super.onDown(e);
            scroller.forceFinished(true);
            return true;
        }
    };
    private GestureDetector gestureDetector;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            testify();
        }
    };

    public ViewHorizontalScrollView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public ViewHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public ViewHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    //位移
    private void testify() {
        float f = unnecessaryOffset * scale;
        if (f > scale * 0.78 * 0.5) {
            f = -(1 - unnecessaryOffset) * scale;
        } else if (-f > scale * 78 * 0.5) {
            f = -(1 + unnecessaryOffset) * scale;
        }
        smoothScrollBy((int) f, 0);
    }

    private void initView() {
        paint = new Paint();//设置画笔
        paint.setTextSize(80);
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(android.R.color.holo_blue_dark));
        d = getResources().getDisplayMetrics().density;
        items = new ArrayList<ItemInfo>();//初始化数组
        scroller = new Scroller(mContext);
        gestureDetector = new GestureDetector(mContext, getureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        if (gestureDetector.onTouchEvent(event) && event.getAction() == MotionEvent.ACTION_UP) {
            testify();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            handler.sendEmptyMessage(0);
        }
        return true;
    }

    // 调用此方法设置滚动的相对偏移
    private void smoothScrollBy(int dx, int dy) {
        scrollOffset += dx;    // 设置mScroller的滚动偏移量
        scroller.startScroll(scroller.getFinalX(), scroller.getFinalY(), dx, dy);
        invalidate();// 这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {// 先判断mScroller滚动是否完成
            scrollTo(scroller.getCurrX(), scroller.getCurrY());// 这里调用View的scrollTo()完成实际的滚动
            postInvalidate();// 必须调用该方法，否则不一定能看到滚动效果
        }
        super.computeScroll();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        items.clear();
        width = getWidth();
        height = getHeight();
        scale = width / 3f;//区分等份
        canvasWidth = 0;
        drawContent(canvas);
    }

    /**
     * 绘制初始化可见视图
     *
     * @param canvas
     */
    private void drawContent(Canvas canvas) {
        float finalCX = width / 2;
        float ch = height / 2;
        cx = -0.5f * scale;
        float progress = 0;
        ItemInfo itemInfo;
        for (int i = 0; i < 10; i++) {
            cx += scale;
            progress = (cx - scrollOffset - finalCX) / scale;
            itemInfo = new ItemInfo();
            if (Math.abs(progress) < 1) {//设置半径的递减
                itemInfo.r = scale * (0.82f - 0.42f * Math.abs(progress)) * 0.5f;
            } else if (Math.abs(progress) < 2) {
                itemInfo.r = scale * (0.7f - 0.3f * Math.abs(progress)) * 0.5f;
            } else if (Math.abs(progress) < 3) {
                itemInfo.r = scale * (0.14f - 0.02f * Math.abs(progress)) * 0.5f;
            } else {
                itemInfo.r = scale * (0.17f - 0.03f * Math.abs(progress)) * 0.5f;
            }
            if (itemInfo.r < 5) {
                itemInfo.r = 5;
            }
            //绘制初始化可见视图的点
            float translate = 0;
            if (Math.abs(progress) <= 1) {
                translate = scale * progress * -0.12f;
                unnecessaryOffset = progress;
            } else if (Math.abs(progress) <= 2) {
                if (progress > 0) {
                    translate = scale * (0.54f - 0.66f * Math.abs(progress));
                } else {
                    translate = -scale * (0.54f - 0.66f * Math.abs(progress));
                }
            } else if (Math.abs(progress) <= 3) {
                if (progress > 0) {
                    translate = scale * (0.98f - 0.88f * Math.abs(progress));
                } else {
                    translate = -scale * (0.98f - 0.88f * Math.abs(progress));
                }
            } else {
                if (progress > 0) {
                    translate = scale * (1.1f - 0.92f * Math.abs(progress));
                } else {
                    translate = -scale * (1.1f - 0.92f * Math.abs(progress));
                }
            }
            itemInfo.cx = cx + translate;
            items.add(itemInfo);
            canvasWidth += itemInfo.cx;
        }
        for (int i = 0; i < items.size(); i++) {//在同样的高下绘制不同大小的圆
            ItemInfo info = items.get(i);
            canvas.drawCircle(info.cx, ch, info.r, paint);
        }
    }

    class ItemInfo {
        float r;//半径
        float cx;//对应的x轴的位置
    }

}
