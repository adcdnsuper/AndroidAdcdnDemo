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

    private String APP_ID = "30072";

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO: 2019/5/5 修改为自己的appId 测试id = 30072
        TPADMobSDK.instance().initSdk(getApplicationContext(), APP_ID);

    }

}
