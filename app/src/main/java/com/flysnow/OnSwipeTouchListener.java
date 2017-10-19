package com.flysnow;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/*
Usage:
  myView.setOnTouchListener(new OnSwipeTouchListener(this) {
    @Override
    public void onSwipeDown() {
      Toast.makeText(MainActivity.this, "Down", Toast.LENGTH_SHORT).show();
    }
  }
*/
public class OnSwipeTouchListener implements OnTouchListener {

    private GestureDetector gestureDetector;
    
    public OnSwipeTouchListener(Context c) {
      gestureDetector = new GestureDetector(c, new GestureListener());
    }

    public boolean onTouch(final View view, final MotionEvent motionEvent) {
//        return gestureDetector.onTouchEvent(motionEvent);
    	boolean result ;
    	view.getParent().requestDisallowInterceptTouchEvent(true);
        if(gestureDetector.onTouchEvent(motionEvent)){
        	result = true;
        }  else{
        	result = false;  
        }
        Log.d("Swipe", "onTouch :result = " + result+ ",action = " + motionEvent );
//        return true;
        return result;
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 50;//100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 50;//100;

//        @Override
//        public boolean onDown(MotionEvent e) {
//            return true;
//        }
        
        // Determines the fling velocity and then fires the appropriate swipe event accordingly
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            Log.d("Swipe", "onFling" );
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                    }
                } else {
                    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeDown();
                        } else {
                            onSwipeUp();
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
//            return result;
            return true;
        }
    }
 
    public void onSwipeRight() {
    }

    public void onSwipeLeft() {
    }

    public void onSwipeUp() {
    }

    public void onSwipeDown() {
    }
}