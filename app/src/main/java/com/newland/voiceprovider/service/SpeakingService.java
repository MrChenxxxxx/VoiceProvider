package com.newland.voiceprovider.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.iflytek.cloud.SpeechError;
import com.newland.voiceprovider.TCallback;
import com.newland.voiceprovider.TtsUtils;
import com.newland.voiceprovider.aidl.ISpeakService;
import com.newland.voiceprovider.aidl.ISynthesizerListener;
import com.newland.voiceprovider.aidl.VoiceConfig;

/**
 * Created by cxg on 2017/9/5.
 */

public class SpeakingService extends Service {
    private static String TAG  = SpeakingService.class.getName();
    TtsUtils ttsUtils;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }

    ISpeakService.Stub mBinder = new  ISpeakService.Stub(){

        @Override
        public void initVoice(VoiceConfig config) throws RemoteException {

        }

        @Override
        public void speak(String content, final ISynthesizerListener listener) throws RemoteException {
            Log.e(TAG,"SpeakingService begin speak");
            if (ttsUtils==null){
                ttsUtils = new TtsUtils(new TCallback() {
                    @Override
                    public void onCompleted(SpeechError speechError) {
                        if (listener!=null){
                            try {
                                listener.onCompleted();
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onSpeakBegin() {
                        if (listener!=null){
                            try {
                                listener.onSpeakBegin();
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }

            ttsUtils.startSpeaking(content);
        }
    };


    private void initTtsUtil(){

    }
}
