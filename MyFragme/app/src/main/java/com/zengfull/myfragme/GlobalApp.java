package com.zengfull.myfragme;

import android.app.Application;

import com.zengfull.myfragme.api.GlobalHttpApi;
import com.zengfull.myfragme.util.SharePreferenceUtil;

/**
 * Created by asus on 2015/12/21.
 */
public class GlobalApp extends Application {

    private static final String APP_NAME = "zengfull";
    private static GlobalApp instance;
    private static SharePreferenceUtil spUtil;

    public static GlobalApp getInstance(){
        return instance;
    }

    public SharePreferenceUtil getSpUtil(){
        if(spUtil == null){
            synchronized (SharePreferenceUtil.class){
                if(spUtil == null){
                    spUtil = new SharePreferenceUtil(this,APP_NAME);
                }
            }
        }
        return spUtil;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
    }
}
