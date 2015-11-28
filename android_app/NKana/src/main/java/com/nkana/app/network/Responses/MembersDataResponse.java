package com.nkana.app.network.Responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chokkar on 27-11-15.
 */

public class MembersDataResponse {

    @SerializedName("member_id")
    private String memberId;

    @SerializedName("date_joined")
    private String dateJoined;

    @SerializedName("is_admin")
    private Boolean isAdmin;

    @SerializedName("group_name")
    private String groupName;

    @SerializedName("is_active")
    private Boolean isActive;

    /**
     *
     * @return
     * The memberId
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     *
     * @param memberId
     * The member_id
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     *
     * @return
     * The dateJoined
     */
    public String getDateJoined() {
        return dateJoined;
    }

    /**
     *
     * @param dateJoined
     * The date_joined
     */
    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    /**
     *
     * @return
     * The isAdmin
     */
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     *
     * @param isAdmin
     * The is_admin
     */
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
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
     * The isActive
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     *
     * @param isActive
     * The is_active
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}