package com.nkana.app.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.nkana.app.Constants.IConstants;
import com.nkana.app.R;
import com.nkana.app.data.DBConnection;
import com.nkana.app.fragment.GroupSetupFragment;
import com.nkana.app.fragment.MembersSetupFragment;
import com.nkana.app.fragment.PlacesFragment;
import com.nkana.app.fragment.RemindersFragment;
import com.nkana.app.fragment.ViewPagerFragment;
import com.nkana.app.network.Responses.RegisterResponse;
import com.nkana.app.network.Responses.UpdateProfileResponse;
import com.nkana.app.network.RestClient;
import com.nkana.app.network.RestError.RestLoginError;

import java.util.ArrayList;

import br.liveo.Model.HelpLiveo;
import br.liveo.interfaces.OnItemClickListener;
import br.liveo.interfaces.OnPrepareOptionsMenuLiveo;
import br.liveo.navigationliveo.NavigationLiveo;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Chokkar G
 */
public class ChildrenMainActivity extends NavigationLiveo implements OnItemClickListener {
    private static final String LOG_TAG = ChildrenMainActivity.class.getSimpleName();
    private Context mContext;
    SharedPreferences preferences;
    private String authKey;
    private HelpLiveo mHelpLiveo;
    private DBConnection dbConnection;
    ArrayList<String> getUserData;
    String[] actions = new String[] {
            "History",
            "Monitor",
            "Statistics"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, actions);
        mContext = this;
        dbConnection = new DBConnection(mContext);
        preferences = getSharedPreferences(IConstants.AUTH_TOKEN, MODE_PRIVATE);
        authKey = preferences.getString(IConstants.AUTHORIZATION, null);
        if (dbConnection.checkUserAvailable()) {
//            checkUserProfile();
        } else {
//            getProfile();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        } else if (id == R.id.action_update){
            Intent intent = new Intent(mContext, UpdateProfile.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {

        RestClient.get().logout(authKey, new Callback<RegisterResponse>() {
            @Override
            public void success(RegisterResponse registerResponse, Response response) {
                // success!
                Toast.makeText(mContext, "Logout successful.", Toast.LENGTH_LONG).show();
                dbConnection.clearUserData();
                SharedPreferences.Editor editor = getSharedPreferences(IConstants.AUTH_TOKEN, MODE_PRIVATE).edit();
                editor.putString(IConstants.AUTHORIZATION, null);
                editor.commit();
                Intent loginIntent = new Intent(mContext , MendorRegistrationActivity.class);
                startActivity(loginIntent);
                finish();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i(LOG_TAG, "Register Error: " + error.getLocalizedMessage());
            }
        });
    }

    private void getProfile() {

        RestClient.get().userProfile(authKey, new Callback<UpdateProfileResponse>() {
            @Override
            public void success(UpdateProfileResponse loginResponse, Response response) {
                // success!
                String firstName = loginResponse.getFirstName();
                String lastName = loginResponse.getLastName();
                String mobileNo = loginResponse.getMobileNo();
                String emailId = loginResponse.getEmailId();
                dbConnection.saveUserData(firstName, lastName, mobileNo, emailId);
                String fullName = firstName + " " + lastName;
                userName.setText(fullName);
                userEmail.setText(emailId);
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

    @Override
    public void onInt(Bundle savedInstanceState) {

        // User Information

        this.userPhoto.setImageResource(R.mipmap.ic_no_user);
        this.userBackground.setImageResource(R.drawable.ic_user_background_first);

        // Creating items navigation
        mHelpLiveo = new HelpLiveo();
        mHelpLiveo.add(getString(R.string.history), R.mipmap.ic_inbox_black_24dp);
//        mHelpLiveo.addSubHeader(getString(R.string.categories)); //Item subHeader
        mHelpLiveo.add(getString(R.string.monitor), R.mipmap.ic_star_black_24dp);
        mHelpLiveo.add(getString(R.string.statistics), R.mipmap.ic_send_black_24dp);
        mHelpLiveo.add(getString(R.string.remainders), R.mipmap.ic_drafts_black_24dp);
        mHelpLiveo.add(getString(R.string.locationsharing), R.mipmap.ic_delete_black_24dp);
        mHelpLiveo.addSeparator(); // Item separator
        mHelpLiveo.add(getString(R.string.help), R.mipmap.ic_report_black_24dp);

        //{optional} - Header Customization - method customHeader
//        View mCustomHeader = getLayoutInflater().inflate(R.layout.custom_header_user, this.getListView(), false);
//        ImageView imageView = (ImageView) mCustomHeader.findViewById(R.id.imageView);

        with(this).startingPosition(0) //Starting position in the list
                .addAllHelpItem(mHelpLiveo.getHelp())

                        //{optional} - List Customization "If you remove these methods and the list will take his white standard color"
                        //.selectorCheck(R.drawable.selector_check) //Inform the background of the selected item color
                        //.colorItemDefault(R.color.nliveo_blue_colorPrimary) //Inform the standard color name, icon and counter
                        //.colorItemSelected(R.color.nliveo_purple_colorPrimary) //State the name of the color, icon and meter when it is selected
                        //.backgroundList(R.color.nliveo_black_light) //Inform the list of background color
                        //.colorLineSeparator(R.color.nliveo_transparent) //Inform the color of the subheader line

                        //{optional} - SubHeader Customization
                .colorItemSelected(R.color.nliveo_blue_colorPrimary)
                .colorNameSubHeader(R.color.nliveo_blue_colorPrimary)
                        //.colorLineSeparator(R.color.nliveo_blue_colorPrimary)

                .footerItem(R.string.settings, R.mipmap.ic_settings_black_24dp)
                        //.footerSecondItem(R.string.settings, R.mipmap.ic_settings_black_24dp)

                        //{optional} - Header Customization
                        //.customHeader(mCustomHeader)

                        //{optional} - Footer Customization
                        //.footerNameColor(R.color.nliveo_blue_colorPrimary)
                        //.footerIconColor(R.color.nliveo_blue_colorPrimary)
                        //.footerBackground(R.color.nliveo_white)

                .setOnClickUser(onClickPhoto)
                .setOnPrepareOptionsMenu(onPrepare)
                .setOnClickFooter(onClickFooter)
                        //.setOnClickFooterSecond(onClickFooter)
                .build();

        int position = this.getCurrentPosition();
        this.setElevationToolBar(position != 2 ? 15 : 0);
    }

    @Override
    public void onItemClick(int position) {
        Fragment mFragment;
        FragmentManager mFragmentManager = getSupportFragmentManager();

        switch (position){
            case 0:
                mFragment = GroupSetupFragment.newInstance(mHelpLiveo.get(position).getName());
                updateTilteBar(position);
                break;
            case 1:
                mFragment = PlacesFragment.newInstance(mHelpLiveo.get(position).getName());
                updateTilteBar(position);
                break;
            case 2:
                mFragment = MembersSetupFragment.newInstance(mHelpLiveo.get(position).getName());
                updateTilteBar(position);
                break;
            case 3:
                mFragment = RemindersFragment.newInstance(mHelpLiveo.get(position).getName());
                updateTilteBar(position);
                break;
            case 4:
                mFragment = new ViewPagerFragment();
                updateTilteBar(position);
                break;
            default:
                mFragment = GroupSetupFragment.newInstance(mHelpLiveo.get(position).getName());
                updateTilteBar(position);
                break;
        }

        if (mFragment != null){
            mFragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
        }

        setElevationToolBar(position != 2 ? 15 : 0);
    }

    private void updateTilteBar(int position) {
        getToolbar().setTitle(mHelpLiveo.get(position).getName());
    }

    private OnPrepareOptionsMenuLiveo onPrepare = new OnPrepareOptionsMenuLiveo() {
        @Override
        public void onPrepareOptionsMenu(Menu menu, int position, boolean visible) {
        }
    };

    private View.OnClickListener onClickPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "onClickPhoto :D", Toast.LENGTH_SHORT).show();
            closeDrawer();
        }
    };

    private View.OnClickListener onClickFooter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            closeDrawer();
        }
    };

    private void checkUserProfile() {
        getUserData = dbConnection.getUserData();
        if (getUserData.size() > 0) {
            String userNameValue = getUserData.get(0) + " " + getUserData.get(1);
            userName.setText(userNameValue);
            userEmail.setText(getUserData.get(3));
        }
    }

}
