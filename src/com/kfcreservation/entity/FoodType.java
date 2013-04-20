package com.kfcreservation.entity;

import com.kfcreservation.core.Record;

import android.content.ContentValues;

public class FoodType implements Record{
	
	private int id;
	private String Name;

	public FoodType(){	}
	
	public FoodType(int id,String Name){
		this.setId(id);
		this.setName(Name);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	
	public void setName(String Name) {
		this.Name = Name;
	}

	@Override
	public ContentValues getContentValues() {
		ContentValues values =new ContentValues();
		values.put("id", id);
		values.put("Name", Name);
		return values;
	}
}
