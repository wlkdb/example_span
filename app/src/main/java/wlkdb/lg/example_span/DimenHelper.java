package wlkdb.lg.example_span;

import android.content.Context;

public class DimenHelper {

    public static int dip2px(Context context, float dip) {
        return (int) (dip * context.getResources().getDisplayMetrics().density);
    }
}
