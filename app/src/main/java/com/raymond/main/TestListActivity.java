package com.raymond.main;



import java.util.ArrayList;
import java.util.List;

import com.main.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TestListActivity extends Activity {
	ListView mListView;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_listview_activity_layout);

		mListView = (ListView)findViewById(R.id.mylv);
		List<String> data = new ArrayList<String>();
		for(int i = 1;i<900;i++){
			data.add("Number"+String.valueOf(i));
		}
		MyAdapter adapter = new MyAdapter(data);
		mListView.setAdapter(adapter);
		mListView.setFriction(ViewConfiguration.getScrollFriction() / 5);
		
	}
	
	
	void initData(){
          

  	    };  
  	    
  	    
	 class MyAdapter extends BaseAdapter{
		List<String> mDataList;
		
		public MyAdapter(List<String> data) {
			mDataList = data;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mDataList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mDataList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			if(convertView == null){
				view = getLayoutInflater().inflate(R.layout.list_item_layout, null);
				Holder holder = new Holder();
				holder.mBtn = (Button)view.findViewById(R.id.mytv);
				view.setTag(holder);
			}
			Holder h = (Holder)view.getTag();
			h.mBtn.setText((String)getItem(position));
			return view;
		}
		
		class Holder{
			public Button mBtn;
		}
		
	}

}
