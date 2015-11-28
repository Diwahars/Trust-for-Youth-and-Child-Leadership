package com.nkana.app.network.RestError;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mobile Team on 14-10-15.
 */
public class RestLoginError {

    @SerializedName("detail")
    public List<String> detail;
}
