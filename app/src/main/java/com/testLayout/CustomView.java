package com.testLayout;

import com.main.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public class CustomView extends RelativeLayout {

	public CustomView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initUI();
	}

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initUI();
	}

	public CustomView(Context context) {
		super(context);
		initUI();
	}

	void initUI(){
		LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.customview_content_layout, this, true);
	}
}
