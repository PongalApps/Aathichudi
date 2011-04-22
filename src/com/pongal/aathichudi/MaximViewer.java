package com.pongal.aathichudi;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import static android.widget.LinearLayout.LayoutParams.*;

import com.pongal.aathichudi.model.Item;

public class MaximViewer extends LinearLayout {

	private TableLayout table;
	private Util util;
	private ScrollView scrollView;
	private HeaderView header;
	private final Context context;

	public MaximViewer(Context context) {
		super(context);
		this.context = context;
		setOrientation(LinearLayout.VERTICAL);
		util = new Util(context);
		header = new HeaderView(context, util, navigationHandler());
		scrollView = new ScrollView(context);
		table = new TableLayout(context);
		scrollView.addView(table);
		addView(header);
		addView(scrollView);
	}
	
	private OnClickListener navigationHandler() {
		return new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				render((Item) v.getTag());
			}
		};
	}

	public void render(final Item item) {
		Animation makeOutAnimation = AnimationUtils.makeOutAnimation(context, false);
		makeOutAnimation.setDuration(1000L);
		makeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {}
			
			@Override
			public void onAnimationRepeat(Animation animation) {}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				Animation makeInAnimation = AnimationUtils.makeInAnimation(context, false);
				makeInAnimation.setDuration(1000L);
				startAnimation(makeInAnimation);
				someMethod(item);
			}
		});
		startAnimation(makeOutAnimation);
	}
	
	public void someMethod(Item item){
		setTag(item);
		table.removeAllViews();
		scrollView.scrollTo(0, 0);
		header.render(item);
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
		textView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT, 1));
		rowLayout.addView(textView);

		if (!item.getChildren().isEmpty()) {
			TextView arrow = util.createTamilTextView(0xFF000000, 18,
					Typeface.NORMAL);
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
			TextView desc = util.createTamilTextView(0xFF000000, 14,
					Typeface.NORMAL);
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
		row.setLayoutParams(new LayoutParams(FILL_PARENT, FILL_PARENT));
		row.setPadding(10, 10, 10, 10);
	}


}
