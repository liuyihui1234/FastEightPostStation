package com.post.station;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.post.station.base.BaseActivity;
import com.post.station.ui.mine.AddExpressBrandActivity;
import com.post.station.utils.AudioUtil;
import com.post.station.widget.SpeechRecognitionPopupWindow;
import com.umeng.socialize.media.Base;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CreateOrderActivity extends BaseActivity {
    private SpeechRecognitionPopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        ButterKnife.bind(this);


    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, CreateOrderActivity.class));
    }
    @OnClick({ R.id.mBackImageBtn,R.id.iv_voice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mBackImageBtn:
                finish();
                break;
            case R.id.iv_voice:
                applyPermissison();
                break;
        }
    }
    //申请录音权限
    private static final int GET_RECODE_AUDIO = 1;
    private static String[] PERMISSION_AUDIO = {
            Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private File wavFileSend;

    /*
     * 申请录音权限*/
    public void applyPermissison() {
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSION_AUDIO,
                    GET_RECODE_AUDIO);
        } else {
            startRecord();
        }
    }
    private void startRecord() {
        AudioUtil.getInstance().startRecord();
        showDialog();
    }

    private void showDialog() {
        if (popupWindow != null) {
            popupWindow = null;
        }
        popupWindow = new SpeechRecognitionPopupWindow(this, aBoolean -> {
        }, s -> {
            stopRecord();
        });
        popupWindow.show();
    }
    private void stopRecord() {
        AudioUtil.getInstance().stopRecord();
        wavFileSend = AudioUtil.getInstance().convertWaveFile();
        String base5 = AudioUtil.imageToBase64(wavFileSend.getAbsolutePath());
        Log.e("语音","base5"+base5);
        upload(base5);
    }

    private void upload(String imagVale) {
        showProgressDialog();
        String url = "http://www.k8yz.com/app/voicetransfer/transferToWord";
        FormBody.Builder builder = new FormBody.Builder();
        /* 添加两个参数 */
        builder.add("voiceImg", "" + imagVale);
        builder.add("sufName", ".wav");
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        new OkHttpClient().newCall(request).enqueue(new okhttp3.Callback() {

            public void onResponse(Call call, Response response) {
                hideProgressDialog();
                if (response.isSuccessful()) {
                    try {
                        String body = response.body().string();
                        Log.e("发现发现发现", "response.body(): "+response.body());
                        JSONObject jsonObject = new JSONObject(body);
                        Log.e("发现发现发现", "onResponse: "+jsonObject);
                        final int code = jsonObject.getInt("code");
                        final String msg = jsonObject.getString("msg");
                        JSONObject data = (JSONObject) jsonObject.get("data");
                        final String text = data.getString("Text");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (code == 1) {
//                                    ed_content.setText(text);
                                } else if (code == 0) {
                                    toast(msg);
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgressDialog();
                        String msg = e.getMessage();
                        toast(msg);
                    }
                });
            }
        });
    }


}
