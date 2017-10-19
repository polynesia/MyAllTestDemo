package adhere;

import com.main.R;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * @author apexhuang
 * @version 1.0
 * @date 2015/9/6
 */
public class AdherentLayoutActivity extends Activity{

    private ImageView mView;
    private ImageView mView2;
    private TextView mTextView;
   
    private AdherentLayout mAdherentLayout;
    private AdherentLayout mAdherentLayout2;
    private AdherentLayout mAdherentLayout3;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adherentlayout);
        
        mView = (ImageView) findViewById(R.id.View);
        mView2 = (ImageView) findViewById(R.id.View2);
        mTextView = (TextView) findViewById(R.id.textView);
        
       
        mAdherentLayout = (AdherentLayout) findViewById(R.id.adherentView);
        mAdherentLayout.setOnAdherentListener(new AdherentLayout.OnAdherentListener() {
            @Override
            public void onDismiss() {
                mView.setImageResource(R.drawable.tip_anim);
                AnimationDrawable animationDrawable = ((AnimationDrawable) mView.getDrawable());
                animationDrawable.stop();
                animationDrawable.start();

                int duration = 0;
                for (int i = 0; i < animationDrawable.getNumberOfFrames(); i++) {
                    duration += animationDrawable.getDuration(i);
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        mAdherentLayout.reset();
                        mView.setImageResource(R.drawable.skin_tips_new);
                    }
                }, duration);

            }
        });

        mAdherentLayout2 = (AdherentLayout) findViewById(R.id.adherentView2);
        mAdherentLayout2.setColor(Color.rgb(48,160,208));
        mAdherentLayout2.setMaxAdherentLength(350);
        mAdherentLayout2.setOnAdherentListener(new AdherentLayout.OnAdherentListener() {
            @Override
            public void onDismiss() {
                mView2.setBackgroundColor(Color.argb(0, 255, 255, 255));
                mTextView.setVisibility(View.INVISIBLE);
                mView2.setImageResource(R.drawable.tip_anim);
                AnimationDrawable animationDrawable = ((AnimationDrawable) mView2.getDrawable());
                animationDrawable.stop();
                animationDrawable.start();

                int duration = 0;
                for (int i = 0; i < animationDrawable.getNumberOfFrames(); i++) {
                    duration += animationDrawable.getDuration(i);
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        mAdherentLayout2.reset();
                        mTextView.setVisibility(View.VISIBLE);
                        mView2.setBackgroundColor(Color.rgb(48, 160, 208));
                    }
                }, duration);

            }
        });

        mAdherentLayout3 = (AdherentLayout) findViewById(R.id.adherentView3);
        mAdherentLayout3.setDismissedEnable(false);
        mAdherentLayout3.setOnAdherentListener(new AdherentLayout.OnAdherentListener() {
            @Override
            public void onDismiss() {
                mAdherentLayout3.reset();
            }
        });









    }
}
