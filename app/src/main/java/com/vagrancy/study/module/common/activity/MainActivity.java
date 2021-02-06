package com.vagrancy.study.module.common.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.vagrancy.study.R;
import com.vagrancy.study.common.base.BaseView;
import com.vagrancy.study.common.base.BasePresenter;
import com.vagrancy.study.common.contract.common.MainContract;
import com.vagrancy.study.model.common.entity.Main;
import com.vagrancy.study.model.knowledge.entity.Knowledge;
import com.vagrancy.study.module.common.adapter.CommonFragmentAdapter;
import com.vagrancy.study.module.knowledge.activity.KnowLedgeExamineActivity;
import com.vagrancy.study.presenter.common.MainPresenter;
import com.vagrancy.study.utils.CommonDaoUtils;
import com.vagrancy.study.utils.DaoUtilsStore;
import com.vagrancy.study.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseView<MainPresenter, MainContract.View<Main>> {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.common_tab)
    CommonTabLayout commonTab;
    private CommonFragmentAdapter mAdapter;
    private String[] mTitle;
    private int[] mUnSelect = {R.drawable.knowledge_un_select_normal,R.drawable.mine_un_select_normal};
    private int[] mSelect = {R.drawable.knowledge_select_normal,R.drawable.mine_select_normal};
    private ArrayList<CustomTabEntity> customTabs = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainContract.View<Main> getContract() {
        return new MainContract.View<Main>() {
        };
    }

    @Override
    public MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void initView(Bundle save) {
        mTitle = getResources().getStringArray(R.array.tab_icon);
        initEntity();
        mAdapter = new CommonFragmentAdapter(getSupportFragmentManager(),mTitle.length);
        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(new CommonPageChangeListener());
        commonTab.setTabData(customTabs);
        commonTab.setCurrentTab(0);
        commonTab.setOnTabSelectListener(new CommonTabSelectListener());
    }

    //初始化icon
    private void initEntity() {
        for (int i = 0; i < mTitle.length; i++) {
            TabCustomTabEntity mTab = new TabCustomTabEntity(mTitle[i],mSelect[i],mUnSelect[i]);
            customTabs.add(mTab);
        }
    }

    private class CommonTabSelectListener implements OnTabSelectListener {

        @Override
        public void onTabSelect(int position) {
            viewPager.setCurrentItem(position);
        }

        @Override
        public void onTabReselect(int position) {

        }
    }

    private class CommonPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int position) {
            commonTab.setCurrentTab(position);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

    @Override
    public void initData() {

    }

    private class TabCustomTabEntity implements CustomTabEntity{
        private String title;
        private int selected;
        private int unSelected;
        public TabCustomTabEntity(String title,int selected,int unSelected){
            this.title = title;
            this.selected = selected;
            this.unSelected = unSelected;
        }
        @Override
        public String getTabTitle() {
            return this.title;
        }

        @Override
        public int getTabSelectedIcon() {
            return this.selected;
        }

        @Override
        public int getTabUnselectedIcon() {
            return this.unSelected;
        }
    }
}