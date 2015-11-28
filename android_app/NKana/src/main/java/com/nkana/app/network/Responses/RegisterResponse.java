package com.nkana.app.network.Responses;

import com.nkana.app.model.Login;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by chokkar
 */

public class RegisterResponse {

    public String email;
    public String id;
    public String error;
    public Login resp;

    // default constructor, getters and setters
    public RegisterResponse() {

    }

    public RegisterResponse(String error, Login resp) {
        this.error = error;
        this.resp = resp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Login getUserVo() {
        return resp;
    }

    public void setUserVo(Login resp) {
        this.resp = resp;
    }

    public String toJSON() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("error", error);
            jsonObject.put("Login", resp);
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

}
