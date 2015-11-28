package com.nkana.app.network.RestError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Android Team
 */

// This is class should be modified as your server's error response

public class RestErrorGeneric {

    public String status_code;
    public String non_field_errors;

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getNon_field_errors() {
        return non_field_errors;
    }

    public void setNon_field_errors(String non_field_errors) {
        this.non_field_errors = non_field_errors;
    }

    private String message;

    public RestErrorGeneric() {
    }

    public RestErrorGeneric(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toJSON() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("status_code", status_code);
            jsonObject.put("non_field_errors", non_field_errors);
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}