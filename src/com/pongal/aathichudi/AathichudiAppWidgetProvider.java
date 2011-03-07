package com.pongal.aathichudi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.pongal.aathichudi.db.DBManager;
import com.pongal.aathichudi.model.MaximRow;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class AathichudiAppWidgetProvider extends AppWidgetProvider {
	private static List<MaximRow> maxims = new ArrayList<MaximRow>();
	private static int currentItem;

	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		if(maxims.size() == 0){
			maxims = new DBManager(context).getMaxims();
		}
		MaximRow maximRow = maxims.get(currentItem % maxims.size());
		currentItem++;
		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.appwidget);
		TextView textView = createTextView(context, maximRow.text, 18);
		textView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		textView.layout(0, 0, textView.getMeasuredWidth(),
				textView.getMeasuredHeight());
		TextView descView = createTextView(context, maximRow.shortDescription, 14);
		descView.measure(MeasureSpec.makeMeasureSpec(320, MeasureSpec.EXACTLY),
				MeasureSpec.makeMeasureSpec(30, MeasureSpec.EXACTLY));
		descView.layout(0, 0, descView.getMeasuredWidth(),
				descView.getMeasuredHeight());
		views.setImageViewBitmap(R.id.maximImage, textView.getDrawingCache());
		views.setImageViewBitmap(R.id.descriptionImage, descView.getDrawingCache());
		
		for (int appWidgetId : appWidgetIds) {
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
	}

	private TextView createTextView(Context context, String text, int size) {
		TextView textView = new TextView(context);
		textView.setTypeface(Util.tf);
		textView.setText(text);
		textView.setTextColor(0xFF000000);
		textView.setTextSize(size);
		textView.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		textView.setDrawingCacheEnabled(true);
		
		return textView;
	}

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		DBManager dbManager = new DBManager(context);
		maxims = dbManager.getMaxims();
	}
	
	
}
