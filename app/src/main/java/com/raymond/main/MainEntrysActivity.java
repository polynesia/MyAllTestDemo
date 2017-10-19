package com.raymond.main;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.compressTest.CompressTestActivity;
import com.flysnow.HelloGridView;
import com.main.R;
import com.move.SimpleMoveActivity;
import com.scroller.TestScrollerActivity;
import com.testAIDL.TestAIDLActivity;
import com.testLayout.testLayoutActivity;
import com.tintdrawable.TestCompoundDrawableActivity;

public class MainEntrysActivity extends Activity implements OnItemClickListener{
	
	

	private final static String ENTRY_A = "HelloGridView";
	private final static String ENTRY_B = "CompressTest";
	private final static String ENTRY_C = "SimpleMoveActivity";
	private final static String ENTRY_D = "testAIDL";
	private final static String ENTRY_E = "testScroller";
	private final static String ENTRY_F = "TestTouchDelegate";
	private final static String ENTRY_G = "TestCompoundDrawableActivity";
	private final static String ENTRY_H = "TestListActivity";
	private final static String ENTRY_I = "testLayoutActivity";
	private final static String ENTRY_J = "TestSoftInputModeActivity";
	private final static String ENTRY_K = "TestSimpleDateFormatActivity";
	private final static String ENTRY_L = "TestSoundPoolActivity";

	private static ArrayList<String> sEntryName = new ArrayList<String>();
	static {
		sEntryName.add(ENTRY_A);
		sEntryName.add(ENTRY_B);
		sEntryName.add(ENTRY_C);
		sEntryName.add(ENTRY_D);
		sEntryName.add(ENTRY_E);
		sEntryName.add(ENTRY_F);
		sEntryName.add(ENTRY_G);
		sEntryName.add(ENTRY_H);
		sEntryName.add(ENTRY_I);
		sEntryName.add(ENTRY_J);
		sEntryName.add(ENTRY_K);
		sEntryName.add(ENTRY_L);
	}

	private class EntryAdapter extends BaseAdapter {
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView item;
			if (convertView == null) {
				item = new TextView(MainEntrysActivity.this);
				item.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
				item.setPadding(10, 10, 10, 10);
				//item.setGravity(Gravity.CENTER);
				convertView = item;
			} else {
				item = (TextView) convertView;
			}

			item.setText(sEntryName.get(position));

			return convertView;
		}

		@Override
		public int getCount() {
			return sEntryName.size();
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public Object getItem(int position) {
			return sEntryName.get(position);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.entry_layout);
		ListView entrys = (ListView) findViewById(R.id.entry_list);
		entrys.setAdapter(new EntryAdapter());
		entrys.setOnItemClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		int pid = android.os.Process.myPid();
		android.os.Process.killProcess(pid);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		String entry = sEntryName.get(position);
		if (ENTRY_A.equals(entry)) {
			Intent intent = new Intent(this, HelloGridView.class);
			startActivity(intent);
		} else if (ENTRY_B.equals(entry)) {
			Intent intent = new Intent(this, CompressTestActivity.class);
			startActivity(intent);
		} else if (ENTRY_C.equals(entry)) {
			Intent intent = new Intent(this, SimpleMoveActivity.class);
			startActivity(intent);
		} else if (ENTRY_D.equals(entry)) {
			Intent intent = new Intent(this, TestAIDLActivity.class);
//			intent.putExtra(FlowPlusPanel.SELECTED_ITEM, AIOPanelUtiles.EXT_PANEL_CAMERA);
			startActivity(intent);
		} else if (ENTRY_E.equals(entry)) {
			Intent intent = new Intent(this, TestScrollerActivity.class);

			startActivity(intent);
		} else if (ENTRY_F.equals(entry)) {
			Intent intent = new Intent(this, TestTouchDelegateActivity.class);

			startActivity(intent);
		} else if(ENTRY_G.equals(entry)){
			Intent intent = new Intent(this, TestCompoundDrawableActivity.class);
			startActivity(intent);
		}else if(ENTRY_H.equals(entry)){
			Intent intent = new Intent(this, TestListActivity.class);
			startActivity(intent);
		}
		else if(ENTRY_I.equals(entry)){
			Intent intent = new Intent(this, testLayoutActivity.class);
			startActivity(intent);
		}else if(ENTRY_J.equals(entry)){
			Intent intent = new Intent(this, TestSoftInputModeActivity.class);
			startActivity(intent);
		}else if(ENTRY_K.equals(entry)){
			Intent intent = new Intent(this, TestSimpleDateFormatActivity.class);
			startActivity(intent);
		}else if(ENTRY_L.equals(entry)){
			Intent intent = new Intent(this, TestSoundPoolActivity.class);
			startActivity(intent);
		}
		
		
	}


}
