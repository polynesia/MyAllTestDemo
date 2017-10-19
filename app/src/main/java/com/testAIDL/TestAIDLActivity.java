package com.testAIDL;


import java.io.UnsupportedEncodingException;

import com.main.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TestAIDLActivity extends Activity implements OnClickListener{
	public static final String TAG = "TestAIDLActivity";
	Button mButBind;
	Button mButCall;
	Button mButUTF;
	TextView mPhoneInfo;
	ServiceConnection mConnection;
	IPreparePresendCallBack remoteCallback;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_aidl);
		mButBind = (Button)findViewById(R.id.butBind);
		mButCall = (Button)findViewById(R.id.butCall);
		mButUTF = (Button)findViewById(R.id.butUtf);
		mPhoneInfo = (TextView)findViewById(R.id.tvinfo);
		mButBind.setOnClickListener(this);
		mButCall.setOnClickListener(this);
		
		mConnection = new ServiceConnection() {
			
			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				remoteCallback = IPreparePresendCallBack.Stub.asInterface(service);
			}
		};
		
//		String nickName = "一望无际一望无际一望无际";//"abc一";//
//		String nickName = "abcdefabcdefabcdefabcdef";//"abc一";//
		String nickName = "嗯嗯嗯嗯嗯嗯嗯嗯嗯";//"abc一";//
//		String nickName = "一";
		try {
			long len = nickName.getBytes("utf-8").length;
			long l = getChineseLength(nickName);
			System.out.println("nickName length  = "+ len + ",l = "+l);
			mButUTF.setText(len+","+l);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getPhoneInfo();
	}
	
	void getPhoneInfo(){
		String abi = Build.CPU_ABI;
		mPhoneInfo.setText(abi);
	}
    public static int getChineseLength(String validateStr) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < validateStr.length(); i++) {
            /* 获取一个字符 */
            String temp = validateStr.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

	void testCall(){
		TransferData data = new TransferData("/root/XXX");
		Log.d(TAG, "[TestAIDL] testCall start. data = "+data);
		try {
			remoteCallback.compress(data);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d(TAG, "[TestAIDL] testCall  end. data = "+data);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		switch(id){
		case R.id.butBind:{
			this.bindService(new Intent(this,TestAIDLService.class ), mConnection, Context.BIND_AUTO_CREATE);
			break;
		}
		case R.id.butCall:{
			testCall();
			break;
		}
		default:
			break;
		
		}
	}

}
