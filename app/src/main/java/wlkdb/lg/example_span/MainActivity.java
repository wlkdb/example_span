package wlkdb.lg.example_span;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.widget.TextView;

import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.text_view);
        FixedSizeImageSpan span = new FixedSizeImageSpan(this, R.mipmap.ic_launcher, Color.BLACK, 60, 60, true);
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        stringBuilder.append("hello world")
                .setSpan(span, 0, 5, SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(stringBuilder);
    }
}
