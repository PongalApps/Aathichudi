package com.pongal.aathichudi.widget;

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.pongal.aathichudi.R;
import com.pongal.aathichudi.model.Item;
import com.pongal.aathichudi.model.ItemBinder;

public class MaximViewer extends ScrollView {

	public MaximViewer(Context context) {
		super(context);
		setLayoutParams(new LayoutParams(FILL_PARENT, FILL_PARENT));
	}

	public void load(Item item, Integer selectedMaximId) {
		this.removeAllViews();
		LinearLayout maxims = renderChildren(item);
		this.addView(maxims);
		if (selectedMaximId != null) {
			expandHeader(maxims, selectedMaximId);
		}
	}

	private LinearLayout renderChildren(Item item) {
		LinearLayout table = new LinearLayout(getContext());
		table.setOrientation(LinearLayout.VERTICAL);
		table.setLayoutParams(new LayoutParams(FILL_PARENT, WRAP_CONTENT));
		for (final Item child : item.getChildren()) {
			final LinearLayout rowView = getRowView(child);
			table.addView(rowView);
		}
		return table;
	}

	private LinearLayout getRowView(Item item) {
		LinearLayout content = new LinearLayout(getContext());
		content.setLayoutParams(new LayoutParams(FILL_PARENT, WRAP_CONTENT));
		content.setOrientation(LinearLayout.VERTICAL);
		content.addView(new Maxim(getContext(), item));
		content.addView(createLine());
		content.setTag(new ItemBinder(item, false));
		if (!item.getChildren().isEmpty()) {
			content.setOnClickListener(getClickListener());
		}
		return content;
	}

	private void expandHeader(LinearLayout view, Integer selectedMaximId) {
		for (int i = 0; i < view.getChildCount(); i++) {
			LinearLayout childView = (LinearLayout) view.getChildAt(i);
			ItemBinder itemBinder = (ItemBinder) childView.getTag();
			Item item = itemBinder.getItem();
			for(Item childItem : item.getChildren()) {
				if(childItem.getId() == selectedMaximId) {
					toggleMaxims(childView);
					return;
				}
			}
		}
	}

	private TextView createLine() {
		TextView line = new TextView(getContext());
		line.setLayoutParams(new LayoutParams(FILL_PARENT, WRAP_CONTENT));
		line.setBackgroundResource(R.layout.maxim_separator);
		return line;
	}

	private OnClickListener getClickListener() {
		return new OnClickListener() {
			public void onClick(final View v) {
				toggleMaxims(v);
			}
		};
	}

	private void toggleMaxims(View maximHeader) {
		LinearLayout parent = (LinearLayout) maximHeader.getParent();
		int childIndex = parent.indexOfChild(maximHeader) + 1;
		ItemBinder itemBinder = (ItemBinder) maximHeader.getTag();
		Log.d("aa",
				"clicked on view " + maximHeader + " having "
						+ itemBinder.isChildrenVisible());

		if (!itemBinder.isChildrenVisible()) {
			LinearLayout children = renderChildren(itemBinder.getItem());
			parent.addView(children, childIndex);
			maximHeader.setAnimation(getMaximHeaderAnim());
		} else {
			parent.removeViewAt(childIndex);
		}
		itemBinder.toggleChilrenVisibility();
	}

	private Animation getMaximHeaderAnim() {
		AnimationSet anims = new AnimationSet(false);
		Animation bump = new ScaleAnimation(1, 0.95f, 1, 1f);
		bump.setDuration(100);
		anims.addAnimation(bump);
		return anims;
	}

	// Constructor for using this in XML Layouts
	public MaximViewer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MaximViewer(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
}
