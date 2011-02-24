package com.pongal.aathichudi.db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pongal.aathichudi.model.Item;
import com.pongal.aathichudi.model.MaximRow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBManager {
	Context context;
	private SQLiteDatabase db;

	public DBManager(Context context) {
		this.context = context;
	}

	public MaximRow getContents() {
		List<MaximRow> rows = getItems();
		Map<Integer, Item> refMap = new HashMap<Integer, Item>();
		for(MaximRow row : rows) {
			Item item = new Item(row);
			refMap.put(row.id, item);
			if(row.group_id != null) {
				Item parent = refMap.get(row.group_id);
				parent.addChild(item);
			}
		}
		return null;
	}

	private List<MaximRow> getItems() {
		List<MaximRow> items = new ArrayList<MaximRow>();
		Cursor cursor;
		try {
			cursor = db.query("contents", new String[] { "id", "text",
					"shortDesc", "group_id" }, null, null, null, null, null,
					null);
//			cursor.moveToFirst();

			do {
				items.add(new MaximRow(cursor.getInt(0), cursor.getString(1),
						cursor.getString(2), cursor.getInt(3)));
			} while (cursor.moveToNext());
			cursor.close();

		} catch (SQLException e) {
			Log.d("DB ERROR", e.toString());
			e.printStackTrace();
		}
		return items;
	}

}