package com.nkana.app.network.Responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chokkar
 */
public class MembersInfoResponse {

    @SerializedName("member_id")
    private String memberId;
    
    @SerializedName("group_id")
    private String groupId;

    @SerializedName("group_name")
    private String groupName;

    @SerializedName("email")
    private String email;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("date_joined")
    private String dateJoined;

    @SerializedName("is_admin")
    private String isAdmin;

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
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     * The first_name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     * The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     * The last_name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    public String getIsAdmin() {
        return isAdmin;
    }

    /**
     *
     * @param isAdmin
     * The is_admin
     */
    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

}