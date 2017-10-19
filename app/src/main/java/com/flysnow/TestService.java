package com.flysnow;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class TestService extends IntentService {

	public TestService() {
		super("TestService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		Log.d("@@@", "onHandleIntent Start!");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d("@@@", "onHandleIntent End!");
	}

}
