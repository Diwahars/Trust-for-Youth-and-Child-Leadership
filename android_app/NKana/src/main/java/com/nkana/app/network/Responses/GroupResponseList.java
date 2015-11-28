package com.nkana.app.network.Responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by chokkar on 19-11-15.
 */
public class GroupResponseList {

    @SerializedName("group_id")
    private String groupId;

    @SerializedName("name")
    private String groupName;

    @SerializedName("enabled")
    private boolean enabled;

    @SerializedName("members")
    private ArrayList<Integer> members;

    @SerializedName("places")
    private ArrayList<Integer> places;

    @SerializedName("version")
    private int version;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public ArrayList<Integer> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Integer> members) {
        this.members = members;
    }

    public ArrayList<Integer> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Integer> places) {
        this.places = places;
    }

}
