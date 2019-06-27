
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

# webview + js
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

###头条 穿山甲 sdk
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

#讯飞
-keep class com.iflytek.** {* ;}
-keep class android.support.v4.**{public * ;}

#百度sdk
-keepclassmembers class * extends android.app.Activity	{
public void *(android.view.View);}
-keepclassmembers enum *	{
public static **[]	values();
public static ** valueOf(java.lang.String);
		}
-keep class com.baidu.mobads.*.**{*;}

#360
-keep class com.ak.** {*;}
-keep class android.support.v4.** {
    public *;
}

#小米
-keep class com.xiaomi.ad.** {*;}
-keep class com.miui.zeus.**{*;}

#谷歌
-keep class com.google.android.gms.** {*;}