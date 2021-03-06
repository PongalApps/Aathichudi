package com.pongal.aathichudi.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.TextView;

public class Util {

	static Typeface tf;
	private Context context;

	public Util(Context context) {
		this.context = context;
		tf = Typeface.createFromAsset(context.getAssets(), "fonts/TSC_Comic.ttf");
	}
	
	
	public TextView createTamilTextView11(int color, int textSize, int style) {
		TextView textView = new TextView(context);
		textView.setTypeface(tf, style);
		textView.setTextColor(color);
		textView.setTextSize(textSize);
		return textView;
	}
	
	public int getDIP(int pixels) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 
                (float) pixels, context.getResources().getDisplayMetrics());
	}
}
