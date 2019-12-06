package com.adcdn.addemo.scene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.FrameLayout;

import com.yunxia.addemo.R;
import com.yunxia.adsdk.games.AdcdnGameAdView;
import com.yunxia.adsdk.games.AdcdnShareDatas;
import com.yunxia.adsdk.games.GameADDatas;
import com.yunxia.adsdk.tpadmobsdk.entity.AdGameSlot;

/**
 * @date : 2019/12/1
 * @description : 场景广告获取demo
 */

public class SceneActivity extends Activity {
    private static final String TAG = "ADCDN_Log";
    private FrameLayout flContainer;
    private AdcdnGameAdView adcdnGameAdView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);
        flContainer = findViewById(R.id.flContainer);

        AdGameSlot adSlot = new AdGameSlot.Builder()
                .setAppId("10001")//场景的appId
                .setGameId("10001")//场景的id
                .setUserId("112247")//用户id
                .setExpressAdId("1010038")//原生模板广告id（上文下浮层）
                .setExpressAdId2("1010039")//原生模板广告id（文字浮层）
                .setVideoAdId("1010152")//激励视频id
                .build();
        adcdnGameAdView = new AdcdnGameAdView(this, adSlot);
        adcdnGameAdView.setGameListener(new GameADDatas() {
            @Override
            public void startShare(AdcdnShareDatas adcdnShareDatas) {
                //客户端自行调用分享
                Log.e(TAG, "分享" + adcdnShareDatas.getUrl() + adcdnShareDatas.getTitle() + adcdnShareDatas.getDesc());
                adcdnShareDatas.beanShare();//分享后需要调用！
            }

            @Override
            public void startLogin() {
                //客户端可以在这里处理登录
                Log.e(TAG, "登录");

            }
        });
        adcdnGameAdView.setTest(true);
        adcdnGameAdView.loadWebView();
        flContainer.addView(adcdnGameAdView);

    }

    @Override
    protected void onDestroy() {
        // 释放广告资源
        adcdnGameAdView.destroy();//注意要在 super.onDestroy()之前调用
        super.onDestroy();
    }

    public static void jumpHere(Context context) {
        context.startActivity(new Intent(context, SceneActivity.class));
    }
}
