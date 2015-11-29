package com.nkana.app.network.Responses;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("status")
    private Integer status;

    /**
     *
     * @return
     * The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

}