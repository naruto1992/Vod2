package com.zxwl.vod2.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.GenericsCallback;
import com.zxwl.vod2.R;
import com.zxwl.vod2.adapter.HomeVideoAdapter;
import com.zxwl.vod2.bean.DataList;
import com.zxwl.vod2.bean.VideoBean;
import com.zxwl.vod2.net.JsonGenericsSerializator;
import com.zxwl.vod2.net.Urls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class VideoFragment extends BaseFragment {

    TwinklingRefreshLayout refreshLayout;
    RecyclerView rvList;

    private String type;
    private int pageNum = 1;
    private int pageSize = 10;
    List<VideoBean> list = new ArrayList<>();
    private HomeVideoAdapter mAdapter;

    private static final int LOAD_REFRESH = 0;
    private static final int LOAD_MORE = 1;
    boolean hasMore = true;


    public static VideoFragment getInstance(Bundle args) {
        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString("video_type", "video");
        }
    }

    @Override
    protected View inflateContentView(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.fragment_video, container, false);
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        refreshLayout = (TwinklingRefreshLayout) view.findViewById(R.id.refreshLayout);
        rvList = (RecyclerView) view.findViewById(R.id.rv_list);
    }

    @Override
    protected void init() {
//        initRefresh();
    }

    private void initRefresh() {
        mAdapter = new HomeVideoAdapter(getActivity(), list);
        rvList.setLayoutManager(new GridLayoutManager(rvList.getContext(), 6, GridLayoutManager.VERTICAL, false));
        //设置刷新的头部
        ProgressLayout progressLayout = new ProgressLayout(getContext());
        refreshLayout.setHeaderView(progressLayout);
        //设置像SwipeRefreshLayout一样的悬浮刷新模式了
        refreshLayout.setFloatRefresh(true);
        //设置是否加载更多_默认为true
        refreshLayout.setEnableLoadmore(true);
        //设置是否回弹
        refreshLayout.setEnableOverScroll(false);
        //设置头部高度
        refreshLayout.setHeaderHeight(140);
        //设置头部的最大高度
        refreshLayout.setMaxHeadHeight(240);
        //设置刷新的view
        refreshLayout.setTargetView(rvList);
        //设置监听
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                getDataList(LOAD_REFRESH, 1);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                if (hasMore) {
                    getDataList(LOAD_MORE, pageNum++);
                } else {
                    showToast("客官，没有更多视频了");
                }
            }
        });
    }

    @Override
    protected void lazyFetchData() {
        //开始刷新
//        refreshLayout.startRefresh();
//        getDataList(LOAD_REFRESH, 1);
    }

    private void getDataList(final int action, int pageNum) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("pageSize", String.valueOf(pageSize));
        params.put("pageNum", String.valueOf(pageNum));

        String url = Urls.baseUrl + Urls.VIDEOACTION_QUERYLIST;
        OkHttpUtils
                .post()//
                .url(url)//
                .params(params)
                .build()//
                .execute(new GenericsCallback<DataList>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        refreshLayout.finishRefreshing();
                        showToast("e=" + e.toString());
                    }

                    @Override
                    public void onResponse(DataList response, int id) {
                        if (null != response.dataList) {
                            hasMore = true;
                            List<VideoBean> newList = response.dataList;
                            newList.add(0, null);
                            newList.add(5, null);
                            newList.add(9, null);
                            if (action == LOAD_REFRESH) {
                                list.clear();
                                list.addAll(newList);
                                rvList.setAdapter(mAdapter);
                                refreshLayout.finishRefreshing();
                            } else {
                                list.addAll(newList);
                                mAdapter.notifyDataSetChanged();
                                refreshLayout.finishLoadmore();
                            }
                        } else {
                            hasMore = false;
                            showToast("客官，没有更多视频了");
                        }
                    }
                });
    }
}
