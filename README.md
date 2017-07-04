# example_span

在实际开发中，有一些精细的UI需求可能会比整体逻辑要花费更多时间。但是产品整体视觉效果的提升，也往往就在这些微小的差异中。例如listView中的复杂item，在item的标题或者边角处可能会需要加各种标签或icon。实现它们有多种方式，比如imageView、iconfont矢量图、span，其中span可以方便的进行图文混排，并设置文字的样式。这里实现了一种自定义的ImageSpan，可设置字体颜色、制定大小的背景图片，方便的实现图文混排。

核心代码：
```
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
```
