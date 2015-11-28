package com.nkana.app.model.registration;

import com.nkana.app.model.Detail;

/**
 * Created by Android Team
 */
public class Error {

    public String message;
    public String code;
    public String type;
    public Detail detail;

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }




}
