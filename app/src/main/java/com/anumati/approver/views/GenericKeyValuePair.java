package com.anumati.approver.views;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class GenericKeyValuePair implements Serializable, Parcelable {
    public String key;
    public String label;
    public String value;

    protected GenericKeyValuePair(Parcel in) {
        key = in.readString();
        label = in.readString();
        value = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(label);
        dest.writeString(value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GenericKeyValuePair> CREATOR = new Creator<GenericKeyValuePair>() {
        @Override
        public GenericKeyValuePair createFromParcel(Parcel in) {
            return new GenericKeyValuePair(in);
        }

        @Override
        public GenericKeyValuePair[] newArray(int size) {
            return new GenericKeyValuePair[size];
        }
    };
}
