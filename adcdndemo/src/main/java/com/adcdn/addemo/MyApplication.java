package com.adcdn.addemo;

import android.app.Application;

import com.adcdn.adsdk.configsdk.change.AdcdnLogTool;
import com.adcdn.adsdk.configsdk.common.AdcdnMobSDK;


/**
 * @author : xnn
 * @date : 2019/5/5
 * @description : replace your description
 */

public class MyApplication extends Application {

    private String APP_ID = "600009";

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO: 2019/5/5 修改为自己的appId
        AdcdnMobSDK.instance().initSdk(getApplicationContext(), APP_ID);
        AdcdnLogTool.isNeedShow = true;//日志开关，测试的时候可以打开

    }

}
