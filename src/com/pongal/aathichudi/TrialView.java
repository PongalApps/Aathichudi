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
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class TrialView extends TableLayout {

	Typeface tf;
	private TableLayout table;

	public TrialView(Context context, AssetManager asset) {
		super(context);
		TableRow tableRow1 = new TableRow(context);
		TextView textView4 = new TextView(context);
		textView4.setText("asdfasfdasdf");
		tableRow1.addView(textView4);
		addView(tableRow1);
		tf = Typeface.createFromAsset(asset, "fonts/TSC_Mylai.ttf");
		table = new TableLayout(context);
		setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		TableRow tableRow = new TableRow(context);
//		RelativeLayout relativeLayout = new RelativeLayout(context);
//		relativeLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			TextView textView1 = new TextView(context);
			textView1.setText("View 1");
			textView1.setTextColor(0xFF00FF00);
			tableRow.addView(textView1);
			
			TextView textView2 = new TextView(context);
			textView2.setText("View 2");
			RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			textView2.setTextColor(0xFF000000);
//			textView2.setLayoutParams(relativeLayoutParams);
		tableRow.addView(textView2);
//		tableRow.addView(relativeLayout);
		addView(tableRow);
//		addView(table);		
	}
}
