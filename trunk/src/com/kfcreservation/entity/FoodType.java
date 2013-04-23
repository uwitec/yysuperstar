package com.kfcreservation.entity;

import com.kfcreservation.core.Record;

import android.content.ContentValues;

public class FoodType implements Record{
	
	private int _fid;
	private String Name;

	public FoodType(){	}
	
	public FoodType(int id,String Name){
		this.setId(id);
		this.setName(Name);
	}
	
	public int getId() {
		return _fid;
	}
	
	public void setId(int _fid) {
		this._fid = _fid;
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
		values.put("_fid", _fid);
		values.put("Name", Name);
		return values;
	}
}
