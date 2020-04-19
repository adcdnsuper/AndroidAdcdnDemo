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

import com.adcdn.addemo.Constant;
import com.yunxia.addemo.R;
import com.yunxia.adsdk.tpadmobsdk.ad.listener.AdcdnNativeExpressAdListener;
import com.yunxia.adsdk.tpadmobsdk.ad.listener.AdcdnNativeModelAdListener;
import com.yunxia.adsdk.tpadmobsdk.ad.nativemodel.AdcdnNativeExpressView;
import com.yunxia.adsdk.tpadmobsdk.ad.nativemodel.AdcdnNativeModelView;
import com.yunxia.adsdk.tpadmobsdk.common.AdcdnMobSDK;
import com.yunxia.adsdk.tpadmobsdk.entity.MyADSize;
import com.yunxia.adsdk.tpadmobsdk.entity.NativeADDatas;
import com.yunxia.adsdk.tpadmobsdk.entity.NativeExpressADDatas;
import com.yunxia.adsdk.tpadmobsdk.entity.NativeModelADDatas;

import java.util.List;

/**
 * @description : 原生模板广告获取demo(2.0)
 *
 */

public class NativeExpressActivity extends Activity implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "NativeExpressActivity";
    private NativeADDatas mNativeADData;

    private Button btnLoad;
    private FrameLayout adView, adView2, adView3;
    private AdcdnNativeExpressView adcdnNativeExpressView;
    private RadioGroup radioGroup;
    private String adPlaceId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_model);
        btnLoad = findViewById(R.id.btn_load);
        adView = findViewById(R.id.ll_adView);
        adView2 = findViewById(R.id.ll_adView2);
        adView3 = findViewById(R.id.ll_adView3);
        radioGroup = findViewById(R.id.radioGroupId);
        radioGroup.setOnCheckedChangeListener(this);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAd();
            }
        });


        adcdnNativeExpressView = new AdcdnNativeExpressView(this,  Constant.AD_NATIVE_3IMG);
        adcdnNativeExpressView.setAdCount(1);//请求广告的数量（1~3），最多一次请求3个广告
        //adcdnNativeExpressView.setADSize(new MyADSize(MyADSize.FULL_WIDTH, MyADSize.AUTO_HEIGHT));//可选，单位dp
        loadAd();
    }


    /**
     * 模板调用方法（三小图，上文下图，左图右文，左文右图，文字浮沉，文字浮层上文下图）
     */
    NativeExpressADDatas nativeExpressADDatas;

    private void loadAd() {
        adcdnNativeExpressView.loadAd(new AdcdnNativeExpressAdListener() {

            @Override
            public void onADLoaded(List<NativeExpressADDatas> adList) {
                Log.e(TAG, "广告下载成功 ::::: " + adList.size());
                if (adList.size() > 0) {
                    nativeExpressADDatas = adList.get(0);
                    nativeExpressADDatas.render();
                }

            }

            @Override
            public void onRenderSuccess(View view) {
                Log.e(TAG, "广告渲染成功 ::::: ");

                if (nativeExpressADDatas != null) {
                    adView.removeAllViews();
                    adView.addView(nativeExpressADDatas.getADView());
                }
//                Toast.makeText(NativeExpressActivity.this, "广告下载成功", Toast.LENGTH_SHORT).show();

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
                adcdnNativeExpressView.setAdId(Constant.AD_NATIVE_3IMG);
                loadAd();
                break;
            case R.id.rb2://上文下图
                adcdnNativeExpressView.setAdId(Constant.AD_NATIVE_1IMG);
                loadAd();
                break;
            case R.id.rb3://左图右文
                adcdnNativeExpressView.setAdId(Constant.AD_NATIVE_1IMG_L);
                loadAd();
                break;
            case R.id.rb4://左文右图
                adcdnNativeExpressView.setAdId(Constant.AD_NATIVE_1IMG_R);
                loadAd();
                break;
            case R.id.rb5://文字浮层
                adcdnNativeExpressView.setAdId(Constant.AD_NATIVE_1IMG_F);
                loadAd();
                break;
            case R.id.rb6://文字浮层（上文下图）
                adcdnNativeExpressView.setAdId(Constant.AD_NATIVE_1IMG_SF);
                loadAd();
                break;


        }


    }
}
