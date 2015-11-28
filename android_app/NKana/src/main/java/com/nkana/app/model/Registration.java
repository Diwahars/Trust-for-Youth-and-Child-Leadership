package com.nkana.app.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ravikumar
 */
public class Registration {

    private String first_name;
    private String last_name;
    private String mobile;
    private String email;
    private String password;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }


    public String toJSON() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("first_name", first_name);
            jsonObject.put("last_name", last_name);
            jsonObject.put("mobile", mobile);
            jsonObject.put("email", email);
            jsonObject.put("password", password);
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}
