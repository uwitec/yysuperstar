package com.kfcreservation.entity;

public class UserAddress {
	private int _Aid;
	private int _Uid;
	private String Address;
	public int get_Aid() {
		return _Aid;
	}
	public void set_Aid(int _Aid) {
		this._Aid = _Aid;
	}
	public int get_Uid() {
		return _Uid;
	}
	public void set_Uid(int _Uid) {
		this._Uid = _Uid;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public UserAddress(int _Aid, int _Uid, String address) {
		super();
		this._Aid = _Aid;
		this._Uid = _Uid;
		Address = address;
	}
	public UserAddress() {
		super();
	}
	@Override
	public String toString() {
		return "UserAddress [_Aid=" + _Aid + ", _Uid=" + _Uid + ", Address="
				+ Address + "]";
	}
	

}
