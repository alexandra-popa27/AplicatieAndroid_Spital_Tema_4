package com.example.tema_4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.widget.Toast;

import java.util.Map;
import java.util.Random;

public class ChartView extends View {

    private final Context context;
    private final Map<String, Integer> source;
    private final Paint paint;
    int left=60;
    int top=100;
    int right=1010;
    int bottom=1050;
    private final RectF oval=new RectF(left,top,right,bottom);

    public ChartView(Context context, Map<String, Integer> source) {
        super(context);
        this.context = context;
        this.source = source;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (source == null || source.isEmpty()) {
            return;
        }

        int numarTeste=0;
        int currentElement=0;
        float angle=0;
        for(String label : source.keySet()){
            numarTeste=numarTeste+source.get(label);
        }
        for(String label : source.keySet()){
            int value = source.get(label);
            //trasare arc
            float sweepAngle=360/numarTeste*value;
            float startAngle=270+currentElement*angle;
            paint.setColor(generateColor(currentElement));
            canvas.drawArc(oval,startAngle,sweepAngle,true,paint);
            //legenda
            paint.setColor(Color.BLACK);
            paint.setTextSize((left+right)/15);
            float x= left+100+((left+right)/2-100)*(1-currentElement);
            float y= 50+(top+bottom)*value/numarTeste;
            canvas.drawText(context.getString(R.string.chart_legend_template, label, value), x, y, paint);
            currentElement++;
            angle=sweepAngle;
        }

    }

    private int generateColor(int currentElement) {
        return currentElement % 2 == 0 ? Color.GREEN : Color.MAGENTA;
    }

    private int calculateMax() {
        int max = 0;
        for (Integer value : source.values()) {
            if (max < value) {
                max = value;
            }
        }
        return max;
    }
}
