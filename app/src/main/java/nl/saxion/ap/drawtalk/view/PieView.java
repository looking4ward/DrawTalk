package nl.saxion.ap.drawtalk.view;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import java.util.ArrayList;

import nl.saxion.ap.drawtalk.R;
import nl.saxion.ap.drawtalk.model.PiePart;

public class PieView extends LinearLayout {

    private ArrayList<PiePart> parts;
    private ArrayList<Paint> paints;
    private String topic;
    private Paint mTextPaint;
    private Paint mTopicPaint;
    private Paint mPieBackground;
    private Paint mShadowPaint;
    private int mTextColor;
    private float mTextWidth = 0.0f;
    private float mTextHeight = 0.0f;
    private float diameter;
    private RectF mBounds;

    public PieView(Context context) {
        super(context);
        initPie();
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPie();
    }

    public PieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPie();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        diameter = canvas.getWidth();

        float startAngle = 0f;
        float endAngle;

        // draw circle
        canvas.drawCircle(diameter / 2, diameter / 2, diameter / 2, mPieBackground);
        Log.d("onDraw", "Circle : " + canvas.getHeight() + " x " + canvas.getHeight());

        int partNumber = 0;
        int[] locationX = {400,150,75,350};
        int[] locationY = {250,250,550,550};
        for (PiePart pp : parts) {

            Log.d("onDraw", "Part" + partNumber + ": " + pp.getTopic() + "-" + pp.getPercentage());

            endAngle = startAngle + (pp.getPercentage() / 100f) * 360f;
            Log.d("onDraw", String.valueOf(startAngle));
            Log.d("onDraw", String.valueOf(endAngle));

            canvas.drawArc(mBounds,
                    360f - endAngle,
                    endAngle - startAngle,
                    true, paints.get(parts.indexOf(pp)));
            startAngle = endAngle;

            canvas.drawText(pp.getTopic(),locationX[partNumber%locationX.length], locationY[partNumber%locationY.length], mTextPaint);
            partNumber++;

        }

        // draw topic
        canvas.drawText(this.topic,((int) diameter) / 2 - 75 , (canvas.getHeight()) / 2 + 30, mTopicPaint);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d("onSizeChanged", "Size changed");
        super.onSizeChanged(w, h, oldw, oldh);


        // Account for padding
        float xpad = (float)(getPaddingLeft() + getPaddingRight());
        float ypad = (float)(getPaddingTop() + getPaddingBottom());

        // Account for the label
        xpad += mTextWidth;

        float ww = (float)w - xpad;
        float hh = (float)h - ypad;

        // Figure out how big we can make the pie.
        diameter = Math.min(ww, hh);


        mBounds = new RectF(0, 0, ww, hh);


    }


    private void initPie() {
        // init parts
        Log.d("initPie", "started initPie");
        this.parts = new ArrayList<>();
        this.paints = new ArrayList<>();
        this.mBounds = new RectF(0, 0, 100, 100);
        this.topic = "";

        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.pieview, this);

        this.setBackgroundColor(Color.LTGRAY);


        mTextColor = 0xff101010;
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(48f);
        mTextPaint.setColor(mTextColor);
        if (mTextHeight == 0) {
            mTextHeight = mTextPaint.getTextSize();
        } else {
            mTextPaint.setTextSize(mTextHeight);
        }

        mTopicPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTopicPaint.setTextSize(80f);
        mTopicPaint.setColor(mTextColor);

        mPieBackground = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPieBackground.setStyle(Paint.Style.FILL);
        mPieBackground.setColor(0xfefefe);
        mPieBackground.setTextSize(mTextHeight);

        mShadowPaint = new Paint(0);
        mShadowPaint.setColor(0xff101010);
        mShadowPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));

    }

    public void addPart(PiePart pp) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(pp.getColor());
        paint.setStyle(Paint.Style.FILL);
        this.paints.add(paint);
        this.parts.add(pp);
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
