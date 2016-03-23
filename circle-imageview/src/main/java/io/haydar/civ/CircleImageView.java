package io.haydar.civ;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by gjy on 16/3/23.
 */
public class CircleImageView extends ImageView {

    private Bitmap mBitmap;

    private Paint mPaint;

    BitmapShader shader;
    Canvas canvas1;

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(0.0f);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return; // couldn't resolve the URI
        }
        if (getWidth() == 0 || getHeight() == 0) {
            return;     // nothing to draw (empty bounds)
        }
        int length = Math.min(getDrawable().getIntrinsicWidth(), getDrawable().getIntrinsicHeight());
        mBitmap = Bitmap.createBitmap(
                getDrawable().getIntrinsicWidth(),
                getDrawable().getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas1 = new Canvas(mBitmap);
        int width = Math.min(getWidth(), getHeight());
        getDrawable().setBounds(0, 0, getDrawable().getIntrinsicWidth(), getDrawable().getIntrinsicHeight());
        float scaleWidth = width / (float) length;
        float scaleHeight = width / (float) length;
        canvas1.scale(scaleWidth, scaleHeight);
        getDrawable().draw(canvas1);
        shader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(shader);
        canvas.drawCircle(width / 2, width / 2, width / 2, mPaint);

    }

}
