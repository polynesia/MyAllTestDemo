package com.raymond.main;



import com.main.R;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.TouchDelegate;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class TestTouchDelegateActivity extends Activity {
	private ImageView img0;  
	private ImageView img1;  
    private ImageView img2;  
    OnClickListener onClickListen0;
    OnClickListener onClickListen1;
    OnClickListener onClickListen2;

    int mDensity;
    private int screenWidth;  
    private int screenHeight;
    RelativeLayout rl,root,rl2;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_touch_delegate_layout);
		rl = (RelativeLayout) findViewById(R.id.relativeLayout1);  
		rl2 = (RelativeLayout) findViewById(R.id.rl2);  
		initData();
		img0 = (ImageView) findViewById(R.id.imageView0);  
		img1 = (ImageView) findViewById(R.id.imageView1);  
        img2 = (ImageView) findViewById(R.id.imageView2); 
        
//        img0.setOnClickListener(onClickListen0);  
        img1.setOnClickListener(onClickListen1);  
        expandViewTouchDelegate(img1, 50*mDensity, 50*mDensity, 50*mDensity, 50*mDensity);
        rl2.setOnClickListener(onClickListen2); 
//        expandViewTouchDelegate(rl2, 0, 180, 90, 90);
//        img2.setOnClickListener(onClickListen2);  
	}
	
	
	void initData() {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels - 50;
		mDensity = (int)dm.density;
		onClickListen0 = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Click 0",
						Toast.LENGTH_SHORT).show();;
			}
		};
		onClickListen1 = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Click 1",
						Toast.LENGTH_SHORT).show();;
			}
		};
		onClickListen2 = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Click 2",
						Toast.LENGTH_SHORT).show();
			}
		};
	};
  	    
  	    
	public static void expandViewTouchDelegate(final View view, final int top, final int bottom, final int left,
			final int right) {
		((View) view.getParent()).post(new Runnable() {
			@Override
			public void run() {
				Rect bounds = new Rect();
				view.setEnabled(true);
				view.getHitRect(bounds);
				bounds.top -= top;
				bounds.bottom += bottom;
				bounds.left -= left;
				bounds.right += right;
				TouchDelegate touchDelegate = new TouchDelegate(bounds, view);
				if (View.class.isInstance(view.getParent())) {
					((View) view.getParent()).setTouchDelegate(touchDelegate);
				}
			}
		});
	}

}
