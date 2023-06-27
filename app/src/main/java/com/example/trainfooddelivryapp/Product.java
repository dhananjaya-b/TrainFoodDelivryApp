package com.example.trainfooddelivryapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private String name;
    private String type;
    private String description;
    private double price;
    private String imageLink;

    private double available;

    public Product() {
    }

    public Product(String name, double price, String imageLink, double available) {
        this.name = name;
        this.price = price;
        this.imageLink = imageLink;
        this.available = available;
    }

    public Product(String name, String type, String description, double price, String imageLink, double available) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        this.imageLink = imageLink;
        this.available = available;
    }

    public double getAvailable() {
        return available;
    }

    public void setAvailable(double available) {
        this.available = available;
    }
    // Getters and Setters for the attributes

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    // Parcelable implementation

    protected Product(Parcel in) {
        name = in.readString();
        type = in.readString();
        description = in.readString();
        price = in.readDouble();
        imageLink = in.readString();
        available =in.readDouble();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(description);
        dest.writeDouble(price);
        dest.writeString(imageLink);
        dest.writeDouble(available);
    }
}
