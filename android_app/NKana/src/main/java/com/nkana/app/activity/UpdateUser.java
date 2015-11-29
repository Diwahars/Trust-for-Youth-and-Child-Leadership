package com.nkana.app.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nkana.app.Constants.IConstants;
import com.nkana.app.R;
import com.nkana.app.data.DBConnection;
import com.nkana.app.network.Responses.UpdateProfileResponse;
import com.nkana.app.network.RestClient;
import com.nkana.app.network.RestError.RestLoginError;
import com.nkana.app.util.StringUtil;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Ravikumar
 */
public class UpdateUser extends AppCompatActivity {
    DBConnection dbConnection;
    Context mContext;
    EditText mFirstNameView, mLastNameView, mMobileNoView, mEmailIdView;
    Button updateButton, cancelButton;
    private String authKey;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_user);
        mContext = this;
        dbConnection = new DBConnection(mContext);
        preferences = getSharedPreferences(IConstants.AUTH_TOKEN, MODE_PRIVATE);
        authKey = preferences.getString(IConstants.AUTHORIZATION, null);

        updateButton = (Button) findViewById(R.id.updateButton);
        cancelButton = (Button) findViewById(R.id.updateCancel);

        mFirstNameView = (EditText) findViewById(R.id.updateFirstName);
        mLastNameView = (EditText) findViewById(R.id.updateLastName);
        mMobileNoView = (EditText) findViewById(R.id.updateMobileNo);
        mEmailIdView = (EditText) findViewById(R.id.updateEmail);

        updateProfileFromDb();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = mFirstNameView.getText().toString();
                String lastName = mLastNameView.getText().toString();
                String mobileNo = mMobileNoView.getText().toString();
                final String emailId = mEmailIdView.getText().toString();

                if (StringUtil.isNullOrEmpty(firstName)) {
                    Toast.makeText(UpdateUser.this, "First Name cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringUtil.isNullOrEmpty(lastName)) {
                    Toast.makeText(UpdateUser.this, "Last Name cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringUtil.isNullOrEmpty(mobileNo)) {
                    Toast.makeText(UpdateUser.this, "Mobile Number cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                RestClient.get().userUpdateProfile(authKey, new Callback<UpdateProfileResponse>() {
                    @Override
                    public void success(UpdateProfileResponse loginResponse, Response response) {
                        // success!
                        String mFirstName = loginResponse.getFirstName();
                        String mLastName = loginResponse.getLastName();
                        String mMobileNo = loginResponse.getMobileNo();
                        dbConnection.updateUserData(mFirstName, mLastName, mMobileNo, emailId);
                        finish();
                        Toast.makeText(mContext, "Profile Updated Successfully", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        RestLoginError restLoginError = (RestLoginError) error.getBodyAs(RestLoginError.class);
                        if (restLoginError != null) {
                            Toast.makeText(mContext, "" + restLoginError.detail, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void updateProfileFromDb() {
        ArrayList<String> getUserData = dbConnection.getUserData();
        if (getUserData.size() > 0) {
            mFirstNameView.setText(getUserData.get(0));
            mLastNameView.setText(getUserData.get(1));
            mMobileNoView.setText(getUserData.get(2));
            mEmailIdView.setText(getUserData.get(3));
        }
    }
}