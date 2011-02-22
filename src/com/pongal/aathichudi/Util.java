package com.pongal.aathichudi;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.TextView;

public class Util {

	private static Typeface tf;
	private Context context;
	private AssetManager asset;

	public Util(Context context, AssetManager asset) {
		this.context = context;
		this.asset = asset;
		tf = Typeface.createFromAsset(asset, "fonts/TSC_Comic.ttf");
	}
	
	
	public TextView createTamilTextView(int color, int size) {
		TextView textView = new TextView(context);
		textView.setTypeface(tf);
		textView.setTextColor(color);
		textView.setTextSize(size);
		return textView;
	}
}
