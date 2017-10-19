package com.flysnow;

import adhere.AdherentLayoutActivity;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.main.R;
/**
 * 
 */
public class HelloGridView extends Activity {
	
	private ImageView img1;  
    private ImageView img2;  
    private ImageView imgBottom;  
    private ImageView imgTop;  
    private int screenWidth;  
    private int screenHeight;
    RelativeLayout rl,root;
    GridView gridView;
    private static final String IMAGEVIEW_TAG = "icon_bitmap";
    		
    /** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		gridView=(GridView)findViewById(R.id.gridview);
		gridView.setAdapter(new ImageAdapter(this));

		img1 = (ImageView) findViewById(R.id.imageView1);  
        img2 = (ImageView) findViewById(R.id.imageView2);  
        imgBottom = (ImageView) findViewById(R.id.ivbottom);  
        imgTop = (ImageView) findViewById(R.id.ivTop);  
        rl = (RelativeLayout) findViewById(R.id.relativeLayout1);  
        root = (RelativeLayout) findViewById(R.id.root);  
  
        DisplayMetrics dm = getResources().getDisplayMetrics();  
        screenWidth = dm.widthPixels;  
        screenHeight = dm.heightPixels - 50;  
        initListeners();
        img1.setOnTouchListener(movingEventListener);  
        img2.setOnTouchListener(movingEventListener);  
        
        imgBottom.setTag(IMAGEVIEW_TAG);
        imgBottom.setOnLongClickListener(longClickListener);
        
  		
		//单击GridView元素的响应
		gridView.setOnItemClickListener(mOnItemClickListener);
		gridView.setOnTouchListener(mSwipeListener);
		gridView.setClickable(true);
		gridView.setOnLongClickListener(longClickListener);
		
		imgBottom.setOnTouchListener(mSwipeListener);
//		imgBottom.setOnTouchListener(mTouchListener2);
		imgBottom.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(HelloGridView.this, "Clicked", Toast.LENGTH_SHORT).show();
				HelloGridView.this.startActivity(new Intent(HelloGridView.this,AdherentLayoutActivity.class));
			}
		});
        imgTop.setOnDragListener(new myDragEventListener(HelloGridView.this));
//        imgBottom.setOnDragListener(new myDragEventListener(HelloGridView.this));
//        imgTop.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent it = new Intent(HelloGridView.this, TestService.class);
//				Log.d("@@@", "Before Call");
//        		HelloGridView.this.startService(it);
//        		Log.d("@@@", "After Call");
//			}
//		});
        
        
//        ListView lv = new ListView(this);
//        lv.setAdapter(new ImageAdapter(this));
//        root.removeAllViews();
//        root.addView(lv);
	}
	View.OnLongClickListener longClickListener;
	OnSwipeTouchListener mSwipeListener;
	OnTouchListener movingEventListener;
    View.OnTouchListener mTouchListener2;
    OnItemClickListener mOnItemClickListener;
	void initListeners(){
		mOnItemClickListener = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//弹出单击的GridView元素的位置
				Toast.makeText(HelloGridView.this,mThumbIds[position], Toast.LENGTH_SHORT).show();
				view.setAlpha(0.8f);
			}
		};
		
		longClickListener = new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				drag(v);
				return false;
			}
		};
		
		mSwipeListener = new OnSwipeTouchListener(HelloGridView.this) {
	  		  @Override
	  		  public void onSwipeDown() {
	  		    Toast.makeText(HelloGridView.this, "Down", Toast.LENGTH_SHORT).show();
	  		  }
	  		  
	  		  @Override
	  		  public void onSwipeLeft() {
	  		    Toast.makeText(HelloGridView.this, "Left", Toast.LENGTH_SHORT).show();
	  		  }
	  		  
	  		  @Override
	  		  public void onSwipeUp() {
	  		    Toast.makeText(HelloGridView.this, "Up", Toast.LENGTH_SHORT).show();
//	  		    AnimationUtils.applyAlphaAniamtion(imgBottom,true, false, 1000);
	  		    	drag(imgBottom);
	  		  }
	  		  
	  		  @Override
	  		  public void onSwipeRight() {
	  		    Toast.makeText(HelloGridView.this, "Right", Toast.LENGTH_SHORT).show();
	  		  }
	  		};
	  		
	  		mTouchListener2 = new View.OnTouchListener() {
	  	        
	  	        float mDownY;
	  	        private int mSwipeSlop = -1;
	  	        
	  	        @Override
	  	        public boolean onTouch(final View v, MotionEvent event) {
	  	            if (mSwipeSlop < 0) {
	  	                mSwipeSlop = ViewConfiguration.get(HelloGridView.this).
	  	                        getScaledTouchSlop();
	  	            }
	  	            switch (event.getAction()) {
	  	            case MotionEvent.ACTION_DOWN:
	  	                if (mItemPressed) {
	  	                    // Multi-item swipes not handled
//	  	                    return false;
	  	                }
	  	                mItemPressed = true;
	  	                mDownY = event.getY();
	  	                break;
	  	            case MotionEvent.ACTION_CANCEL:
	  	                mItemPressed = false;
	  	                break;
	  	            case MotionEvent.ACTION_MOVE:
	  	                {
	  	                    float y = event.getY();
	  	                    float deltaY = mDownY-y;
//	  	                    float deltaXAbs = Math.abs(deltaX);
	  	                    if (!mSwiping) {
	  	                        if (deltaY > mSwipeSlop) {
	  	                            mSwiping = true;
//	  	                            mListView.requestDisallowInterceptTouchEvent(true);
	  	                            gridView.requestDisallowInterceptTouchEvent(true);
	  	                        }
	  	                    }
	  	                }
	  	                break;
	  	            case MotionEvent.ACTION_UP:
	  	                {
	  	                    if (mSwiping) {
	  	                        float y = event.getY();
	  	                        float deltaY = mDownY - y;

	  	                        final boolean remove;
	  	                        if (deltaY > v.getWidth() / 4) {
	  	                        	Toast.makeText(HelloGridView.this, "Up!!!!", Toast.LENGTH_SHORT).show();
	  	                        } 

	  	                    }
	  	                }
	  	                mItemPressed = false;
	  	                break;
	  	            default: 
//	  	                return false;
	  	            }
	  	            return true;
	  	        }
	  	    };
	  		
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
	  	  
//	  	                if (bottom > screenHeight) {  
//	  	                    bottom = screenHeight;  
//	  	                    top = bottom - v.getHeight();  
//	  	                }  
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
	}
	
	
	
	
	void drag(View v){
		 // Create a new ClipData.
	    // This is done in two steps to provide clarity. The convenience method
	    // ClipData.newPlainText() can create a plain text ClipData in one step.

	    // Create a new ClipData.Item from the ImageView object's tag
	    ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());

	    // Create a new ClipData using the tag as a label, the plain text MIME type, and
	    // the already-created item. This will create a new ClipDescription object within the
	    // ClipData, and set its MIME type entry to "text/plain"
	    ClipData dragData = new ClipData((CharSequence) v.getTag(),new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);

	    // Instantiates the drag shadow builder.
	    View.DragShadowBuilder myShadow = new MyDragShadowBuilder(v);
//	    View.DragShadowBuilder myShadow =new View.DragShadowBuilder(v);

	    // Starts the drag

	            v.startDrag(dragData,  // the data to be dragged
	                        myShadow,  // the drag shadow builder
	                        null,      // no need to use local data
	                        0          // flags (not currently used, set to 0)
	            );
	}

	private static class MyDragShadowBuilder extends View.DragShadowBuilder {

	    private static Drawable shadow;

	        public MyDragShadowBuilder(View v) {

	            super(v);

	            shadow = new ColorDrawable(Color.LTGRAY);
	        }

	        // Defines a callback that sends the drag shadow dimensions and touch point back to the
	        // system.
	        @Override
	        public void onProvideShadowMetrics (Point size, Point touch){
	            // Defines local variables
	            int width;
				int height;

	            // Sets the width of the shadow to half the width of the original View
	            width = getView().getWidth() / 2;

	            // Sets the height of the shadow to half the height of the original View
	            height = getView().getHeight() / 2;

	            // The drag shadow is a ColorDrawable. This sets its dimensions to be the same as the
	            // Canvas that the system will provide. As a result, the drag shadow will fill the
	            // Canvas.
	            shadow.setBounds(0, 0, width, height);

	            // Sets the size parameter's width and height values. These get back to the system
	            // through the size parameter.
	            size.set(width, height);

	            // Sets the touch point's position to be in the middle of the drag shadow
	            touch.set(width / 2, height / 2);
	        }

	        // Defines a callback that draws the drag shadow in a Canvas that the system constructs
	        // from the dimensions passed in onProvideShadowMetrics().
	        @Override
	        public void onDrawShadow(Canvas canvas) {

	            // Draws the ColorDrawable in the Canvas passed in from the system.
	            shadow.draw(canvas);
	        }
	    }
	
	
	private class ImageAdapter extends BaseAdapter{
		private Context mContext;

		public ImageAdapter(Context context) {
			this.mContext=context;
		}

		@Override
		public int getCount() {
			return mThumbIds.length;
		}

		@Override
		public Object getItem(int position) {
			return mThumbIds[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//定义一个ImageView,显示在GridView里
			ImageView imageView;
			if(convertView==null){
				imageView=new ImageView(mContext);
				imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            imageView.setPadding(8, 8, 8, 8);
//	            imageView.setOnTouchListener(mSwipeListener);
//	            imageView.setOnTouchListener(mTouchListener2);
//	            imageView.setClickable(true);
//	            imageView.setOnClickListener(new View.OnClickListener() {
//					
//					@Override
//					public void onClick(View v) {
//						// TODO Auto-generated method stub
//						Toast.makeText(HelloGridView.this, "Clicked!!", Toast.LENGTH_SHORT).show();
//					}
//				});
//	            imageView.setOnClickListener(null);
			}else{
				imageView = (ImageView) convertView;
			}
			imageView.setImageResource(mThumbIds[position]);
			
			return imageView;
		}
		

		
	}
	//展示图片
	private Integer[] mThumbIds = {
            R.drawable.a, R.drawable.b,
            R.drawable.c
//            R.drawable.sample_2, R.drawable.sample_3,
//            R.drawable.sample_4, R.drawable.sample_5,
//            R.drawable.sample_6, R.drawable.sample_7,
//            R.drawable.sample_0, R.drawable.sample_1,
//            R.drawable.sample_2, R.drawable.sample_3,
//            R.drawable.sample_4, R.drawable.sample_5,
//            R.drawable.sample_6, R.drawable.sample_7,
//            R.drawable.sample_0, R.drawable.sample_1,
//            R.drawable.sample_2, R.drawable.sample_3,
//            R.drawable.sample_4, R.drawable.sample_5,
//            R.drawable.sample_6, R.drawable.sample_7
    };
	
	boolean mItemPressed,mSwiping;

 
}