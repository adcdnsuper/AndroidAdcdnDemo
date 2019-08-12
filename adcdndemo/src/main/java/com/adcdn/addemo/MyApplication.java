package com.adcdn.addemo;

import android.app.Application;
import android.content.Context;

import com.yunxia.adsdk.tpadmobsdk.common.TPADMobSDK;


/**
 * @author : xnn
 * @date : 2019/5/5
 * @description : replace your description
 */

public class MyApplication extends Application {

//    private String APP_ID = "30072";
//    private String APP_ID = "1030008";
    private String APP_ID = "1030007";
//    private String APP_ID = "1030010";

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO: 2019/5/5 修改为自己的appId
        TPADMobSDK.instance().initSdk(getApplicationContext(), APP_ID);

    }

}
