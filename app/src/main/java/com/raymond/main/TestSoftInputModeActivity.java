package com.raymond.main;



import com.main.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class TestSoftInputModeActivity extends Activity {
	private ImageView img1;  
    private ImageView img2;  
    OnTouchListener movingEventListener;
    OnTouchListener movingEventListener2;
    
    private int screenWidth;  
    private int screenHeight;
    RelativeLayout rl,root;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.phone_bind_number_from_new_user_guide);
//		setContentView(R.layout.phone_bind_number_from_new_user_guide2);
//		setContentView(R.layout.phone_bind_number_from_new_user_guide3);
		setContentView(R.layout.phone_bind_number_from_new_user_guide4);
      getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//      getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//      getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
//      getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED);
	}
	
	
	void initData(){
        DisplayMetrics dm = getResources().getDisplayMetrics();  
        screenWidth = dm.widthPixels;  
        screenHeight = dm.heightPixels - 50;  	    

  	    };  
  	    
  	    
	

}
