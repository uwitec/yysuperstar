package com.kfcreservation.entity;

public class Order {

	private String name;
	private String phone;
	private String address;
	private String msg;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "Order [name=" + name + ", phone=" + phone + ", address="
				+ address + ", msg=" + msg + "]";
	}
	
}
