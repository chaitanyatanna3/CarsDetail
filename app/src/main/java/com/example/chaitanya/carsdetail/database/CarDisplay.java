package com.example.chaitanya.carsdetail.database;

import android.os.Parcel;
import android.os.Parcelable;

//parcelabler class of Cars to display the cars in the activity
public class CarDisplay implements Parcelable {

    String name, modelName, year;

    public CarDisplay() {

    }

    public CarDisplay(String name, String modelName, String year) {
        this.name = name;
        this.modelName = modelName;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getModelName() {
        return modelName;
    }

    public String getYear() {
        return year;
    }

    protected CarDisplay(Parcel in) {
        name = in.readString();
        modelName = in.readString();
        year = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(modelName);
        dest.writeString(year);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CarDisplay> CREATOR = new Parcelable.Creator<CarDisplay>() {
        @Override
        public CarDisplay createFromParcel(Parcel in) {
            return new CarDisplay(in);
        }

        @Override
        public CarDisplay[] newArray(int size) {
            return new CarDisplay[size];
        }
    };
}
