package com.newland.voiceprovider.aidl;
import com.newland.voiceprovider.aidl.ISynthesizerListener;
import com.newland.voiceprovider.aidl.VoiceConfig;
/**
 * Created by cxg on 2017/9/5.
 */

interface ISpeakService {

    void initVoice(in VoiceConfig config);
    void speak(String content ,ISynthesizerListener listener);
}
