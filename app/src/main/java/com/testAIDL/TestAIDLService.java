package com.testAIDL;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class TestAIDLService extends Service {

	IPreparePresendCallBack mCallBack;
	public static final String TAG = "TestAIDLService";
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		mCallBack = new IPreparePresendCallBack.Stub() {
			
			@Override
			public void onPrepare(IBinder messenger) throws RemoteException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void compress(TransferData data) throws RemoteException {
				Log.d(TAG, "[TestAIDL] callback start. data = "+data);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				data.dstPath = "/remote/result/XXX";
				
				Log.d(TAG, "[TestAIDL] callback end. data = "+data);
				
			}
		};
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return mCallBack.asBinder();
	}

}
