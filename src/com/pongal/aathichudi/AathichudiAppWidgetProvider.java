package com.pongal.aathichudi;

import java.util.Random;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.RemoteViews;
import android.widget.TextView;

public class AathichudiAppWidgetProvider extends AppWidgetProvider {
	private String[] texts = new String[] { "text 1", "text 2", "text 3" };

	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		for (int appWidgetId : appWidgetIds) {
			RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.appwidget);
			TextView textView = new TextView(context);
			textView.setText(texts[new Random().nextInt(texts.length)]);
			textView.setDrawingCacheEnabled(true);
			textView.measure(
					MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
					MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
			textView.layout(0, 0, textView.getMeasuredWidth(),
					textView.getMeasuredHeight());
			views.setImageViewBitmap(R.id.appWidgetImage, textView.getDrawingCache());
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
	}
}
