package com.pongal.aathichudi.model;

public class MaximRow {
	public Integer id;
	public String text;
	public String shortDescription;
	public Integer group_id;

	public MaximRow(Integer id, String text, String shortDesc, Integer group_id) {
		this.id = id;
		this.text = text;
		this.shortDescription = shortDesc;
		this.group_id = group_id;
	}

	public MaximRow() {
	}

}
