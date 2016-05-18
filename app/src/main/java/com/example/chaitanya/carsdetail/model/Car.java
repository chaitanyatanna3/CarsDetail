package com.example.chaitanya.carsdetail.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


/**
 * class of Cars for initialization of all json data using retrofit
 */
public class Car {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("niceName")
    private String niceName;

    @SerializedName("models")
    private List<CarArrayModel> carArrayModels = new ArrayList<CarArrayModel>();


    public Car(Integer id, String name, String niceName, List<CarArrayModel> carArrayModels) {
        this.id = id;
        this.name = name;
        this.niceName = niceName;
        this.carArrayModels = carArrayModels;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
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

    public List<CarArrayModel> getCarArrayModels() {
        return carArrayModels;
    }
    public void setCarArrayModels(List<CarArrayModel> carArrayModels) {
        this.carArrayModels = carArrayModels;
    }


}
