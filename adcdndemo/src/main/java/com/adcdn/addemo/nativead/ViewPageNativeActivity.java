package com.adcdn.addemo.nativead;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.yunxia.addemo.R;
import com.yunxia.adsdk.tpadmobsdk.ad.listener.AdcdnNativeAdListener;
import com.yunxia.adsdk.tpadmobsdk.ad.nativead.AdcdnNativeView;
import com.yunxia.adsdk.tpadmobsdk.entity.NativeADDatas;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ciba
 * @date : 2018/6/28
 * @description : 原生广告获取demo
 */

public class ViewPageNativeActivity extends Activity {
    private static final String TAG = "ADCDN_Log";
    private ViewPager pager;
    private AdcdnNativeView adcdnNativeView;
    private NativeADDatas mNativeADData;
    protected AQuery aQuery;
    private List<ViewGroup> list = new ArrayList<>();
    private int count = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_view_page);


        for (int i = 0; i < 4; i++) {
            list.add(getADView());
        }

        pager = findViewById(R.id.view_pager);
        PagerAdapter adapter = new ViewAdapter(this, list);
        pager.setAdapter(adapter);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static void jumpHere(Context context) {
        context.startActivity(new Intent(context, ViewPageNativeActivity.class));
    }


    public ViewGroup getADView() {
        ViewGroup inflate = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.item_page, null);
        return inflate;

    }


}
