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

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.ListView;
import android.widget.Toast;

import com.nkana.app.Constants.IConstants;
import com.nkana.app.R;
import com.nkana.app.model.GeneralError;
import com.nkana.app.network.Responses.GroupResponseList;
import com.nkana.app.network.Responses.UtilizationResponse;
import com.nkana.app.network.RestClient;
import com.nkana.app.network.RestError.RestLoginError;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.Chart;
import lecho.lib.hellocharts.view.ColumnChartView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by chokkar
 */
public class GraphSetupFragment extends Fragment {

    private static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";
	private static final String LOG_TAG = GraphSetupFragment.class.getSimpleName();
	private String authKey;
	private SharedPreferences preferences;
	private ProgressDialog progressDialog;
	private static final int DEFAULT_DATA = 0;
	private static final int SUBCOLUMNS_DATA = 1;
	private static final int STACKED_DATA = 2;
	private static final int NEGATIVE_SUBCOLUMNS_DATA = 3;
	private static final int NEGATIVE_STACKED_DATA = 4;
	private ColumnChartView chart;
	private ColumnChartData data;
	private boolean hasAxes = true;
	private boolean hasAxesNames = true;
	private boolean hasLabels = false;
	private boolean hasLabelForSelected = false;
	private int dataType = DEFAULT_DATA;

	public static GraphSetupFragment newInstance(String text){
		GraphSetupFragment mFragment = new GraphSetupFragment();
		Bundle mBundle = new Bundle();
		mBundle.putString(TEXT_FRAGMENT, text);
		mFragment.setArguments(mBundle);
		return mFragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		View rootView = inflater.inflate(R.layout.fragment_graph_setup, container, false);
		chart = (ColumnChartView) rootView.findViewById(R.id.chart);
		chart.setOnValueTouchListener(new ValueTouchListener());
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		preferences = getActivity().getSharedPreferences(IConstants.AUTH_TOKEN, getActivity().MODE_PRIVATE);
		authKey = preferences.getString(IConstants.AUTHORIZATION, null);

//		getGroupsList();
		generateData();
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

	@Override
	public void onResume() {
		super.onResume();
//		displayGraphDetails();
	}

	private void displayGraphDetails() {
		progressDialog = new ProgressDialog(getActivity());
		RestClient.get().utilizationValues(new Callback<UtilizationResponse>() {
			@Override
			public void success(UtilizationResponse utilizationResponse, Response response) {
				// success!
				Log.i(LOG_TAG, "Admin data: " + utilizationResponse.getUtilizationKeys());
				progressDialog.dismiss();

			}

			@Override
			public void failure(RetrofitError error) {
				progressDialog.dismiss();
				GeneralError generalError = (GeneralError) error.getBodyAs(GeneralError.class);
				if (generalError != null) {
					Toast.makeText(getActivity(), "" + generalError.getError().getMessage() + "\n"
							+ generalError.getError().getDetail(), Toast.LENGTH_LONG).show();
				}
			}
		});
	}


	private void generateData() {
		switch (dataType) {
			case DEFAULT_DATA:
				generateDefaultData();
				break;
			case SUBCOLUMNS_DATA:
				generateSubcolumnsData();
				break;
			case STACKED_DATA:
				generateStackedData();
				break;
			case NEGATIVE_SUBCOLUMNS_DATA:
				generateNegativeSubcolumnsData();
				break;
			case NEGATIVE_STACKED_DATA:
				generateNegativeStackedData();
				break;
			default:
				generateDefaultData();
				break;
		}
	}

	private void generateDefaultData() {
		int numSubcolumns = 1;
		int numColumns = 8;
		// Column can have many subcolumns, here by default I use 1 subcolumn in each of 8 columns.
		List<Column> columns = new ArrayList<Column>();
		List<SubcolumnValue> values;
		for (int i = 0; i < numColumns; ++i) {

			values = new ArrayList<SubcolumnValue>();
			for (int j = 0; j < numSubcolumns; ++j) {
				values.add(new SubcolumnValue((float) Math.random() * 50f + 5, ChartUtils.pickColor()));
			}

			Column column = new Column(values);
			column.setHasLabels(hasLabels);
			column.setHasLabelsOnlyForSelected(hasLabelForSelected);
			columns.add(column);
		}

		data = new ColumnChartData(columns);

		if (hasAxes) {
			Axis axisX = new Axis();
			Axis axisY = new Axis().setHasLines(true);
			if (hasAxesNames) {
				axisX.setName("Axis X");
				axisY.setName("Axis Y");
			}
			data.setAxisXBottom(axisX);
			data.setAxisYLeft(axisY);
		} else {
			data.setAxisXBottom(null);
			data.setAxisYLeft(null);
		}

		chart.setColumnChartData(data);

	}

	/**
	 * Generates columns with subcolumns, columns have larger separation than subcolumns.
	 */
	private void generateSubcolumnsData() {
		int numSubcolumns = 4;
		int numColumns = 4;
		// Column can have many subcolumns, here I use 4 subcolumn in each of 8 columns.
		List<Column> columns = new ArrayList<Column>();
		List<SubcolumnValue> values;
		for (int i = 0; i < numColumns; ++i) {

			values = new ArrayList<SubcolumnValue>();
			for (int j = 0; j < numSubcolumns; ++j) {
				values.add(new SubcolumnValue((float) Math.random() * 50f + 5, ChartUtils.pickColor()));
			}

			Column column = new Column(values);
			column.setHasLabels(hasLabels);
			column.setHasLabelsOnlyForSelected(hasLabelForSelected);
			columns.add(column);
		}

		data = new ColumnChartData(columns);

		if (hasAxes) {
			Axis axisX = new Axis();
			Axis axisY = new Axis().setHasLines(true);
			if (hasAxesNames) {
				axisX.setName("Axis X");
				axisY.setName("Axis Y");
			}
			data.setAxisXBottom(axisX);
			data.setAxisYLeft(axisY);
		} else {
			data.setAxisXBottom(null);
			data.setAxisYLeft(null);
		}

		chart.setColumnChartData(data);

	}

	/**
	 * Generates columns with stacked subcolumns.
	 */
	private void generateStackedData() {
		int numSubcolumns = 4;
		int numColumns = 8;
		// Column can have many stacked subcolumns, here I use 4 stacke subcolumn in each of 4 columns.
		List<Column> columns = new ArrayList<Column>();
		List<SubcolumnValue> values;
		for (int i = 0; i < numColumns; ++i) {

			values = new ArrayList<SubcolumnValue>();
			for (int j = 0; j < numSubcolumns; ++j) {
				values.add(new SubcolumnValue((float) Math.random() * 20f + 5, ChartUtils.pickColor()));
			}

			Column column = new Column(values);
			column.setHasLabels(hasLabels);
			column.setHasLabelsOnlyForSelected(hasLabelForSelected);
			columns.add(column);
		}

		data = new ColumnChartData(columns);

		// Set stacked flag.
		data.setStacked(true);

		if (hasAxes) {
			Axis axisX = new Axis();
			Axis axisY = new Axis().setHasLines(true);
			if (hasAxesNames) {
				axisX.setName("Axis X");
				axisY.setName("Axis Y");
			}
			data.setAxisXBottom(axisX);
			data.setAxisYLeft(axisY);
		} else {
			data.setAxisXBottom(null);
			data.setAxisYLeft(null);
		}

		chart.setColumnChartData(data);
	}

	private void generateNegativeSubcolumnsData() {

		int numSubcolumns = 4;
		int numColumns = 4;
		List<Column> columns = new ArrayList<Column>();
		List<SubcolumnValue> values;
		for (int i = 0; i < numColumns; ++i) {

			values = new ArrayList<SubcolumnValue>();
			for (int j = 0; j < numSubcolumns; ++j) {
				int sign = getSign();
				values.add(new SubcolumnValue((float) Math.random() * 50f * sign + 5 * sign, ChartUtils.pickColor
						()));
			}

			Column column = new Column(values);
			column.setHasLabels(hasLabels);
			column.setHasLabelsOnlyForSelected(hasLabelForSelected);
			columns.add(column);
		}

		data = new ColumnChartData(columns);

		if (hasAxes) {
			Axis axisX = new Axis();
			Axis axisY = new Axis().setHasLines(true);
			if (hasAxesNames) {
				axisX.setName("Axis X");
				axisY.setName("Axis Y");
			}
			data.setAxisXBottom(axisX);
			data.setAxisYLeft(axisY);
		} else {
			data.setAxisXBottom(null);
			data.setAxisYLeft(null);
		}

		chart.setColumnChartData(data);
	}

	private void generateNegativeStackedData() {

		int numSubcolumns = 4;
		int numColumns = 8;
		// Column can have many stacked subcolumns, here I use 4 stacke subcolumn in each of 4 columns.
		List<Column> columns = new ArrayList<Column>();
		List<SubcolumnValue> values;
		for (int i = 0; i < numColumns; ++i) {

			values = new ArrayList<SubcolumnValue>();
			for (int j = 0; j < numSubcolumns; ++j) {
				int sign = getSign();
				values.add(new SubcolumnValue((float) Math.random() * 20f * sign + 5 * sign, ChartUtils.pickColor()));
			}

			Column column = new Column(values);
			column.setHasLabels(hasLabels);
			column.setHasLabelsOnlyForSelected(hasLabelForSelected);
			columns.add(column);
		}

		data = new ColumnChartData(columns);

		// Set stacked flag.
		data.setStacked(true);

		if (hasAxes) {
			Axis axisX = new Axis();
			Axis axisY = new Axis().setHasLines(true);
			if (hasAxesNames) {
				axisX.setName("Axis X");
				axisY.setName("Axis Y");
			}
			data.setAxisXBottom(axisX);
			data.setAxisYLeft(axisY);
		} else {
			data.setAxisXBottom(null);
			data.setAxisYLeft(null);
		}

		chart.setColumnChartData(data);
	}

	private int getSign() {
		int[] sign = new int[]{-1, 1};
		return sign[Math.round((float) Math.random())];
	}

	private void toggleLabels() {
		hasLabels = !hasLabels;

		if (hasLabels) {
			hasLabelForSelected = false;
			chart.setValueSelectionEnabled(hasLabelForSelected);
		}

		generateData();
	}

	private void toggleLabelForSelected() {
		hasLabelForSelected = !hasLabelForSelected;
		chart.setValueSelectionEnabled(hasLabelForSelected);

		if (hasLabelForSelected) {
			hasLabels = false;
		}

		generateData();
	}

	private void toggleAxes() {
		hasAxes = !hasAxes;

		generateData();
	}

	private void toggleAxesNames() {
		hasAxesNames = !hasAxesNames;

		generateData();
	}

	/**
	 * To animate values you have to change targets values and then call {@link Chart#startDataAnimation()}
	 * method(don't confuse with View.animate()).
	 */
	private void prepareDataAnimation() {
		for (Column column : data.getColumns()) {
			for (SubcolumnValue value : column.getValues()) {
				value.setTarget((float) Math.random() * 100);
			}
		}
	}

	private class ValueTouchListener implements ColumnChartOnValueSelectListener {

		@Override
		public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
			Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onValueDeselected() {
			// TODO Auto-generated method stub

		}

	}
}
