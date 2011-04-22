package com.pongal.aathichudi;

import android.app.Activity;
import android.media.MediaPlayer;
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
			if (getIntent().hasExtra("groupId")) {
				currentNode = manager.getNode(getIntent().getExtras().getInt(
						"groupId"));
			} else
				currentNode = manager.getContents();
		}

		maximViewer = new MaximViewer(getApplicationContext());
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
		outState.putSerializable("data", maximViewer.getItem());
	}
}