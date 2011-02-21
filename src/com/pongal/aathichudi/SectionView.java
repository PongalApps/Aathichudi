package com.pongal.aathichudi;

import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class SectionView extends ScrollView {

	Typeface tf;
	private TableLayout table;

	public SectionView(Context context, AssetManager asset) {
		super(context);
		tf = Typeface.createFromAsset(asset, "fonts/TSC_Mylai.ttf");
		table = new TableLayout(context);
		addView(table);		
	}

	public void addRows(Integer startTextIndex, Integer count,
			View.OnClickListener listener, List<SectionInfo> rowIdentifier) {
		table.removeAllViews();
		scrollTo(0, 0);
		for (int i = 0; i < count; i++) {
			TableRow row = new TableRow(getContext());
			setRowStyle(row);
			if (rowIdentifier != null && i < rowIdentifier.size()) {
				row.setTag(rowIdentifier.get(i));
			}
			TextView textView = new TextView(getContext());
			textView.setTypeface(tf);
			textView.setText((i + startTextIndex));
			textView.setTextColor(0xFF000000);
			textView.setTextSize(18);
			row.addView(textView);
			if (listener != null) {
				row.setOnClickListener(listener);
				row.setOnTouchListener(getHighlightListener());
			}
			
			table.addView(row);
		}
	}

	private OnTouchListener getHighlightListener() {
		return new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				((TableRow)v).setSelected(true);
				Log.d(null, event.getAction()+"");
				if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
					v.setBackgroundResource(R.layout.cellbg_highlt);
				}
				if(event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
					v.setBackgroundResource(R.layout.cellbg);
				}
				return false;
			}
		};
	}

	private void setRowStyle(TableRow row) {
		row.setBackgroundResource(R.layout.cellbg);
		row.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		row.setPadding(10, 10, 10, 10);
	}
}
