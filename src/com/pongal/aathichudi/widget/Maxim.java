package com.pongal.aathichudi.widget;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import android.content.Context;
import android.graphics.Typeface;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pongal.aathichudi.model.Item;

public class Maxim extends LinearLayout {

	public Maxim(Context context, Item item) {
		super(context);
		setupMaxim(item);
	}

	private void setupMaxim(Item item) {
		setAnimation(getMaximAnim());
		setPadding(0, 0, 40, 0);
		setLayoutParams(new LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
		setOrientation(LinearLayout.VERTICAL);

		int textSize = item.hasShortDesc() ? 18 : 20;
		int textStyle = item.hasShortDesc() ? Typeface.BOLD : Typeface.NORMAL;
		TextView textView = new TamilTextView(getContext(), 0xFF000000, textSize, textStyle);
		textView.setText(item.getText());
		textView.setLayoutParams(new LayoutParams(WRAP_CONTENT, WRAP_CONTENT, 1));
		addView(textView);

		if (item.hasShortDesc()) {
			TextView desc = new TamilTextView(getContext(), 0xEE000000, 14, Typeface.NORMAL);
			desc.setLayoutParams(new LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
			desc.setText(item.getShortDesc());
			addView(desc);
		}
	}

	private Animation getMaximAnim() {
		int duration = 500;
		Animation show = new AlphaAnimation(0, 1);
		show.setDuration(100);
		show.setFillAfter(true);
		Animation slide = new TranslateAnimation(-250, 20, 0, 0);
		slide.setDuration(duration);
		slide.setFillAfter(true);
		AnimationSet anims = new AnimationSet(false);
		anims.setFillAfter(true);
		anims.addAnimation(show);
		anims.addAnimation(slide);
		return anims;
	}
}
