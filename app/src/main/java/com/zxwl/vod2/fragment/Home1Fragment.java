package com.zxwl.vod2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.zxwl.vod2.R;
import com.zxwl.vod2.adapter.MyPagerAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class Home1Fragment extends BaseFragment {

    SlidingTabLayout videoTabLayout;

    ViewPager videoVp;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"Hot", "Travel", "Movie", "TV play", "Variety", "Manga"};
    private MyPagerAdapter adapter;

    public static Home1Fragment newInstance() {
        Home1Fragment fragment = new Home1Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View inflateContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home1, container, false);
    }

    @Override
    protected void findViews(View view) {
        videoTabLayout = (SlidingTabLayout) view.findViewById(R.id.video_tabs);
        videoVp = (ViewPager) view.findViewById(R.id.video_vp);
    }

    @Override
    protected void init() {
        for (int i = 0; i < mTitles.length; i++) {
            Bundle data = new Bundle();
            data.putString("video_type", mTitles[i]);
            mFragments.add(VideoFragment.getInstance(data));
        }
        adapter = new MyPagerAdapter(getChildFragmentManager(), mFragments, mTitles);
        videoVp.setAdapter(adapter);
        videoTabLayout.setViewPager(videoVp, mTitles, getActivity(), mFragments);
    }

    @Override
    protected void lazyFetchData() {

    }

}
