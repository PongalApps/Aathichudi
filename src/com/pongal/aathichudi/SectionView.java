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
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class SectionView extends ScrollView {

	private TableLayout table;
	private Util util;

	public SectionView(Context context, AssetManager asset) {
		super(context);
		util = new Util(context, asset);
		table = new TableLayout(context);
		addView(table);		
	}

	public void addRows(Integer startTextIndex, Integer count,
			View.OnClickListener listener, List<SectionInfo> rowIdentifier) {
		table.removeAllViews();
		scrollTo(0, 0);
		for (int i = 0; i < count; i++) {
			LinearLayout rowLayout = new LinearLayout(getContext());
			setRowStyle(rowLayout);
			if (rowIdentifier != null && i < rowIdentifier.size()) {
				rowLayout.setTag(rowIdentifier.get(i));
			}
			TextView textView = util.createTamilTextView(0xFF000000, 18);
			textView.setId((i + startTextIndex));
			textView.setText((i + startTextIndex));
			textView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1));
			rowLayout.addView(textView);
			if (listener != null) {
				TextView arrow = util.createTamilTextView(0xFF000000, 18);
				arrow.setText(">");
				arrow.setId((i + startTextIndex)+1900);
				rowLayout.addView(arrow);
				rowLayout.setOnClickListener(listener);
				rowLayout.setOnTouchListener(getHighlightListener());
			}
			table.addView(rowLayout);
		}
	}

	private OnTouchListener getHighlightListener() {
		return new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
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

	private void setRowStyle(LinearLayout row) {
		row.setBackgroundResource(R.layout.cellbg);
		row.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		row.setPadding(10, 10, 10, 10);
	}
}
