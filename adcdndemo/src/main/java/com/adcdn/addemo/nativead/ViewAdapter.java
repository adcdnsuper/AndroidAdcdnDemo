package com.adcdn.addemo.nativead;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.yunxia.addemo.R;
import com.yunxia.adsdk.tpadmobsdk.ad.listener.AdcdnNativeAdListener;
import com.yunxia.adsdk.tpadmobsdk.ad.nativead.AdcdnNativeView;
import com.yunxia.adsdk.tpadmobsdk.entity.NativeADDatas;

import java.util.List;

public class ViewAdapter extends PagerAdapter {
    private static final String TAG = "ADCDN_Log";
    private List<ViewGroup> datas;
    private NativeADDatas mNativeADData;
    private AdcdnNativeView adcdnNativeView;
    private Context context;

    public ViewAdapter(Context context, List<ViewGroup> list) {
        datas = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewGroup view = datas.get(position);
        container.addView(view);
        adcdnNativeView = new AdcdnNativeView((Activity) context, "1010016");
        loadAd(view, position);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(datas.get(position));
    }

    private void loadAd(ViewGroup view, int position) {
        adcdnNativeView.loadAd(new AdcdnNativeAdListener() {
            @Override
            public void onADLoaded(NativeADDatas nativeADData) {
                mNativeADData = nativeADData;
                if (mNativeADData != null) {
                    AQuery aQuery = new AQuery(view);
                    aQuery.id(R.id.iv_ad).image(mNativeADData.getImgUrl(), false, true);
                    aQuery.id(R.id.tv_item).text(position + 1 + "");
                    mNativeADData.onExposured(view); // 必须调用曝光接口
                }

            }

            @Override
            public void onADError(String error) {

                Toast.makeText(context, "广告下载失败" + error, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onExposured() {
                Toast.makeText(context, "广告展示曝光回调，但不一定是曝光成功了，比如一些网络问题导致上报失败 ::::: ", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "广告展示曝光回调，但不一定是曝光成功了，比如一些网络问题导致上报失败 ::::: ");

            }

            @Override
            public void onClicked() {
                Toast.makeText(context, "广告被点击了 ::::: ", Toast.LENGTH_SHORT).show();

                Log.e(TAG, "广告被点击了 ::::: ");

            }


        });

    }
}