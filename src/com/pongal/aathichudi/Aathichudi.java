package com.pongal.aathichudi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.pongal.aathichudi.db.DBManager;
import com.pongal.aathichudi.model.Item;

public class Aathichudi extends Activity {

	private MaximViewer maximViewer;
	Item currentNode;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DBManager manager = new DBManager(getApplicationContext());
		currentNode = getIntent().hasExtra("groupId") ? manager
				.getMaximTree(getIntent().getExtras().getInt("groupId")) : manager
				.getMaximTree(null);
		maximViewer = new MaximViewer(getApplicationContext());
		maximViewer.render(currentNode, null);
		setContentView(maximViewer);
//		TextView tv = new TextView(getApplicationContext());
//		tv.setText("sample");
//		setContentView(tv);
	}
}