package com.kfcreservation.entity;

import android.content.ContentValues;

import com.kfcreservation.core.Record;

public class UserFoods implements Record {

	private int _ufid;
	private int Userid;
	private int Foodid;
	private int Serial;
	private int Count;
	private int Status;
	
	public UserFoods(){	}
	
	public UserFoods(int _ufid, int Userid, int Foodid, int Serial, int Count, int Status){
		this.set_ufid(_ufid);
		this.setUserid(Userid);
		this.setFoodid(Foodid);
		this.setSerial(Serial);
		this.setCount(Count);
		this.setStatus(Status);
	}
	
	public int get_ufid() {
		return _ufid;
	}

	public void set_ufid(int _ufid) {
		this._ufid = _ufid;
	}

	public int getUserid() {
		return Userid;
	}

	public void setUserid(int userid) {
		Userid = userid;
	}

	public int getFoodid() {
		return Foodid;
	}

	public void setFoodid(int foodid) {
		Foodid = foodid;
	}

	public int getSerial() {
		return Serial;
	}

	public void setSerial(int serial) {
		Serial = serial;
	}

	public int getCount() {
		return Count;
	}

	public void setCount(int count) {
		Count = count;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	@Override
	public ContentValues getContentValues() {
		ContentValues values =new ContentValues();
		values.put("Userid", Userid);
		values.put("Foodid", Foodid);
		values.put("Serial", Serial);
		values.put("Count", Count);
		values.put("Status", Status);
		return values;
	}

}
