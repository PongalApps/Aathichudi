package com.pongal.aathichudi.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pongal.aathichudi.model.Item;

public class DBManager {
	Context context;
	private DBHelper helper;
	private SQLiteDatabase db;

	public DBManager(Context context) {
		this.context = context;
		helper = new DBHelper(context);
		db = helper.openDatabase();
		helper.close();
	}

	public Item getMaximTree(Integer groupId) {
		String queryString = (groupId == null) ? null : 
			"group_id = '"+ groupId.toString() +"'" + " or id ='" + groupId.toString() + "'";
		List<MaximRow> rows = getItems(queryString, null);
		return listToTree(rows);
	}
	
	public Item getRandomMaxim() {
		Random randomNumber = new Random();
		List<MaximRow> items = getItems("shortDesc != ''", null);
		MaximRow randomMaxim = items.get(randomNumber.nextInt(108) + 1);
		return new Item(randomMaxim.id, randomMaxim.text, randomMaxim.shortDescription);
	}
	
	private List<MaximRow> getItems(String selectionString, String[] selectionArgs) {
		List<MaximRow> items = new ArrayList<MaximRow>();
		Cursor cursor;
		try {
			cursor = db.query("contents", new String[] { "id", "text",
					"shortDesc", "group_id" }, selectionString, selectionArgs, null,
					null, "abs(id)");

			while (cursor.moveToNext()) {
				items.add(new MaximRow(cursor.getInt(0), cursor.getString(1),
						cursor.getString(2), cursor.getInt(3)));
			}
			cursor.close();

		} catch (SQLException e) {
			Log.d("DB ERROR", e.toString());
			e.printStackTrace();
		}
		db.close();
		return items;
	}
	
	private Item listToTree(List<MaximRow> rows) {
		Map<Integer, Item> refMap = new HashMap<Integer, Item>();
		for (MaximRow row : rows) {
			refMap.put(row.id, new Item(row.id, row.text, row.shortDescription));
		}
		Item root = null;
		for (MaximRow row : rows) {
			Item currItem = refMap.get(row.id);
			Item parent = refMap.get(row.group_id);
			if (row.group_id != 0 && parent != null) {
				parent.addChild(currItem);
			} else {
				root = currItem;
			}
		}
		return root;		
	}

}