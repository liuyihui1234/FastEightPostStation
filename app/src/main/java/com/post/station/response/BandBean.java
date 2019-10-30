package com.post.station.response;

import android.os.Parcel;
import android.os.Parcelable;

public class BandBean implements Parcelable{
    public String img;
    public String name;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    protected BandBean(Parcel in) {
        img = in.readString();
        name = in.readString();

    }
    public BandBean() {
    }
    public String toString() {
        return "BandBean{" +
                "img='" + img + '\'' +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(img);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<BandBean> CREATOR = new Parcelable.Creator<BandBean>() {
        @Override
        public BandBean createFromParcel(Parcel in) {

             return new BandBean(in);
        }

        @Override
        public BandBean[] newArray(int size) {
            return new BandBean[size];
        }
    };
}
