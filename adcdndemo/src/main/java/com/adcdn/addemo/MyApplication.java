package com.adcdn.addemo;

import android.app.Application;
import android.content.Context;

import com.yunxia.adsdk.tpadmobsdk.change.AdcdnLogTool;
import com.yunxia.adsdk.tpadmobsdk.common.AdcdnMobSDK;


/**
 * @author : xnn
 * @date : 2019/5/5
 * @description : replace your description
 */

public class MyApplication extends Application {

    //    private String APP_ID = "30072";
    private String APP_ID = "600009";
//    private String APP_ID = "1030076";

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO: 2019/5/5 修改为自己的appId
        AdcdnMobSDK.instance().initSdk(getApplicationContext(), APP_ID);
        AdcdnLogTool.isNeedShow = true;//日志开关，测试的时候可以打开

    }

}
