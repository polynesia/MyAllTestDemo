package com.compressTest;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.main.R;

public class CompressTestActivity extends Activity  implements OnClickListener{
	Button butJpg;
	Button butPng;
	Button butChange;
	
	static final String ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();
	String src = ROOT + File.separator + "DCIM"+ File.separator + "Origin.png";
	String dstjpg = ROOT + File.separator + "DCIM"+ File.separator + "Dst.jpg";
	String dstpng = ROOT + File.separator + "DCIM"+ File.separator + "Dst.png";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.compress_test);
		butJpg = (Button)findViewById(R.id.butjpg);
		butPng = (Button)findViewById(R.id.butpng);
		butChange = (Button)findViewById(R.id.change);
		butJpg.setOnClickListener(this);
		butPng.setOnClickListener(this);
		butChange.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch(id){
		case R.id.butjpg:{
			compressJpg();
			break;
		}
		case R.id.butpng:{
			compressPng();
			break;
		}
		case R.id.change:{
			RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams)butChange.getLayoutParams();
			lp.leftMargin = 40*(int)(getResources().getDisplayMetrics().density);
//			butChange.setLayoutParams(lp);
			butChange.requestLayout();
			break;
		}
		default:
			break;
		}
		
	}
	
	void compressJpg(){
		compress(src, dstjpg, 70, CompressFormat.JPEG);
	}
	
	void compressPng(){
		compress(src, dstpng, 70, CompressFormat.PNG);
	}
	
	
	void compress(String src,String dst,int quality,CompressFormat format){
		new File(dst).delete();
		File d = new File(dst);
		if(!d.exists()){
			try {
				d.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Bitmap bm = BitmapFactory.decodeFile(src);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(d);
			//CompressFormat.JPEG
			bm.compress(format, quality, fos);
			
			fos.flush();
			FileDescriptor fd = fos.getFD();
			if (fd != null && fd.valid()) {
				fd.sync();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fos = null;
			}
			bm.recycle();
		}
		
	}
}
