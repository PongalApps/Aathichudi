package com.pongal.aathichudi;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.GetChars;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.pongal.aathichudi.model.Item;

public class MaximViewer extends LinearLayout {

	private TextView header;
	private TableLayout table;
	private Util util;
	private ScrollView scrollView;

	public MaximViewer(Context context, AssetManager asset) {
		super(context);
		setOrientation(LinearLayout.VERTICAL);
		util = new Util(context, asset);
		header = getHeader();
		scrollView = new ScrollView(context);
		table = new TableLayout(context);
		scrollView.addView(table);
		addView(header);
		addView(scrollView);
	}

	public void render(Item item) {
		setTag(item);
		table.removeAllViews();
		scrollView.scrollTo(0, 0);
		header.setText(item.getText());
		for (Item child : item.getChildren()) {
			table.addView(getRowView(child));
		}
	}

	public Item getItem() {
		return (Item) getTag();
	}

	private LinearLayout getRowView(Item item) {
		LinearLayout wrapper = new LinearLayout(getContext());
		LinearLayout rowLayout = new LinearLayout(getContext());

		int textStyle = item.hasShortDesc() ? Typeface.BOLD : Typeface.NORMAL;
		TextView textView = util.createTamilTextView(0xFF000000, 18, textStyle);
		textView.setText(item.getText());
		textView.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1));
		rowLayout.addView(textView);

		if (!item.getChildren().isEmpty()) {
			TextView arrow = util.createTamilTextView(0xFF000000, 18, Typeface.NORMAL);
			arrow.setText(">");
			rowLayout.addView(arrow);
			wrapper.setOnClickListener(getClickListener());
			wrapper.setOnTouchListener(getHighlightListener());
		}

		wrapper.setOrientation(LinearLayout.VERTICAL);
		setRowStyle(wrapper);
		wrapper.setTag(item);
		wrapper.addView(rowLayout);

		if (item.hasShortDesc()) {
			TextView desc = util.createTamilTextView(0xFF000000, 14, Typeface.NORMAL);
			desc.setText(item.getShortDesc());
			wrapper.addView(desc);
		}

		return wrapper;
	}

	private OnClickListener getClickListener() {
		return new OnClickListener() {
			@Override
			public void onClick(View v) {
				render((Item) v.getTag());
			}
		};
	}

	private OnTouchListener getHighlightListener() {
		return new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN
						|| event.getAction() == MotionEvent.ACTION_MOVE) {
					v.setBackgroundResource(R.layout.cellbg_highlt);
				}
				if (event.getAction() == MotionEvent.ACTION_UP
						|| event.getAction() == MotionEvent.ACTION_CANCEL) {
					v.setBackgroundResource(R.layout.cellbg);
				}
				return false;
			}
		};
	}

	private void setRowStyle(LinearLayout row) {
		row.setBackgroundResource(R.layout.cellbg);
		row.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		row.setPadding(10, 10, 10, 10);
	}

	private TextView getHeader() {
		TextView header = util.createTamilTextView(0xFFFFFFFF, 22, Typeface.BOLD);
		header.setText(R.string.header);
		header.setBackgroundResource(R.layout.headerbg);
		header.setPadding(5, 5, 5, 5);
		header.setGravity(Gravity.CENTER);
		return header;
	}

}
