package com.example.chaitanya.carsdetail.model;


import com.google.gson.annotations.SerializedName;

/**
 * class of CarYears for initialization of all json data using retrofit
 */
public class CarArrayYears {

    @SerializedName("id")
    private Integer id;

    @SerializedName("year")
    private Integer year;

    public CarArrayYears(Integer id, Integer year) {
        this.id = id;
        this.year = year;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
}
