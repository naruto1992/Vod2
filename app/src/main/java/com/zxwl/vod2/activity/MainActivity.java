package com.zxwl.vod2.activity;

import android.support.v4.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.zxwl.vod2.R;
import com.zxwl.vod2.bean.TabEntity;
import com.zxwl.vod2.fragment.Home1Fragment;
import com.zxwl.vod2.fragment.Home2Fragment;
import com.zxwl.vod2.fragment.Home3Fragment;
import com.zxwl.vod2.fragment.Home4Fragment;
import com.zxwl.vod2.utils.AppManager;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;

    private String[] mTitles = {"Video", "Travel", "Ticket", "Mine"};

    private int[] mIconUnselectIds = {R.mipmap.tab_video, R.mipmap.tab_travel, R.mipmap.tab_ticket, R.mipmap.tab_mine};

    private int[] mIconSelectIds = {R.mipmap.tab_video_select, R.mipmap.tab_travel_select, R.mipmap.tab_ticket_select, R.mipmap.tab_mine_select};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        for (int i = 0, count = mTitles.length; i < count; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i])); //添加的对象需要自定义，且实现CustomTabEntity接口
        }

        fragments.add(Home1Fragment.newInstance());
        fragments.add(Home2Fragment.newInstance());
        fragments.add(Home3Fragment.newInstance());
        fragments.add(Home4Fragment.newInstance());

        tabLayout.setTabData(mTabEntities, this, R.id.container, fragments);
        tabLayout.setCurrentTab(0);//默认选中项

        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
               /* showToast("onTabSelect " + position);*/
            }

            @Override
            public void onTabReselect(int position) {
              /*  showToast("onTabReselect " + position);*/
            }
        });
    }

    long time;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - time > 2000) {
            time = System.currentTimeMillis();
            showToast("再次点击退出程序");
            return;
        }
        finish();
        AppManager.getInstance().appExit();
    }
}
