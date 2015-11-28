package com.nkana.app.network.Responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ravikumar on 10/16/2015.
 */
public class UpdateProfileResponse {
    @SerializedName("mobile")
    private String mobileNo;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("email")
    private String emailId;

    public String getMobileNo() {
        return mobileNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailId() {
        return emailId;
    }
}
