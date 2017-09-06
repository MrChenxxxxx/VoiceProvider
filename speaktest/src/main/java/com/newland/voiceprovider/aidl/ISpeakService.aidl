package com.newland.voiceprovider.aidl;
import com.newland.voiceprovider.aidl.ISynthesizerListener;
/**
 * Created by cxg on 2017/9/5.
 */

interface ISpeakService {

    void speak(String content ,ISynthesizerListener listener);
}
