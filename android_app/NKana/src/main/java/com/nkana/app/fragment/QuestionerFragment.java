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

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.nkana.app.Constants.IConstants;
import com.nkana.app.R;

/**
 * Created by chokkar
 */
public class QuestionerFragment extends Fragment {

    private static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";
	private static final String LOG_TAG = QuestionerFragment.class.getSimpleName();
	private String authKey;
	private SharedPreferences preferences;

	String[] text0 = { "1", "2", "3",
			"4", "5", "6", "7" ,"8" , "9" ,"10"};

	String[] text1 = { "1", "2", "3",
			"4", "5", "6", "7" ,"8" , "9" ,"10"};

	String[] text2 = { "1", "2", "3",
			"4", "5", "6", "7" ,"8" , "9" ,"10"};

	String[] text3 = { "1", "2", "3",
			"4", "5", "6", "7" ,"8" , "9" ,"10"};

	String[] text4 = { "1", "2", "3",
			"4", "5", "6", "7" ,"8" , "9" ,"10"};

	String[] text5 = { "1", "2", "3",
			"4", "5", "6", "7" ,"8" , "9" ,"10"};

	String[] text6 = { "1", "2", "3",
			"4", "5", "6", "7" ,"8" , "9" ,"10"};

	String[] text7 = { "1", "2", "3",
			"4", "5", "6", "7" ,"8" , "9" ,"10"};

	String[] text8 = { "1", "2", "3",
			"4", "5", "6", "7" ,"8" , "9" ,"10"};

	String[] text9 = { "1", "2", "3",
			"4", "5", "6", "7" ,"8" , "9" ,"10"};
	int[] val1 = { 0, 1, 2, 3, 4, 5, 6};



	Spinner spinner0, spinner1, spinner2 , spinner3 , spinner4, spinner5, spinner6, spinner7, spinner8, spinner9, spinner10;

	public static QuestionerFragment newInstance(String text){
		QuestionerFragment mFragment = new QuestionerFragment();
		Bundle mBundle = new Bundle();
		mBundle.putString(TEXT_FRAGMENT, text);
		mFragment.setArguments(mBundle);
		return mFragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		View rootView = inflater.inflate(R.layout.mentor_tracking_details, container, false);
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		preferences = getActivity().getSharedPreferences(IConstants.AUTH_TOKEN, getActivity().MODE_PRIVATE);
		authKey = preferences.getString(IConstants.AUTHORIZATION, null);


		spinner0 = (Spinner)rootView.findViewById(R.id.spinner0);
		ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, text0);
		adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner0.setAdapter(adapter0);
		spinner0.setOnItemSelectedListener(onItemSelectedListener0);


		spinner1 = (Spinner)rootView.findViewById(R.id.spinner1);
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, text1);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(adapter1);
		spinner1.setOnItemSelectedListener(onItemSelectedListener0);


		spinner2 = (Spinner)rootView.findViewById(R.id.spinner2);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, text1);
		//adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(adapter2);
		spinner2.setOnItemSelectedListener(onItemSelectedListener0);

		spinner3 = (Spinner)rootView.findViewById(R.id.spinner3);
		ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, text1);
		//adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner3.setAdapter(adapter3);
		spinner3.setOnItemSelectedListener(onItemSelectedListener0);

		spinner4 = (Spinner)rootView.findViewById(R.id.spinner4);
		ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, text1);
		//adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner4.setAdapter(adapter4);
		spinner4.setOnItemSelectedListener(onItemSelectedListener0);

		spinner5 = (Spinner)rootView.findViewById(R.id.spinner5);
		ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, text1);
		//adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner5.setAdapter(adapter5);
		spinner5.setOnItemSelectedListener(onItemSelectedListener0);

		spinner6 = (Spinner)rootView.findViewById(R.id.spinner6);
		ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, text1);
		//adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner6.setAdapter(adapter6);
		spinner6.setOnItemSelectedListener(onItemSelectedListener0);

		spinner7 = (Spinner)rootView.findViewById(R.id.spinner7);
		ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, text1);
		//adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner7.setAdapter(adapter7);
		spinner7.setOnItemSelectedListener(onItemSelectedListener0);

		spinner8 = (Spinner)rootView.findViewById(R.id.spinner8);
		ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, text1);
		//adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner8.setAdapter(adapter8);
		spinner8.setOnItemSelectedListener(onItemSelectedListener0);

		spinner9 = (Spinner)rootView.findViewById(R.id.spinner9);
		ArrayAdapter<String> adapter9 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, text1);
		//adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner9.setAdapter(adapter9);
		spinner9.setOnItemSelectedListener(onItemSelectedListener0);

//		getGroupsList();
		return rootView;		
	}

	AdapterView.OnItemSelectedListener onItemSelectedListener0 =
			new AdapterView.OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
										   int position, long id) {
					String s0 = (String)parent.getItemAtPosition(position);

				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {}
			};

	AdapterView.OnItemSelectedListener onItemSelectedListener1 =
			new AdapterView.OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
										   int position, long id) {
					String s1 = String.valueOf(val1[position]);

				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {}

			};

	AdapterView.OnItemSelectedListener onItemSelectedListener2 =
			new AdapterView.OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
										   int position, long id) {
					MyClass obj = (MyClass)(parent.getItemAtPosition(position));

				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {}

			};

	//define our custom class
	public class MyClass{

		private String text;
		private int value;


		public MyClass(String text, int value){
			this.text = text;
			this.value = value;
		}

		public void setText(String text){
			this.text = text;
		}

		public String getText(){
			return this.text;
		}

		public void setValue(int value){
			this.value = value;
		}

		public int getValue(){
			return this.value;
		}
	}

	//custom adapter
	public class MySpinnerAdapter extends ArrayAdapter<MyClass>{

		private Context context;
		private MyClass[] myObjs;

		public MySpinnerAdapter(Context context, int textViewResourceId,
								MyClass[] myObjs) {
			super(context, textViewResourceId, myObjs);
			this.context = context;
			this.myObjs = myObjs;
		}

		public int getCount(){
			return myObjs.length;
		}

		public MyClass getItem(int position){
			return myObjs[position];
		}

		public long getItemId(int position){
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView label = new TextView(context);
			label.setText(myObjs[position].getText());
			return label;
		}

		@Override
		public View getDropDownView(int position, View convertView,
									ViewGroup parent) {
			TextView label = new TextView(context);
			label.setText(myObjs[position].getText());
			return label;
		}
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
		inflater.inflate(R.menu.group_setup_menu, menu);
		menu.findItem(R.id.menu_add).setVisible(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {

		case R.id.menu_add:
			break;
		}
		return true;
	}
}
