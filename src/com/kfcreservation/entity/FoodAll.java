package com.kfcreservation.entity;

import android.content.ContentValues;

import com.kfcreservation.core.Record;

public class FoodAll implements Record{
	private int id;
	private String name;
	private double price;
	private int foodType;
	private String img;
	
	public FoodAll(){
		
	}
	
	public FoodAll(int id,String name,double price,int foodType,String img){
		this.setId(id);
		this.setName(name);
		this.setPrice(price);
		this.setFoodType(foodType);
		this.setImg(img);
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getFoodType() {
		return foodType;
	}
	public void setFoodType(int foodType) {
		this.foodType = foodType;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public ContentValues getContentValues() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
