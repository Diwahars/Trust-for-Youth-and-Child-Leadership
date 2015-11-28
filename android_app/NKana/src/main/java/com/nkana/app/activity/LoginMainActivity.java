package com.nkana.app.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nkana.app.R;
import com.nkana.app.data.DBConnection;
import com.nkana.app.model.DeviceConfig;
import com.nkana.app.model.GeneralError;
import com.nkana.app.model.Login;
import com.nkana.app.network.Responses.RetriveProfileResponse;
import com.nkana.app.network.RestClient;
import com.nkana.app.util.StringUtil;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Chokkar G
 */

public class LoginMainActivity extends AppCompatActivity {
    private static final String LOG_TAG = LoginMainActivity.class.getSimpleName();
    private Context context;
    private EditText userEmail;
    private EditText userPassword;
    private Login login;
    private DeviceConfig deviceConfig;
    private Button loginButton ;
    private static final String ANDROID = "Android";
    private String mDeviceID;
    private DBConnection dbConnection;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main_activity);
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
                String userNameValue = userEmail.getText().toString();
                String userPasswordValue = userPassword.getText().toString();
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

        RestClient.get().retriveProfile(login.getUsername(), new Callback<RetriveProfileResponse>() {
            @Override
            public void success(RetriveProfileResponse retriveProfileResponse, Response response) {
                // success!
                progressDialog.dismiss();
                Toast.makeText(context, "Retrive name:" + retriveProfileResponse.getFullName(), Toast.LENGTH_LONG).show();
                if (retriveProfileResponse.getFullName() == null) {
                    Intent intent = new Intent(context, ChooseMainActivity.class);
                    startActivity(intent);
                 } else {
                    String category = retriveProfileResponse.getCategory();
                    if (category.contentEquals("Volunteer")) {
                        Intent intent = new Intent(context, VolunteerMainActivity.class);
                        startActivity(intent);
                    } else if (category.contentEquals("Mentor")) {
                        Intent intent = new Intent(context, VolunteerMainActivity.class);
                        startActivity(intent);
                    } else if (category.contentEquals("Children")) {
                        Intent intent = new Intent(context, ChildrenMainActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();
                GeneralError generalError = (GeneralError) error.getBodyAs(GeneralError.class);
                if (generalError != null) {
                    Toast.makeText(context, "" + generalError.getError().getMessage() + "\n"
                            + generalError.getError().getDetail(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}