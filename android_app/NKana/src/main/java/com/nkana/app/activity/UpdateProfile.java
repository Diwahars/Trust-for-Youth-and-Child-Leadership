package com.nkana.app.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nkana.app.R;
import com.nkana.app.adapter.ProfileAlertAdapter;
import com.nkana.app.data.DBConnection;
import com.nkana.app.network.Responses.UpdateProfileResponse;
import com.nkana.app.network.RestClient;
import com.nkana.app.network.RestError.RestLoginError;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by Ravikumar
 */
public class UpdateProfile extends AppCompatActivity {
    private static final String LOG_TAG = UpdateProfile.class.getSimpleName();
    private FloatingActionButton floatingActionButton;
    private static final String UPDATE_PROFILE = "Update Profile";
    private static final String CHANGE_PASSWORD = "Change Password";
    private static final String ALERT_PROFILE_TITLE = "Profile";
    private Context mContext;
    private SharedPreferences preferences;
    private String authKey;
    private TextView mFirstNameView, mLastNameView, mMobileNoView, mEmailIdView;
    private DBConnection dbConnection;
    ArrayList<String> getUserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        mContext = this;
        dbConnection = new DBConnection(mContext);
        preferences = getSharedPreferences("AuthToken", MODE_PRIVATE);
        authKey = preferences.getString("Authorization", null);
        mFirstNameView = (TextView) findViewById(R.id.firstNameProfile);
        mLastNameView = (TextView) findViewById(R.id.lastNameProfile);
        mMobileNoView = (TextView) findViewById(R.id.mobileNoProfile);
        mEmailIdView = (TextView) findViewById(R.id.emailIdProfile);
        if (dbConnection.checkUserAvailable()) {
           checkUserProfile();
        } else {
            getProfile();
        }
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_upload);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uploadTitleList[] = {UPDATE_PROFILE, CHANGE_PASSWORD};
                Integer uploadImageList[] = {R.drawable.update_profile, R.drawable.change_password};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View convertView = inflater.inflate(R.layout.popup_listview, null);
                ListView listView = (ListView) convertView.findViewById(R.id.dialog_list);
                ProfileAlertAdapter uploadAlertAdapter = new ProfileAlertAdapter(mContext, uploadTitleList, uploadImageList);
                listView.setAdapter(uploadAlertAdapter);
                TextView title = new TextView(mContext);
                title.setText(ALERT_PROFILE_TITLE);
                title.setBackgroundColor(getResources().getColor(R.color.half_black));
                title.setPadding(10, 10, 10, 10);
                title.setGravity(Gravity.CENTER);
                title.setTextColor(getResources().getColor(R.color.white));
                title.setTextSize(20);
                alertDialogBuilder.setCustomTitle(title);
                alertDialogBuilder.setView(convertView);
                final AlertDialog alertDialog = alertDialogBuilder.show();

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0) {
                            Intent intent = new Intent(mContext, UpdateUser.class);
                            startActivity(intent);
                        } else {

                        }
                        alertDialog.dismiss();
                    }
                });
            }
        });
    }

    private void checkUserProfile() {
        getUserData = dbConnection.getUserData();
        if (getUserData.size() > 0) {
            mFirstNameView.setText(getUserData.get(0));
            mLastNameView.setText(getUserData.get(1));
            mMobileNoView.setText(getUserData.get(2));
            mEmailIdView.setText(getUserData.get(3));
        }
    }

    private void getProfile() {
        RestClient.setupRestClient();
        RestClient.get().userProfile(authKey, new Callback<UpdateProfileResponse>() {
            @Override
            public void success(UpdateProfileResponse loginResponse, Response response) {
                // success!
                String firstName = loginResponse.getFirstName();
                String lastName = loginResponse.getLastName();
                String mobileNo = loginResponse.getMobileNo();
                String emailId = loginResponse.getEmailId();
                dbConnection.saveUserData(firstName, lastName, mobileNo, emailId);
                mFirstNameView.setText(firstName);
                mLastNameView.setText(lastName);
                mMobileNoView.setText(mobileNo);
                mEmailIdView.setText(emailId);
            }

            @Override
            public void failure(RetrofitError error) {
                RestLoginError restLoginError = (RestLoginError) error.getBodyAs(RestLoginError.class);
                if (restLoginError != null) {
                    Log.i(LOG_TAG, "Non field error: " + restLoginError.detail);
                    Toast.makeText(mContext, "" + restLoginError.detail, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
