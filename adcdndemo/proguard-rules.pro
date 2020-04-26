
-dontwarn com.adcdn.adsdk.**
-keep class com.adcdn.adsdk.**{*;}
-keep interface com.adcdn.adsdk.**{*;}
-keep class com.android.**{*;}
-keep class com.adcdn.adsdk.toutiao.SdkInitImp
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

# webview + js
-keepattributes *JavascriptInterface*
-keepattributes *Annotation*

#sdk
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

