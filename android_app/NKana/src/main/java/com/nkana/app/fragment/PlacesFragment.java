/*
 * Copyright 2015 Rudson Lima
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nkana.app.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.nkana.app.Constants.IConstants;
import com.nkana.app.R;
import com.nkana.app.network.Responses.GroupResponseList;
import com.nkana.app.network.RestClient;
import com.nkana.app.network.RestError.RestLoginError;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by chokkar
 */
public class PlacesFragment extends Fragment {

    private boolean mSearchCheck;
    private static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";
	private String authKey;
	private SharedPreferences preferences;
	private ListView listview;
	private static final String LOG_TAG = PlacesFragment.class.getSimpleName();

	public static PlacesFragment newInstance(String text){
		PlacesFragment mFragment = new PlacesFragment();
		Bundle mBundle = new Bundle();
		mBundle.putString(TEXT_FRAGMENT, text);
		mFragment.setArguments(mBundle);
		return mFragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		View rootView = inflater.inflate(R.layout.fragment_places, container, false);
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT ));
		listview = (ListView) rootView.findViewById(R.id.listview);
		preferences = getActivity().getSharedPreferences(IConstants.AUTH_TOKEN, getActivity().MODE_PRIVATE);
		authKey = preferences.getString(IConstants.AUTHORIZATION, null);
		getGroupsList();
		return rootView;		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu, menu);
        
        //Select search item
        final MenuItem menuItem = menu.findItem(R.id.menu_search);
        menuItem.setVisible(true);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint(this.getString(R.string.search));

        ((EditText) searchView.findViewById(R.id.search_src_text))
                .setHintTextColor(getResources().getColor(R.color.nliveo_white));
        searchView.setOnQueryTextListener(onQuerySearchView);

		menu.findItem(R.id.menu_add).setVisible(true);

		mSearchCheck = false;	
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {

		case R.id.menu_add:
            Toast.makeText(getActivity(), R.string.add, Toast.LENGTH_SHORT).show();
			break;

		case R.id.menu_search:
			mSearchCheck = true;
            Toast.makeText(getActivity(), R.string.search, Toast.LENGTH_SHORT).show();
			break;
		}
		return true;
	}	

   private SearchView.OnQueryTextListener onQuerySearchView = new SearchView.OnQueryTextListener() {
       @Override
       public boolean onQueryTextSubmit(String s) {
           return false;
       }

       @Override
       public boolean onQueryTextChange(String s) {
           if (mSearchCheck){
               // implement your search here
           }
           return false;
       }
   };

	private void getGroupsList() {
		RestClient.setupRestClient();
		RestClient.get().groupsList(authKey, new Callback<List<GroupResponseList>>() {

			@Override
			public void success(final List<GroupResponseList> groupResponseList, Response response) {
				final ArrayList<String> list = new ArrayList<String>();
				for(int index=0;index<groupResponseList.size();index++){
					list.add(groupResponseList.get(index).getGroupName());
					Log.i(LOG_TAG, "Group name: " + groupResponseList.get(index).getGroupName());
				}
				if(list.size() > 0){
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
							android.R.layout.simple_list_item_1, android.R.id.text1, list);
					listview.setAdapter(adapter);
					Log.i(LOG_TAG, "List size: " + list.size() + "adapter" + adapter + "listview" + listview);
					listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
							String groupId = groupResponseList.get(position).getGroupId();
							Log.i(LOG_TAG, "Group id: " + groupResponseList.get(position).getGroupId());
						}
					});
				}

			}

			@Override
			public void failure(RetrofitError error) {
				RestLoginError restLoginError = (RestLoginError) error.getBodyAs(RestLoginError.class);
				if (restLoginError != null) {
					Log.i(LOG_TAG, "Non field error: " + restLoginError.detail);
					Toast.makeText(getActivity(), "" + restLoginError.detail, Toast.LENGTH_LONG).show();
				}
			}
		});
	}
}
