package com.pongal.aathichudi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableRow;
import android.widget.TextView;

public class Aathichudi extends Activity {
	
	private SectionView mainView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        
        mainView = new SectionView(getApplicationContext(), getAssets());
        renderMainView();
    }

	private OnClickListener getSectionListener() {		
		return new OnClickListener() {
			@Override
			public void onClick(View v) {
				SectionInfo info = (SectionInfo)v.getTag();
				mainView.addRows(info.startIndex, info.count, null, null);
			}
		};
	}

	private List<SectionInfo> getSectionRowIdentifiers() {
		List<SectionInfo> identifiers = new ArrayList<SectionInfo>();
		identifiers.add(new SectionInfo(R.string.uyirvarukkam1, 13));
		identifiers.add(new SectionInfo(R.string.uyirmeivarukkam1, 18));
		identifiers.add(new SectionInfo(R.string.kagaravarukkam1, 12));
		identifiers.add(new SectionInfo(R.string.sagaravarukkam1, 11));
		identifiers.add(new SectionInfo(R.string.tagaravarukkam1, 11));
		identifiers.add(new SectionInfo(R.string.nagaravarukkam1, 11));
		identifiers.add(new SectionInfo(R.string.pagaravarukkam1, 11));
		identifiers.add(new SectionInfo(R.string.magaravarukkam1, 11));
		identifiers.add(new SectionInfo(R.string.vagaravarukkam1, 11));
		return identifiers;
	}

	@Override
	public void onBackPressed() {
		renderMainView();
	}

	private void renderMainView() {
		mainView.addRows(R.string.section1, 9, getSectionListener(), getSectionRowIdentifiers());
		setContentView(mainView);
	}

	@Override
	protected void onStop() {
		super.onStop();
		finish();
	}
    
}