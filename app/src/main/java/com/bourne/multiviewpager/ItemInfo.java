package com.bourne.multiviewpager;

import android.os.Parcel;
import android.os.Parcelable;


public class ItemInfo implements Parcelable {

    public int imageRes;
    public String title;

    public ItemInfo(int imageRes, String title) {
        this.imageRes = imageRes;
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.imageRes);
        dest.writeString(this.title);
    }

    protected ItemInfo(Parcel in) {
        this.imageRes = in.readInt();
        this.title = in.readString();
    }

    public static final Creator<ItemInfo> CREATOR = new Creator<ItemInfo>() {
        @Override
        public ItemInfo createFromParcel(Parcel source) {
            return new ItemInfo(source);
        }

        @Override
        public ItemInfo[] newArray(int size) {
            return new ItemInfo[size];
        }
    };
}
