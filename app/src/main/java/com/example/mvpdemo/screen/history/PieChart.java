package com.example.mvpdemo.screen.history;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class PieChart extends View {
    protected Double percentX;
    protected Double percentY;
    private Paint paint;
    int width = 0;
    int height = 0;
    int radius;
    boolean isInit = false;


    public PieChart(Context context) {
        super(context);
    }

    public PieChart(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }

    public PieChart(Context context, AttributeSet attributeSet, int defStyleAttr){
        super(context,attributeSet, defStyleAttr);
    }

    public PieChart(Context context, Double percentX, Double percentY){
        super(context);
        this.percentX = percentX;
        this.percentY = percentY;
    }
    private void initPie(){
        width = getWidth();
        height = getHeight();
        radius = 100;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setColor(Color.parseColor("#da4747"));
        isInit = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInit) {
            initPie();
        }
        drawCircle(canvas);
    }

    private void drawCircle(Canvas canvas) {
        paint.reset();
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        canvas.drawPaint(paint);
        canvas.drawCircle(width/2,height/2,radius,paint);
    }
}
