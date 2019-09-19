package com.post.station.ui.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.post.station.R;

public class AddExpressBrandActivity extends AppCompatActivity {
private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_express_brand);
        textView=findViewById(R.id.textView);
        SpannableString spanString = new SpannableString("快递品\n牌,切勿遗漏!  !  !");
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(25);
        spanString.setSpan(span, 0, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanString.setSpan(new ForegroundColorSpan(Color.RED), 0, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.append(spanString);
    }
}
