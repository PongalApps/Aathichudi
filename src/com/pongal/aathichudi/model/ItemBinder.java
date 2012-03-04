package com.pongal.aathichudi.model;

public class ItemBinder {
	private Item item;
	private boolean childrenVisible;
	
	public ItemBinder(Item item, boolean childrenVisible) {
		this.item = item;
		this.childrenVisible = childrenVisible;
	}

	public Item getItem() {
		return item;
	}

	public boolean isChildrenVisible() {
		return childrenVisible;
	}
	
	public void toggleChilrenVisibility() {
		childrenVisible = !childrenVisible;
	}
}
