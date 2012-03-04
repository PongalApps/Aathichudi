package com.pongal.aathichudi;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.pongal.aathichudi.db.DBManager;
import com.pongal.aathichudi.model.Item;
import com.pongal.aathichudi.widget.TamilTextView;
import com.pongal.aathichudi.widget.Util;

public class AathichudiAppWidgetProvider extends AppWidgetProvider {

	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		Item maxim = new DBManager(context).getRandomMaxim();
		Log.d("aa", "maxim: " + maxim.getText());
		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.appwidget);
		Bitmap textView = createTextImage(context, maxim.getText(), 20);
		Bitmap description = createTextImage(context, maxim.getShortDesc(), 14);

		views.setImageViewBitmap(R.id.maximImage, textView);
		views.setImageViewBitmap(R.id.maximDesc, description);

		for (int appWidgetId : appWidgetIds) {
			Intent intent = new Intent(context, Aathichudi.class);
			intent.putExtra("maximId", maxim.getId());
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
					intent, PendingIntent.FLAG_UPDATE_CURRENT);
			views.setOnClickPendingIntent(R.id.maximImage, pendingIntent);
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
	}

	private Bitmap createTextImage(Context context, String text, int textSize) {
		Util util = new Util(context);
		TextView textView = new TamilTextView(context, 0xFFFFFFFF, textSize,
				Typeface.NORMAL);
		textView.setText(text);
		textView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		textView.setDrawingCacheEnabled(true);
		int maxWidth = util.getDIP(294);
		textView.measure(MeasureSpec.makeMeasureSpec(maxWidth,
				MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(
				util.getDIP(72), MeasureSpec.AT_MOST));
		textView.layout(0, 0, textView.getMeasuredWidth(),
				textView.getMeasuredHeight());
		Bitmap proxy = Bitmap.createBitmap(textView.getWidth(),
				textView.getHeight(), Config.ARGB_8888);
		Canvas c = new Canvas(proxy);
		c.drawBitmap(textView.getDrawingCache(), new Matrix(), null);
		return proxy.copy(Config.ARGB_8888, true);
	}

}
