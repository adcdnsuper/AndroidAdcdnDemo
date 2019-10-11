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
import android.widget.RadioGroup;
import android.widget.Toast;

import com.yunxia.addemo.R;
import com.yunxia.adsdk.tpadmobsdk.ad.listener.AdcdnNativeExpressAdListener;
import com.yunxia.adsdk.tpadmobsdk.ad.nativemodel.AdcdnNativeExpressView;
import com.yunxia.adsdk.tpadmobsdk.entity.MyADSize;
import com.yunxia.adsdk.tpadmobsdk.entity.NativeADDatas;
import com.yunxia.adsdk.tpadmobsdk.entity.NativeExpressADDatas;

/**
 * @description : 原生模板广告获取demo(2.0)
 */

public class NativeExpressActivity extends Activity implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "NativeExpressActivity";
    private NativeADDatas mNativeADData;

    private Button btnLoad;
    private FrameLayout adView;
    private AdcdnNativeExpressView adcdnNativeExpressView;
    private RadioGroup radioGroup;
    private String adPlaceId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_model);
        btnLoad = findViewById(R.id.btn_load);
        adView = findViewById(R.id.ll_adView);
        radioGroup = findViewById(R.id.radioGroupId);
        radioGroup.setOnCheckedChangeListener(this);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAd();
            }
        });


        adcdnNativeExpressView = new AdcdnNativeExpressView(this, "1010033");
        //adcdnNativeExpressView.setADSize(new MyADSize(MyADSize.FULL_WIDTH, MyADSize.AUTO_HEIGHT));//可选，单位dp
        loadAd();
    }


    private void loadAd() {
        adcdnNativeExpressView.loadAd(new AdcdnNativeExpressAdListener() {

            @Override
            public void onADReceiv(View view) {
                adView.removeAllViews();
                adView.addView(view);

                Log.e(TAG, "广告下载成功 ::::: ");
                Toast.makeText(NativeExpressActivity.this, "广告下载成功", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onADError(String error) {
                Toast.makeText(NativeExpressActivity.this, "广告下载失败" + error, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onExposured(View view) {
                Log.e(TAG, "广告展示曝光回调，但不一定是曝光成功了，比如一些网络问题导致上报失败 ::::: ");
            }

            @Override
            public void onClicked(View view) {
                Log.e(TAG, "广告被点击了 ::::: ");

            }

            @Override
            public void onAdClose(View view) {

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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getCheckedRadioButtonId()) {
            case R.id.rb1://三小图
                adcdnNativeExpressView.setAdId("1010042");
                loadAd();
                break;
            case R.id.rb2://上文下图
                adcdnNativeExpressView.setAdId("1010033");
                loadAd();
                break;
            case R.id.rb3://左图右文
                adcdnNativeExpressView.setAdId("1010040");
                loadAd();
                break;
            case R.id.rb4://左文右图
                adcdnNativeExpressView.setAdId("1010041");
                loadAd();
                break;
            case R.id.rb5://文字浮层
                adcdnNativeExpressView.setAdId("1010038");
                loadAd();
                break;
            case R.id.rb6://文字浮层（上文下图）
                adcdnNativeExpressView.setAdId("1010039");
                loadAd();
                break;
        }

    }
}
