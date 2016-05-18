package com.example.chaitanya.carsdetail.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * class of CarResponse for getting the response of how many json objects are there in the given json API
 */
public class CarResponse {

    @SerializedName("makesCount")
    private int makesCount;

    @SerializedName("makes")
    private List<Car> makes;

    @SerializedName("total_makes")
    private int totalMakes;

    @SerializedName("total_makesCount")
    private int totalMakesCount;

    public int getMakesCount() {
        return makesCount;
    }
    public void setMakesCount(int makesCount) {
        this.makesCount = makesCount;
    }

    public List<Car> getMakes() {
        return makes;
    }
    public void setMakes(List<Car> makes) {
        this.makes = makes;
    }

    public int getTotalMakes() {
        return totalMakes;
    }
    public void setTotalMakes(int totalMakes) {
        this.totalMakes = totalMakes;
    }

    public int getTotalMakesCount() {
        return totalMakesCount;
    }
    public void setTotalMakesCount(int totalMakesCount) {
        this.totalMakesCount = totalMakesCount;
    }
}
