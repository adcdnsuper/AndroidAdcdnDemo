package com.adcdn.addemo.nativead;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.yunxia.addemo.R;
import com.yunxia.adsdk.tpadmobsdk.ad.listener.AdcdnNativeExpressAdListener;
import com.yunxia.adsdk.tpadmobsdk.ad.nativemodel.AdcdnNativeExpressView;
import com.yunxia.adsdk.tpadmobsdk.entity.NativeADDatas;
import com.yunxia.adsdk.tpadmobsdk.entity.NativeExpressADDatas;

/**
 * @author : ciba
 * @date : 2019/8/25
 * @description : 原生模板广告获取demo
 */

public class NativeExpressActivity extends Activity {
    private static final String TAG = "NativeExpressActivity";
    private NativeADDatas mNativeADData;

    private Button btnLoad;
    private FrameLayout adView;
    private AdcdnNativeExpressView adcdnNativeExpressView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_model);
        btnLoad = findViewById(R.id.btn_load);
        adView = findViewById(R.id.ll_adView);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAd();
            }
        });


//        nativeModelView = new AdcdnNativeModelView(this, "1000382");
        adcdnNativeExpressView = new AdcdnNativeExpressView(this, "1010033");
        loadAd();
    }


    private void loadAd() {
        adcdnNativeExpressView.loadAd(new AdcdnNativeExpressAdListener() {

            @Override
            public void onADReceiv(NativeExpressADDatas nativeExpressADDatas) {
                adView.removeAllViews();
                adView.addView(nativeExpressADDatas.getADView());
                nativeExpressADDatas.onExposured(adView);//必须调用此方法，否则影响计费

                Log.e(TAG, "广告下载成功 ::::: ");
                Toast.makeText(NativeExpressActivity.this, "广告下载成功", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onADError(String error) {
                Toast.makeText(NativeExpressActivity.this, "广告下载失败" + error, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onExposured() {
                Log.e(TAG, "广告展示曝光回调，但不一定是曝光成功了，比如一些网络问题导致上报失败 ::::: ");
            }

            @Override
            public void onClicked() {
                Log.e(TAG, "广告被点击了 ::::: ");

            }

            @Override
            public void onAdClose() {

            }


        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adcdnNativeExpressView.destroy();
    }

    public static void jumpHere(Context context) {
        context.startActivity(new Intent(context, NativeExpressActivity.class));
    }


    private boolean isVisible(View v) {
        return v.getLocalVisibleRect(new Rect());
    }
}
