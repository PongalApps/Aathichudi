package com.pongal.aathichudi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	private Item parent;
	private List<Item> children = new ArrayList<Item>();
	private String text;
	private String shortDesc;

	public Item(MaximRow row) {
		this.text = row.text;
		this.shortDesc = row.shortDescription;
	}

	public void addChild(Item item) {
		children.add(item);
		item.parent = this;
	}

	public Item getParent() {
		return parent;
	}

	public List<Item> getChildren() {
		return children;
	}

	public String getText() {
		return text;
	}

	public String getShortDesc() {
		return shortDesc;
	}
	
	public boolean hasShortDesc() {
		return shortDesc.length() != 0;
	}
	
	

}
