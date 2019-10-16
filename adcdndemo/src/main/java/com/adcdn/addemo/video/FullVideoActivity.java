package com.adcdn.addemo.video;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yunxia.addemo.R;
import com.yunxia.adsdk.tpadmobsdk.ad.listener.AdcdnVideoFullAdListener;
import com.yunxia.adsdk.tpadmobsdk.ad.video.AdcdnFullVideoView;

/**
 * @author : ciba
 * @date : 2018/6/28
 * @description : 全屏video广告获取demo
 */

public class FullVideoActivity extends Activity {
    private static final String TAG = "ADMobGen_Log";
    private AdcdnFullVideoView adcdnFullVideoView;

    private Button btnLoad, btnShow, isReady;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reward_video);
        btnLoad = findViewById(R.id.btn_load);
        btnShow = findViewById(R.id.btn_show);
        isReady = findViewById(R.id.is_ready);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adcdnFullVideoView.loadAd();
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adcdnFullVideoView.showAd(FullVideoActivity.this);
            }
        });

        adcdnFullVideoView = new AdcdnFullVideoView(this, "1010113");
        // 设置广告监听（不设置也行）
        adcdnFullVideoView.setListener(new AdcdnVideoFullAdListener() {

            @Override
            public void onAdShow() {
                Log.e(TAG, "广告展示曝光回调，但不一定是曝光成功了，比如一些网络问题导致上报失败 ::::: ");
            }

            @Override
            public void onAdVideoBarClick() {
                Log.e(TAG, "广告被点击了 ::::: ");
            }

            @Override
            public void onAdClose() {
                Log.e(TAG, "广告被关闭了，该回调不一定会有 ::::: ");
            }

            @Override
            public void onVideoComplete() {
                Log.e(TAG, "广告播放完成 ::::: ");
            }

            @Override
            public void onSkippedVideo() {
                Log.e(TAG, "广告被跳过了 ::::: ");
            }

            @Override
            public void onFullScreenVideoCached() {
                Log.e(TAG, "广告下载完成了 ::::: ");
                adcdnFullVideoView.showAd(FullVideoActivity.this);
            }

            @Override
            public void onError(String s) {
                Log.e(TAG, "广告加载失败了 ::::: " + s);
            }


        });
    }

    @Override
    protected void onDestroy() {
        // 释放广告资源
        super.onDestroy();
    }

    public static void jumpHere(Context context) {
        context.startActivity(new Intent(context, FullVideoActivity.class));
    }
}
