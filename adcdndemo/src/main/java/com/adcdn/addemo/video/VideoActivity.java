package com.adcdn.addemo.video;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yunxia.addemo.R;
import com.yunxia.adsdk.tpadmobsdk.ad.listener.AdcdnVideoAdListener;
import com.yunxia.adsdk.tpadmobsdk.ad.listener.AdcdnVideoLoadListener;
import com.yunxia.adsdk.tpadmobsdk.ad.video.AdcdnVideoView;

/**
 * @author : xnn
 * @date : 2018/6/28
 * @description : video广告获取demo
 */

public class VideoActivity extends Activity {
    private static final String TAG = "ADCDN_Log";
    private AdcdnVideoView adcdnVideoView;

    private Button btnLoad, btnShow;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reward_video);
        btnLoad = findViewById(R.id.btn_load);
        btnShow = findViewById(R.id.btn_show);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adcdnVideoView.loadAd();
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adcdnVideoView.showAd();
            }
        });

        adcdnVideoView = new AdcdnVideoView(this, "1010030");
        // 设置广告监听（不设置也行）
        adcdnVideoView.setListener(new AdcdnVideoAdListener() {

            @Override
            public void onVideoDownloadSuccess() {
                Log.e(TAG, "广告下载完成了 ::::: ");
                adcdnVideoView.showAd();
                Toast.makeText(VideoActivity.this, "广告下载成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVideoDownloadFailed() {
                Log.e(TAG, "广告下载失败了 ::::: ");
            }

            @Override
            public void playCompletion() {
                Log.e(TAG, "广告播放完成 ::::: ");
            }

            @Override
            public void onAdShow() {
                Log.e(TAG, "广告展示曝光回调，但不一定是曝光成功了，比如一些网络问题导致上报失败 ::::: ");
            }

            @Override
            public void onAdClick() {
                Log.e(TAG, "广告被点击了 ::::: ");
            }

            @Override
            public void onAdClose() {
                Log.e(TAG, "广告被关闭了，该回调不一定会有 ::::: ");
            }

            @Override
            public void onAdFailed(String s) {
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
        context.startActivity(new Intent(context, VideoActivity.class));
    }
}
