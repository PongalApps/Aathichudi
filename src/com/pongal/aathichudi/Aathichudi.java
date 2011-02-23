package com.pongal.aathichudi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pongal.aathichudi.db.DBManager;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Aathichudi extends Activity {
	
	private SectionView tableView;
	private Util util;
	private TextView header;
	private DBManager manager;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        manager = new DBManager(getApplicationContext());
        Item item = manager.getRow(10);
        util = new Util(getApplicationContext(), getAssets());
        LinearLayout mainView = new LinearLayout(getApplicationContext());
		mainView.setOrientation(LinearLayout.VERTICAL);
		header = getHeader();
		mainView.addView(header);
		Log.d(null, item.id + " " + item.text + " " + item.group_id + " " + item.shortDescription);
        tableView = new SectionView(getApplicationContext(), getAssets());
		mainView.addView(tableView);
        renderHomePage();
        setContentView(mainView);
    }

	private OnClickListener getSectionListener() {		
		return new OnClickListener() {
			@Override
			public void onClick(View v) {
				SectionInfo info = (SectionInfo)v.getTag();
				header.setText(info.group);
				tableView.addRows(info.startIndex, info.count, null, null);
			}
		};
	}

	private List<SectionInfo> getSectionRowIdentifiers() {
		List<SectionInfo> identifiers = new ArrayList<SectionInfo>();
		identifiers.add(new SectionInfo(R.string.section1, R.string.uyirvarukkam1, 13));
		identifiers.add(new SectionInfo(R.string.section2, R.string.uyirmeivarukkam1, 18));
		identifiers.add(new SectionInfo(R.string.section3, R.string.kagaravarukkam1, 12));
		identifiers.add(new SectionInfo(R.string.section4, R.string.sagaravarukkam1, 11));
		identifiers.add(new SectionInfo(R.string.section5, R.string.tagaravarukkam1, 11));
		identifiers.add(new SectionInfo(R.string.section6, R.string.nagaravarukkam1, 11));
		identifiers.add(new SectionInfo(R.string.section7, R.string.pagaravarukkam1, 11));
		identifiers.add(new SectionInfo(R.string.section8, R.string.magaravarukkam1, 11));
		identifiers.add(new SectionInfo(R.string.section9, R.string.vagaravarukkam1, 11));
		return identifiers;
	}

	@Override
	public void onBackPressed() {
		renderHomePage();
	}

	private void renderHomePage() {
		header.setText(R.string.header);
		tableView.addRows(R.string.section1, 9, getSectionListener(), getSectionRowIdentifiers());
		
	}

	private TextView getHeader() {
		TextView header = util.createTamilTextView(0xFFFFFFFF, 22);
		header.setText(R.string.header);
		header.setBackgroundResource(R.layout.headerbg);
		header.setPadding(5, 5, 5, 5);	
		header.setGravity(Gravity.CENTER);
		return header;
	}

	@Override
	protected void onStop() {
		super.onStop();
		finish();
	}
    
}