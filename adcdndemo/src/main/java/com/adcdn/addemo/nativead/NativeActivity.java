package com.adcdn.addemo.nativead;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.yunxia.addemo.R;
import com.yunxia.adsdk.tpadmobsdk.ad.listener.AdcdnNativeAdListener;
import com.yunxia.adsdk.tpadmobsdk.ad.nativead.AdcdnNativeView;
import com.yunxia.adsdk.tpadmobsdk.entity.NativeADDatas;

/**
 * @author : ciba
 * @date : 2018/6/28
 * @description : 原生广告获取demo
 */

public class NativeActivity extends Activity {
    private static final String TAG = "ADCDN_Log";
    private AdcdnNativeView adcdnNativeView;
    private NativeADDatas mNativeADData;

    private Button btnLoad;
    protected AQuery aQuery;
    private FrameLayout videoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);
        aQuery = new AQuery(this);
        btnLoad = findViewById(R.id.btn_load);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadAd();
            }
        });

        adcdnNativeView = new AdcdnNativeView(this, "1010033");
        loadAd();

    }

    private void loadAd() {
        adcdnNativeView.loadAd(new AdcdnNativeAdListener() {
            @Override
            public void onADLoaded(NativeADDatas nativeADData) {
                mNativeADData = nativeADData;
                if (mNativeADData != null) {
                    showAD();
                }
                Toast.makeText(NativeActivity.this, "广告下载成功", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onADError(String error) {
                Toast.makeText(NativeActivity.this, "广告下载失败" + error, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onExposured() {
                Toast.makeText(NativeActivity.this, "广告展示曝光回调，但不一定是曝光成功了，比如一些网络问题导致上报失败 ::::: ", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "广告展示曝光回调，但不一定是曝光成功了，比如一些网络问题导致上报失败 ::::: ");

            }

            @Override
            public void onClicked() {
                Toast.makeText(NativeActivity.this, "广告被点击了 ::::: ", Toast.LENGTH_SHORT).show();

                Log.e(TAG, "广告被点击了 ::::: ");

            }


        });

    }


    /**
     * 展示原生广告时，一定要调用onExposured接口曝光广告
     */
    public void showAD() {
        videoView = findViewById(R.id.ly_video);
        if (mNativeADData.getAdPatternType() == AdcdnNativeView.NATIVE_3IMAGE && mNativeADData.getImgList().size() >= 3) {
            findViewById(R.id.native_3img_ad_container).setVisibility(View.VISIBLE);
            findViewById(R.id.native_ad_container).setVisibility(View.INVISIBLE);
            aQuery.id(R.id.img_1).image(mNativeADData.getImgList().get(0), false, true);
            aQuery.id(R.id.img_2).image(mNativeADData.getImgList().get(1), false, true);
            aQuery.id(R.id.img_3).image(mNativeADData.getImgList().get(2), false, true);
            aQuery.id(R.id.native_3img_title).text(mNativeADData.getTitle());
            aQuery.id(R.id.native_3img_desc).text(mNativeADData.getDesc());
        } else if (mNativeADData.getAdPatternType() == AdcdnNativeView.NATIVE_2IMAGE_2TEXT) {
            findViewById(R.id.native_3img_ad_container).setVisibility(View.INVISIBLE);
            findViewById(R.id.native_ad_container).setVisibility(View.VISIBLE);
            findViewById(R.id.img_poster).setVisibility(View.VISIBLE);
            videoView.setVisibility(View.GONE);
            aQuery.id(R.id.img_logo).image(mNativeADData.getIconUrl(), false, true);
            aQuery.id(R.id.img_poster).image(mNativeADData.getImgUrl(), false, true);
            aQuery.id(R.id.text_name).text(mNativeADData.getTitle());
            aQuery.id(R.id.text_desc).text(mNativeADData.getDesc());


        } else if (mNativeADData.getAdPatternType() == AdcdnNativeView.NATIVE_1IMAGE_2TEXT) {
            findViewById(R.id.native_3img_ad_container).setVisibility(View.INVISIBLE);
            findViewById(R.id.native_ad_container).setVisibility(View.VISIBLE);
            aQuery.id(R.id.img_logo).image(mNativeADData.getImgUrl(), false, true);
            aQuery.id(R.id.img_poster).clear();
            aQuery.id(R.id.text_name).text(mNativeADData.getTitle());
            aQuery.id(R.id.text_desc).text(mNativeADData.getDesc());
        } else if (mNativeADData.getAdPatternType() == AdcdnNativeView.NATIVE_VIDEO) {
            findViewById(R.id.native_3img_ad_container).setVisibility(View.INVISIBLE);
            findViewById(R.id.native_ad_container).setVisibility(View.VISIBLE);
            findViewById(R.id.img_poster).setVisibility(View.GONE);
            videoView.setVisibility(View.VISIBLE);
            aQuery.id(R.id.img_logo).image(mNativeADData.getIconUrl(), false, true);
            aQuery.id(R.id.img_poster).clear();
            aQuery.id(R.id.text_name).text(mNativeADData.getTitle());
            aQuery.id(R.id.text_desc).text(mNativeADData.getDesc());
//            aQuery.id(R.id.iv_source).text(mNativeADData.getSource());
            View video = mNativeADData.getAdView();
            if (video != null) {
                if (video.getParent() == null) {
                    videoView.removeAllViews();
                    videoView.addView(video);
                }
            }

        }
        if (TextUtils.isEmpty(mNativeADData.getSource())) {
            aQuery.id(R.id.tv_source).text("广告");
        } else {
            aQuery.id(R.id.tv_source).text("广告·" + mNativeADData.getSource());
        }
        mNativeADData.onExposured(this.findViewById(R.id.nativeADContainer)); // 必须调用曝光接口
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static void jumpHere(Context context) {
        context.startActivity(new Intent(context, NativeActivity.class));
    }
}
