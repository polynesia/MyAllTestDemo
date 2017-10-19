package com.raymond.main;

import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by raymondhu on 2017/10/19.
 */

public class SoundPoolHelper {
    SoundPool soundPool;
    List<String> filePathList;
    HashMap<String,Integer> fileSoundIDMap ;
    HashMap<String,Integer> playingStreamMap ;
    HashSet<Integer> loadedSoundSet;
    public SoundPoolHelper(List<String> fileList) {
        this.filePathList = fileList;
        fileSoundIDMap = new HashMap<String, Integer>();
        playingStreamMap = new HashMap<String, Integer>();
        loadedSoundSet = new HashSet<Integer>();
    }

    public void loadMusic(){
        if(filePathList ==null || filePathList.size()<1){
            return;
        }
        if(soundPool == null){
            soundPool = new SoundPool(filePathList.size(), AudioManager.STREAM_MUSIC,0);
            soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    if(status == 0){
                        loadedSoundSet.add(sampleId);
                    }
                }
            });
        }
        for(String path: filePathList){
            int soundID = soundPool.load(path,1);
            fileSoundIDMap.put(path,soundID);
        }
    }

    public void playMusic(String path,boolean loop){
        Integer soundID = fileSoundIDMap.get(path);
        if(soundID == null){
            return;
        }
        if(!loadedSoundSet.contains(soundID)){
            return;
        }
        if(soundPool!=null){
            int streamID = soundPool.play(soundID, 1, 1, 1,loop?-1:0, 1.0f);
            playingStreamMap.put(path,streamID);
        }
    }
    public void stopMusic(String path){
        Integer soundID = fileSoundIDMap.get(path);
        if(soundID == null){
            return;
        }
        if(!loadedSoundSet.contains(soundID)){
            return;
        }
        Integer steamID = playingStreamMap.get(path);
        if(steamID == null){
            return;
        }
        if(soundPool!=null){
            soundPool.stop(steamID);
        }
    }
    public void releaseMusic(){
        if(soundPool!=null){
            soundPool.release();
            soundPool = null;
            fileSoundIDMap.clear();
            playingStreamMap.clear();
        }
    }
}
