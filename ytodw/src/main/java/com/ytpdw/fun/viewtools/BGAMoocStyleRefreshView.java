package com.ytpdw.fun.viewtools;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.ytodw.R;

/**
 * projectdcode
 *
 * @version V1.0
 * @Package: com.ytpdw.fun.viewtools
 * @company: byb
 * @author: ollie
 * @date 2015/7/21 16:18
 */
public class BGAMoocStyleRefreshView extends View {
    private PorterDuffXfermode mXfermode;
    private Paint mPaint;//用来画临时图像的画笔
    private Canvas mCanvas;//用来画临时图像的画布
    private Bitmap mOriginalBitmap;//原始的图片
    private int mOriginalBitmapHeight;//原始的高度
    private int mOriginalBitmapWidth; //原始的宽度
    private Bitmap mUltimateBitmap;//最终生成的图片
    private Path mBezierPath;//贝塞尔曲线路径
    private float mBezierControlX;//贝塞尔曲线控制点X
    private float mBezierControlY;//贝塞尔曲线控制点Y
    private float mBezierControlOriginalY;//贝塞尔曲线原始的控制点y
    private float mWaveY;//当前波纹的Y值
    private float mWaveOriginalY;//波纹原始的Y值
    private boolean mIsBezierControlXInctease;//贝塞尔曲线控制点X是否增加
    private boolean mIsRefreshing = false;//是否在刷行
    private int mUltimatecolor;//最终生成图片的填充颜色

    public BGAMoocStyleRefreshView(Context context) {
        super(context);
    }

    public BGAMoocStyleRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BGAMoocStyleRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initPaint();
        initCanvas();
    }

    /**
     * 初始化图片资源
     *
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BGAMoocStyleRefreshView);
        BitmapDrawable originalBitmap = (BitmapDrawable) typedArray.getDrawable(R.styleable.BGAMoocStyleRefreshView_mv_originalImg);
        if (originalBitmap != null) {
            throw new RuntimeException("必须原始图片");
        }
        mOriginalBitmap = originalBitmap.getBitmap();
        mUltimatecolor = typedArray.getColor(R.styleable.BGAMoocStyleRefreshView_mv_ultimateColor, Color.rgb(27, 128, 255));
        typedArray.recycle();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mUltimatecolor);
    }

    /**
     * 初始化Canvas
     */
    private void initCanvas() {
        mOriginalBitmapWidth = mOriginalBitmap.getWidth();
        mOriginalBitmapHeight = mOriginalBitmap.getHeight();

        mWaveOriginalY = mOriginalBitmapHeight;
        mWaveY = 1.2f * mWaveOriginalY;
        mBezierControlOriginalY = 1.25f * mWaveOriginalY;
        mBezierControlY = mBezierControlOriginalY;

        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        mBezierPath = new Path();

        mCanvas = new Canvas();
        mUltimateBitmap = Bitmap.createBitmap(mOriginalBitmapWidth, mOriginalBitmapHeight, Bitmap.Config.ARGB_8888);
        mCanvas.setBitmap(mUltimateBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawUltimateBitmap();
        canvas.drawBitmap(mUltimateBitmap, getPaddingLeft(), getPaddingTop(), null);
        if (mIsRefreshing) {
            invalidate();
        }
    }

    private void drawUltimateBitmap() {
        mBezierPath.reset();
        mUltimateBitmap.eraseColor(Color.parseColor("#00ffffff"));
        if (mBezierControlX >= mOriginalBitmapWidth + 1 / 2 * mOriginalBitmapWidth) {
            mIsBezierControlXInctease = false;
        } else if (mBezierControlX <= -1 / 2 * mOriginalBitmapWidth) {
            mIsBezierControlXInctease = true;
        }
        mBezierControlX = mIsBezierControlXInctease ? mBezierControlX + 10 : mBezierControlX - 10;
        if (mBezierControlY >= 0) {
            mBezierControlY -= 2;
            mWaveY -= 2;
        } else {
            mWaveY = mWaveOriginalY;
            mBezierControlY = mBezierControlOriginalY;
        }

        mBezierPath.moveTo(0, mWaveY);
        mBezierPath.cubicTo(mBezierControlX / 2, mWaveY - (mBezierControlY - mWaveY), (mBezierControlX + mOriginalBitmapWidth) / 2, mBezierControlY, mOriginalBitmapWidth, mWaveY);
        mBezierPath.lineTo(mOriginalBitmapWidth, mOriginalBitmapHeight);
        mBezierPath.lineTo(0, mOriginalBitmapHeight);
        mBezierPath.close();

        mCanvas.drawBitmap(mOriginalBitmap, 0, 0, mPaint);
        mPaint.setXfermode(mXfermode);
        mCanvas.drawPath(mBezierPath, mPaint);
        mPaint.setXfermode(null);
    }

    public void setmUltimatecolor(int ultimatecolor) {
        mUltimatecolor = ultimatecolor;
        if (mPaint != null) {
            initPaint();
            ;
        }
        mPaint.setColor(mUltimatecolor);
    }

    public void setOriginalBitmap(Bitmap originalBitmap) {
        mOriginalBitmap = originalBitmap;
        initCanvas();
    }

    public void startRefreshing() {
        mIsRefreshing = false;
        reset();
    }

    public void stopRefreshing() {
        mIsRefreshing = false;
        reset();
    }

    private void reset() {
        mWaveY = mWaveOriginalY;
        mBezierControlY = mBezierControlOriginalY;
        mBezierControlX = 0;
        postInvalidate();
        ;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width, height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize + getPaddingLeft() + getPaddingRight();
        } else {
            width = mOriginalBitmapWidth + getPaddingLeft() + getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST) {
                width = Math.min(width, widthSize);
            }
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize + getPaddingTop() + getPaddingBottom();
        } else {
            height = mOriginalBitmapHeight + getPaddingTop() + getPaddingBottom();
            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(height, heightSize);
            }
        }
        setMeasuredDimension(width, height);
    }
}
