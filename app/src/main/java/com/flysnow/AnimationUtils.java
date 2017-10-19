package com.flysnow;

import java.util.ArrayList;
import java.util.Arrays;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

public class AnimationUtils {
	public static void applyAlphaAniamtion(View view,boolean fillBefore,boolean fillAfter,int duration){
		AnimationSet as=new AnimationSet(true);
		AlphaAnimation  alpha = new AlphaAnimation(1.0f,0f);
		TranslateAnimation trans = new TranslateAnimation(0f, 0f, 0f, -2*view.getHeight());
		as.addAnimation(alpha);
		as.addAnimation(trans);
		as.setDuration(duration);
		as.setFillBefore(fillBefore);
		as.setFillAfter(fillAfter);
//		alpha.setDuration(duration);
//		alpha.setFillAfter(fill);
//		view.startAnimation(alpha);
		view.startAnimation(as);
//		Arrays.asList(array)
		String[] a = new String[]{"ss"};
		ArrayList<String> al = (ArrayList<String>) Arrays.asList(a);
	}
}
