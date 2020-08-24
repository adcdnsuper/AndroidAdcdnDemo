package com.adcdn.addemo;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.webkit.WebView;

import com.adcdn.adsdk.configsdk.change.AdcdnLogTool;
import com.adcdn.adsdk.configsdk.common.AdcdnMobSDK;
import com.bytedance.sdk.openadsdk.TTAdConfig;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdSdk;


/**
 * @author : xnn
 * @date : 2019/5/5
 * @description :45
 */

public class MyApplication extends Application {


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onCreate() {
        super.onCreate();
        webviewSetPath(this);
        // TODO: 2019/5/5 修改为自己的appId
        AdcdnMobSDK.instance().initSdk(getApplicationContext(), Constant.APPID);
        AdcdnLogTool.isNeedShow = true;//日志开关，测试的时候可以打开

    }

    //Android P 以及之后版本不支持同时从多个进程使用具有相同数据目录的WebView
    //为其它进程webView设置目录

    @RequiresApi(api = 28)
    public void webviewSetPath(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            String processName = getProcessName(this);
            String packageName = this.getPackageName();
            if (!packageName.equals(processName)) {
                WebView.setDataDirectorySuffix(processName);
            }
        }
    }

    public String getProcessName(Context context) {
        if (context == null) return null;
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == android.os.Process.myPid()) {
                return processInfo.processName;
            }
        }
        return null;
    }

}
