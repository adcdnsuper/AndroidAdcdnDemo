# ADCDN广告接入说明文档
## 1.概述
尊敬的开发者朋友，欢迎您使用ADCDN广告sdk平台。通过本文档，您可以轻松的在几分钟之内完成广告的集成过程。
## 3.SDK接入流程
### 3.1 添加sdk到工程
接入环境：Androidstudio
可以复制Demo中libs文件目录下的依赖包到项目中。
```
android {
 ....
 //1.添加依赖目录
 repositories {
   flatDir {
     dirs 'libs'
   }
   }
 }
   dependencies {
   ....
   //2.添加依赖包
     //必须的依赖包
 implementation(name:	'adcdnsdk-1.0',	ext:	'aar')
 implementation 'pl.droidsonroids.gif:android-gif-drawable:1.2.6'

 //根据APP需要添加的依赖包
 //广点通
 implementation(name:	'gdt-release',	ext:	'aar')
 //头条需要添加以下依赖包
 implementation(name:	'toutiao-release',	ext:	'aar')
 implementation(name:	'open_ad_sdk',	ext:	'aar')
  //adview需要添加以下依赖包
  implementation(name:	'adview-release',	ext:	'aar')


```
**注意事项

*如果项目中集成了重复的其他广告平台sdk尽量移除避免冲突
### 3.2权限申请
使用sdk时可能需要以下权限，为了保证使用广告的正确，请在6.0以及以上的手机中使用sdk前及时申请
```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" /> 
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" /> 
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" /> 
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" /> 
<uses-permission android:name="android.permission.GET_TASKS" />
<uses-permission android:name="android.permission.WAKE_LOCK" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" /> 
<uses-permission android:name="android.permission.ACCESS_COARSE_UPDATES" /> 
<uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />
```
PS:ACCESS_COARSE_LOCATION̵READ_PHONE_STATE̵WRITE_EXTERNAL_STORAGE̵ ACCESS_NETWORK_STATE̵ACCESS_WIFI_STATE这几个权限请确保获取，否则可能无法获取广告 （可参考Demo中的SplashActivity）
## 4. 接入代码
### 4.1 sdk初始化
提示：appid请联系商务获取，并在Application的onCreat()方法中进行SDK初始化

```
// TODO: 2018/10/22 修改为自己的appId 测试id = 2
    TPADMobSDK.instance().initSdk(getApplicationContext(), APP_ID);
```
### 4.2 开屏广告示例
提示：
1.开屏广告默认为屏幕高度的100%，可自定义高度比例，但不能低于0.75
2.注意加载开屏广告时，请保证开屏view控件处于可见状态，否则会出现获取不到广告的情况
```
        adcdnSplashView = new AdcdnSplashView(this, "请填写对应的plcId", flContainer);
        adcdnSplashView.setListener(new AdcdnSplashAdListener() {
            @Override
            public void onADExposure() {
                Log.e(TAG, "广告展示曝光回调，但不一定是曝光成功了，比如一些网络问题导致上报失败 ::::: ");
            }

            @Override
            public void onADFailed(String error) {
                Log.e(TAG, "广告获取失败了 ::::: " + error);
            }

            @Override
            public void onADReceiv() {
                Log.e(TAG, "广告获取成功了 ::::: ");
            }

            @Override
            public void onADClick() {
                Log.e(TAG, "广告被点击了 ::::: ");
            }

            @Override
            public void onAdClose() {
                Log.e(TAG, "广告被关闭了 ::::: ");
            }
        });
        flContainer.addView(adcdnSplashView);
        adcdnSplashView.loadAd();
```
### 4.3 banner广告示例
Banner广告控件容器保证不低于50dp，建议使用自适应
```
 // 初始化Banner广告
        adcdnBannerView = new AdcdnBannerView(this,"请填写对应的plcId");
        // 不设置banner广告尺寸大小则默认比例为: 640*100;
		//adMobGenBannerView.setADSize(640,100);
        // 设置广告监听（不设置也行）
        adcdnBannerView.setListener(new AdcdnBannerAdListener() {
            @Override
            public void onADExposure() {
                Log.e(TAG, "广告展示曝光回调，但不一定是曝光成功了，比如一些网络问题导致上报失败 ::::: ");
            }

            @Override
            public void onADFailed(String s) {
                Log.e(TAG, "广告获取失败了 ::::: " + s);
            }

            @Override
            public void onADReceiv() {
                Log.e(TAG, "广告获取成功了 ::::: ");
            }

            @Override
            public void onADClick() {
                Log.e(TAG, "广告被点击了 ::::: ");
            }

            @Override
            public void onAdClose() {
                Log.e(TAG, "广告被关闭了，改回调不一定会有 ::::: ");
            }
        });
        // 把广告控件添加到容器
        flContainer.addView(adcdnBannerView);
        // 开始获取广告
        adcdnBannerView.loadAd();
```
### 4.4 信息流广告示例
```
 adcdnInformation = new AdcdnInformation(this, adType, "请填写对应的plcId");
        adcdnInformation.setListener(new SimpleAdcdnInformationAdListener() {
            @Override
            public void onADExposure(IADMobGenInformation adMobGenInformation) {
                Log.e(TAG, "广告展示曝光回调，但不一定是曝光成功了，比如一些网络问题导致上报失败 	::::: ");
            }

            @Override
            public void onADReceiv(IADMobGenInformation adMobGenInformation) {
                Log.e(TAG, "广告获取成功 ::::: ");
              
            }

            @Override
            public void onADClick(IADMobGenInformation adMobGenInformation) {
                Log.e(TAG, "广告被点击 ::::: ");
            }

            @Override
            public void onADFailed(String error) {
                Log.e(TAG, "广告数据获取失败时回调 ::::: " + error);
           
            }
        });
      adMobGenInformation.loadAd();
```
### 4.5 插屏广告示例
```
 adcdnInsertView = new AdcdnInsertView(InterstitialActivity.this,"请填写对应的plcId");
        adcdnInsertView.setListener(new AdcdnInsertitailAdListener() {
            @Override
            public void onADExposure() {
                Log.e(TAG, "广告展示曝光回调，但不一定是曝光成功了，比如一些网络问题导致上报失败 ::::: ");
            }

            @Override
            public void onADOpen() {
                Log.e(TAG, "广告打开成功了 ::::: ");
            }

            @Override
            public void onADLeftApplication() {
                Log.e(TAG, "广告onADLeftApplication ::::: ");
            }

            @Override
            public void onADFailed(String s) {
             
                Log.e(TAG, "广告获取失败了 ::::: " + s);
            }

            @Override
            public void onADReceiv() {
               
                Log.e(TAG, "广告获取成功了 ::::: ");
            }

            @Override
            public void onADClick() {
                Log.e(TAG, "广告被点击了 ::::: ");
            }

            @Override
            public void onAdClose() {
                Log.e(TAG, "广告被关闭了，改回调不一定会有 ::::: ");
            }
        });
        adcdnInsertView.loadAd();
```
### 4.5 激励视屏广告示例
```
 adcdnVideoView = new AdcdnVideoView(this, "请填写对应的plcId");
        // 设置广告监听（不设置也行）
        adcdnVideoView.setListener(new AdcdnVideoAdListener() {

            @Override
            public void onVideoDownloadSuccess() {
                Log.e(TAG, "广告下载完成了 ::::: ");
            }

            @Override
            public void onVideoDownloadFailed() {
                Log.e(TAG, "广告下载失败了 ::::: ");
            }

            @Override
            public void playCompletion() {
                Log.e(TAG, "广告播放完成 ::::: ");
            }

            @Override
            public void onAdShow() {
                Log.e(TAG, "广告展示曝光回调，但不一定是曝光成功了，比如一些网络问题导致上报失败 ::::: ");
            }

            @Override
            public void onAdClick() {
                Log.e(TAG, "广告被点击了 ::::: ");
            }

            @Override
            public void onAdClose() {
                Log.e(TAG, "广告被关闭了，该回调不一定会有 ::::: ");
            }

            @Override
            public void onAdFailed(String s) {
                Log.e(TAG, "广告加载失败了 ::::: " + s);
            }
        });

//下载点击按钮
         btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adcdnVideoView.loadAd(new AdcdnVideoLoadListener() {
                    @Override
                    public void onLoadSucceed() {
                        
                    }

                    @Override
                    public void onLoadFailed(String s) {
                        
                    }
                });
            }
        });
//展示广告点击按钮
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adcdnVideoView.showAd();
            }
        });
```

注意事项：
在不使用的时候（退出activity等）记得及时释放广告资源

## 5.适配Android7.0
如果您的应用需要适配7.0以上，请在AndroidManifest中添加以下代码：
```
<!--腾讯FileProvider-->
<provider
    android:name="android.support.v4.content.FileProvider"
    android:authorities="${applicationId}.fileprovider"
    android:exported="false"
    android:grantUriPermissions="true">
    <meta-data
        android:name="android.support.FILE_PROVIDER_PATHS"
        android:resource="@xml/gdt_file_path" />
</provider>

<!--百度FileProvider-->
<!-- <provider
    android:name="com.baidu.mobads.openad.FileProvider"
    android:authorities="${applicationId}.bd.provider"
    android:exported="false"
    android:grantUriPermissions="true">
        <meta-data
            android:name="android.support.FILE_PROVIDER_PATHS"
            android:resource="@xml/adcdn_file_paths" />
</provider> -->
<!--头条FileProvider-->
<provider
    android:name="com.bytedance.sdk.openadsdk.TTFileProvider"
    android:authorities="${applicationId}.TTFileProvider"
    android:exported="false"
    android:grantUriPermissions="true">
        <meta-data
            android:name="android.support.FILE_PROVIDER_PATHS"
            android:resource="@xml/adcdn_file_paths" />
</provider>

 <uses-library
            android:name="org.apache.http.legacy"
            android:required="false" />
```
** 注意：各个平台的provider在需要是添加，不需要时移除掉，否则会出现异常
在res/xml目录下，新建一个XML文件ﬁle_paths,在该文件中添加如下代码：
```
<?xml version="1.0" encoding="utf-8"?>
<paths xmlns:android="http://schemas.android.com/apk/res/android">
    <!--百度下载配置-->
    <external-files-path
        name="bdpath"
        path="bddownload/" />
    <external-path
        name="bdpathsd"
        path="bddownload/" />
    <!-- 头条下载配置-->
    <external-files-path
        name="external_files_path"
        path="Download" />
    <!-- 腾讯下载配置-->
    <external-path
        name="gdt_sdk_download_path"
        path="GDTDOWNLOAD" />
</paths>
```
为了适配下载和安装相关功能，在工程中引用包 com.android.support:support-v4:24.2.0 使用24.2.0以及以上版本

## 6. 混淆配置
广告sdk内部混淆，若您项目需要进行混淆则需要在混淆文件中添加以下配置


-dontwarn com.yunxia.adsdk.**

-keep class com.yunxia.adsdk.**{*;}

-keep interface com.yunxia.adsdk.**{*;}

-keep class com.android.**{*;}

-keep class com.yunxia.adsdk.admobhttp.**{	*;	}

-keep class com.jaredrummler.android.processes.**{*;}

-keep class com.jaredrummler.android.processes.models.**{*;}

-dontwarn org.apache.commons.**

-keep class org.apache.**{	*;	}

-ignorewarnings

-dontnote android.net.http.*

-dontnote org.apache.commons.codec.**

-dontnote org.apache.http.**

#webview + js

-keepattributes *JavascriptInterface*

-keepattributes *Annotation*

#广点通sdk

-keep class com.qq.e.**	{public protected *;}

-keep class android.support.v4.**{public *;}

-keep class android.support.v7.**{public *;}

-keep class MTT.ThirdAppInfoNew	{*;}

-keep class com.tencent.**	{*;}

-dontwarn com.androidquery.**

-keep class com.androidquery.** { *;}

-dontwarn tv.danmaku.**

-keep class tv.danmaku.** { *;}

-dontwarn androidx.**

#头条 穿山甲 sdk

-keep class com.bytedance.sdk.openadsdk.**	{	*;	}

-keep class com.androidquery.callback.**	{*;}

-keep class com.bytedance.sdk.openadsdk.service.TTDownloadProvider

-keep class com.androidquery.auth.TwitterHandle.**	{	*;	}

-keep class com.androidquery.**	{*;}

-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,*Annotation*,EnclosingMethod

-keep class com.bytedance.sdk.openadsdk.** {*;}

-keep class com.androidquery.callback.** {*;}

-keep public interface com.bytedance.sdk.openadsdk.downloadnew.** {*;}

-keep class com.ss.sys.ces.* {*;}



#adView

-keepclassmembers class * {public *;}
-keep public class com.kyview.** {*;}
-keep public class com.kuaiyou.** {*;}

## 7. 常见问题
get ad ﬁled 广告未放量，请联系商务

platams is empty 广告渠道未接入，请联系商务



