package adhere;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class DragLayout extends RelativeLayout {

	public DragLayout(Context context) {
		super(context);
		init();
	}

	public DragLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public DragLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	void init(){
		child = this.getChildAt(0);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
	}

	int lastX, lastY;  
	View child;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		  
          switch (event.getAction()) {  
          case MotionEvent.ACTION_DOWN:  
              lastX = (int) event.getRawX();  
              lastY = (int) event.getRawY();  
              break;  
          case MotionEvent.ACTION_MOVE:  
              int dx = (int) event.getRawX() - lastX;  
              int dy = (int) event.getRawY() - lastY;  

              int left = child.getLeft() + dx;  
              int top = child.getTop() + dy;  
              int right = child.getRight() + dx;  
              int bottom = child.getBottom() + dy;  
              // 设置不能出界  
              if (left < 0) {  
                  left = 0;  
                  right = left + child.getWidth();  
              }  

              if (right > this.getWidth()) {  
                  right = this.getWidth();  
                  left = right - child.getWidth();  
              }  

              if (top < 0) {  
                  top = 0;  
                  bottom = top + child.getHeight();  
              }  

              if (bottom > this.getHeight()) {  
                  bottom = this.getHeight();  
                  top = bottom - child.getHeight();  
              }  
//              if (bottom > rl.getHeight()) {  
//                  bottom = rl.getHeight();  
//                  top = bottom - v.getHeight();  
//              }  

              child.layout(left, top, right, bottom);  

              lastX = (int) event.getRawX();  
              lastY = (int) event.getRawY();  

              break;  
          case MotionEvent.ACTION_UP:  
              break;  
          }  
          return true;  
      
//		return false;
//		return super.onTouchEvent(event);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
	}

	
}
