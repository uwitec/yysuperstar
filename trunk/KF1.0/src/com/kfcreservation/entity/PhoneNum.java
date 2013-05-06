package com.kfcreservation.entity;

public class PhoneNum {
	private int _pid;
	private int _uid;
	private String PhoneNum;
	public int get_pid() {
		return _pid;
	}
	public void set_pid(int _pid) {
		this._pid = _pid;
	}
	public int get_uid() {
		return _uid;
	}
	public void set_uid(int _uid) {
		this._uid = _uid;
	}
	public String getPhoneNum() {
		return PhoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		PhoneNum = phoneNum;
	}
	public PhoneNum() {
		super();
	}
	public PhoneNum(int _pid, int _uid, String phoneNum) {
		super();
		this._pid = _pid;
		this._uid = _uid;
		PhoneNum = phoneNum;
	}
	@Override
	public String toString() {
		return "PhoneNum [_pid=" + _pid + ", _uid=" + _uid + ", PhoneNum="
				+ PhoneNum + "]";
	}

}
