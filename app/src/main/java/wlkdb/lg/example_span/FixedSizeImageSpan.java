package wlkdb.lg.example_span;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

/**
 * 可以为text指定字体颜色，背景大小和resourceId
 */

public class FixedSizeImageSpan extends ImageSpan {

    private int textColor;
    private int width;
    private int height;

    public FixedSizeImageSpan(Context context, int resourceId, int textColor,
            int width, int height, boolean isDip) {
        super(context, resourceId);
        if (isDip) {
            width = DimenHelper.dip2px(context, width);
            height = DimenHelper.dip2px(context, height);
        }
        this.textColor = textColor;
        this.width = width;
        this.height = height;
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end,
            Paint.FontMetricsInt fm) {
        return width;
    }

    private static final float OVERSHOOT_RATIO = 0.125f;
    private static final float bgOffsetY = -1;

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end,
            float x, int top, int y, int bottom, Paint paint) {
        Drawable drawable = getDrawable();
        canvas.save();
        drawable.setBounds(0, 0, width, height);
        canvas.translate(Math.round(x),
                Math.round(y - drawable.getBounds().bottom + height * OVERSHOOT_RATIO) + bgOffsetY);
        drawable.draw(canvas);
        canvas.restore();

        canvas.save();
        paint.setColor(textColor);
        float textWidth = paint.measureText(text, start, end);
        canvas.translate((width - textWidth) / 2, -(height - paint.getTextSize()) / 2);
        canvas.drawText(text, start, end, x, y, paint);
        canvas.restore();
    }
}