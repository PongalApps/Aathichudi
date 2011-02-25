package com.pongal.aathichudi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.pongal.aathichudi.db.DBManager;
import com.pongal.aathichudi.model.Item;

public class Aathichudi extends Activity {

	private MaximViewer maximViewer;
	Item currentNode;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			currentNode = (Item) savedInstanceState.getSerializable("data");
		} else {
			DBManager manager = new DBManager(getApplicationContext());
			currentNode = manager.getContents();
		}

		maximViewer = new MaximViewer(getApplicationContext(), getAssets());
		maximViewer.render(currentNode);
		setContentView(maximViewer);
	}

	@Override
	public void onBackPressed() {
		Item item = maximViewer.getItem();
		if (item.getParent() != null) {
			currentNode = item.getParent();
			maximViewer.render(currentNode);
		} else {
			finish();
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d(null, "Onsaved instance state: " + maximViewer.getItem());
		outState.putSerializable("data", maximViewer.getItem());
	}
}