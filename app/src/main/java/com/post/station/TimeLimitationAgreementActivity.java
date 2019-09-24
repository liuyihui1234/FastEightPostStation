package com.post.station;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

public class TimeLimitationAgreementActivity extends AppCompatActivity {
private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_limitation_agreement);
        textView = findViewById(R.id.textView);
        SpannableString spanString = new SpannableString("必须做\n派件");
        spanString.setSpan((new StyleSpan(Typeface.BOLD)), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.append(spanString);
    }
}
