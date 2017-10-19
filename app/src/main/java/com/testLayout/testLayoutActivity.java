package com.testLayout;



import com.main.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class testLayoutActivity extends Activity {
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
		setContentView(R.layout.test_layout_activity_layout);

	}
	
	
	void initData(){
        DisplayMetrics dm = getResources().getDisplayMetrics();  
        screenWidth = dm.widthPixels;  
        screenHeight = dm.heightPixels - 50;  	    

  	    };  
  	    
  	    
	

}
