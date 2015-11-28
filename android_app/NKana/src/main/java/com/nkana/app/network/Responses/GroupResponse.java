package com.nkana.app.network.Responses;

import com.nkana.app.model.Members;
import com.nkana.app.model.Places;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by chokkar on 18-11-15.
 */
public class GroupResponse {

    @SerializedName("group_id")
    public String group_id;

    @SerializedName("name")
    public String name;

    @SerializedName("max_speed")
    public String max_speed;

    @SerializedName("min_battery")
    public String min_battery;

    @SerializedName("speed_alert")
    public String speed_alert;

    @SerializedName("collision_alert")
    public String collision_alert;

    @SerializedName("min_battery_alert")
    public String min_battery_alert;

    @SerializedName("fence_violation_alert")
    public String fence_violation_alert;

    @SerializedName("enabled")
    public String enabled;

    @SerializedName("is_public")
    public String is_public;

    @SerializedName("places")

    public ArrayList<Places> places;

    @SerializedName("version")
    public String version;

    @SerializedName("members")
    public ArrayList<Members> members;

    public ArrayList<Members> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Members> members) {
        this.members = members;
    }

    public ArrayList<Places> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Places> places) {
        this.places = places;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMax_speed() {
        return max_speed;
    }

    public void setMax_speed(String max_speed) {
        this.max_speed = max_speed;
    }

    public String getMin_battery() {
        return min_battery;
    }

    public void setMin_battery(String min_battery) {
        this.min_battery = min_battery;
    }

    public String getSpeed_alert() {
        return speed_alert;
    }

    public void setSpeed_alert(String speed_alert) {
        this.speed_alert = speed_alert;
    }

    public String getCollision_alert() {
        return collision_alert;
    }

    public void setCollision_alert(String collision_alert) {
        this.collision_alert = collision_alert;
    }

    public String getMin_battery_alert() {
        return min_battery_alert;
    }

    public void setMin_battery_alert(String min_battery_alert) {
        this.min_battery_alert = min_battery_alert;
    }

    public String getFence_violation_alert() {
        return fence_violation_alert;
    }

    public void setFence_violation_alert(String fence_violation_alert) {
        this.fence_violation_alert = fence_violation_alert;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getIs_public() {
        return is_public;
    }

    public void setIs_public(String is_public) {
        this.is_public = is_public;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


}
