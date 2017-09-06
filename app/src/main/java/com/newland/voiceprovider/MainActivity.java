package com.newland.voiceprovider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.iflytek.cloud.SpeechError;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getName();

    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TtsUtils ttsUtil = new TtsUtils(new TCallback() {
            @Override
            public void onCompleted(SpeechError speechError) {
                Log.d(TAG,"onCompleted");
            }

            @Override
            public void onSpeakBegin() {

                Log.d(TAG,"onSpeakBegin");
            }
        });
        findViewById(R.id.btn_speak).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = ((EditText) findViewById(R.id.et_content)).getText().toString();
                if (TextUtils.isEmpty(s)){
                    return;
                }
                ttsUtil.startSpeaking(s);
            }
        });
    }
}
