package com.nkana.app.network.Responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chokkar
 */

public class GroupDeleteResponse {

    @SerializedName("place_id")
    private String placeId;

    @SerializedName("name")
    private String name;

    @SerializedName("group_id")
    private String groupId;

    @SerializedName("group_name")
    private String groupName;

    @SerializedName("address")
    private Object address;

    @SerializedName("enabled")
    private Boolean enabled;

    @SerializedName("is_public")
    private Boolean isPublic;

    @SerializedName("group")
    private Integer group;

    /**
     *
     * @return
     * The placeId
     */
    public String getPlaceId() {
        return placeId;
    }

    /**
     *
     * @param placeId
     * The place_id
     */
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The groupId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     *
     * @param groupId
     * The group_id
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     *
     * @return
     * The groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     *
     * @param groupName
     * The group_name
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     *
     * @return
     * The address
     */
    public Object getAddress() {
        return address;
    }

    /**
     *
     * @param address
     * The address
     */
    public void setAddress(Object address) {
        this.address = address;
    }

    /**
     *
     * @return
     * The enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     *
     * @param enabled
     * The enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     *
     * @return
     * The isPublic
     */
    public Boolean getIsPublic() {
        return isPublic;
    }

    /**
     *
     * @param isPublic
     * The is_public
     */
    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    /**
     *
     * @return
     * The group
     */
    public Integer getGroup() {
        return group;
    }

    /**
     *
     * @param group
     * The group
     */
    public void setGroup(Integer group) {
        this.group = group;
    }
}
