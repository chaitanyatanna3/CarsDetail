package com.example.chaitanya.carsdetail.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * class of CarModels for initialization of all json data using retrofit
 */
public class CarArrayModel {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("niceName")
    private String niceName;

    @SerializedName("years")
    private List<CarArrayYears> carArrayYears = new ArrayList<CarArrayYears>();


    public CarArrayModel(String id, String name, String niceName, List<CarArrayYears> carArrayYears) {
        this.id = id;
        this.name = name;
        this.niceName = niceName;
        this.carArrayYears = carArrayYears;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getNiceName() {
        return niceName;
    }
    public void setNiceName(String niceName) {
        this.niceName = niceName;
    }

    public List<CarArrayYears> getCarArrayYears() {
        return carArrayYears;
    }
    public void setCarArrayYears(List<CarArrayYears> carArrayYears) {
        this.carArrayYears = carArrayYears;
    }

}
