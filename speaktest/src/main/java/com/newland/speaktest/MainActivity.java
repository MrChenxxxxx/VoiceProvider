package com.newland.speaktest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.newland.voiceprovider.aidl.ISpeakService;
import com.newland.voiceprovider.aidl.ISynthesizerListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setAction("com.newland.voiceprovider.service");
        intent = getExplicitIntent(MainActivity.this, intent);
        boolean b = bindService(intent, conn, BIND_AUTO_CREATE);
        Log.d("cxg", "binder : " + b);

        findViewById(R.id.btn_speak).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String message = ((EditText) findViewById(R.id.et_msg)).getText().toString();

                    iMyAidlInterface.speak(message, new ISynthesizerListener.Stub() {
                        @Override
                        public void onSpeakBegin() throws RemoteException {
                            log("onSpeakBegin");
                        }

                        @Override
                        public void onBufferProgress(int i, int i1, int i2, String s) throws RemoteException {

                            log("onBufferProgress");
                        }

                        @Override
                        public void onSpeakPaused() throws RemoteException {

                            log("onSpeakPaused");
                        }

                        @Override
                        public void onSpeakResumed() throws RemoteException {

                            log("onSpeakResumed");
                        }

                        @Override
                        public void onSpeakProgress(int i, int i1, int i2) throws RemoteException {

                            log("onSpeakProgress");
                        }

                        @Override
                        public void onCompleted() throws RemoteException {

                            log("onCompleted");
                        }

                        @Override
                        public void onEvent(int i, int i1, int i2) throws RemoteException {

                            log("onEvent");
                        }
                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    ISpeakService iMyAidlInterface;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("cxg", "onServiceConnected");
            iMyAidlInterface = ISpeakService.Stub.asInterface(iBinder);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            Log.e("cxg", "onServiceConnected");
        }
    };

    private static Intent getExplicitIntent(Context context, Intent implicitIntent) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);
        Intent explicitIntent = new Intent(implicitIntent);
        explicitIntent.setComponent(component);
        return explicitIntent;
    }

    private void log(String msg){
        Log.e("cxg",msg);
    }

}
