package com.pongal.aathichudi.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	private Item parent;
	private LinkedList<Item> children = new LinkedList<Item>();
	private String text;
	private String shortDesc;
	private Integer id;

	public Item(Integer id, String text, String shortDesc) {
		this.id = id;
		this.text = text;
		this.shortDesc = shortDesc;
	}

	public void addChild(Item item) {
		children.add(item);
		item.parent = this;
	}
	
	public Integer getId() {
		return this.id;
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
	
	public Item getPrevious(){
		if(parent == null || parent.children.getFirst() == this) return null;
		return parent.children.get(parent.children.indexOf(this) - 1);
	}
	
	public Item getNext(){
		if(parent == null || parent.children.getLast() == this) return null;
		return parent.children.get(parent.children.indexOf(this) + 1);
	}


}
