package com.pongal.aathichudi;

import android.app.Activity;
import android.os.Bundle;

import com.pongal.aathichudi.db.DBManager;
import com.pongal.aathichudi.model.Item;
import com.pongal.aathichudi.widget.MaximViewer;

public class Aathichudi extends Activity {

	Item currentNode;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DBManager manager = new DBManager(getApplicationContext());
		Integer selectedMaximId = getIntent().getExtras() != null ? getIntent()
				.getExtras().getInt("maximId") : null;
		currentNode = manager.getMaximTree(null);
		
		setContentView(R.layout.app_container);
		MaximViewer maximViewer =  (MaximViewer) findViewById(R.id.maximViewer);
		maximViewer.load(currentNode, selectedMaximId);
	}
}