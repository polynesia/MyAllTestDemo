package com.raymond.main;



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

public class SimpleDemoActivity extends Activity {
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
		setContentView(R.layout.simple_demo_layout);
		rl = (RelativeLayout) findViewById(R.id.relativeLayout1);  
		initData();
		img1 = (ImageView) findViewById(R.id.imageView1);  
        img2 = (ImageView) findViewById(R.id.imageView2); 
        
        img1.setOnTouchListener(movingEventListener);  
        img2.setOnTouchListener(movingEventListener2);  
	}
	
	
	void initData(){
        DisplayMetrics dm = getResources().getDisplayMetrics();  
        screenWidth = dm.widthPixels;  
        screenHeight = dm.heightPixels - 50;  	    

  	    };  
  	    
  	    
	

}
