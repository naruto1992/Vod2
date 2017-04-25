package com.zxwl.vod2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxwl.vod2.R;

public class Home4Fragment extends Fragment {


    public Home4Fragment() {
    }

    public static Home4Fragment newInstance() {
        Home4Fragment fragment = new Home4Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home4, container, false);
    }

}
