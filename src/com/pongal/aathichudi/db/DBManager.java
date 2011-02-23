package com.pongal.aathichudi.db;

import java.util.ArrayList;
import java.util.Arrays;

import com.pongal.aathichudi.Item;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBManager {
	Context context;

	private SQLiteDatabase db;

	private final String DB_NAME = "aathichudi.db";
	private final int DB_VERSION = 1;

	private final String TABLE_NAME = "contents";
	private final String TABLE_ROW_ID = "id";
	private final String TABLE_ROW_ONE = "text";
	private final String TABLE_ROW_TWO = "group_id";

	public DBManager(Context context) {
		this.context = context;
		// CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);
		// this.db = helper.getWritableDatabase();
		this.db = SQLiteDatabase.openDatabase(
				"data/data/com.pongal.aathichudi/databases/aathichudi.db",
				null, SQLiteDatabase.OPEN_READONLY);
	}

	public Item getRow(long rowID) {
		Item items = new Item();
		Cursor cursor;

		try {
			cursor = db.query(TABLE_NAME, new String[]{"id", "text", "shortDesc","group_id"}, null, null, null, null, null,null);
			cursor.moveToLast();

			items = new Item(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
			cursor.close();
		} catch (SQLException e) {
			Log.d("DB ERROR", e.toString());
			e.printStackTrace();
		}

		return items;
	}

	private class CustomSQLiteOpenHelper extends SQLiteOpenHelper {
		public CustomSQLiteOpenHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// String newTableQueryString = "create table " +
			// TABLE_NAME +
			// " (" +
			// TABLE_ROW_ID + " integer primary key autoincrement not null," +
			// TABLE_ROW_ONE+ " text," +
			// TABLE_ROW_TWO + " text" +");";
			// // execute the query string to the database.
			// db.execSQL(newTableQueryString);
		}

		@Override
		public void onOpen(SQLiteDatabase db) {
			Log.d("ACCESSING DB", "On open");
			super.onOpen(db);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
	}
}