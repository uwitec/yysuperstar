package com.kfcreservation.core;

import java.util.concurrent.CountDownLatch;

public class OperCountDownLatch extends CountDownLatch{

	private boolean flag = true;
	
	public OperCountDownLatch(int count) {
		super(count);
	}
	
	public boolean getFlag() {
		return flag;
	}
 
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
