package com.move;



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

public class SimpleMoveActivity extends Activity {
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
		setContentView(R.layout.simple_move_layout);
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
		movingEventListener = new OnTouchListener() {  
  	        int lastX, lastY;  
  	  
  	        @Override  
  	        public boolean onTouch(View v, MotionEvent event) {  
  	            switch (event.getAction()) {  
  	            case MotionEvent.ACTION_DOWN:  
  	                lastX = (int) event.getRawX();  
  	                lastY = (int) event.getRawY();  
  	                break;  
  	            case MotionEvent.ACTION_MOVE:  
  	                int dx = (int) event.getRawX() - lastX;  
  	                int dy = (int) event.getRawY() - lastY;  
  	  
  	                int left = v.getLeft() + dx;  
  	                int top = v.getTop() + dy;  
  	                int right = v.getRight() + dx;  
  	                int bottom = v.getBottom() + dy;  
  	                // 设置不能出界  
  	                if (left < 0) {  
  	                    left = 0;  
  	                    right = left + v.getWidth();  
  	                }  
  	  
  	                if (right > screenWidth) {  
  	                    right = screenWidth;  
  	                    left = right - v.getWidth();  
  	                }  
  	  
  	                if (top < 0) {  
  	                    top = 0;  
  	                    bottom = top + v.getHeight();  
  	                }  
  	  
//  	                if (bottom > screenHeight) {  
//  	                    bottom = screenHeight;  
//  	                    top = bottom - v.getHeight();  
//  	                }  
  	                if (bottom > rl.getHeight()) {  
  	                    bottom = rl.getHeight();  
  	                    top = bottom - v.getHeight();  
  	                }  
  	  
  	                v.layout(left, top, right, bottom);  
  	  
  	                lastX = (int) event.getRawX();  
  	                lastY = (int) event.getRawY();  
  	  
  	                break;  
  	            case MotionEvent.ACTION_UP:  
  	                break;  
  	            }  
  	            return true;  
  	        }  
  	    };  
  	    
  	    
		movingEventListener2 = new OnTouchListener() {  
  	        int lastX, lastY;  
  	  
  	        @Override  
  	        public boolean onTouch(View v, MotionEvent event) {  
  	            switch (event.getAction()) {  
  	            case MotionEvent.ACTION_DOWN:  
  	                lastX = (int) event.getRawX();  
  	                lastY = (int) event.getRawY();  
  	                break;  
  	            case MotionEvent.ACTION_MOVE:  
  	                int dx = (int) event.getRawX() - lastX;  
  	                int dy = (int) event.getRawY() - lastY;  
  	  
  	                int left = v.getLeft() + dx;  
  	                int top = v.getTop() + dy;  
  	                int right = v.getRight() + dx;  
  	                int bottom = v.getBottom() + dy;  
  	                // 设置不能出界  
  	                if (left < 0) {  
  	                    left = 0;  
  	                    right = left + v.getWidth();  
  	                }  
  	  
  	                if (right > screenWidth) {  
  	                    right = screenWidth;  
  	                    left = right - v.getWidth();  
  	                }  
  	  
  	                if (top < 0) {  
  	                    top = 0;  
  	                    bottom = top + v.getHeight();  
  	                }  
  	  
//  	                if (bottom > screenHeight) {  
//  	                    bottom = screenHeight;  
//  	                    top = bottom - v.getHeight();  
//  	                }  
  	                if (bottom > rl.getHeight()) {  
  	                    bottom = rl.getHeight();  
  	                    top = bottom - v.getHeight();  
  	                }  
  	  
//  	                v.layout(left, top, right, bottom);  
  	                
  	                v.offsetLeftAndRight((int) dx);
					v.offsetTopAndBottom((int) dy);
					v.invalidate();
					rl.invalidate();
					
//  	                final View fV = v;
//  	                final int fDx = dx;
//  	                final int fDy = dy;
//  	                v.post(new Runnable() {
//						@Override
//						public void run() {
//							fV.offsetLeftAndRight((int) fDx);
//							fV.offsetTopAndBottom((int) fDy);
//							fV.invalidate();
//						}
//					});
  	                lastX = (int) event.getRawX();  
  	                lastY = (int) event.getRawY();  
  	  
  	                break;  
  	            case MotionEvent.ACTION_UP:  
  	                break;  
  	            }  
  	            return true;  
  	        }  
  	    };  
  	    
  	    
	}

}
