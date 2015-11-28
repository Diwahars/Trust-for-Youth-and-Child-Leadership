package com.nkana.app.model;

/**
 * Created by Ravikumar
 */
public class ChangePassword {
    String current_password;
    String new_password;
    String re_new_password;

    public void setCurrent_password(String current_password) {
        this.current_password = current_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public void setRe_new_password(String re_new_password) {
        this.re_new_password = re_new_password;
    }


}
