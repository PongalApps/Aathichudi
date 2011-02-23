package com.pongal.aathichudi;

public class Item {
	public Item(int id, String text, String shortDesc, int group_id) {
		this.id = id;
		this.text = text;
		this.shortDescription = shortDesc;
		this.group_id = group_id;
	}
	
	public Item() {
	
	}
	public int id;
	public String text;
	public String shortDescription;
	public int group_id;
}
