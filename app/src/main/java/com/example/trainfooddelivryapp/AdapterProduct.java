package com.example.trainfooddelivryapp;

import android.os.Parcel;
import android.os.Parcelable;

public class AdapterProduct implements Parcelable {
    private String name;
    private double price;
    private String imageLink;

    // Constructor, getters, and setters

    // Parcelable implementation
    protected AdapterProduct(Parcel in) {
        name = in.readString();
        price = in.readDouble();
        imageLink = in.readString();
    }

    public static final Creator<AdapterProduct> CREATOR = new Creator<AdapterProduct>() {
        @Override
        public AdapterProduct createFromParcel(Parcel in) {
            return new AdapterProduct(in);
        }

        @Override
        public AdapterProduct[] newArray(int size) {
            return new AdapterProduct[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeString(imageLink);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
