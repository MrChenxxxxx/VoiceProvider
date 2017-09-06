// SpeakState.aidl
package com.newland.voiceprovider.aidl;

// Declare any non-default types here with import statements

interface ISynthesizerListener {

     void onSpeakBegin();
     void onBufferProgress(int i, int i1, int i2, String s);
     void onSpeakPaused() ;
     void onSpeakResumed() ;
     void onSpeakProgress(int i, int i1, int i2) ;
     void onCompleted() ;
     void onEvent(int i, int i1, int i2) ;
}
