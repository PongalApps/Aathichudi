package com.pongal.aathichudi;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pongal.aathichudi.model.Item;

public class HeaderView extends LinearLayout {

	private TextView previous;
	private TextView next;
	private TextView header;
	private final Util util;
	private final OnClickListener navigationHandler;

	public HeaderView(Context context, Util util,
			OnClickListener navigationHandler) {
		super(context);
		this.util = util;
		this.navigationHandler = navigationHandler;
		setBackgroundResource(R.layout.headerbg);
		previous = getNavigationButton(context, "<");
		previous.setGravity(Gravity.RIGHT);
		next = getNavigationButton(context, ">");
		header = getHeader();
		addView(previous);
		addView(header);
		addView(next);
	}

	public void render(Item item) {
		header.setText(item.getText());
		renderNavigationLink(item.getNext(), next);
		renderNavigationLink(item.getPrevious(), previous);

	}

	private void renderNavigationLink(Item item, TextView navigationView) {
		navigationView.setVisibility(item != null ? VISIBLE : INVISIBLE);
		if (item != null) {
			navigationView.setTag(item);
		}
	}

	private TextView getHeader() {
		TextView header = util.createTamilTextView(0xFFFFFFFF, 22,
				Typeface.BOLD);
		header.setText(R.string.header);
		header.setPadding(5, 5, 5, 5);
		header.setGravity(Gravity.CENTER);
		header.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 8));
		return header;
	}

	private TextView getNavigationButton(Context context, String text) {
		TextView navigationLink = util.createTamilTextView(0xFFFFFFFF, 22,
				Typeface.BOLD);
		navigationLink.setText(text);
		navigationLink.setVisibility(INVISIBLE);
		navigationLink.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1));
		navigationLink.setOnClickListener(navigationHandler);
		return navigationLink;
	}

}
