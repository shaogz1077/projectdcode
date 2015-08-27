package com.ytpdw.fun.viewtools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import java.util.ArrayList;
import java.util.List;

public class HorizontalScrollView extends View {
//	private final static int DETAIL_DATE = 1;
//	private final static int SIMPLE_DATE = 2;

    private Scroller scroller;
    private Context context;
    private float scrollOffset;
    SimpleOnGestureListener gestureListener = new SimpleOnGestureListener() {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            Log.i("getview", "onScroll");
            smoothScrollBy((int) distanceX, 0);
            // invalidate();
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            Log.i("getview", "onFling");
            // scroller.fling((int) e1.getX(), (int) e1.getY(), (int) velocityX,
            // 0, getWidth(), getWidth(), 0, 0);
            // invalidate();
            return true;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            scroller.forceFinished(true);
            return true;
        }
    };
    private float unnecessaryOffset;
    private GestureDetector gestureDetector;
    private Paint paint;
    //	private RectF rectF;
    private int width;
    private int canvasWidth;
    private int height;
    private float d;
    private float scale;
    private float cx;
    private List<ItemInfo> Items;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            testify();
        }

        ;
    };

    public HorizontalScrollView(Context context, AttributeSet attrs,
                                int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public HorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public HorizontalScrollView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        context = getContext();
        scroller = new Scroller(context);
        gestureDetector = new GestureDetector(context, gestureListener);
        Items = new ArrayList<HorizontalScrollView.ItemInfo>();
        paint = new Paint();
        paint.setTextSize(80);
        paint.setStrokeWidth(4);
        paint.setStyle(Style.FILL);
        paint.setColor(getResources().getColor(android.R.color.holo_blue_dark));
//		rectF = new RectF();
        d = getResources().getDisplayMetrics().density;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    ;

    public boolean onTouchEvent(MotionEvent event) {

        if (gestureDetector.onTouchEvent(event)
                && event.getAction() == MotionEvent.ACTION_UP) {
            // scroller.abortAnimation();
            testify();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            handler.sendEmptyMessage(0);
        }
        return true;
    }

    // 位移
    private void testify() {
        Log.i("getview", "testify()==>" + unnecessaryOffset);
        float f = unnecessaryOffset * scale;
        if (f > scale * 0.78 * 0.5) {
            f = -(1 - unnecessaryOffset) * scale;
        } else if (-f > scale * 0.78 * 0.5) {
            f = -(1 + unnecessaryOffset) * scale;
        }
        smoothScrollBy((int) f, 0);
    }


    // 调用此方法设置滚动的相对偏移
    public void smoothScrollBy(int dx, int dy) {
        // 设置mScroller的滚动偏移量
        scrollOffset += dx;
        Log.i("getView", "scrollOffset==>" + scrollOffset);
        scroller.startScroll(scroller.getFinalX(), scroller.getFinalY(), dx, dy);
        invalidate();// 这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
    }

    @Override
    public void computeScroll() {

        // 先判断mScroller滚动是否完成
        if (scroller.computeScrollOffset()) {

            // 这里调用View的scrollTo()完成实际的滚动
            scrollTo(scroller.getCurrX(), scroller.getCurrY());

            // 必须调用该方法，否则不一定能看到滚动效果
            postInvalidate();
        }
        super.computeScroll();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Items.clear();
        width = getWidth();
        height = getHeight();
        scale = width / 3f;
        canvasWidth = 0;
        drawContent(canvas);

    }

    private void drawContent(Canvas canvas) {
        float finalCX = width / 2;
        float ch = height / 2;
        cx = -0.5F * scale;
        float progress = 0;

        ItemInfo itemInfo;


        for (int i = 0; i < 100; i++) {
            cx += scale;
            progress = (cx - scrollOffset - finalCX) / scale;
            itemInfo = new ItemInfo();
            if (Math.abs(progress) < 1) {
                itemInfo.r = scale * (0.82F - 0.42F * Math.abs(progress))
                        * 0.5f;
            } else if (Math.abs(progress) < 2) {
                itemInfo.r = scale * (0.7F - 0.3F * Math.abs(progress)) * 0.5f;
            } else if (Math.abs(progress) < 3) {
                itemInfo.r = scale * (0.14F - 0.02F * Math.abs(progress))
                        * 0.5f;
            } else {
                itemInfo.r = (scale * (0.17F - 0.03F * Math.abs(progress))) * 0.5f;
            }
            if (itemInfo.r < 5) {
                itemInfo.r = 5;
            }
            float translate;
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
            Items.add(itemInfo);
            canvasWidth += itemInfo.cx;
        }

        for (int i = 0; i < Items.size(); i++) {
            ItemInfo info = Items.get(i);
            canvas.drawCircle(info.cx, ch, info.r, paint);
        }

    }


    private class ItemInfo {
        float r;
        float cx;
        int color;
        int textType;
        boolean isOrder;
        boolean isOverTime;
        String topText;
        String boottomText;
        String hour;
        String timeQuantum;

        @Override
        public String toString() {
            return "ItemInfo [r=" + r + ", cx=" + cx + "]";
        }
    }
}
