package com.pongal.aathichudi;

import static android.view.View.MeasureSpec.UNSPECIFIED;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import android.view.View.MeasureSpec;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.pongal.aathichudi.db.DBManager;
import com.pongal.aathichudi.model.MaximRow;

public class AathichudiAppWidgetProvider extends AppWidgetProvider {
	private static List<MaximRow> maxims = new ArrayList<MaximRow>();

	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		if(maxims.size() == 0){
			maxims = new DBManager(context).getMaxims();
		}
		Random maximNumber = new Random();
		MaximRow maximRow = maxims.get(maximNumber.nextInt(108) + 1);
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget);
		Bitmap textView = createTextImage(context, maximRow.text, 20);
		Bitmap description = createTextImage(context, maximRow.shortDescription, 12);
		
		views.setImageViewBitmap(R.id.maximImage, textView);
		views.setImageViewBitmap(R.id.maximDesc, description);
		
		for (int appWidgetId : appWidgetIds) {
			Intent intent = new Intent(context, Aathichudi.class);
			intent.putExtra("groupId", maximRow.group_id);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.maximImage, pendingIntent);
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
	}

	private Bitmap createTextImage(Context context, String text, int textSize) {
		Util util = new Util(context);
		TextView textView = util.createTamilTextView(0xFFFFFFFF, textSize, Typeface.NORMAL);
		textView.setText(text);
		textView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		textView.setDrawingCacheEnabled(true);
		int maxWidth = util.getDIP(290); 
		textView.measure(MeasureSpec.makeMeasureSpec(maxWidth, MeasureSpec.EXACTLY),
				MeasureSpec.makeMeasureSpec(0, UNSPECIFIED));
		textView.layout(0, 0, textView.getMeasuredWidth(), textView.getMeasuredHeight());
		Bitmap proxy = Bitmap.createBitmap(textView.getWidth(), textView.getHeight(), Config.ARGB_8888);
		Canvas c = new Canvas(proxy);
		c.drawBitmap(textView.getDrawingCache(), new Matrix(), null);
		return proxy.copy(Config.ARGB_8888, true);
	}
	
	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		DBManager dbManager = new DBManager(context);
		maxims = dbManager.getMaxims();
	}
	
	
}
