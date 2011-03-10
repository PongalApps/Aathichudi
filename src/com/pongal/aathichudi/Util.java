package com.pongal.aathichudi;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.TextView;

public class Util {

	static Typeface tf;
	private Context context;

	public Util(Context context) {
		this.context = context;
		tf = Typeface.createFromAsset(context.getAssets(), "fonts/TSC_Comic.ttf");
	}
	
	
	public TextView createTamilTextView(int color, int size, int style) {
		TextView textView = new TextView(context);
		textView.setTypeface(tf, style);
		textView.setTextColor(color);
		textView.setTextSize(size);
		return textView;
	}
}
