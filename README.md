<p align="center">
<img src="https://github.com/pengshuangta/images/blob/master/adcdn_picture.png">
</p>
<h1 align="center">ADCDN SDK for Android</h1>

## 1.概述
通过本文档，您可以轻松的在几分钟之内完成广告的集成过程。


ADCDN广告SDK支持如下广告功能:

| 广告功能        | 详情 | 
| --------       | -----   |
| 开屏广告        | 开屏广告         |
| 原生广告        | （三小图、左图右文、左文右图、文字浮层、上下图文、上文下浮层）        |
| 横幅广告        | 横幅广告         |
| 插屏广告        | 插屏广告         |
| 视频广告2.0       | 激励视频广告（横屏、竖屏） 非激励视频广告（横屏、竖屏）         |
| 营销场景变现场景       | 消星星、橘子消成语、转盘、抢夺金币等游戏         |
## 2.接入iOS请跳转以下链接
[接入IOS ADCDN SDK](https://github.com/adcdnsuper/iOSAdcdnDemo)
## 3.SDK接入流程
### 3.0 兼容和历史版本
android5.0及以上，最新版本号：V 8.1.2 .

| 版本号        | 更新内容 | 更新时间 |
| --------       | -----   |----- |
| V8.5.5        |  优化SDK，修复bug       |2020-11-03|
| V8.5.2        |  优化SDK，修复bug       |2020-10-21|
| V8.4.3        |  优化SDK，提高变现能力       |2020-09-27|
| V8.2.0        |  优化sdk，提高视频加载速度       |2020-08-28|
| V8.1.2        |  优化sdk，提高营销场景广告变现能力       |2020-08-12|
| V7.2.0        |  优化营销场景页面和内容增加添加到桌面快捷方式的入口，去除原先营销场景view的集成方式，改成跳转的方式       |2020-06-17|
| V7.0.3        | 1、提供根据版本号关闭游戏场景入口的方法；2、优化初始化失败重试方案；3、修复优量汇横幅广告轮播问题        |2020-05-25|
| V7.0.2        | 适配了V4.11.8的优量汇版本横幅广告加载crash问题，原因：横幅广告初始化方法V4.11.8之后废弃了之前的初始化方法         |2020-05-19|
| V7.0.1        | 优化营销场景的加载速度         |2020-05-18|
| V7.0.0        |   新增营销场景变现场景      |2020-05-15|

### 3.1 添加sdk到工程
接入环境：Androidstudio
可以复制Demo中libs文件目录下的依赖包到项目中。
```java
android {
        defaultConfig {
            multiDexEnabled true//方法数量超量需要设置
        }
 //.添加依赖目录
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
      implementation 'com.android.support:support-v4:28.0.0'
      implementation 'com.android.support:recyclerview-v7:28.0.0'
      implementation(name:'adcdnsdk_7.0_20200515',ext:'aar')
      implementation 'pl.droidsonroids.gif:android-gif-drawable:1.2.6'
      implementation(name:'GDTSDK.unionNormal.4.210.1080',ext:'aar')
      implementation(name:'open_ad_sdk',ext:'aar')
}


```
##### 注意事项

*如果项目中集成了重复的其他广告平台sdk尽量移除避免冲突，确保所使用的 support:recyclerview-v7，如果不包含此方法请升级 android 开发套件，确保所使用的 android-support-v4.jar 包中的 android.support.v4.app.NotificationCompat.Builder 类包含 setProgress 方法，如果不包含此方法请升级 android 开发套件

*广告拉取失败，禁止多次重试请求广告，避免请求量消耗过大，导致填充率过低，影响系统对您流量的评价从而影响变现效果，得不到广告收益。

### 3.2 权限申请
使用sdk时可能需要以下权限，为了保证使用广告的正确，请在6.0以及以上的手机中使用sdk前及时申请
```xml
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
<uses-permission android:name="android.permission.CAMERA" /> <!-- 摄像头权限 -->
```
PS:ACCESS_FINE_LOCATION，
   READ_PHONE_STATE，
   WRITE_EXTERNAL_STORAGE，
   这几个权限请确保获取，否则可能无法获取广告 （可参考Demo中的SplashActivity）

### 3.3 适配Android7.0
如果您的应用需要适配7.0以上，请在AndroidManifest中添加以下代码：
```xml
       
 <provider
     android:name="android.support.v4.content.FileProvider"
     android:authorities="${applicationId}.fileprovider"
     android:exported="false"
     android:grantUriPermissions="true">
     <meta-data
         android:name="android.support.FILE_PROVIDER_PATHS"
         android:resource="@xml/adcdn_file_paths" />
 </provider>
 
 <provider
     android:name="com.bytedance.sdk.openadsdk.TTFileProvider"
     android:authorities="${applicationId}.TTFileProvider"
     android:exported="false"
     android:grantUriPermissions="true">
     <meta-data
         android:name="android.support.FILE_PROVIDER_PATHS"
         android:resource="@xml/adcdn_file_paths" />
 </provider>

 <provider
     android:name="com.bytedance.sdk.openadsdk.multipro.TTMultiProvider"
     android:authorities="${applicationId}.TTMultiProvider"
     android:exported="false" />

 <uses-library
     android:name="org.apache.http.legacy"
     android:required="false" />
```
** 注意：各个平台的provider在需要是添加，不需要时移除掉，否则会出现异常
在res/xml目录下，新建一个XML文件ﬁle_paths,在该文件中添加如下代码：
```xml
<?xml version="1.0" encoding="utf-8"?>
<paths xmlns:android="http://schemas.android.com/apk/res/android">

    <!-- 头条下载配置-->
    <external-files-path
        name="external_files_path"
        path="Download" />
    <!-- 腾讯下载配置-->
    <external-cache-path
       name="gdt_sdk_download_path1"
       path="com_qq_e_download" />
    <cache-path
        name="gdt_sdk_download_path2"
        path="com_qq_e_download" />

    <!-- ADCDN下载配置-->
    <external-path
         name="external_storage_root"
         path="." />
</paths>
```
为了适配下载和安装相关功能，在工程中引用包 com.android.support:support-v4:24.2.0 使用24.2.0以及以上版本

## 4. 接入代码
### 4.1 sdk初始化
提示：appid请联系商务获取，并在Application的onCreat()方法中进行SDK初始化

```java
// TODO: 2018/10/22 修改为自己的appId
     AdcdnMobSDK.instance().initSdk(getApplicationContext(), APP_ID);
```
### 4.2 开屏广告示例
提示：
1.开屏广告默认为屏幕高度的100%，可自定义高度比例，但不能低于0.75
2.注意加载开屏广告时，请保证开屏view控件处于可见状态，否则会出现获取不到广告的情况
3.开屏容器的布局高度不要写0dp或者wrap_content，建议用match_parent，否则会出现不显示广告的情况
```java
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
```java
  // 初始化Banner广告
  adcdnBannerView = new AdcdnBannerView(this,"请填写对应的plcId");
  adcdnBannerView.setRefreshTime(30);//刷新广告频率，区间30s·120s，单位s
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

### 4.4 原生模板广告示例
```java
 adcdnNativeExpressView = new AdcdnNativeExpressView(this, "请填写对应的plcId");
 adcdnNativeExpressView.setAdCount(3);//请求广告的数量（1~3），最多一次请求3个广告
 //adcdnNativeExpressView.setADSize(new MyADSize(MyADSize.FULL_WIDTH, MyADSize.AUTO_HEIGHT));//可选，单位dp
 adcdnNativeExpressView.loadAd(new AdcdnNativeExpressAdListener() {

            @Override
            public void onADLoaded(List<NativeExpressADDatas> adList) {
                Log.e(TAG, "广告下载成功 ::::: ");
                adView.removeAllViews();
                adView2.removeAllViews();
                adView3.removeAllViews();
                if (adList.size() > 0) {
                    adView.addView(adList.get(0).getADView());
                    adList.get(0).render();
                }
                if (adList.size() > 1) {
                    adView2.addView(adList.get(1).getADView());
                    adList.get(1).render();
                }
                if (adList.size() > 2) {
                    adView3.addView(adList.get(2).getADView());
                    adList.get(2).render();
                }
            }

            @Override
            public void onRenderSuccess(View view) {
                Log.e(TAG, "广告渲染成功 ::::: ");
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
```


### 4.5 插屏广告示例
```java
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
### 4.6 激励视屏广告示例
```java
 AdVideoSlot adSlot = new AdVideoSlot.Builder()
                .setCodeId("请填写对应的plcId")
                .setSupportDeepLink(true)
                .setImageAcceptedSize(1080, 1920)
                .setRewardName("金币") //奖励的名称
                .setRewardAmount(3)  //奖励的数量
                .setUserID("user123") //必传参数，表来标识应用侧唯一用户；若非服务器回调模式或不需sdk透传//可设置为空字符串
                .setMediaExtra("media_extra") //附加参数，可选
                .setOrientation(AdcdnVideoView.VERTICAL) //必填参数，期望视频的播放方向：AdcdnVideoView.HORIZONTAL 或 AdcdnVideoView.VERTICAL
                .build();
 adcdnVideoView = new AdcdnVideoView(this, adSlot);
 adcdnVideoView.setListener(new AdcdnVideoAdListener() {
  
              @Override
              public void onVideoDownloadSuccess() {
                  Log.e(TAG, "广告下载完成了 ::::: ");
                  adcdnVideoView.showAd();
                  Toast.makeText(VideoActivity.this, "广告下载成功", Toast.LENGTH_SHORT).show();
              }
  
              @Override
              public void onVideoDownloadFailed() {
                  Log.e(TAG, "广告下载失败了 ::::: ");
              }
  
              @Override
              public void playCompletion() {//这个回调后可以给用户奖励
                  Log.e(TAG, "广告播放完成 ::::: ");
              }
  
              @Override
              public void onRewardVerify(boolean b, AdVideoSlot adVideoSlot) {//服务器对服务器的回调时用到
                  Log.e(TAG, " amount:" + adVideoSlot.getRewardAmount() +
                          " name:" + adVideoSlot.getRewardName() + " userId:" + adVideoSlot.getUserID());
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
                        adcdnVideoView.loadAd();
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


### 4.7 全屏视屏广告示例
```java
 //activity,位置id，期望视频方向(横屏AdcdnFullVideoView.HORIZONTAL，竖屏AdcdnFullVideoView.VERTICAL)
adcdnFullVideoView = new AdcdnFullVideoView(this, "请填写对应的plcId",AdcdnFullVideoView.VERTICAL);
 // 设置广告监听（不设置也行）
adcdnFullVideoView.setListener(new AdcdnVideoFullAdListener() {

     @Override
     public void onAdShow() {
         Log.e(TAG, "广告展示曝光回调，但不一定是曝光成功了，比如一些网络问题导致上报失败 ::::: ");
     }

     @Override
     public void onAdVideoBarClick() {
         Log.e(TAG, "广告被点击了 ::::: ");
     }

     @Override
     public void onAdClose() {
         Log.e(TAG, "广告被关闭了，该回调不一定会有 ::::: ");
     }

     @Override
     public void onVideoComplete() {
         Log.e(TAG, "广告播放完成 ::::: ");
     }

     @Override
     public void onSkippedVideo() {
         Log.e(TAG, "广告被跳过了 ::::: ");
     }

     @Override
     public void onFullScreenVideoCached() {
         Log.e(TAG, "广告下载完成了 ::::: ");
     }

     @Override
     public void onError(String s) {
         Log.e(TAG, "广告加载失败了 ::::: " + s);
     }
 });

//下载点击按钮
   btnLoad.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  adcdnFullVideoView.loadAd();
              }
          });
//展示广告点击按钮
  btnShow.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 adcdnFullVideoView.showAd(FullVideoActivity.this);
             }
         });
```

### 4.8 营销场景接入示例
```java
//注意：目前营销场景只支持anrdoid 5.0或以上

     findViewById(R.id.tvScene).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                        Toast.makeText(MainActivity.this, "不支持低版本，仅支持android 5.0或以上版本!", Toast.LENGTH_LONG).show();
                    } else {
                        AdcdnMobSDK.instance().gameBox.startIntent(MainActivity.this,"userId");//如果没有userId可不传
                    }
                }
            });
```

注意事项：
在不使用的时候（退出activity等）记得及时释放广告资源


## 5. 混淆配置
广告sdk内部混淆，若您项目需要进行混淆则需要在混淆文件中添加以下配置

```java
-dontwarn com.adcdn.adsdk.**
-keep class com.adcdn.adsdk.**{*;}
-keep interface com.adcdn.adsdk.**{*;}
-keep class com.android.**{*;}
-keep class com.adcdn.adsdk.admobhttp.**{	*;	}
-keep class com.jaredrummler.android.processes.**{*;}
-keep class com.jaredrummler.android.processes.models.**{*;}
-keep class it.sauronsoftware.base64.**{*;}
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
```




