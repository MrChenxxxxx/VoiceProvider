package com.newland.voiceprovider;

import com.iflytek.cloud.SpeechError;

/**
 * Created by cxg on 2017/5/19.
 */

public interface TCallback {
    void onCompleted(SpeechError speechError);

    void onSpeakBegin();
}
