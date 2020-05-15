package com.adcdn.addemo.gamebox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.FrameLayout;

import com.adcdn.adsdk.configsdk.common.AdcdnMobSDK;
import com.adcdn.adsdk.configsdk.entity.AdGameBoxSlot;
import com.adcdn.adsdk.games.common.AdcdnGameBox;
import com.yunxia.addemo.R;

/**
 * @description : 游戏盒子接入
 * 目前只支持anrdoid 5.0或以上
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
                .setImmersive(true)//是否需要沉浸式状态栏显示游戏盒子
                .setExistNav(true)//是否游戏盒子需要右上角退出按钮
                .build();
        adcdnGameAdView = new AdcdnGameBox(this, adSlot);
        adcdnGameAdView.loadWebView();
        flContainer.addView(adcdnGameAdView);
        int scenesSwitch = AdcdnMobSDK.instance().getScenesSwitch();//如果不等于1，说明游戏盒子被关闭，可以在外部路口隐藏游戏盒子

    }

    //这个地方一定要设置，否则无法正常上传头像
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        adcdnGameAdView.setOnActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if (adcdnGameAdView == null) {
            finish();
        } else {
            if (!adcdnGameAdView.backWebview()) {
                adcdnGameAdView.showBackDialog();//这里调用弹框确认是否退出，不要确认的话直接调用finish();
            }
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
