package com.nkana.app.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chokkar on 18-11-15.
 */
public class Members {

    @SerializedName("member_id")
    public String member_id;

    @SerializedName("email")
    public String email;

    @SerializedName("mobile")
    public String mobile;

    @SerializedName("first_name")
    public String first_name;

    @SerializedName("last_name")
    public String last_name;
}
