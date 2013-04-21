package com.kfcreservation.entity;

import com.kfcreservation.core.Record;

import android.content.ContentValues;

public class UserInfo implements Record {
	
	private int id;
	private String PhoneNum;
	private String Password;

	public UserInfo(){	}
	
	public UserInfo(int id, String PhoneNum, String Password){
		this.setId(id);
		this.setPhoneNum(PhoneNum);
		this.setPassword(Password);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPhoneNum() {
		return PhoneNum;
	}
	
	public void setPhoneNum(String PhoneNum) {
		this.PhoneNum = PhoneNum;
	}
	
	public String getPassword() {
		return Password;
	}
	
	public void setPassword(String Password) {
		this.Password = Password;
	}

	@Override
	public ContentValues getContentValues() {
		ContentValues values =new ContentValues();
		values.put("PhoneNum", PhoneNum);
		values.put("Password", Password);
		return values;
	}
}
