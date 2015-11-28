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
import com.nkana.app.network.Responses.LoginResponse;
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

public class LoginMendorActivity extends AppCompatActivity {
    private static final String LOG_TAG = LoginMendorActivity.class.getSimpleName();
    private Context context;
    private EditText userEmail;
    private EditText userPassword;
    private Login login;
    private DeviceConfig deviceConfig;
    private Button loginButton;
    private static final String ANDROID = "Android";
    private String mDeviceID;
    private DBConnection dbConnection;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_mendor_activity);
        context = this;
        dbConnection = new DBConnection(context);

        userEmail = (EditText) findViewById(R.id.userEmail);
        userPassword = (EditText) findViewById(R.id.userPassword);
        loginButton = (Button) findViewById(R.id.loginButton);
        login = new Login();
        deviceConfig = new DeviceConfig();
//        login.setPassword("chokkar");
//        login.setUsername("chokkar.g@gmail.com");


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String userNameValue = userEmail.getText().toString();
//                String userPasswordValue = userPassword.getText().toString();
               String userNameValue = "chokkar.g@gmail.com";
                String userPasswordValue = "chokkarg";
                if(StringUtil.isNullOrEmpty(userNameValue) && StringUtil.isNullOrEmpty(userPasswordValue)){
                    Toast.makeText(context , "Please fill a form" , Toast.LENGTH_LONG).show();
                } else{
                    login.setUsername(userNameValue);
                    login.setPassword(userPasswordValue);
                    deviceConfig.setDevice_id(getDeviceID());
                    deviceConfig.setDevice_type(ANDROID);
                    progressDialog = new ProgressDialog(context);
                    doLogin();
                }
            }
        });
    }

    private String getDeviceID() {
        if (mDeviceID == null) {
            mDeviceID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        return mDeviceID;
    }


/*
* This method will help to call login api with the help of retrofit
* */

    public void doLogin() {
        progressDialog.setMessage("Logging in...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        RestClient.setupLoginRestClient(login);
        RestClient.get().login(deviceConfig, new Callback<LoginResponse>() {
            @Override
            public void success(LoginResponse loginResponse, Response response) {
                // success!
                progressDialog.dismiss();
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

                SharedPreferences.Editor editor = getSharedPreferences(IConstants.AUTH_TOKEN, MODE_PRIVATE).edit();

                List<Header> headerList = response.getHeaders();
                for (Header header : headerList) {
                    String headerName = header.getName();
                    String headerValue = header.getValue();
                    if (headerName.equals(IConstants.AUTHORIZATION)) {
                        Log.d(LOG_TAG, header.getName() + " " + headerValue);
                        editor.putString(headerName, headerValue);
                        editor.commit();
                    }
                }

                String result = sb.toString();
                Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG).show();
                Log.i(LOG_TAG, "Login data:" + result);
                Intent intent = new Intent(context, MendorMainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();
                GeneralError generalError = (GeneralError) error.getBodyAs(GeneralError.class);
                if (generalError != null) {
                    Toast.makeText(context, "" + generalError.getError().getMessage() +"\n"
                            + generalError.getError().getDetail(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}