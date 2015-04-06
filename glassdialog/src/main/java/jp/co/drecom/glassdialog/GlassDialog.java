package jp.co.drecom.glassdialog;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;


/**
 * TODO: document your custom view class.
 */
public class GlassDialog extends View {

    private Context context;

    private Drawable mBackgroundDrawable;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

    private ObjectAnimator mScaleAnim;
    private PropertyValuesHolder mScaleX;
    private PropertyValuesHolder mScaleY;





    //customize attribute
    private String mTextTitle;
    private String mTextContent;
    private float mTextSize = 0;
    private float mTouchPointX;
    private float mTouchPointY;

    public GlassDialog(Context context) {
        super(context);
        init(context, null, 0);
    }

    public GlassDialog(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public GlassDialog(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);

    }

    private void init(Context context,AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.GlassDialog, defStyle, 0);

        this.context = context;

        mTextTitle = a.getString(
                R.styleable.GlassDialog_glassTextTitle);
        mTextContent = a.getString(
                R.styleable.GlassDialog_glassTextContent);
        mTextSize = a.getDimension(R.styleable.GlassDialog_glassTextSize, mTextSize);
        mBackgroundDrawable = context.getResources().getDrawable(R.drawable.glass);

        a.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        //TODO
        //init the animation

        mScaleX = PropertyValuesHolder.ofFloat("scaleX",0.0f, 1.2f, 1.0f, 1.1f, 1.0f);
        mScaleY = PropertyValuesHolder.ofFloat("scaleY",0.0f, 1.2f, 1.0f, 1.1f, 1.0f);
        mScaleAnim = ObjectAnimator.ofPropertyValuesHolder(this,mScaleX,mScaleY);
        mScaleAnim.setDuration(500);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mTextSize);
        mTextWidth = mTextPaint.measureText(mTextTitle);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        Log.v("TAG", "width is " + getWidth() + " height is " + getHeight());
        int contentHeight = getHeight() - paddingTop - paddingBottom;



        // Draw the example drawable on top of the text.
        if (mBackgroundDrawable != null) {
            mBackgroundDrawable.setBounds(paddingLeft, paddingTop,
                    paddingLeft + contentWidth, paddingTop + contentHeight);
            mBackgroundDrawable.draw(canvas);
        }


        // Draw the text.
        canvas.drawText(mTextTitle,
                paddingLeft + (contentWidth - mTextWidth) / 2,
                paddingTop + (contentHeight + mTextHeight) / 2,
                mTextPaint);
        canvas.drawText(mTextContent,
                mTextWidth + paddingLeft + (contentWidth - mTextWidth) / 2,
                mTextHeight + paddingTop + (contentHeight + mTextHeight) / 2,
                mTextPaint);



    }

    public void startAnim(MotionEvent event) {
        Log.v("TAG", "left is " + getX() + " top is " + getY());

        setX((int) event.getX());
        //should minus the action bar height + status bar height
        setY((int) event.getY());

        Log.v("TAG", "left is " + getX() + " top is " + getY());
//        startAnimation(mScaleAnim);
        mScaleAnim.start();
    }

    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
    public String getTextTile() {
        return mTextTitle;
    }

    /**
     * Sets the view's example string attribute value. In the example view, this string
     * is the text to draw.
     *
     * @param exampleString The example string attribute value to use.
     */
    public void setTextTitle(String exampleString) {
        mTextTitle = exampleString;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the content attribute value.
     *
     * @return The content attribute value.
     */
    public String getTextContent() {
        return mTextContent;
    }

    /**
     * Sets the view's content attribute value.
     *
     * @param exampleString The content attribute value to use.
     */
    public void setTextContent(String exampleString) {
        mTextContent = exampleString;
        invalidateTextPaintAndMeasurements();
    }

}
