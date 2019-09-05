package com.adcdn.addemo.splash;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.adcdn.addemo.MainActivity;
import com.yunxia.addemo.R;
import com.yunxia.adsdk.tpadmobsdk.ad.listener.AdcdnSplashAdListener;
import com.yunxia.adsdk.tpadmobsdk.ad.splash.AdcdnSplashView;

import java.util.ArrayList;
import java.util.List;


public class SplashActivity2 extends AppCompatActivity{
    private static final String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION
            , Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE
            , Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE};

    private boolean needJumpMain = false;
    private boolean readyJump = false;
    List<String> permissionList = new ArrayList<>();
    private static final String TAG = "ADCDN_Log";
    private AdcdnSplashView adcdnSplashView;
    private FrameLayout flContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        for (String permission : permissions) {
            int checkSelfPermission = ContextCompat.checkSelfPermission(this, permission);
            if (PackageManager.PERMISSION_GRANTED == checkSelfPermission) {
                continue;
            }
            permissionList.add(permission);
        }
        flContainer = findViewById(R.id.flContainer);
        initAd();

    }

    private void initAd() {
        adcdnSplashView = new AdcdnSplashView(this, "1010029", flContainer);
        adcdnSplashView.setListener(new AdcdnSplashAdListener() {
            @Override
            public void onADExposure() {
                Log.e(TAG, "广告展示曝光回调，但不一定是曝光成功了，比如一些网络问题导致上报失败 ::::: ");
            }

            @Override
            public void onADFailed(String s) {
                Log.e(TAG, "广告获取失败了 ::::: " + s);
                Toast.makeText(SplashActivity2.this, s + "", Toast.LENGTH_SHORT).show();
                finish();

            }

            @Override
            public void onADReceiv() {
                Log.e(TAG, "广告获取成功了 ::::: ");
            }

            @Override
            public void onADClick() {
                Log.e(TAG, "广告被点击了 ::::: ");
                readyJump = true;
            }

            @Override
            public void onAdClose() {
                readyJump = true;
                Log.e(TAG, "广告被关闭了 ::::: ");
                finish();

            }
        });
        flContainer.addView(adcdnSplashView);
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), 1);
        } else {
            adcdnSplashView.loadAd();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            adcdnSplashView.loadAd();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adcdnSplashView != null) {
            adcdnSplashView.destroy();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        needJumpMain = true;
//        checkJump();

    }

    @Override
    protected void onPause() {
        super.onPause();
        needJumpMain = false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //防止用户返回键退出APP影响曝光
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    public static void jumpHere(Context context) {
        context.startActivity(new Intent(context, SplashActivity2.class));
    }
}

