package com.tintdrawable;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BufferedHeader;
import org.apache.http.protocol.HttpContext;

import com.main.R;
import com.main.R.drawable;
import com.main.R.id;
import com.main.R.layout;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TestCompoundDrawableActivity extends Activity implements View.OnClickListener {
	TextView mTextView;
    ImageView mImageView;
	OnTouchListener mSelectEffectListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				if (v instanceof ImageView) {
					((ImageView) v).setColorFilter(0x66000000);
				}
				break;
			case MotionEvent.ACTION_UP:
				if (v instanceof ImageView) {
					((ImageView) v).setColorFilter(0x00000000);
				}
				break;

			}
			return false;
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_compound_drawable_layout);
		mTextView = (TextView) findViewById(R.id.mytv);  
		mImageView = (ImageView) findViewById(R.id.myiv);  
		mTextView.setOnClickListener(this);
		mImageView.setOnClickListener(this);
//		int color = getResources().getColor(R.color.forty_transparent_black);//1711276032
//		mImageView.setBackgroundResource(R.drawable.qq_dating_company_pic_selector);
//		mImageView.setBackgroundResource(R.drawable.qq_freshnews_not_praise_selector);
//		mImageView.setImageDrawable(getResources().getDrawable(R.drawable.kobe));
//		mImageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
//		mImageView.setImageDrawable(getResources().getDrawable(R.drawable.qq_freshnews_not_praise_selector));

//		testTintImage();
		testDynamicSelector();
//		testTouchEffect();
	}
	
//	public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {  
//	    final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
//	    DrawableCompat.setTintList(wrappedDrawable, colors);
//	    return wrappedDrawable;
//	}
	
	void initData(){ 	 
  	    }
	void testTintImage(){
		mImageView.setImageResource(R.drawable.kobe);
	}
	void testTouchEffect(){//work
		mImageView.setImageResource(R.drawable.kobe);
		mImageView.setOnTouchListener(mSelectEffectListener);
	}
	void testDynamicSelector(){
		Drawable slectorDrawable = createDrawableSelector(this, getResources().getDrawable(R.drawable.kobe));
//		Drawable slectorDrawable = createDrawableSelector(this, R.drawable.kobe);
		mImageView.setImageDrawable(slectorDrawable);
//		mImageView.setBackgroundDrawable(slectorDrawable);
	}
	
	String baseURL = "http://p.qq.com/s/RyXL3wBR?_wv=2281701505&rdm=92ebd1";
	String baseURL2 = "http://www.baidu.com";
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		switch(id){
		case R.id.mytv:{
//			testPost();
//			try {
//				redirectPath(baseURL,null);
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			}
			getResponseRedirectLocation(baseURL2);
//			Toast.makeText(this, "click text", Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.myiv:{
			Toast.makeText(this, "click img", Toast.LENGTH_SHORT).show();
			
			Drawable drawable = mImageView.getDrawable();
			mImageView.invalidate();
			break;
		}
		
		}
	};  
	public static Bitmap convertDrawable2BitmapByCanvas(Drawable drawable) {
		Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(),
		drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888: Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);

		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
		drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;
		}
  	public static Drawable createDrawableSelector(Context ctx,int resid ){
  		Drawable normaldrawable =  ctx.getResources().getDrawable(resid);

  		StateListDrawable stateList = new StateListDrawable();
//  		Drawable selectedDrawable = normaldrawable.getConstantState().newDrawable();
  		Drawable selectedDrawable = normaldrawable.mutate();

  		selectedDrawable.setColorFilter(0x66000000, PorterDuff.Mode.SRC_ATOP);
		Drawable d2 =  ctx.getResources().getDrawable(resid);
  		stateList.addState(new int[]{android.R.attr.state_pressed}, selectedDrawable);
  		stateList.addState(new int[]{android.R.attr.state_selected}, selectedDrawable);
  		stateList.addState(new int[]{}, d2);
  		return stateList;
  	}
	
  	public static Drawable createDrawableSelector(Context ctx,Drawable normaldrawable){
  		StateListDrawable stateList = new StateListDrawable();
  		Drawable d2 = normaldrawable.getConstantState().newDrawable();
  		Drawable selectedDrawable = normaldrawable.mutate();

//  		Drawable selectedDrawable = ctx.getResources().getDrawable(R.drawable.icon);
  		
//  		Bitmap normalBitmap = convertDrawable2BitmapByCanvas(normaldrawable);
//  		Bitmap selectBitmap = Bitmap.createBitmap(normaldrawable.getIntrinsicWidth(), 
//  				normaldrawable.getIntrinsicHeight(), Config.ARGB_8888);
//  		Canvas c = new Canvas(selectBitmap);
//  	    Paint p = new Paint();
//  	    c.drawBitmap(normalBitmap, 0, 0, p);
//  	    Drawable selectedDrawable = new BitmapDrawable(selectBitmap);
  	    
  		selectedDrawable.setColorFilter(0x66000000, PorterDuff.Mode.SRC_ATOP);
  		stateList.addState(new int[]{android.R.attr.state_pressed}, selectedDrawable);
  		stateList.addState(new int[]{android.R.attr.state_selected}, selectedDrawable);
  		stateList.addState(new int[]{}, d2);
  		return stateList;
  	}
	
  	
  	
  	public String redirectPath(final String str)
			throws MalformedURLException {
		URL url = null;
		String realURL = null;
		HttpURLConnection conn = null;
		try {
			url = new URL(str);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setInstanceFollowRedirects(true);
			conn.getResponseCode();// trigger server redirect
			realURL = conn.getURL().toString();
			Log.d("", str + "\r\n" + "redirect to \r\n" + realURL);
		} catch (MalformedURLException e) {
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return realURL;
	}
  	
  	private String getResponseRedirectLocation(String requestUrl) {
        String location = null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection mConnection = (HttpURLConnection) url
                    .openConnection();
            mConnection.setInstanceFollowRedirects(false);
            mConnection.setRequestMethod("GET");
            mConnection.setConnectTimeout(3*1000);
            mConnection.connect();
            location = mConnection.getHeaderField("location");
        } catch (MalformedURLException e) {
        	
        } catch (IOException e) {

        }
        return location;
    }
  	void testPost(){



        try
        {
//        	String baseURL = "http://p.qq.com/s/RyXL3wBR?_wv=2281701505&rdm=92ebd1";
        	String baseURL = "http://www.baidu.com";
//            HttpEntity requestHttpEntity = new UrlEncodedFormEntity(
//                    pairList);
            // URL使用基本URL即可，其中不需要加参数
            HttpPost httpPost = new HttpPost(baseURL);
            // 将请求体内容加入请求中
//            httpPost.setEntity(requestHttpEntity);
            // 需要客户端对象来发送请求
            HttpClient httpClient = new DefaultHttpClient();
            
            


            
            RedirectHandler customRedirectHandler = new CustomRedirectHandler();
            //...
            ((AbstractHttpClient) httpClient).setRedirectHandler(customRedirectHandler);
            // 发送请求
            HttpResponse response = httpClient.execute(httpPost);
            // 显示响应
            showResponseResult(response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
  	}
    class CustomRedirectHandler extends DefaultRedirectHandler {
        public URI getLocationURI(HttpResponse response, HttpContext context) {
        	Log.d("TAG", "response = "+ response);
        	
        	Header header = ((BasicHttpResponse )response).getFirstHeader("Location");
        	Log.d("TAG", "header = "+ header);
        	String longURL = ((BufferedHeader)header).getValue();
        	Log.d("TAG", "longURL = "+ longURL);
        	URI result = null;
			try {
				result = new URI(longURL);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
        }
    }
    private void showResponseResult(HttpResponse response)
    {
        if (null == response)
        {
            return;
        }

        HttpEntity httpEntity = response.getEntity();
        try
        {
            InputStream inputStream = httpEntity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    inputStream));
            String result = "";
            String line = "";
            while (null != (line = reader.readLine()))
            {
                result += line;

            }

            System.out.println(result);
//            mResult.setText("Response Content from server: " + result);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
