package com.newland.voiceprovider;

import android.app.Application;
import android.content.Context;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

/**
 * Created by cxg on 2017/9/5.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    public static Context getAppInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=59ae0ace");
    }
}
