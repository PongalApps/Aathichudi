package com.pongal.aathichudi;

import android.app.Activity;
import android.os.Bundle;

import com.pongal.aathichudi.db.DBManager;
import com.pongal.aathichudi.model.Item;

public class Aathichudi extends Activity {

	private MaximViewer maximViewer;
	private DBManager manager;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		manager = new DBManager(getApplicationContext());
		maximViewer = new MaximViewer(getApplicationContext(), getAssets());
		maximViewer.render(manager.getContents());
		setContentView(maximViewer);
	}

	@Override
	public void onBackPressed() {
		Item item = maximViewer.getItem();
		if (item.getParent() != null)
			maximViewer.render(item.getParent());
		else
			finish();
	}

	@Override
	protected void onStop() {
		super.onStop();
		finish();
	}

}