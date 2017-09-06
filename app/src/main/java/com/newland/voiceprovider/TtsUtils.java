package com.newland.voiceprovider;

import android.os.Bundle;
import android.text.TextUtils;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;

/**
 * Created by cxg on 2017/5/19.
 */

public class TtsUtils {

    private SpeechSynthesizer mTts;
    private TCallback callback;

    public TtsUtils(TCallback callback){
        this.callback = callback;
    }


    private void initVoice() {
        //1.创建SpeechSynthesizer对象, 第二个参数：本地合成时传InitListener
        mTts = SpeechSynthesizer.createSynthesizer(MyApplication.getAppInstance(), null);
        //2.合成参数设置，详见《科大讯飞MSC API手册(Android)》SpeechSynthesizer 类
        mTts.setParameter(SpeechConstant.VOICE_NAME, "vixy");//设置发音人
        // 0 小燕 青年女声 中英文（普通话） xiaoyan
        // 1 小宇 青年男声 中英文（普通话） xiaoyu
        // 2 凯瑟琳 青年女声 英文 catherine
        // 3 亨利 青年男声 英文 henry
        // 4 玛丽 青年女声 英文 vimary
        // 5 小研 青年女声 中英文（普通话） vixy
        // 6 小琪 青年女声 中英文（普通话） vixq xiaoqi
        // 7 小峰 青年男声 中英文（普通话） vixf
        // 8 小梅 青年女声 中英文（粤语） vixm xiaomei
        // 9 小莉 青年女声 中英文（台湾普通话） vixl xiaolin
        // 10 小蓉 青年女声 汉语（四川话） vixr xiaorong
        // 11 小芸 青年女声 汉语（东北话） vixyun xiaoqian
        // 12 小坤 青年男声 汉语（河南话） vixk xiaokun
        // 13 小强 青年男声 汉语（湖南话） vixqa xiaoqiang
        // 14 小莹 青年女声 汉语（陕西话） vixying
        // 15 小新 童年男声 汉语（普通话） vixx xiaoxin
        // 16 楠楠 童年女声 汉语（普通话） vinn nannan
        // 17 老孙 老年男声 汉语（普通话） vils
        mTts.setParameter(SpeechConstant.SPEED, "70");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "100");//设置音量，范围0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
        //设置合成音频保存位置（可自定义保存位置），保存在“./sdcard/iflytek.pcm”
        //保存在SD卡需要在AndroidManifest.xml添加写SD卡权限
        //如果不需要保存合成音频，注释该行代码
        //sTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");
    }

    public void startSpeaking(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        if (mTts == null){
            initVoice();
        }
        mTts.startSpeaking(text,mSynListener);
    }

    private SynthesizerListener mSynListener = new SynthesizerListener() {
        @Override
        public void onSpeakBegin() {
            if (callback != null){
                callback.onSpeakBegin();
            }
        }

        @Override
        public void onBufferProgress(int i, int i1, int i2, String s) {

        }

        @Override
        public void onSpeakPaused() {

        }

        @Override
        public void onSpeakResumed() {

        }

        @Override
        public void onSpeakProgress(int i, int i1, int i2) {

        }

        @Override
        public void onCompleted(SpeechError speechError) {
            if (callback != null){
                callback.onCompleted(speechError);
            }
        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {

        }
    };
}
