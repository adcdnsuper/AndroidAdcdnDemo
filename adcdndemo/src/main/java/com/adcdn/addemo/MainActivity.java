package com.adcdn.addemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.adcdn.addemo.banner.BannerActivity;
import com.adcdn.addemo.gamebox.GameCenterActivity;
import com.adcdn.addemo.nativead.NativeExpressActivity;
import com.adcdn.addemo.splash.SplashActivity2;
import com.adcdn.addemo.video.FullVideoActivity;
import com.adcdn.addemo.video.VideoActivity;
import com.yunxia.addemo.R;
import com.adcdn.addemo.interstitial.InterstitialActivity;


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
        findViewById(R.id.tvVideo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoActivity.jumpHere(MainActivity.this);
            }
        });

        findViewById(R.id.tvNativeEx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NativeExpressActivity.jumpHere(MainActivity.this);
            }
        });


        findViewById(R.id.tvFullVideo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullVideoActivity.jumpHere(MainActivity.this);
            }
        });


        findViewById(R.id.tvScene).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameCenterActivity.jumpHere(MainActivity.this);
            }
        });

    }
}
