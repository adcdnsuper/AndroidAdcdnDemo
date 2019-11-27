package com.adcdn.addemo.nativead;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.adcdn.addemo.view.ILoadMoreListener;
import com.adcdn.addemo.view.LoadMoreRecyclerView;
import com.adcdn.addemo.view.LoadMoreView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.yunxia.addemo.R;
import com.yunxia.adsdk.tpadmobsdk.ad.listener.AdcdnNativeAdListener;
import com.yunxia.adsdk.tpadmobsdk.ad.nativead.AdcdnNativeView;
import com.yunxia.adsdk.tpadmobsdk.entity.NativeADDatas;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ciba
 * @date : 2018/6/28
 * @description : 原生自渲染列表广告获取demo
 */

public class NativeListActivity extends Activity {
    private static final int LIST_ITEM_COUNT = 30;
    private static final String TAG = "Adcdn_Log";
    private AdcdnNativeView adcdnNativeView;

    private Button btnLoad;
    private LoadMoreRecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    private List<NativeADDatas> mData;
    private FrameLayout videoView;
    private ViewGroup nativeADContainer;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_list);
        btnLoad = findViewById(R.id.btn_load);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.clear();
                myAdapter.notifyDataSetChanged();
                loadAd();
            }
        });

        initListView();
    }

    private void initListView() {
        mRecyclerView = findViewById(R.id.my_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mData = new ArrayList<>();
        myAdapter = new MyAdapter(this, mData);
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLoadMoreListener(new ILoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadAd();
            }
        });

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadAd();
            }
        }, 500);
    }


    private void loadAd() {
        adcdnNativeView = new AdcdnNativeView(this, "1010033");
        adcdnNativeView.setAdCount(3);
        adcdnNativeView.loadAd(new AdcdnNativeAdListener() {

            @Override
            public void onADLoaded(List<NativeADDatas> nativeADData) {
                Log.e(TAG, nativeADData.size() + "");
                if (mRecyclerView != null) {
                    mRecyclerView.setLoadingFinish();
                }
                if (nativeADData == null || nativeADData.isEmpty()) {
                    Toast.makeText(NativeListActivity.this, "广告下载失败", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < LIST_ITEM_COUNT; i++) {
                    mData.add(null);
                }

                int count = mData.size();
                for (NativeADDatas ad : nativeADData) {
                    int random = (int) (Math.random() * LIST_ITEM_COUNT) + count - LIST_ITEM_COUNT;
                    mData.set(random, ad);
                }


                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onADError(String error) {
                if (mRecyclerView != null) {
                    mRecyclerView.setLoadingFinish();
                }
                Toast.makeText(NativeListActivity.this, "广告下载失败" + error, Toast.LENGTH_SHORT).show();

            }


        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        adcdnNativeView.destroy();
    }

    public static void jumpHere(Context context) {
        context.startActivity(new Intent(context, NativeListActivity.class));
    }


    private static class MyAdapter extends RecyclerView.Adapter {
        private static final int FOOTER_VIEW_COUNT = 1;

        private static final int ITEM_VIEW_TYPE_LOAD_MORE = -1;
        private static final int ITEM_VIEW_TYPE_NORMAL = 0;
        private static final int NATIVE_3IMAGE = 1;
        private static final int NATIVE_1IMAGE_2TEXT = 2;
        private static final int NATIVE_2IMAGE_2TEXT = 3;
        private static final int NATIVE_VIDEO = 4;

        private List<NativeADDatas> mData;
        private Context mContext;
        private RequestManager mRequestManager;

        public MyAdapter(Context context, List<NativeADDatas> data) {
            this.mContext = context;
            this.mData = data;
            mRequestManager = Glide.with(mContext);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder holder = null;
            switch (viewType) {
                case ITEM_VIEW_TYPE_LOAD_MORE:
                    return new LoadMoreViewHolder(new LoadMoreView(mContext));
                case NATIVE_1IMAGE_2TEXT:
                    return new SmallAdViewHolder(LayoutInflater.from(mContext).inflate(R.layout.listitem_ad_small_pic, parent, false));
                case NATIVE_2IMAGE_2TEXT:
                    return new LargeAdViewHolder(LayoutInflater.from(mContext).inflate(R.layout.listitem_ad_large_pic, parent, false));
                case NATIVE_3IMAGE:
                    return new GroupAdViewHolder(LayoutInflater.from(mContext).inflate(R.layout.listitem_ad_group_pic, parent, false));
                case NATIVE_VIDEO:
                    return new VideoAdViewHolder(LayoutInflater.from(mContext).inflate(R.layout.listitem_ad_large_video, parent, false));
                default:
                    return new NormalViewHolder(LayoutInflater.from(mContext).inflate(R.layout.listitem_normal, parent, false));
            }
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            int count = mData.size();
            NativeADDatas nativeADDatas;
            if (holder instanceof SmallAdViewHolder) {
                nativeADDatas = mData.get(position);
                SmallAdViewHolder smallAdViewHolder = (SmallAdViewHolder) holder;
                bindData(smallAdViewHolder, nativeADDatas);
                if (nativeADDatas.getImgList() != null && !nativeADDatas.getImgList().isEmpty()) {
                    String image = nativeADDatas.getImgList().get(0);
                    if (image != null) {
                        mRequestManager.load(image).into(smallAdViewHolder.mSmallImage);
                    }
                }

            } else if (holder instanceof LargeAdViewHolder) {
                nativeADDatas = mData.get(position);
                LargeAdViewHolder largeAdViewHolder = (LargeAdViewHolder) holder;
                bindData(largeAdViewHolder, nativeADDatas);
                if (nativeADDatas.getImgList() != null && !nativeADDatas.getImgList().isEmpty()) {
                    String image = nativeADDatas.getImgList().get(0);
                    if (image != null) {
                        mRequestManager.load(image).into(largeAdViewHolder.mLargeImage);
                    }
                }

            } else if (holder instanceof GroupAdViewHolder) {
                nativeADDatas = mData.get(position);
                GroupAdViewHolder groupAdViewHolder = (GroupAdViewHolder) holder;
                bindData(groupAdViewHolder, nativeADDatas);
                if (nativeADDatas.getImgList() != null && nativeADDatas.getImgList().size() >= 3) {
                    String image1 = nativeADDatas.getImgList().get(0);
                    String image2 = nativeADDatas.getImgList().get(1);
                    String image3 = nativeADDatas.getImgList().get(2);
                    if (image1 != null) {
                        mRequestManager.load(image1).into(groupAdViewHolder.mGroupImage1);
                    }
                    if (image2 != null) {
                        mRequestManager.load(image2).into(groupAdViewHolder.mGroupImage2);
                    }
                    if (image3 != null) {
                        mRequestManager.load(image3).into(groupAdViewHolder.mGroupImage3);
                    }
                }

            } else if (holder instanceof VideoAdViewHolder) {
                nativeADDatas = mData.get(position);
                VideoAdViewHolder videoAdViewHolder = (VideoAdViewHolder) holder;
                bindData(videoAdViewHolder, nativeADDatas);
                if (videoAdViewHolder.videoView != null) {
                    View video = nativeADDatas.getAdView();
                    if (video != null) {
                        if (video.getParent() == null) {
                            videoAdViewHolder.videoView.removeAllViews();
                            videoAdViewHolder.videoView.addView(video);
                        }
                    }
                }

            } else if (holder instanceof NormalViewHolder) {
                NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
                normalViewHolder.idle.setText("Recycler item " + position);
            } else if (holder instanceof LoadMoreViewHolder) {
                LoadMoreViewHolder loadMoreViewHolder = (LoadMoreViewHolder) holder;
            }

            if (holder instanceof LoadMoreViewHolder) {
                holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            } else if (holder instanceof SmallAdViewHolder ||
                    holder instanceof VideoAdViewHolder ||
                    holder instanceof LargeAdViewHolder ||
                    holder instanceof GroupAdViewHolder) {
                holder.itemView.setBackgroundColor(Color.WHITE);
            } else {
                holder.itemView.setBackgroundColor(getColorRandom());
            }
        }

        private int getColorRandom() {
            int a = Double.valueOf(Math.random() * 255).intValue();
            int r = Double.valueOf(Math.random() * 255).intValue();
            int g = Double.valueOf(Math.random() * 255).intValue();
            int b = Double.valueOf(Math.random() * 255).intValue();
            return Color.argb(a, r, g, b);
        }

        private void bindData(final AdViewHolder adViewHolder, NativeADDatas ad) {

            //重要! 这个涉及到广告计费，必须正确调用。convertView必须使用ViewGroup。
            ad.registerViewForInteraction((ViewGroup) adViewHolder.itemView, new NativeADDatas.AdInteractionListener() {
                @Override
                public void onAdClicked(NativeADDatas var2) {

                    Log.e(TAG, var2.getTitle() + "广告点击");
                }

                @Override
                public void onAdShow(NativeADDatas var1) {
                    Log.e(TAG, var1.getTitle() + "广告展示");

                }

            });
            adViewHolder.mTitle.setText(ad.getTitle());
            adViewHolder.mDescription.setText(ad.getDesc());
            adViewHolder.mSource.setText(ad.getSource() == null ? "广告" : "广告" + ad.getSource());
            String icon = ad.getIconUrl();
            if (icon != null) {
                mRequestManager.load(icon).into(adViewHolder.mIcon);
            }

        }


        @Override
        public int getItemCount() {
            int count = mData == null ? 0 : mData.size();
            return count + FOOTER_VIEW_COUNT;
        }

        @Override
        public int getItemViewType(int position) {
            if (mData != null) {
                int count = mData.size();
                if (position >= count) {
                    return ITEM_VIEW_TYPE_LOAD_MORE;
                } else {
                    NativeADDatas ad = mData.get(position);
                    if (ad == null) {
                        return ITEM_VIEW_TYPE_NORMAL;
                    } else if (ad.getAdPatternType() == AdcdnNativeView.NATIVE_1IMAGE_2TEXT) {
                        return NATIVE_1IMAGE_2TEXT;
                    } else if (ad.getAdPatternType() == AdcdnNativeView.NATIVE_2IMAGE_2TEXT) {
                        return NATIVE_2IMAGE_2TEXT;
                    } else if (ad.getAdPatternType() == AdcdnNativeView.NATIVE_3IMAGE) {
                        return NATIVE_3IMAGE;
                    } else if (ad.getAdPatternType() == AdcdnNativeView.NATIVE_VIDEO) {
                        return NATIVE_VIDEO;
                    } else {
                        return ITEM_VIEW_TYPE_NORMAL;
                    }
                }

            }
            return super.getItemViewType(position);
        }

        @Override
        public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);

            RecyclerView.LayoutManager layout = recyclerView.getLayoutManager();
            if (layout != null && layout instanceof GridLayoutManager) {
                final GridLayoutManager manager = (GridLayoutManager) layout;
                manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        int type = getItemViewType(position);
                        if (type == ITEM_VIEW_TYPE_LOAD_MORE || type == NATIVE_VIDEO) {
                            return manager.getSpanCount();
                        }
                        return 1;
                    }
                });
            }
        }

        @Override
        public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
            //noinspection unchecked
            super.onViewAttachedToWindow(holder);
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                int position = holder.getLayoutPosition();
                int type = getItemViewType(position);
                if (type == ITEM_VIEW_TYPE_LOAD_MORE || type == NATIVE_VIDEO) {
                    StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                    p.setFullSpan(true);
                }
            }
        }

        @SuppressWarnings("WeakerAccess")
        private static class VideoAdViewHolder extends AdViewHolder {
            @SuppressWarnings("CanBeFinal")
            FrameLayout videoView;

            @SuppressWarnings("RedundantCast")
            public VideoAdViewHolder(View itemView) {
                super(itemView);

                mTitle = (TextView) itemView.findViewById(R.id.tv_listitem_ad_title);
                mSource = (TextView) itemView.findViewById(R.id.tv_listitem_ad_source);
                mDescription = (TextView) itemView.findViewById(R.id.tv_listitem_ad_desc);
                videoView = (FrameLayout) itemView.findViewById(R.id.iv_listitem_video);
                mIcon = (ImageView) itemView.findViewById(R.id.iv_listitem_icon);

            }
        }

        private static class LargeAdViewHolder extends AdViewHolder {
            ImageView mLargeImage;

            @SuppressWarnings("RedundantCast")
            public LargeAdViewHolder(View itemView) {
                super(itemView);

                mTitle = (TextView) itemView.findViewById(R.id.tv_listitem_ad_title);
                mSource = (TextView) itemView.findViewById(R.id.tv_listitem_ad_source);
                mDescription = (TextView) itemView.findViewById(R.id.tv_listitem_ad_desc);
                mLargeImage = (ImageView) itemView.findViewById(R.id.iv_listitem_image);
                mIcon = (ImageView) itemView.findViewById(R.id.iv_listitem_icon);
            }
        }

        private static class SmallAdViewHolder extends AdViewHolder {
            ImageView mSmallImage;

            @SuppressWarnings("RedundantCast")
            public SmallAdViewHolder(View itemView) {
                super(itemView);

                mTitle = (TextView) itemView.findViewById(R.id.tv_listitem_ad_title);
                mSource = (TextView) itemView.findViewById(R.id.tv_listitem_ad_source);
                mDescription = (TextView) itemView.findViewById(R.id.tv_listitem_ad_desc);
                mSmallImage = (ImageView) itemView.findViewById(R.id.iv_listitem_image);
                mIcon = (ImageView) itemView.findViewById(R.id.iv_listitem_icon);
            }
        }


        @SuppressWarnings("CanBeFinal")
        private static class GroupAdViewHolder extends AdViewHolder {
            ImageView mGroupImage1;
            ImageView mGroupImage2;
            ImageView mGroupImage3;

            @SuppressWarnings("RedundantCast")
            public GroupAdViewHolder(View itemView) {
                super(itemView);

                mTitle = (TextView) itemView.findViewById(R.id.tv_listitem_ad_title);
                mSource = (TextView) itemView.findViewById(R.id.tv_listitem_ad_source);
                mDescription = (TextView) itemView.findViewById(R.id.tv_listitem_ad_desc);
                mGroupImage1 = (ImageView) itemView.findViewById(R.id.iv_listitem_image1);
                mGroupImage2 = (ImageView) itemView.findViewById(R.id.iv_listitem_image2);
                mGroupImage3 = (ImageView) itemView.findViewById(R.id.iv_listitem_image3);
                mIcon = (ImageView) itemView.findViewById(R.id.iv_listitem_icon);
            }
        }

        private static class AdViewHolder extends RecyclerView.ViewHolder {
            ImageView mIcon;
            TextView mTitle;
            TextView mSource;
            TextView mDescription;

            public AdViewHolder(View itemView) {
                super(itemView);
            }
        }

        private static class NormalViewHolder extends RecyclerView.ViewHolder {
            TextView idle;

            @SuppressWarnings("RedundantCast")
            public NormalViewHolder(View itemView) {
                super(itemView);

                idle = (TextView) itemView.findViewById(R.id.text_idle);

            }
        }

        @SuppressWarnings({"CanBeFinal", "WeakerAccess"})
        private static class LoadMoreViewHolder extends RecyclerView.ViewHolder {
            TextView mTextView;
            ProgressBar mProgressBar;

            @SuppressWarnings("RedundantCast")
            public LoadMoreViewHolder(View itemView) {
                super(itemView);

                itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));

                mTextView = (TextView) itemView.findViewById(R.id.tv_load_more_tip);
                mProgressBar = (ProgressBar) itemView.findViewById(R.id.pb_load_more_progress);
            }
        }
    }

}
