package com.rain.library.impl;

import android.os.Parcel;
import android.os.Parcelable;

import com.rain.library.bean.Photo;
import com.rain.library.utils.Rlog;

import java.util.ArrayList;

/**
 * @author:duyu
 * @org :   www.yudu233.com
 * @email : yudu233@gmail.com
 * @date :  2019/5/16 18:56
 * @filename : PhotoSelectCallback
 * @describe :
 */
public class PhotoSelectCallback implements Parcelable {


    protected PhotoSelectCallback() {
    }

    public void singleSelect(ArrayList<Photo> photos) {
    }

    public void moreSelect(ArrayList<Photo> photos) {
    }

    public void clipImage(ArrayList<Photo> photos) {
    }

    public void cameraImage(ArrayList<Photo> photos) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    protected PhotoSelectCallback(Parcel in) {
    }

    public static final Creator<PhotoSelectCallback> CREATOR = new Creator<PhotoSelectCallback>() {
        @Override
        public PhotoSelectCallback createFromParcel(Parcel source) {
            return new PhotoSelectCallback(source);
        }

        @Override
        public PhotoSelectCallback[] newArray(int size) {
            return new PhotoSelectCallback[size];
        }
    };
}