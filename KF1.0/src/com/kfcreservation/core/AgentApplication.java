package com.kfcreservation.core;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

public class AgentApplication extends Application {

	private List<Activity> activities = new ArrayList<Activity>();  
	
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
		for(Activity activity :activities){
			activity.finish();
		}
		
		System.exit(0);  

	}

}
