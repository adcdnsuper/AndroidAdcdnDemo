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
import com.yunxia.adsdk.tpadmobsdk.entity.AdVideoSlot;

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

        AdVideoSlot adSlot = new AdVideoSlot.Builder()
                .setCodeId("1010030")
                .setSupportDeepLink(true)
                .setImageAcceptedSize(1080, 1920)
                .setRewardName("金币") //奖励的名称
                .setRewardAmount(3)  //奖励的数量
                .setUserID("user123") //必传参数，表来标识应用侧唯一用户；若非服务器回调模式或不需sdk透传//可设置为空字符串
                .setMediaExtra("media_extra") //附加参数，可选
                .setOrientation(AdcdnVideoView.VERTICAL) //必填参数，期望视频的播放方向：AdcdnVideoView.HORIZONTAL 或 AdcdnVideoView.VERTICAL
                .build();
        adcdnVideoView = new AdcdnVideoView(this, adSlot);
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
            public void onRewardVerify(boolean b, AdVideoSlot adVideoSlot) {
                Log.e(TAG, " amount:" + adVideoSlot.getRewardAmount() +
                        " name:" + adVideoSlot.getRewardName() + " userId:" + adVideoSlot.getUserID());
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
