package com.nkana.app.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Ravikumar
 */
public class DBConnection extends SQLiteOpenHelper {
	private static final String TAG = "DBConnection";
	private static final String DB_NAME = "geofencedroid";
	public static final int DATABASE_VERSION = 1;
	private Context mContext;
	public static final String TABLE_USER = "user_profile";
	public static final String FIRST_NAME = "first_name";
	public static final String LAST_NAME = "last_name";
	public static final String MOBILE_NO = "mobile_no";
	public static final String EMAIL_ID = "email_id";

	private final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + " ("+ FIRST_NAME + " TEXT, " + LAST_NAME + " TEXT, " + MOBILE_NO + " TEXT, " + EMAIL_ID + " TEXT);";

	/**
	 * Constructor Takes and keeps a reference of the passed context in order to
	 * access to the application assets and resources.
	 * 
	 * @param context
	 */
	public DBConnection(Context context) {
		super(context, DB_NAME, null, DATABASE_VERSION);
		this.mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_USER);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_USER);
		onCreate(db);
	}

	public void saveUserData(String firstName, String lastName, String mobileNo, String emailId) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(FIRST_NAME, firstName);
		values.put(LAST_NAME, lastName);
		values.put(MOBILE_NO, mobileNo);
		values.put(EMAIL_ID, emailId);
		db.insert(TABLE_USER, null, values);
		db.close();
	}

	public Boolean checkUserAvailable() {
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "SELECT * FROM " + TABLE_USER;
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<String> getUserData() {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<String> collectUserData = new ArrayList<>();
		String query = "SELECT * FROM " + TABLE_USER;
		Cursor cur = db.rawQuery(query, null);
		if (cur.moveToFirst()) {
			for (int i = 0; i < cur.getColumnCount(); i++) {
				collectUserData.add(cur.getString(i));
			}
		}
		cur.close();
		return collectUserData;
	}

	public void updateUserData(String firstName, String lastName, String mobileNo, String emailId) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(FIRST_NAME, firstName);
		values.put(LAST_NAME, lastName);
		values.put(MOBILE_NO, mobileNo);
		db.update(TABLE_USER, values, EMAIL_ID + " = '" + emailId + "'", null);
		db.close();
	}

	public void clearUserData(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DELETE FROM " + TABLE_USER);
	}
}
