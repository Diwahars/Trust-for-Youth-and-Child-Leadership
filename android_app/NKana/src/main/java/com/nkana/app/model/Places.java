package com.nkana.app.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chokkar on 18-11-15.
 */
public class Places {

    @SerializedName("place_id")
    public String place_id;

    @SerializedName("name")
    public String name;

    @SerializedName("entry_alert")
    public boolean entry_alert;

    @SerializedName("exit_alert")
    public boolean exit_alert;

    @SerializedName("latitude")
    public double latitude;

    @SerializedName("longitude")
    public double longitude;

    @SerializedName("fence_range")
    public int fence_range;

    @SerializedName("geopoints")
    public String geopoints;

    @SerializedName("address")
    public String address;

    @SerializedName("enabled")
    public boolean enabled;


}
