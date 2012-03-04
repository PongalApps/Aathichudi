package com.pongal.aathichudi.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TamilTextView extends TextView {
	
	Typeface typeface;

	public TamilTextView(Context context, int color, int textSize, int style) {
		super(context);
		typeface = Typeface.createFromAsset(context.getAssets(), "fonts/TSC_Comic.ttf");
		setTypeface(typeface,style);
		setTextColor(color);
		setTextSize(textSize);
	}

	public TamilTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		typeface = Typeface.createFromAsset(context.getAssets(), "fonts/TSC_Comic.ttf");
		setTypeface(typeface);
	}
	
	
}
