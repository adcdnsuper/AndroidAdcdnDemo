package com.adcdn.addemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.adcdn.addemo.banner.BannerActivity;
import com.adcdn.addemo.information.InformationActivity;
import com.adcdn.addemo.splash.SplashActivity2;
import com.adcdn.addemo.video.VideoActivity;
import com.yunxia.addemo.R;
import com.adcdn.addemo.interstitial.InterstitialActivity;
import com.yunxia.adsdk.tpadmobsdk.ad.constant.InformationAdType;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        findViewById(R.id.tvSplash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SplashActivity2.jumpHere(MainActivity.this);
            }
        });
        findViewById(R.id.tvBanner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BannerActivity.jumpHere(MainActivity.this);
            }
        });
        findViewById(R.id.tvInsert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InterstitialActivity.jumpHere(MainActivity.this);
            }
        });
        findViewById(R.id.tvInformation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InformationActivity.jumpHere(MainActivity.this, InformationAdType.NORMAL);
            }
        });
        findViewById(R.id.tvInformationImageOnly).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InformationActivity.jumpHere(MainActivity.this, InformationAdType.ONLY_IMAGE);
            }
        });
        findViewById(R.id.tvInformationRightImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InformationActivity.jumpHere(MainActivity.this, InformationAdType.RIGHT_IMAGE);
            }
        });
        findViewById(R.id.tvInformationLeftImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InformationActivity.jumpHere(MainActivity.this, InformationAdType.LEFT_IMAGE);
            }
        });
        findViewById(R.id.tvInformationBottomImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InformationActivity.jumpHere(MainActivity.this, InformationAdType.BOTTOM_IMAGE);
            }
        });
        findViewById(R.id.tvInformationvertric).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InformationActivity.jumpHere(MainActivity.this, InformationAdType.VERTICALPICFLOW);
            }
        });
        findViewById(R.id.tvVideo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoActivity.jumpHere(MainActivity.this);
            }
        });
    }
}
