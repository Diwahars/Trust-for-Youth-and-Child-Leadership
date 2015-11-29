package com.nkana.app.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nkana.app.Constants.IConstants;
import com.nkana.app.R;
import com.nkana.app.data.DBConnection;
import com.nkana.app.model.DeviceConfig;
import com.nkana.app.model.GeneralError;
import com.nkana.app.model.Registration;
import com.nkana.app.model.RegistrationError;
import com.nkana.app.network.Responses.LoginResponse;
import com.nkana.app.network.Responses.RegisterResponse;
import com.nkana.app.network.RestClient;
import com.nkana.app.util.StringUtil;
import com.nkana.app.model.Login;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.client.Header;

/**
 * Created by Chokkar G
 */

public class MendorRegistrationActivity extends AppCompatActivity {


    private static final String LOG_TAG = RegistrationActivity.class.getSimpleName();
    private EditText firstName, lastName, mobileNumber, email, passWord;
    private Button registerButton;
    private Registration registration;
    private Context context;
    private String name, dob, sex, designation, placeofwork, experience, address, mobileno, email_id, areaofexpertise , hoursinaweek;
    private String firstNameValue, lastNameValue , emailValue , passWordValue , mobileNumberValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mentor_register_activity);
        initUiViews();
        registration = new Registration();
        context = this;
    }

    private void initUiViews() {
        lastName = (EditText) findViewById(R.id.lastname);
        mobileNumber = (EditText) findViewById(R.id.mobileno);
        email = (EditText) findViewById(R.id.email);
        passWord = (EditText) findViewById(R.id.password);
        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRegister();
            }
        });
    }


    private void doRegister() {
        if (!initializeUserInfo()) {
            return;
        }
        RestClient.get().register(registration, new Callback<RegisterResponse>() {

            @Override
            public void success(RegisterResponse registerResponse, Response response) {
                // success!
                BufferedReader reader = null;
                StringBuilder sb = new StringBuilder();
                try {
                    reader = new BufferedReader(new InputStreamReader(response.getBody().in()));
                    String line;
                    try {
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String result = sb.toString();
                Toast.makeText(context, "Registration Successful, please verify through Online", Toast.LENGTH_LONG).show();
                Log.i(LOG_TAG, result);
            }

            @Override
            public void failure(RetrofitError error) {
                RegistrationError registrationError = (RegistrationError) error.getBodyAs(RegistrationError.class);
                if (registrationError != null) {
                    Toast.makeText(context, "" + registrationError.getError().getMessage() + "\n"
                            + "Email " + registrationError.getError().getDetail().getEmail(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean initializeUserInfo() {
        firstNameValue = firstName.getText().toString();
        lastNameValue = lastName.getText().toString();
        emailValue = email.getText().toString();
        passWordValue = passWord.getText().toString();
        mobileNumberValue = mobileNumber.getText().toString();
        Log.i(LOG_TAG , "First: " +firstNameValue + "lastNameValue: " +lastNameValue +"emailValue" +emailValue
                + "passWordValue "+passWordValue +"mobileNumberValue "+mobileNumberValue);
//        firstNameValue = "chokkar";
//        lastNameValue = "G";
//        emailValue = "chokkar.g@gmail.com";
//        passWordValue = "chokkar";
//        mobileNumberValue = "9940892858";


        if(StringUtil.isNullOrEmpty(firstNameValue) || StringUtil.isNullOrEmpty(lastNameValue)
                || StringUtil.isNullOrEmpty(emailValue) || StringUtil.isNullOrEmpty(passWordValue)
                || StringUtil.isNullOrEmpty(mobileNumberValue)){
            Toast.makeText(context, "Please fill the form", Toast.LENGTH_LONG).show();
            return false;
        } else {
            registration.setFirst_name(firstNameValue);
            registration.setLast_name(lastNameValue);
            registration.setEmail(emailValue);
            registration.setPassword(passWordValue);
            registration.setMobile(mobileNumberValue);
            return true;
        }
    }
}