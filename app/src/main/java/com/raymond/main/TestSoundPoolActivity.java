package com.raymond.main;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.main.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestSoundPoolActivity extends Activity {

    Button but1,but2,but3;
    SoundPool pool;
    Map<String, Integer> poolMap;
    static final String filePath1 = "/sdcard/AllTest/raw/newblogtoast.wav";
    static final String filePath2 = "/sdcard/AllTest/raw/notificationsound.wav";
    static final String filePath3 = "/sdcard/AllTest/raw/qqmsg.mp3";
    SoundPoolHelper soundPoolHelper;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id){
                case R.id.button:{
                    if (pool != null) {
                        pool.play(poolMap.get("newqqmsg"), 1.0f, 1.0f, 0, 0, 1.0f);
                    }
                    soundPoolHelper.playMusic(filePath1,false);
                    break;
                }
                case R.id.button2:{
                    if (pool != null) {
                        pool.play(poolMap.get("newweibontf"), 1.0f, 1.0f, 0, 0,
                                1.0f);
                    }
                    soundPoolHelper.playMusic(filePath2,true);
                    break;
                }
                case R.id.button3:{
                    if (pool != null) {
                        pool.play(poolMap.get("newweibotoast"), 1.0f, 1.0f, 0, 0,
                                1.0f);
                    }
                    soundPoolHelper.stopMusic(filePath2);
                    break;
                }
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sound_pool);
        but1 = (Button)findViewById(R.id.button);
        but2 = (Button)findViewById(R.id.button2);
        but3 = (Button)findViewById(R.id.button3);

        but1.setOnClickListener(onClickListener);
        but2.setOnClickListener(onClickListener);
        but3.setOnClickListener(onClickListener);


//        poolMap = new HashMap<String, Integer>();
//        pool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);

//        poolMap.put("newqqmsg", pool.load(this, R.raw.qqmsg, 1));
//        poolMap.put("newweibontf", pool.load(this, R.raw.notificationsound, 1));
//        poolMap.put("newweibotoast", pool.load(this, R.raw.newblogtoast, 1));

//        poolMap.put("newqqmsg", pool.load(filePath1, 1));
//        poolMap.put("newweibontf", pool.load(filePath2, 1));
//        poolMap.put("newweibotoast", pool.load(filePath3, 1));
//        pool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
//
//            @Override
//            public void onLoadComplete(SoundPool soundPool, int sampleId,
//                                       int status) {
//                // 每次装载完成均会回调
//                Log.i("main", "音频池资源id为：" + sampleId + "的资源装载完成");
//                // 当前装载完成ID为map的最大值，即为最后一次装载完成
//                if (sampleId == poolMap.size()) {
//                    Toast.makeText(TestSoundPoolActivity.this, "加载声音池完成!",
//                            Toast.LENGTH_SHORT).show();
//                    // 进入应用播放四次声音
//                    pool.play(poolMap.get("newweibotoast"), 1.0f, 1.0f, 0, 3,
//                            1.0f);
//                }
//            }
//        });
        ArrayList<String> pathList = new ArrayList<String>();
        pathList.add(filePath1);
        pathList.add(filePath2);
        pathList.add(filePath3);
        soundPoolHelper = new SoundPoolHelper(pathList);
        soundPoolHelper.loadMusic();
    }

    @Override
    protected void onDestroy() {
        // 销毁的时候释放SoundPool资源
        if (pool != null) {
            pool.release();
            pool = null;
        }
        super.onDestroy();
    }
}
