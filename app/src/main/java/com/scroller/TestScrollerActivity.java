package com.scroller;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.main.R;

public class TestScrollerActivity extends Activity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testscroller_activity);
        LayoutInflater.from(this);
    }
}
