package com.adcdn.addemo.gamebox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.FrameLayout;

import com.adcdn.adsdk.commonsdk.GameBoxDatas;
import com.adcdn.adsdk.configsdk.common.AdcdnMobSDK;
import com.adcdn.adsdk.configsdk.entity.AdGameBoxSlot;
import com.adcdn.adsdk.games.common.AdcdnGameBox;
import com.yunxia.addemo.R;

/**
 * @description : 游戏盒子接入
 */

public class GameCenterActivity extends Activity {
    private static final String TAG = "ADCDN_Log";
    private FrameLayout flContainer;
    private AdcdnGameBox adcdnGameAdView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        flContainer = findViewById(R.id.flContainer);

        AdGameBoxSlot adSlot = new AdGameBoxSlot.Builder()
                .setUserId("16")//使用app的userid（如果没有用户体系或者用户未登陆可以不传）
                .setExistNav("1")//游戏盒子首页是否需要退出按钮（1：需要，0：不需要）
                .setUserSyetem("1")//app是否有用户体系
                .setNickname("demo")//用户昵称
                .setAvatarUrl("http://b-ssl.duitang.com/uploads/item/201410/09/20141009224754_AswrQ.jpeg")//用户头像
                .build();
        adcdnGameAdView = new AdcdnGameBox(this, adSlot, new GameBoxDatas() {
            @Override
            public void startLogin() {
                //在这个回调跳转app的登陆界面
                Log.e("xnn", "登录");

            }
        });

        adcdnGameAdView.loadWebView();
        flContainer.addView(adcdnGameAdView);

        int scenesSwitch = AdcdnMobSDK.instance().getScenesSwitch();//如果不等于1，说明游戏盒子被关闭，可以在外部路口隐藏游戏盒子

    }

    @Override
    public void onBackPressed() {
        if (!adcdnGameAdView.backWebview()) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        // 释放广告资源
        adcdnGameAdView.destroy();//注意要在 super.onDestroy()之前调用
        super.onDestroy();
    }

    public static void jumpHere(Context context) {
        context.startActivity(new Intent(context, GameCenterActivity.class));
    }
}
