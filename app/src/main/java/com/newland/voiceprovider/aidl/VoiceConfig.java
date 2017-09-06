package com.newland.voiceprovider.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cxg on 2017/9/5.
 */

public class VoiceConfig implements Parcelable {
    private String language ;
    private int speed;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    protected VoiceConfig(Parcel in) {
        language = in.readString();
        speed = in.readInt();
    }

    public static final Creator<VoiceConfig> CREATOR = new Creator<VoiceConfig>() {
        @Override
        public VoiceConfig createFromParcel(Parcel in) {
            return new VoiceConfig(in);
        }

        @Override
        public VoiceConfig[] newArray(int size) {
            return new VoiceConfig[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(language);
        parcel.writeInt(speed);
    }
}
