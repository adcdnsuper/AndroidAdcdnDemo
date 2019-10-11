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
import com.yunxia.adsdk.tpadmobsdk.ad.listener.AdcdnNativeModelAdListener;
import com.yunxia.adsdk.tpadmobsdk.ad.nativemodel.AdcdnNativeModelView;
import com.yunxia.adsdk.tpadmobsdk.entity.MyADSize;
import com.yunxia.adsdk.tpadmobsdk.entity.NativeADDatas;
import com.yunxia.adsdk.tpadmobsdk.entity.NativeModelADDatas;

/**
 * @description : 原生模板纯图广告获取(1.0)
 */

public class NativeModelActivity extends Activity {
    private static final String TAG = "NativeModelActivity";
    private NativeADDatas mNativeADData;

    private Button btnLoad;
    private FrameLayout adView;
    private AdcdnNativeModelView nativeModelView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_model_pic);
        btnLoad = findViewById(R.id.btn_load);
        adView = findViewById(R.id.ll_adView);
        btnLoad.setOnClickListener(v -> loadAd());


        nativeModelView = new AdcdnNativeModelView(this, "1010033");
        //设置广告宽高，不设置默认宽高(可选)
        nativeModelView.setADSize(new MyADSize(MyADSize.FULL_WIDTH, MyADSize.AUTO_HEIGHT));
        loadAd();
    }


    private void loadAd() {
        nativeModelView.loadAd(new AdcdnNativeModelAdListener() {

            @Override
            public void onADReceiv(NativeModelADDatas nativeModelADDatas) {
                adView.removeAllViews();
                adView.addView(nativeModelADDatas.getADView());
                nativeModelADDatas.onExposured(adView);//必须调用此方法，否则影响计费

                Log.e(TAG, "广告下载成功 ::::: ");
                Toast.makeText(NativeModelActivity.this, "广告下载成功", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onADError(String error) {
                Toast.makeText(NativeModelActivity.this, "广告下载失败" + error, Toast.LENGTH_SHORT).show();

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
        nativeModelView.destroy();
    }

    public static void jumpHere(Context context) {
        context.startActivity(new Intent(context, NativeModelActivity.class));
    }


}
