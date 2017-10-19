package com.raymond.main;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import com.main.R;

import android.app.Activity;
import android.net.ParseException;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TestSimpleDateFormatActivity extends Activity {
	private TextView tv1;
	private TextView tv2;

	private int screenWidth;
	private int screenHeight;
	RelativeLayout rl, root;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_simpledateformate_layout);

		tv1 = (TextView) findViewById(R.id.mytv1);
		tv2 = (TextView) findViewById(R.id.mytv2);
		initData();
	}

	final String START_DATE = "2016-04-06 11:55:00";
	final String END_DATE = "2016-07-06 13:00:00";
	void initData() {
		long timeStart = data2Millis(START_DATE);
		tv1.setText(START_DATE+"="+timeStart);
		long timeEnd = data2Millis(END_DATE);
		tv2.setText(END_DATE+"="+timeEnd);
	};

	static SimpleDateFormat sSdf = null;

	public static long data2Millis(String date) {
		if (sSdf == null) {
			sSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
					Locale.getDefault());
			sSdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		}
		long millionSeconds = -1;
		try {
			millionSeconds = sSdf.parse(date).getTime();
		} catch (java.text.ParseException e) {
			Log.d("OlympicUtil",""+ e);
		} 
		return millionSeconds;
	}

}
