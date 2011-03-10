package com.pongal.aathichudi;

import static android.view.View.MeasureSpec.UNSPECIFIED;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import java.util.ArrayList;
import java.util.List;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.pongal.aathichudi.db.DBManager;
import com.pongal.aathichudi.model.MaximRow;

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
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget);
		TextView textView = createTextView(context, maximRow.text);
		
		views.setImageViewBitmap(R.id.maximImage, textView.getDrawingCache());
		
		for (int appWidgetId : appWidgetIds) {
			Intent intent = new Intent(context, Aathichudi.class);
			intent.putExtra("groupId", maximRow.group_id);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.maximImage, pendingIntent);
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
	}

	private TextView createTextView(Context context, String text) {
		TextView textView = new Util(context).createTamilTextView(0xFFFFFFFF, 25, Typeface.NORMAL);
		textView.setText(text);
		textView.setLayoutParams(new LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
		textView.setDrawingCacheEnabled(true);
		textView.measure(MeasureSpec.makeMeasureSpec(0, UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(0, UNSPECIFIED));
		textView.layout(0, 0, textView.getMeasuredWidth(),
				textView.getMeasuredHeight());
		return textView;
	}

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		DBManager dbManager = new DBManager(context);
		maxims = dbManager.getMaxims();
	}
	
	
}
