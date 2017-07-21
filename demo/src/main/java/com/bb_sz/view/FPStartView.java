package com.bb_sz.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/7/21.
 */

public class FPStartView extends View {
    private int N = 10;
    private float cx;
    private float cy;
    private float cRadius;
    private Paint mCirclePaint;
    private Paint mCenterPointPaint;
    private Paint mLineXYPaint;
    private Paint inCirclePaint;
    private float inx;
    private float iny;
    private float inRadius;
    private Paint runCirclePaint;
    private float runRadius;
    private Paint runPointPaint;
    private double arg = 90;
    private Paint fiveStartPaint;
    private float fiveStartRadius;
    private boolean isNeedOther = false;
    private double xMultiple = 1.5;
    private double yMultiple = 1.5;
    private boolean isStop = true;

    private static List<Float> fiveX = new ArrayList<>();
    private static List<Float> fiveY = new ArrayList<>();

    public FPStartView(Context context) {
        super(context);
    }

    public FPStartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FPStartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void stop() {
        isStop = true;
        Log.i("SKYVIew", "stop()");
    }

    public void start(double arg, double xMultiple, double yMultiple) {
        this.arg = arg;
        this.xMultiple = xMultiple;
        this.yMultiple = yMultiple;
        fiveX.clear();
        fiveY.clear();
        fiveX = null;
        fiveY = null;
        fiveX = new ArrayList<>();
        fiveY = new ArrayList<>();
        isStop = false;
        postInvalidate();
        Log.i("SKYVIew", "start(), arg = " + arg + ", xM = " + xMultiple + ", yM = " + yMultiple);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initCenterPoint();
        drawCenterPoint(canvas);
        drawCircle(canvas);
        drawXY(canvas);
        drawRunCircle(canvas);
        drawRunPoint(canvas);
        drawInCircle(canvas);
        drawFiveStarCircle(canvas);

        int size = fiveX.size();
        for (int i = 0; i < size; i++) {
            drawFivePoint(fiveX.get(i), fiveY.get(i), canvas);
        }

//        canvas.drawPoints();

//        Path path = new Path();
//        canvas.drawPath(path, fiveStartPaint);
        if (!isStop) {
            arg += 10;
            arg = arg % (360 * N);
            postInvalidate();
        }
    }

    private void drawFivePoint(Float x, Float y, Canvas canvas) {
//        canvas.drawCircle(x, y, 1, fiveStartPaint);
        canvas.drawPoint(x, y, fiveStartPaint);
    }

    private void drawFiveStarCircle(Canvas canvas) {
        if (fiveStartRadius == 0) {
            fiveStartRadius = getWidth() - cx - runRadius;
        }
        if (null == fiveStartPaint) {
            fiveStartPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            fiveStartPaint.setStrokeWidth(10);
            fiveStartPaint.setStyle(Paint.Style.STROKE);
            fiveStartPaint.setColor(Color.RED);
        }

//        x=2cos(3t)+5cos(2t)
//        y=2sin(3t)-5sin(2t)

//        float x = (float) (inx + fiveStartRadius * Math.cos(Math.toRadians(360 * 3 - arg + arg * 0.5)));// 3arg
//        float y = (float) (iny + fiveStartRadius * Math.sin(Math.toRadians(360 * 3 - arg + arg * 0.5)));

//        float x = (float) (inx + fiveStartRadius * Math.cos(Math.toRadians(360 * N - arg + arg * 0.80)));//6 arg
//        float y = (float) (iny + fiveStartRadius * Math.sin(Math.toRadians(360 * N - arg + arg * 0.80)));

//        float x = (float) (inx + fiveStartRadius * Math.cos(Math.toRadians(360 * N - arg + arg * 0.70)));//13 arg
//        float y = (float) (iny + fiveStartRadius * Math.sin(Math.toRadians(360 * N - arg + arg * 0.70)));

//        float x = (float) (inx + fiveStartRadius * Math.cos(Math.toRadians(360 * N - arg + arg * 0.40)));//8 arg
//        float y = (float) (iny + fiveStartRadius * Math.sin(Math.toRadians(360 * N - arg + arg * 0.40)));

//        float x = (float) (inx + fiveStartRadius * Math.cos(Math.toRadians(360 * N - arg + arg * 0.30)));//17 arg
//        float y = (float) (iny + fiveStartRadius * Math.sin(Math.toRadians(360 * N - arg + arg * 0.30)));

//        float x = (float) (inx + fiveStartRadius * Math.cos(Math.toRadians(360 * N - arg + arg * 0.60)));//7 角星
//        float y = (float) (iny + fiveStartRadius * Math.sin(Math.toRadians(360 * N - arg + arg * 0.60)));

//        float x = (float) (inx + fiveStartRadius * Math.cos(Math.toRadians(360 * N - arg + arg * 0.90)));//11 个圆角
//        float y = (float) (iny + fiveStartRadius * Math.sin(Math.toRadians(360 * N - arg + arg * 0.90)));

//        float x = (float) (inx + fiveStartRadius * Math.cos(Math.toRadians(360 * N - arg + arg * 0.20)));//9 个圆角
//        float y = (float) (iny + fiveStartRadius * Math.sin(Math.toRadians(360 * N - arg + arg * 0.20)));

//        float x = (float) (inx + fiveStartRadius * Math.cos(Math.toRadians(360 * N - arg + arg * 0.10)));//xx 个圆角
//        float y = (float) (iny + fiveStartRadius * Math.sin(Math.toRadians(360 * N - arg + arg * 0.10)));

        float x = (float) (inx + fiveStartRadius * Math.cos(Math.toRadians(360 * N - arg + arg * xMultiple)));//xx 个圆角
        float y = (float) (iny + fiveStartRadius * Math.sin(Math.toRadians(360 * N - arg + arg * yMultiple)));

//        x = (float) (runRadius * Math.cos(Math.toRadians(arg)) + cRadius * Math.cos(Math.toRadians(arg)));
//        y = (float) (runRadius * Math.sin(Math.toRadians(arg)) - cRadius * Math.sin(Math.toRadians(arg)));

        if (fiveX.size() <= 360 * N) {
            fiveX.add(x);
            fiveY.add(y);
            Log.v("SKYVIEW", "x = " + x + ", y = " + y);
        }
//        canvas.drawCircle(x, y, 1, fiveStartPaint);
    }

    private void drawRunPoint(Canvas canvas) {
        inx = (float) (cx + runRadius * Math.cos(Math.toRadians(arg)));
        iny = (float) (cy + runRadius * Math.sin(Math.toRadians(arg)));

        if (null == runPointPaint) {
            runPointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            runPointPaint.setStrokeWidth(10);
            runPointPaint.setStyle(Paint.Style.FILL);
            runPointPaint.setColor(Color.RED);
        }

        if (isNeedOther) canvas.drawCircle(inx, iny, 10, runPointPaint);

    }

    private void drawRunCircle(Canvas canvas) {
        if (runRadius == 0)
            runRadius = cRadius * 0.4f;
        if (null == runCirclePaint) {
            runCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            runCirclePaint.setStrokeWidth(3);
            runCirclePaint.setStyle(Paint.Style.STROKE);
            runCirclePaint.setColor(Color.BLACK);
        }
        if (isNeedOther) canvas.drawCircle(cx, cy, runRadius, runCirclePaint);
    }

    private void drawInCircle(Canvas canvas) {
        if (inRadius == 0)
            inRadius = cRadius * 0.6f;
        if (null == inCirclePaint) {
            inCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            inCirclePaint.setStrokeWidth(3);
            inCirclePaint.setStyle(Paint.Style.STROKE);
            inCirclePaint.setColor(Color.BLACK);
        }
        if (isNeedOther) canvas.drawCircle(inx, iny, inRadius, inCirclePaint);
    }

    private void drawXY(Canvas canvas) {
        float startXX = 0;
        float endXX = getWidth();
        float startYX = cy;
        float endYX = cy;

        float startXY = cx;
        float endXY = cx;
        float startYY = 0;
        float endYY = getHeight();
        float[] fps = {startXX, startYX, endXX, endYX, startXY, startYY, endXY, endYY};
        if (null == mLineXYPaint) {
            mLineXYPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mLineXYPaint.setStrokeWidth(1);
            mLineXYPaint.setStyle(Paint.Style.FILL);
            mLineXYPaint.setColor(Color.BLACK);
        }
        if (isNeedOther) canvas.drawLines(fps, mLineXYPaint);
    }

    private void drawCenterPoint(Canvas canvas) {
        if (null == mCenterPointPaint) {
            mCenterPointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mCenterPointPaint.setStrokeWidth(10);
            mCenterPointPaint.setStyle(Paint.Style.FILL);
            mCenterPointPaint.setColor(Color.BLACK);
        }
        if (isNeedOther) canvas.drawCircle(cx, cy, 10, mCenterPointPaint);
    }

    private void drawCircle(Canvas canvas) {
        if (null == mCirclePaint) {
            mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mCirclePaint.setStrokeWidth(5);
            mCirclePaint.setStyle(Paint.Style.STROKE);
            mCirclePaint.setColor(Color.BLUE);
        }
        if (isNeedOther) canvas.drawCircle(cx, cy, cRadius, mCirclePaint);
    }

    private void initCenterPoint() {
        cx = (getWidth() - getPaddingLeft() - getPaddingRight()) / 2;
        cy = (getHeight() - getPaddingTop() - getPaddingBottom()) / 2;
        cRadius = Math.min(cx, cy);
        cx += getPaddingLeft();
        cy += getPaddingTop();
    }
}
