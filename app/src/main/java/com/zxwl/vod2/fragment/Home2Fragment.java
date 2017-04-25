package com.zxwl.vod2.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxwl.vod2.R;

public class Home2Fragment extends BaseFragment {


    public static Home2Fragment newInstance() {
        Home2Fragment fragment = new Home2Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View inflateContentView(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.fragment_home2, container, false);
        return rootView;
    }

    @Override
    protected void findViews(View view) {

    }

    @Override
    protected void init() {

    }


    @Override
    protected void lazyFetchData() {

    }

}
